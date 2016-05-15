
package uk.nhs.interoperability.payloads.gui.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiseExclusions;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiser;
import uk.nhs.interoperability.payloads.gui.model.Vocabulary;
import uk.nhs.interoperability.payloads.gui.model.VocabularySerialiser;

public class PayloadObjectServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("**** Payload Object Servlet ****: ");
		// Set response content type
		response.setContentType("application/json");
		
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String paramName = (String)e.nextElement();
			System.out.println("  Parameter passed to servlet: "+paramName+
					"="+request.getParameter(paramName));
		}
		
		String name = "ClinicalDocument";
		String packg = "uk.nhs.interoperability.payloads.toc_edischarge_draftB";
		String parentPayloadField = null;
		boolean reset = false;
		
		if (request.getParameter("newPayloadName") != null) {
			if (request.getParameter("newPayloadPackg") != null) {
				name = request.getParameter("newPayloadName");
				packg = request.getParameter("newPayloadPackg");
				if (request.getParameter("reset") != null) {
					reset = request.getParameter("reset").equals("true");
				}
				if (request.getParameter("parentPayloadField") != null) {
					parentPayloadField = request.getParameter("parentPayloadField");
				}
			}
		}
		System.out.println("**** Params:  newPayloadName:"+name+
				" newPayloadPackg:"+packg);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectSerialiser());
		gsonBuilder.registerTypeAdapter(Vocabulary.class, new VocabularySerialiser());
		gsonBuilder.setExclusionStrategies(new PayloadObjectSerialiseExclusions());
		Gson gson = gsonBuilder.create();
		
		Payload doc;
		
		// See if we have a payload in a request attribute, and if so use that
		if (request.getAttribute("payload") != null) {
			System.out.println("Using parent payload passed on from save servlet");
			doc = (Payload)request.getAttribute("payload");
		} else {
			// Get the payload we want to send back.
			doc = PayloadStackManager.pushNewPayloadToStack(request,
												reset, name, packg, parentPayloadField);
		}
		
		System.out.println("Payload to return to browser: " + doc.toString());
		PrintWriter out = response.getWriter();
		gson.toJson(doc, Payload.class, out);
	} 
	
}
