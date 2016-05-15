
package uk.nhs.interoperability.payloads.gui.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectDeserialiser;
import uk.nhs.interoperability.payloads.util.xml.PayloadSerialiser;
import uk.nhs.interoperability.payloads.util.xml.XMLNamespaceContext;

public class SavePayloadServlet extends HttpServlet {
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("**** Save Servlet ****: ");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		Gson gson = gsonBuilder.create();
		
		boolean isPop = true;
		if (request.getParameter("action") != null) {
			System.out.println("Action: " + request.getParameter("action"));
			if (request.getParameter("action").equals("push")) {
				isPop = false;
			}
		}
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		Payload payload = null;
		try {
			BufferedReader reader = request.getReader();
			payload = gson.fromJson(reader, Payload.class);
			/*System.out.println(payload);
		    while ((line = reader.readLine()) != null)
		    	jb.append(line);
		    System.out.println(jb.toString());*/
		} catch (Exception e) { e.printStackTrace(); }
		
		if (isPop) {
			// Save and pop the payload off the stack, returning the new one
			Payload newPayload = PayloadStackManager.saveAndPopPayload(payload, request);
			request.setAttribute("payload", newPayload);
		} else {
			// Save the payload - the payload servlet will push the new one on for us
			PayloadStackManager.savePayload(payload, request);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("payload");
		rd.forward(request, response);
		//PrintWriter out = response.getWriter();
		//out.append(PayloadSerialiser.serialise(payload, rootNodeName, namespaces));
		//out.append("Done"); 
	}
}
