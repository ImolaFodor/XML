app.controller('viewAktController', function($scope,$state,$stateParams,aktService){
	$scope.init = function(){
		$scope.showNoFile = true;
		aktService.openXTML($stateParams.id, function(response){
			$scope.showNoFile = false;
		}, function(response){
			$scope.showNoFile = true;
		})
	};
	
});