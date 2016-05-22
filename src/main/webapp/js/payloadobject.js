var app = angular.module('PayloadObject',[]);
app.controller('PayloadCtrl', function($scope, $http) { 
	var payloadScope = this;
	this.payloadStack = [];
	
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
		if (response.data) {
			response = response.data;
		}
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
		
		$.each(this.fields, function( index, field ) {
			if (field.value) {
				submitData['payload'][field.name] = field.value;
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
	
	this.save = function(action, newPayloadName,
							newPayloadPackg, parentFieldName) {
		var submitData = {};
		
		submitData['name'] = this.name;
		submitData['packg'] = this.packg;
		submitData['payload'] = {};
		
		$.each(this.fields, function( index, field ) {
			if (field.value) {
				submitData['payload'][field.name] = field.value;
			}
		});
		
		if (action == 'pop') {
			$http.post('/itk-payloads-gui/save', submitData).then(
					payloadScope.popChild,
					function(response) {
						payloadScope.cda = "An error occurred saving the data!";
						console.log(response);
					}
			);
		} else {
			$http.post('/itk-payloads-gui/save?action=push&newPayloadName='+newPayloadName+
						'&newPayloadPackg='+newPayloadPackg+'&parentFieldName='+parentFieldName,
						submitData).then(
					payloadScope.pushChild,
					function(response) {
						payloadScope.cda = "An error occurred saving the data!";
						console.log(response);
					}
			);
		}
	}
	
	this.setDocumentType = function(name, packg, label) {
		$('#chosenDocumentType').html(label)
		$http.get("/itk-payloads-gui/payload?newPayloadName="+name+"&newPayloadPackg="+packg+"&reset=true")
			.success(payloadScope.resetStack)
			.error(function(response) {
				alert("Server call failed");
			});
	}
	
	this.resetStack = function(response) {
		payloadScope.payloadStack.length=0;
		payloadScope.processPayloadFromServer(response);
	}
	
	this.pushChild = function(response) {
		if (response.data)
			payloadScope.payloadStack.push(response.data.name);
		else
			payloadScope.payloadStack.push(response.name);
		console.log('New Stack:');
		console.log(payloadScope.payloadStack);
		payloadScope.processPayloadFromServer(response);
	}
	
	this.popChild = function(response) {
		payloadScope.payloadStack.pop();
		console.log('New Stack:');
		console.log(payloadScope.payloadStack);
		payloadScope.processPayloadFromServer(response.data);
	}
	/*
	this.openChildPayload = function(name, packg, parentFieldName) {
		$http.get("/itk-payloads-gui/save?action=push&name="+name+
					"&packg="+packg+"&parentPayloadField="+parentFieldName)
			.success(payloadScope.pushChild)
			.error(function(response) {
				alert("Server call failed");
			});
	}
	*/
	
	// Call the server to get our initial data
	$http.get("/itk-payloads-gui/payload?newPayloadName="+this.name+
			"&newPayloadPackg="+this.packg+"&reset=true")
		.success(payloadScope.pushChild)
		.error(function(response) {
			alert("Server call failed");
		});
});
