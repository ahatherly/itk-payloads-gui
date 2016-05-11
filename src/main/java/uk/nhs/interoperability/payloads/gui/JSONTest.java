package uk.nhs.interoperability.payloads.gui;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class JSONTest {

	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().create();
		
		ClinicalDocument doc = new ClinicalDocument();
		
		
		
		
		doc.setDocumentId("DocID1234");
		
		Map<String, Field> fields = doc.getFieldDefinitions();
		System.out.println("Fields: "+fields.size());
		
		gson.toJson(fields.values(), System.out);
	}

}
