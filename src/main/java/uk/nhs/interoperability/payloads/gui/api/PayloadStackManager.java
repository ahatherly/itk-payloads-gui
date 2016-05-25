package uk.nhs.interoperability.payloads.gui.api;

import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.nhs.interoperability.payloads.DomainObjectFactory;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.gui.model.SerialisablePayloadAndFieldName;

public class PayloadStackManager {
	public static Payload pushNewPayloadToStack(HttpServletRequest request,
			final boolean reset, final String name, final String packg,
			final String parentFieldName) {
		Payload doc;
		Stack<SerialisablePayloadAndFieldName> payloadStack;
		
		// If we are resetting (or the session is empty) then we are
		// clearing the stack and making the requested payload the new base of the stack
		if (reset || (!stacksAreInSession(request))) {
			doc = DomainObjectFactory.getDomainObject(name, packg);
			payloadStack = new Stack<SerialisablePayloadAndFieldName>();
			SerialisablePayloadAndFieldName entry =
					new SerialisablePayloadAndFieldName(doc, null);
			payloadStack.push(entry);
			// Save the updated stack back in the session
			saveStacksToSession(request, payloadStack);
		} else {
			// We need to add our new payload to the top of the stack, and also store
			// the field it should be saved back into when complete
			doc = DomainObjectFactory.getDomainObject(name, packg);
			payloadStack = loadPayloadStackFromSession(request);
			//doc = (Payload)session.getAttribute("Parent");
			SerialisablePayloadAndFieldName entry =
					new SerialisablePayloadAndFieldName(doc, parentFieldName);
			payloadStack.push(entry);
			
			// Save the updated stack back in the session
			saveStacksToSession(request, payloadStack);
		}
		
		//System.out.println("New stack: " + payloadStack.toString());
		
		return doc;
	}

	public static Payload saveAndPopPayload(Payload payload, HttpServletRequest request) {
		Stack<SerialisablePayloadAndFieldName> payloadStack;

		if (!stacksAreInSession(request)) {
			return null;
		} else {
			payloadStack = loadPayloadStackFromSession(request);
			
			// Pop our child payload off the stack and keep a note of the field
			// from the parent to save it into
			SerialisablePayloadAndFieldName payloadFromTopOfStack = payloadStack.pop();
			String parentFieldName = payloadFromTopOfStack.getFieldName();
			
			// Also pop the parent so we can modify it
			SerialisablePayloadAndFieldName parentPayloadToUpdate
					= payloadStack.pop();
			
			// Now add the new child payload into the parent in the relevant field
			Payload parentPayload = parentPayloadToUpdate.getPayload();
			parentPayload.setValue(parentFieldName, payload);
			// And push the updated parent back onto the stack
			SerialisablePayloadAndFieldName newParentPayload =
					new SerialisablePayloadAndFieldName(parentPayload,
									parentPayloadToUpdate.getFieldName());
			payloadStack.push(newParentPayload);
			
			// Save the updated stack back in the session
			saveStacksToSession(request, payloadStack);
			
			return parentPayload;
		}
	}

	/**
	 * Saves the payload onto the stack and store it in the session
	 * @param payload
	 * @param request
	 * @return
	 */
	public static void savePayload(Payload payload, HttpServletRequest request) {
		Stack<SerialisablePayloadAndFieldName> payloadStack;

		if (!stacksAreInSession(request)) {
			return;
		} else {
			payloadStack = loadPayloadStackFromSession(request);
			
			// Pop our currently active payload off the stack
			SerialisablePayloadAndFieldName payloadFromTopOfStack = payloadStack.pop();
			String parentFieldName = payloadFromTopOfStack.getFieldName();
						
			// Push the updated payload back onto the stack
			SerialisablePayloadAndFieldName newPayload =
					new SerialisablePayloadAndFieldName(payload, parentFieldName);
			payloadStack.push(newPayload);
			
			// Save the updated stack back in the session
			saveStacksToSession(request, payloadStack);
		}
	}
	
	protected static boolean stacksAreInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (!(session.getAttribute("PayloadStack") == null));
	}
	
	protected static void saveStacksToSession(HttpServletRequest request, 
				Stack<SerialisablePayloadAndFieldName> payloadStack) {
		HttpSession session = request.getSession(true);
		// Save the updated stack back in the session
		session.setAttribute("PayloadStack", payloadStack);
	}
	
	protected static Stack<SerialisablePayloadAndFieldName> 
						loadPayloadStackFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		// Load the stack from the session
		return (Stack<SerialisablePayloadAndFieldName>)session.getAttribute("PayloadStack");
	}
}
