var app = angular.module('PayloadObject',[]);
app.controller('PayloadCtrl', function($scope, $http) { 
	$scope.cda = 'Click the generate button to generate CDA here!';
	$http.get("/itk-payloads-gui/payload").then(
		function(response) {
			$scope.fields = response.data.fields;
			$scope.data = response.data.payload.fields;
			$scope.versionedName = response.data.versionedName;
			$scope.name = response.data.name;
			$scope.packg = response.data.packg;
			
			// Now, insert any data we have into the relevant fields
			$.each($scope.data, function( keyToSet, valueToSet ) {
				$.each($scope.fields, function( index, field ) {
					if (field.name == keyToSet) {
						field.value = valueToSet;
					}
				});
			});
		},
		function(response) {
			alert("Server call failed");
		}
	);
	
	$scope.generateCDA = function() {
		
		var submitData = {};
		/*private ArrayList<Field> fields = null;
		private Payload payload = null;
		private String name = null;
		private String packg = null;
		private String versionedName = null;*/
		
		
		submitData['name'] = $scope.name;
		submitData['packg'] = $scope.packg;
		submitData['versionedName'] = $scope.versionedName;
		submitData['payload'] = {};
		submitData['payload']['fields'] = {};
		
		$.each($scope.fields, function( index, field ) {
			if (field.value) {
				submitData['payload']['fields'][field.name] = field.value;
			}
		});
		
		console.log(submitData);
		
		$http.post('/itk-payloads-gui/generateCDA', submitData).then(
				function(response) {
					$scope.cda = response.data;
				},
				function(response) {
					$scope.cda = "An error occurred generating CDA output!";
				}
		);
	}
	
});