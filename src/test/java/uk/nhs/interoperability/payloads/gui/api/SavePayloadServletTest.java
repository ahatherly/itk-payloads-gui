package uk.nhs.interoperability.payloads.gui.api;

import static org.junit.Assert.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Stack;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.commontypes.PersonName;
import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;
import uk.nhs.interoperability.payloads.templates.PersonUniversal;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class SavePayloadServletTest extends AbstractServletTests {
	
	@Test
	public void testSavePayload() {
		
		setUpMocks();

		// Stick some stuff into the session
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.childscreeningv2";
		Payload doc = PayloadStackManager.pushNewPayloadToStack(request, true, name, packg, null);
		String name2 = "ChildPatientUniversal";
		String packg2 = "uk.nhs.interoperability.payloads.templates";
		Payload doc2 = PayloadStackManager.pushNewPayloadToStack(request, false, name2, packg2, "ChildPatient");

		// Inject a request body with our payload to save
		String requestBody = "{\"name\":\"ChildPatientUniversal\",\"packg\":\"uk.nhs.interoperability.payloads.templates\",\"payload\":{\"Gender\":\"2\"}}";
		BufferedReader br = new BufferedReader(new StringReader(requestBody));
		try {
			when(request.getReader()).thenReturn(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Call our save servlet
		SavePayloadServlet servlet = new SavePayloadServlet();
		try {
			servlet.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Stack:");
		for (SerialisablePayloadAndFieldName item : payloadStack) {
			System.out.println("   -> " + item.getPayload().toString());
		}
		
		//assertEquals(1,payloadStack.size());
		//Payload tempPayload = payloadStack.peek().getPayload();
		//assertEquals("DocumentId", tempPayload1.getFieldDefinitions().get("DocumentId").getName());
	}	
}
