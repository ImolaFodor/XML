app.controller('viewAmandmanController', function($scope,$state,$stateParams,amandmanService){
	$scope.init = function(){
		$scope.ucitavanje = true;
		amandmanService.openXTML($stateParams.id, function(response){
			console.log(response);
			$scope.htmlToShow = response.data;
			$scope.showNoFile = false;
			$scope.ucitavanje = false;
		}, function(response){
			$scope.ucitavanje = false;
			$scope.showNoFile = true;
		})
	};
	
});