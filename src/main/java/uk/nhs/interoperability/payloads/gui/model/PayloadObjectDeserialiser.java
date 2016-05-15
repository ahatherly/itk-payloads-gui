package uk.nhs.interoperability.payloads.gui.model;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import uk.nhs.interoperability.payloads.CodedValue;
import uk.nhs.interoperability.payloads.DomainObjectFactory;
import uk.nhs.interoperability.payloads.FieldType;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;
import uk.nhs.interoperability.payloads.vocabularies.VocabularyEntry;

public class PayloadObjectDeserialiser implements JsonDeserializer<Payload> {
	public Payload deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException {
		
		JsonObject jsonObject = json.getAsJsonObject();
		JsonObject payload = jsonObject.getAsJsonObject("payload");
		JsonObject fields = payload.getAsJsonObject("fields");
		
		String name = jsonObject.getAsJsonPrimitive("name").getAsString();
		String packg = jsonObject.getAsJsonPrimitive("packg").getAsString();
		
		Set<Entry<String, JsonElement>> fieldSet;
		if (fields == null) {
			fieldSet = new HashSet<Entry<String, JsonElement>>();
		} else {
			fieldSet = fields.entrySet();
		}
		
		Payload doc = DomainObjectFactory.getDomainObject(name, packg);
		
		for (Entry<String, JsonElement> entry : fieldSet) {
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			
			Field fieldDefinition = doc.getFieldDefinitions().get(key);
			FieldType fieldType = fieldDefinition.getTypeEnum();
			
			switch(fieldType) {
			case String:
			case XML:
				// If this is a field value, add it to the payload
				if (value.isJsonPrimitive()) {
					doc.setValue(key, value.getAsString());
				}
				break;
			case CodedValue:
				String code = value.getAsString();
				if (code != null) {
					Vocabulary vocab = Vocabularies.getVocab(fieldDefinition.getVocabulary());
					VocabularyEntry vocabEntry = vocab.getEntry(code);
					if (vocabEntry != null) {
						CodedValue cv = new CodedValue(vocabEntry, null);
						doc.setValue(key, cv);
					} else {
						System.out.println("VOCAB WAS NULL!!! Code="+code+" Field="+key);
					}
				} else {
					System.out.println("CODE WAS NULL!!! Field="+key);
				}
				break;
			}
			
			
			//TODO: Deal with child payloads and other types here...
		}
		return doc;
	}
}
