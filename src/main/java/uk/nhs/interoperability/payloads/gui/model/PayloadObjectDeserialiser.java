package uk.nhs.interoperability.payloads.gui.model;

import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class PayloadObjectDeserialiser implements JsonDeserializer<Payload> {
	public Payload deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException {
		
		ClinicalDocument doc = new ClinicalDocument();
		
		System.out.println("Attempting to deserialise a payload...");
		
		JsonObject jsonObject = json.getAsJsonObject();
		
		JsonObject fields = jsonObject.getAsJsonObject("fields");
		
		Set<Entry<String, JsonElement>> fieldSet = fields.entrySet();
		
		for (Entry<String, JsonElement> entry : fieldSet) {
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			
			// If this is a field value, add it to the payload
			if (value.isJsonPrimitive()) {
				doc.setValue(key, value.getAsString());
			}
			
			//TODO: Deal with child payloads and other types here...
		}
		return doc;
	}
}
