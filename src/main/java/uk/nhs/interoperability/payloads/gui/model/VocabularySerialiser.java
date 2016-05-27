package uk.nhs.interoperability.payloads.gui.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.vocabularies.VocabularyEntry;

public class VocabularySerialiser implements JsonSerializer<uk.nhs.interoperability.payloads.vocabularies.Vocabulary> {
	public JsonElement serialize(uk.nhs.interoperability.payloads.vocabularies.Vocabulary src,
			Type typeOfSrc, JsonSerializationContext context) {
	    
		String name = src.getVocabName();
		String oid = null;
		ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String, String>>();
		
		for (String code : src.getEntries().keySet()) {
			HashMap entry = new HashMap();
			entry.put("code", code);
			String displayName = src.getEntry(code).getDisplayName();
			if (displayName == null) {
				displayName = code;
			}
			entry.put("displayName", displayName);
			if (oid == null) {
				oid = src.getEntry(code).getOID();
			}
			entries.add(entry);
		}
		
		JsonObject json = new JsonObject();
		json.add("vocabName", new JsonPrimitive(name));
		if (oid != null) {
			json.add("oid", new JsonPrimitive(oid));
		}
		json.add("entries", context.serialize(entries));
		return json;
	}
}