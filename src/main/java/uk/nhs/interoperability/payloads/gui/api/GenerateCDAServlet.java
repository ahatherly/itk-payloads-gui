
package uk.nhs.interoperability.payloads.gui.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.gui.model.DummyPayload;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectDeserialiser;
import uk.nhs.interoperability.payloads.util.xml.PayloadSerialiser;
import uk.nhs.interoperability.payloads.util.xml.XMLNamespaceContext;

public class GenerateCDAServlet extends HttpServlet {
	protected static XMLNamespaceContext parentNamespaces = Namespaces.parentNamespaces;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/xml");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		
		Gson gson = gsonBuilder.create();
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		Payload payload = null;
		try {
			BufferedReader reader = request.getReader();
			payload = gson.fromJson(reader, Payload.class);
			System.out.println(payload);
		    /*while ((line = reader.readLine()) != null)
		    	jb.append(line);
		    System.out.println(jb.toString());*/
		} catch (Exception e) { e.printStackTrace(); }
		
		XMLNamespaceContext namespaces;
		String rootNodeName;
		
		namespaces = parentNamespaces;
		
		if (payload.getRootNode() == null) {
			rootNodeName = "ClinicalDocument";
		} else {
			rootNodeName = payload.getRootNode();
			if (rootNodeName.length() == 0) {
				rootNodeName = "ClinicalDocument";
			} else if (rootNodeName.startsWith("x:")) {
				rootNodeName = rootNodeName.substring(2);
			}
		}
		
		PrintWriter out = response.getWriter();
		out.append(PayloadSerialiser.serialise(payload, rootNodeName, namespaces));
		//out.append("Done");
	}
}
