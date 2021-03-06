app.controller('aktDialogController', function($scope, $mdDialog, aktService, akt){
	$scope.init = function(){
		$scope.akt = akt;
		$scope.akt = "";
		$scope.greskaCreate = false;
	}
	
	$scope.cancel = function(){
		$mdDialog.hide();
	}
	$scope.save = function(){
		aktService.create($scope.akt, function(response){
			$scope.cancel();
		}, function(response){
			$scope.greskaCreate = true;
		})
	}
	
	$scope.retDateFromLong = function(long){
		var date = new Date(long);
		return date;
	}
	
	$scope.delete = function(){
	}
});