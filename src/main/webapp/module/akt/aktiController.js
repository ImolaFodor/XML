app.controller('aktiController', function($scope,$state,$mdDialog,$translate, loginService, aktService){
	$scope.init = function(){

		$scope.ucitajAkte();
	};
	$scope.dodajNoviAkt = function(){
		var akt = {};
		$mdDialog.show({
	          controller: 'aktDialogController',
	          templateUrl: 'module/akt/aktDialog/aktDialog.html',
	          clickOutsideToClose: true,
	          akt: akt
	       }).then(function(){
	    	   $scope.ucitajAkte();
	       });
	}
	
	$scope.ucitajAkte = function(){
		aktService.getAll(function(response){
			$scope.akti = response.data;
			console.log($scope.akti);
		})
	}
	$scope.deleteAkt = function(akt){
		aktService.delete(akt.id, function(response){
			$scope.ucitajAkte();
		}, function(response){
			
		})
	}
	
	$scope.openXTML = function(akt){
		aktService.openXTML(akt.id, function(response){
			$mdDialog.show({
		          templateUrl: 'module/akt/aktXHTML.html',
		          clickOutsideToClose: true,
		          akt: akt
		       }).then(function(){
		    	   ucitajAkte();
		       });
		})
	}
});