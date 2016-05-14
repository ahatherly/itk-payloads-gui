package uk.nhs.interoperability.payloads.gui.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;

public class PayloadObjectSerialiser implements JsonSerializer<Payload> {
	public JsonElement serialize(Payload src, Type typeOfSrc, JsonSerializationContext context) {
	    
		String name = src.getClassName().toString();
		String packg = src.getPackage().toString();
		
		// Values within payload
		HashMap<String, Object> fields = new HashMap<String, Object>();
		for (String fieldName : src.getFieldDefinitions().keySet()) {
			fields.put(fieldName, src.getValue(fieldName));
		}
		
		JsonObject json = new JsonObject();
		
		// Field definitions
		JsonElement fieldDefinitionsJson = context.serialize(getFieldDefinitions(src));
		// Field values
		JsonElement fieldValues = context.serialize(fields);
		
		json.add("name", new JsonPrimitive(name));
		json.add("packg", new JsonPrimitive(packg));
		json.add("fields", fieldDefinitionsJson);
		json.add("payload", fieldValues);
		
		return json;
	}
	
	private ArrayList<Field> getFieldDefinitions(Payload payload) {
		ArrayList<Field> fields = new ArrayList<Field>();
		
		for (String name : payload.getFieldDefinitions().keySet()) {
			Field f = payload.getFieldDefinitions().get(name);
			
			// Don't include the fixed fields
			switch(f.getTypeEnum()) {
			case Fixed:
				break;
			case CodedValue:
				FieldDecorator fd = new FieldDecorator(f);
				Vocabulary vocabValues = Vocabularies.getVocab(f.getVocabulary());
				fd.setVocabValues(vocabValues);
				fields.add(fd);
				break;
			default:
				fields.add(f);
				break;
			}
		}
		return fields;
	}
}