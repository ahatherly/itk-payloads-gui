package uk.nhs.interoperability.payloads.gui.api;

import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.nhs.interoperability.payloads.DomainObjectFactory;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.gui.model.PayloadAndFieldName;

public class PayloadStackManager {
	public static Payload getPayloadFromStack(HttpServletRequest request,
			final boolean reset, final String name, final String packg,
			final String parentFieldName) {
		Payload doc;
		Stack<PayloadAndFieldName> payloadStack;
		HttpSession session = request.getSession(true);
		
		// If we are resetting (or the session is empty) then we are
		// clearing the stack and making the requested payload the new base of the stack
		if (reset || (session.getAttribute("PayloadStack") == null)) {
			doc = DomainObjectFactory.getDomainObject(name, packg);
			payloadStack = new Stack<PayloadAndFieldName>();
			payloadStack.push(new PayloadAndFieldName(doc, null));
			session.setAttribute("PayloadStack", payloadStack);
		} else {
			// We need to add our new payload to the top of the stack, and also store
			// the field it should be saved back into when complete
			doc = DomainObjectFactory.getDomainObject(name, packg);
			payloadStack = (Stack<PayloadAndFieldName>)session.getAttribute("PayloadStack");
			payloadStack.push(new PayloadAndFieldName(doc, parentFieldName));
		}
		
		System.out.println("New stack: " + payloadStack.toString());
		
		return doc;
	}

}
