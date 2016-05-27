package uk.nhs.interoperability.payloads.gui.api;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Stack;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.commontypes.PersonName;
import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;
import uk.nhs.interoperability.payloads.templates.PersonUniversal;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class PayloadStackManagerTest extends AbstractServletTests {
	
	@Test
	public void testPushNewPayloadToStack() {
		
		setUpMocks();
		// Inject a request body
		//PrintWriter writer = new PrintWriter("somefile.txt");
        //when(response.getWriter()).thenReturn(writer);
		
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		
		// Call our method
		Payload doc = PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);
		
		assertTrue(PayloadStackManager.stacksAreInSession(request));
		assertEquals(name, doc.getClassName());
		assertEquals(packg, doc.getPackage());
		assertEquals(1,payloadStack.size());
		Payload tempPayload = payloadStack.peek().getPayload();
		assertEquals("DocumentId", tempPayload.getFieldDefinitions().get("DocumentId").getName());
		
		// Now push another one into the stack
		String name2 = "ChildPatientUniversal";
		String packg2 = "uk.nhs.interoperability.payloads.templates";
		
		Payload doc2 = PayloadStackManager.pushNewPayloadToStack(request, false, name2, packg2, "parentField");
				
		/*System.out.println("Stack:");
		for (SerialisablePayloadAndFieldName item : payloadStack) {
			System.out.println("   -> " + item.getPayload().getClassName());
		}*/
		
		assertTrue(PayloadStackManager.stacksAreInSession(request));
		assertEquals(2,payloadStack.size());
		Payload tempPayload2 = payloadStack.pop().getPayload();
		assertEquals("DateOfBirth", tempPayload2.getFieldDefinitions().get("DateOfBirth").getName());
		Payload tempPayload1 = payloadStack.pop().getPayload();
		assertEquals("DocumentId", tempPayload1.getFieldDefinitions().get("DocumentId").getName());
	}
	
	@Test
	public void testSaveAndPopPayload() {
		
		// First we need to push a couple of payloads onto the stack
		setUpMocks();
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		Payload doc = PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);
		String name2 = "PersonUniversal";
		String packg2 = "uk.nhs.interoperability.payloads.templates";
		Payload doc2 = PayloadStackManager.pushNewPayloadToStack(request, false, name2, packg2, "Authenticator");
		
		assertEquals(2, payloadStack.size());
		
		// Add a field to save
		PersonUniversal newDoc2 = new PersonUniversal();
		newDoc2.setOrgName("ChildPayloadOrg");
		
		// Now call our method to save the updated child payload
		PayloadStackManager.saveAndPopPayload(newDoc2, request);
		assertEquals(1, payloadStack.size());
		
		Payload savedPayload = payloadStack.peek().getPayload();
		assertEquals("ChildPayloadOrg", ((ClinicalDocument)savedPayload).getAuthenticator().getOrgName());
	}
	
	@Test
	public void testSaveAndPopPayloadTwoLayers() {
		
		// First we need to push three payloads onto the stack
		setUpMocks();
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		Payload doc = PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);
		String name2 = "PersonUniversal";
		String packg2 = "uk.nhs.interoperability.payloads.templates";
		Payload doc2 = PayloadStackManager.pushNewPayloadToStack(request, false, name2, packg2, "Authenticator");
		String name3 = "PersonName";
		String packg3 = "uk.nhs.interoperability.payloads.commontypes";
		Payload doc3 = PayloadStackManager.pushNewPayloadToStack(request, false, name3, packg3, "PersonName");
		
		assertEquals(3, payloadStack.size());
		
		// Add a field to save
		PersonName personName = new PersonName("Mr", "Adam", "Hatherly");
		
		// Now call our method to save the updated child payload
		Payload poppedPayload = PayloadStackManager.saveAndPopPayload(personName, request);
		assertEquals(2, payloadStack.size());
		
		// And again to pop back up to the top level payload
		PayloadStackManager.saveAndPopPayload(poppedPayload, request);
		assertEquals(1, payloadStack.size());
		
		Payload savedPayload = payloadStack.peek().getPayload();
		assertEquals("Hatherly", ((ClinicalDocument)savedPayload).getAuthenticator().getPersonName().getFamilyName());
		
		//System.out.println("Final payload: " + savedPayload.toString());
	}
	
	@Test
	public void testSavePayload() {
		setUpMocks();

		// We have to have a payload in the stack before we can save it
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		// Call our method
		PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);

		ClinicalDocument doc = new ClinicalDocument();
		doc.setDocumentId("1234");
		PayloadStackManager.savePayload(doc, request, "parentField");
		Payload savedPayload = payloadStack.peek().getPayload();
		assertEquals(doc.toString(), savedPayload.toString());
	}
	
	@Test
	public void testSavePayloadWithChildPayload() {
		setUpMocks();

		// We have to have a payload in the stack before we can save it
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		// Call our method
		PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);

		ClinicalDocument doc = new ClinicalDocument();
		doc.setDocumentId("1234");
		PersonUniversal authenticator = new PersonUniversal();
		authenticator.setOrgName("OrgName");
		doc.setAuthenticator(authenticator);
		PayloadStackManager.savePayload(doc, request, "Authenticator");
		Payload savedPayload = payloadStack.peek().getPayload();
		//assertEquals(doc.toString(), savedPayload.toString());
		assertEquals(doc.toString(), savedPayload.toString());
	}	
}
