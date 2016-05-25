package uk.nhs.interoperability.payloads.gui.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.commontypes.PersonName;
import uk.nhs.interoperability.payloads.templates.AuthorPersonUniversal;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class GSONTest {
	private static String input = "{\"name\":\"ClinicalDocument\",\"packg\":\"uk.nhs.interoperability.payloads.toc_edischarge_draftB\","
			+ "\"payload\":{\"fields\":{\"DocumentId\":\"1234\",\"DocumentVersionNumber\":\"1\",\"Author\":{\"fields\":{\"Name\":{\"fields\":{\"Title\":\"Mr\",\"GivenName\":[\"Adam\"],\"FamilyName\":\"Hatherly\"}}}}}}}";
	
	public static void main(String[] args) {
		//System.out.println("Default serialise:");
		//defaultSerialise();
		//System.out.println("Custom serialise:");
		//serialise();
		parse();
	}
	
	private static void serialise() {
		
		ClinicalDocument doc = new ClinicalDocument();
		doc.setDocumentId("1234");
		doc.setDocumentVersionNumber("1");
		AuthorPersonUniversal author = new AuthorPersonUniversal();
		author.setName(new PersonName("Mr", "Adam", "Hatherly"));
		doc.setAuthor(author);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ClinicalDocument.class, new PayloadObjectSerialiser());
		gsonBuilder.setExclusionStrategies(new PayloadObjectSerialiseExclusions());
		Gson gson = gsonBuilder.create();
		
		
		gson.toJson(doc, System.out);
	}
	
	private static void defaultSerialise() {
		
		ClinicalDocument doc = new ClinicalDocument();
		doc.setDocumentId("1234");
		doc.setDocumentVersionNumber("1");
		AuthorPersonUniversal author = new AuthorPersonUniversal();
		author.setName(new PersonName("Mr", "Adam", "Hatherly"));
		doc.setAuthor(author);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		gson.toJson(doc, System.out);
	}
	
	private static void parse() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(Payload.class, new PayloadInstanceCreator());
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		Gson gson = gsonBuilder.create();
		
		Payload p = gson.fromJson(input, Payload.class);
		System.out.println(p);
	}
}
