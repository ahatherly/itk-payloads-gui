
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

import uk.nhs.interoperability.payloads.commontypes.PersonName;
import uk.nhs.interoperability.payloads.gui.model.PayloadObject;
import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.templates.Author;
import uk.nhs.interoperability.payloads.templates.AuthorPersonUniversal;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class PayloadObjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/json");
		
		Gson gson = new GsonBuilder().create();
		
		PayloadObject payload;
		ClinicalDocument doc;
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("PayloadObject") == null) {
			payload = new PayloadObject(new ClinicalDocument());
		} else {
			payload = (PayloadObject)session.getAttribute("PayloadObject");
		}
		
		doc = ((ClinicalDocument)payload.getPayload());
		
		doc.setDocumentId("1234");
		doc.setDocumentVersionNumber("1");
		AuthorPersonUniversal author = new AuthorPersonUniversal();
		author.setName(new PersonName("Mr", "Adam", "Hatherly"));
		doc.setAuthor(author);
		
		PrintWriter out = response.getWriter();
		gson.toJson(payload, out);
	}
}
