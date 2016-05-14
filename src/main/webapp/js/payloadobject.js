var app = angular.module('PayloadObject',[]);
app.controller('PayloadCtrl', function($scope, $http) { 
	var payloadScope = this;
	
	this.payloadList = [
	    {'label':'Transfer Of Care', 'name': 'ClinicalDocument', 'packg':'uk.nhs.interoperability.payloads.toc_edischarge_draftB'},
	    {'label':'Child Screening', 'name': 'ClinicalDocument', 'packg':'uk.nhs.interoperability.payloads.childscreeningv2'},
	    {'label':'Non-Coded CDA', 'name': 'ClinicalDocument', 'packg':'uk.nhs.interoperability.payloads.noncodedcdav2'},
	    {'label':'End of Life Care', 'name': 'ClinicalDocument', 'packg':'uk.nhs.interoperability.payloads.endoflifecarev1'},
	    {'label':'Author (Person)', 'name': 'AuthorPersonUniversal', 'packg':'uk.nhs.interoperability.payloads.templates'},
	    {'label':'Child (Person)', 'name': 'ChildPatientUniversal', 'packg':'uk.nhs.interoperability.payloads.templates'}
	];
	
	this.cda = 'Click the generate button to generate CDA here!';
	this.packg = 'uk.nhs.interoperability.payloads.childscreeningv2';
	this.name = 'ClinicalDocument';
	
	this.processPayloadFromServer = function(response) {
		payloadScope.fields = response.fields;
		payloadScope.data = response.payload;
		payloadScope.name = response.name;
		payloadScope.packg = response.packg;
		
		// Now, insert any data we have into the relevant fields
		$.each(payloadScope.data, function( keyToSet, valueToSet ) {
			$.each(payloadScope.fields, function( index, field ) {
				if (field.name == keyToSet) {
					field.value = valueToSet;
				}
			});
		});
	};
	
	this.generateCDA = function() {
		
		var submitData = {};
		
		submitData['name'] = this.name;
		submitData['packg'] = this.packg;
		submitData['payload'] = {};
		submitData['payload']['fields'] = {};
		
		$.each(this.fields, function( index, field ) {
			if (field.value) {
				submitData['payload']['fields'][field.name] = field.value;
			}
		});
		
		$http.post('/itk-payloads-gui/generateCDA', submitData).then(
				function(response) {
					payloadScope.cda = response.data;
				},
				function(response) {
					payloadScope.cda = "An error occurred generating CDA output!";
				}
		);
	}
	
	this.setDocumentType = function(name, packg, label) {
		$('#chosenDocumentType').html(label)
		$http.get("/itk-payloads-gui/payload?name="+name+"&packg="+packg+"&reset=true")
			.success(payloadScope.processPayloadFromServer)
			.error(function(response) {
				alert("Server call failed");
			});
	}
	
	// Call the server to get our initial data
	$http.get("/itk-payloads-gui/payload?name="+this.name+"&packg="+this.packg+"&reset=true")
		.success(payloadScope.processPayloadFromServer)
		.error(function(response) {
			alert("Server call failed");
		});
});
