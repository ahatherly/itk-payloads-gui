package uk.nhs.interoperability.payloads.gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

import junit.framework.TestCase;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class SerialisablePayloadAndFieldNameTest extends TestCase {

	@Test
	public void testCreateFromPayload() {
		ClinicalDocument payload = new ClinicalDocument();
		payload.setDocumentId("1234");
		payload.setDocumentVersionNumber("2");
		payload.setPreviousDocumentVersionNumber("1");
		
		// Convert payload to a serialisable object
		SerialisablePayloadAndFieldName sp = new SerialisablePayloadAndFieldName(payload, "parentField");
		
		String json = sp.getPayloadJson();
		System.out.println("JSON payload: " + json);
		assertNotNull(json);
		assertTrue(json.length()>0);
		
		// Now, convert it back
		ClinicalDocument convertedBack = (ClinicalDocument)sp.getPayload();
		System.out.println("Converted back to payload: " + convertedBack.toString());
		
		assertEquals(payload.getDocumentId(), convertedBack.getDocumentId());
		assertEquals(payload.getDocumentVersionNumber(), convertedBack.getDocumentVersionNumber());
		assertEquals(payload.getPreviousDocumentVersionNumber(), convertedBack.getPreviousDocumentVersionNumber());
		
		// Check the field name has been retained
		assertEquals(sp.getFieldName(), "parentField");
	}
}
