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
import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;

public class PayloadStackManagerTest {
	
	protected Stack<SerialisablePayloadAndFieldName> payloadStack;
	HttpServletRequest request;
	HttpSession mocksession;
	
	public void setUp() {
		request = mock(HttpServletRequest.class);
		mocksession = mock(HttpSession.class);
		
		when(request.getSession(true)).thenReturn(mocksession);
		when(request.getSession()).thenReturn(mocksession);
		
		// Store our session value so we can return it again
		Mockito.doAnswer(new Answer<Void>() {
		    public Void answer(InvocationOnMock invocation) {
		      Object[] args = invocation.getArguments();
		      //System.out.println("called with arguments: " + Arrays.toString(args));
		      if (args[0].equals("PayloadStack")) {
		    	  payloadStack = (Stack<SerialisablePayloadAndFieldName>)args[1];
		      }
		      return null;
		    }
		}).when(mocksession).setAttribute(anyString(), anyObject());
		
		Mockito.doAnswer(new Answer<Object>() {
		    public Object answer(InvocationOnMock invocation) {
		      Object[] args = invocation.getArguments();
		      //System.out.println("get attribute called with arguments: " + Arrays.toString(args));
		      if (args[0].equals("PayloadStack")) {
		    	  return payloadStack;
		      }
		      return null;
		    }
		}).when(mocksession).getAttribute(anyString());
	}
	
	@Test
	public void testPushNewPayloadToStack() {
		
		setUp();
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
	
	/*
	@Test
	public void testSaveAndPopPayload() {
		saveAndPopPayload(Payload payload, HttpServletRequest request);
	}
	*/
	
	/*
	@Test
	public void testSavePayload() {
		savePayload(Payload payload, HttpServletRequest request);
	}
	*/
	
	/*
	@Test
	public void testStacksAreInSession() {
		stacksAreInSession(HttpServletRequest request);
	}
	*/
	/*
	@Test
	public void testSaveStacksToSession() {
		saveStacksToSession(HttpServletRequest request, 
				Stack<SerialisablePayloadAndFieldName> payloadStack);
	}
	
	@Test
	public void testLoadPayloadStackFromSession() {
		loadPayloadStackFromSession(HttpServletRequest request);
	}
	*/
}
