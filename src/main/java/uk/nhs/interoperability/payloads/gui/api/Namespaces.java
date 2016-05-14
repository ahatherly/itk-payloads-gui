/*
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package uk.nhs.interoperability.payloads.gui.api;

import uk.nhs.interoperability.payloads.util.xml.XMLNamespaceContext;

public class Namespaces {
	protected static XMLNamespaceContext parentNamespaces = new XMLNamespaceContext();
	protected static final String namespacesToAdd = " xmlns=\"urn:hl7-org:v3\"" +
						" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
						" xmlns:npfitlc=\"NPFIT:HL7:Localisation\"";
	
	
	static {
		parentNamespaces.addNamespace("x", "urn:hl7-org:v3", true);
		parentNamespaces.addNamespace("", "urn:hl7-org:v3", false);
		parentNamespaces.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance", false);
		parentNamespaces.addNamespace("npfitlc", "NPFIT:HL7:Localisation", false);		
	}

	public static XMLNamespaceContext getParentNamespaces() {
		return parentNamespaces;
	}

	public static String getNamespacestoadd() {
		return namespacesToAdd;
	}

}