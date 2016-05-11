
package uk.nhs.interoperability.payloads.gui.api;

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

import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class PayloadObjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/json");
		
		Gson gson = new GsonBuilder().create();
		
		ClinicalDocument doc;
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("ClinicalDocument") == null) {
			doc = new ClinicalDocument();
		} else {
			doc = (ClinicalDocument)session.getAttribute("ClinicalDocument");
		}
		
		
		Map<String, Field> fields = doc.getFieldDefinitions();
		
		PrintWriter out = response.getWriter();
		gson.toJson(fields.values(), out);
		}
}
