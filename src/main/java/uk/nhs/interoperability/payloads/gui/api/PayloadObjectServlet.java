
package uk.nhs.interoperability.payloads.gui.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.DomainObjectFactory;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;
import uk.nhs.interoperability.payloads.gui.model.PayloadAndFieldName;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiseExclusions;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiser;
import uk.nhs.interoperability.payloads.gui.model.Vocabulary;
import uk.nhs.interoperability.payloads.gui.model.VocabularySerialiser;

public class PayloadObjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/json");
		
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		String parentPayloadField = null;
		boolean reset = false;
		
		if (request.getParameter("name") != null) {
			if (request.getParameter("packg") != null) {
				name = request.getParameter("name");
				packg = request.getParameter("packg");
				if (request.getParameter("reset") != null) {
					reset = request.getParameter("reset").equals("true");
				}
				if (request.getParameter("parentPayloadField") != null) {
					parentPayloadField = request.getParameter("parentPayloadField");
				}
			}
		}
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectSerialiser());
		gsonBuilder.registerTypeAdapter(Vocabulary.class, new VocabularySerialiser());
		gsonBuilder.setExclusionStrategies(new PayloadObjectSerialiseExclusions());
		Gson gson = gsonBuilder.create();
		
		// Get the payload we want to send back.
		Payload doc = PayloadStackManager.getPayloadFromStack(request,
											reset, name, packg, parentPayloadField);
		
		PrintWriter out = response.getWriter();
		gson.toJson(doc, Payload.class, out);
	}
	
}
