package uk.nhs.interoperability.payloads.gui.api;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Stack;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;

public abstract class AbstractServletTests {
	
	protected Stack<SerialisablePayloadAndFieldName> payloadStack;
	ByteArrayOutputStream bos;
	String responseContent;
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession mocksession;
	
	protected void setUpMocks() {
		bos = new ByteArrayOutputStream();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		mocksession = mock(HttpSession.class);
		
		when(request.getSession(true)).thenReturn(mocksession);
		when(request.getSession()).thenReturn(mocksession);
		
		class DummyServletOutputStream extends ServletOutputStream {
			@Override
			public boolean isReady() {
				return true;
			}
			@Override
			public void setWriteListener(WriteListener arg0) {
			}
			@Override
			public void write(int b) throws IOException {
				bos.write(b);
			}
		}
		
		try {
			when(response.getOutputStream()).thenReturn(new DummyServletOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		when(request.getRequestDispatcher(anyString())).thenReturn(null);
		
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
}
