app.controller('amandmanDialogController', function($scope, $mdDialog, aktService, akt, amandmanService){
	$scope.init = function(){
		$scope.akt = akt;
		$scope.amandman = "";
		$scope.greskaCreate = false;
	}
	
	$scope.cancel = function(){
		$mdDialog.hide();
	}
	$scope.save = function(){
		amandmanService.create($scope.amandman, $scope.akt.id, function(response){
			$scope.cancel();
		}, function(response){
			$scope.greskaCreate = true;
		})
	}
	
	$scope.delete = function(){
	}
});