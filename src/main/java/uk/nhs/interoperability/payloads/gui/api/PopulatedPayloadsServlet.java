
package uk.nhs.interoperability.payloads.gui.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;

public class PopulatedPayloadsServlet extends HttpServlet {
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("**** Populated Payloads Servlet ****: ");
		
		Stack<SerialisablePayloadAndFieldName> payloadStack;
		payloadStack = PayloadStackManager.loadPayloadStackFromSession(request);

		PrintWriter out = response.getWriter();
		out.append("<html><body>Payloads in stack: " + payloadStack.size() + "<br/><ul>");
		
		int n=1;
		for (SerialisablePayloadAndFieldName item : payloadStack) {
			out.append("<li>Item " + n + " : " + item.getPayload().toString() + "</li>");
			out.append("<li>JSON " + n + " : " + item.getPayloadJson() + "</li>");
			out.append("<li>Parent field for item " + n++ + " : " + item.getFieldName() + "</li>");
		}
		out.append("</ul></body></html>"); 
	} 
}
