package uk.nhs.interoperability.payloads.gui.model;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.vocabularies.VocabularyEntry;

public class Vocabulary extends uk.nhs.interoperability.payloads.vocabularies.Vocabulary {

	public Vocabulary(String name) {
		super(name);
	}
	
	public String toJSON() {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Vocabulary.class,
											new VocabularySerialiser());
		Gson gson = gsonBuilder.create();
		return gson.toJson(this);
	}
}
