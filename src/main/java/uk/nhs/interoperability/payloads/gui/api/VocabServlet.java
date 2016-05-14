
package uk.nhs.interoperability.payloads.gui.api;

import java.io.IOException;
import java.io.PrintWriter;

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
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiseExclusions;
import uk.nhs.interoperability.payloads.gui.model.PayloadObjectSerialiser;
import uk.nhs.interoperability.payloads.gui.model.Vocabularies;

public class VocabServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/json");
		
		String vocab = null;
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("vocab") != null) {
			vocab = request.getParameter("vocab");
			out.write(Vocabularies.getVocabJson(vocab));
		} else {
			out.write("No vocab specified");
		}
	}
}
