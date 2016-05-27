package uk.nhs.interoperability.payloads.gui.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import uk.nhs.interoperability.payloads.vocabularies.VocabularyEntry;

public class Vocabularies {
	
	// Cache in-memory to avoid constantly having to read terms using reflection
	private static HashMap<String, Vocabulary> vocabularies = new HashMap<String, Vocabulary>();
	
	public static String getVocabJson(String vocabName) {
		return getVocab(vocabName).toJSON();
	}
	
	public static Vocabulary getVocab(String vocabName) {
		if (!vocabularies.containsKey(vocabName)) {
			Vocabulary v = getVocabEnumByReflection(vocabName);
			vocabularies.put(vocabName, v);
		}
		return vocabularies.get(vocabName);
	}
	
	private static Vocabulary getVocabEnumByReflection(String enumName) {
		HashMap<String,VocabularyEntry> entries = new HashMap<String,VocabularyEntry>();
		VocabularyEntry entry;
		try {
			Class<?> c = Class.forName("uk.nhs.interoperability.payloads.vocabularies.generated." + enumName);
			List<?> keys = Arrays.asList(c.getEnumConstants());
			for (Object key: keys) {
				entry = (VocabularyEntry)key;
				entries.put(entry.getCode(),entry);
			}
		} catch (ClassNotFoundException e) {
			// Perhaps this is an internal vocab.. try that
			try {
				Class<?> c = Class.forName("uk.nhs.interoperability.payloads.vocabularies.internal." + enumName);
				List<?> keys = Arrays.asList(c.getEnumConstants());
				for (Object key: keys) {
					entry = (VocabularyEntry)key;
					entries.put(entry.getCode(),entry);
				}
			}  catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
		}
		
		Vocabulary newVocab = new Vocabulary(enumName);
		for (String key : entries.keySet()) {
			newVocab.add(key, entries.get(key));
		}
		return newVocab;
	}
	
	public static void main(String[] args) {
		System.out.println(getVocabJson("x_BasicConfidentialityKind"));
	}
}
