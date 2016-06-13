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

import uk.nhs.interoperability.payloads.FieldType;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;

public class PayloadObjectSerialiser implements JsonSerializer<Payload> {
	public JsonElement serialize(Payload src, Type typeOfSrc, JsonSerializationContext context) {
	    System.out.println("CALLING PAYLOAD SERIALISER!");
		String name = src.getClassName().toString();
		String packg = src.getPackage().toString();		
		
		JsonObject json = new JsonObject();
		
		// Field definitions
		JsonElement fieldDefinitionsJson = context.serialize(getFieldDefinitions(src));
		
		json.add("name", new JsonPrimitive(name));
		json.add("packg", new JsonPrimitive(packg));
		//json.add("fields", fieldDefinitionsJson);
		
		// Now add the actual payload field values
		// Values within payload
		
		JsonObject payloadJsonObject = new JsonObject();
		
		HashMap<String, Object> fields = new HashMap<String, Object>();
		for (String fieldName : src.getFieldDefinitions().keySet()) {
			if (src.getValue(fieldName) != null) {
				Field fieldDefinition = src.getFieldDefinitions().get(fieldName);
				FieldType fieldType = fieldDefinition.getTypeEnum();
				switch(fieldType) {
				case String:
				case XML:
				case CodedValue:
				case HL7Date:
				case CompositionalStatement:
					System.out.println("Adding simple value for field: " + fieldName);
					payloadJsonObject.add(fieldName, context.serialize(src.getValue(fieldName)));
					break;
				case Templated:
				case Other:
					System.out.println("Casting child payload for field: " + fieldName);
					if (fieldDefinition.getMaxOccurs() > 1) {
						//ArrayList<Payload> payloadObjects = new ArrayList<Payload>();
						//payloadObjects = (ArrayList<Payload>)src.getValue(fieldName);
						
						//payloadJsonObject.add(fieldName, context.serialize(src.getValue(fieldName)));
						//fields.put(fieldName, payloadObjects); 
					} else {
						JsonElement val = context.serialize((Payload)src.getValue(fieldName));
						payloadJsonObject.add(fieldName, val);
						//fields.put(fieldName, (Payload)src.getValue(fieldName));
					}
				}
			}
		}
		
		json.add("payload", payloadJsonObject);
		
		//JsonElement fieldValues = context.serialize(fields);
		//json.add("payload", fieldValues);
		
		return json;
	}
	
	private ArrayList<Field> getFieldDefinitions(Payload payload) {
		ArrayList<Field> fields = new ArrayList<Field>();
		
		for (String name : payload.getFieldDefinitions().keySet()) {
			Field f = payload.getFieldDefinitions().get(name);
			Vocabulary vocabValues;
			FieldDecorator fd;
			// Don't include the fixed fields
			switch(f.getTypeEnum()) {
			case Fixed:
				break;
			default:
				if (f.getVocabulary() != null) {
					// Normally this is coded values, but some strings also have vocabs...
					fd = new FieldDecorator(f);
					vocabValues = Vocabularies.getVocab(f.getVocabulary());
					fd.setVocabValues(vocabValues);
					fields.add(fd);
				} else {
					fields.add(f);
				}
				break;
			}
		}
		return fields;
	}
}