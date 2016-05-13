
package uk.nhs.interoperability.payloads.gui.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.gui.model.PayloadInstanceCreator;
import uk.nhs.interoperability.payloads.gui.model.PayloadObject;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectDeserialiser;
import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class GenerateCDAServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/xml");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadInstanceCreator());
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		
		Gson gson = gsonBuilder.create();
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		PayloadObject payload = null;
		try {
			BufferedReader reader = request.getReader();
			payload = gson.fromJson(reader, PayloadObject.class);
			//System.out.println(p.getPayload());
		    /*while ((line = reader.readLine()) != null)
		    	jb.append(line);
		    System.out.println(jb.toString());*/
		} catch (Exception e) { e.printStackTrace(); }

		PrintWriter out = response.getWriter();
		out.append(payload.getPayload().serialise());
	}
}
