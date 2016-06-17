app.controller('viewAktController', function($scope,$state,$stateParams,aktService){
	$scope.init = function(){
		$scope.ucitavanje = true;
		aktService.openXTML($stateParams.id, function(response){
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