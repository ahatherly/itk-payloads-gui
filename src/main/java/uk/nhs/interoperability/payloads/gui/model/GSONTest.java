package uk.nhs.interoperability.payloads.gui.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;

public class GSONTest {
	private static String input = "{\"name\":\"ClinicalDocument\",\"packg\":\"uk.nhs.interoperability.payloads.toc_edischarge_draftB\",\"versionedName\":\"#\",\"payload\":{\"fields\":{\"DocumentId\":\"1234\",\"DocumentTitle\":\"Hello!\",\"DocumentVersionNumber\":\"1\",\"Author\":{\"fields\":{\"Name\":{\"fields\":{\"Title\":\"Mr\",\"GivenName\":[\"Adam\"],\"FamilyName\":\"Hatherly\"},\"parentObjectXPath\":\"\",\"parentObjectNames\":[]}},\"parentObjectXPath\":\"\",\"parentObjectNames\":[]}}}}";
	
	public static void main(String[] args) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(Payload.class, new PayloadInstanceCreator());
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		Gson gson = gsonBuilder.create();
		
		PayloadObject p = gson.fromJson(input, PayloadObject.class);
		System.out.println(p.getPayload());
	}
}
