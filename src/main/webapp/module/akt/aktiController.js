app.controller('aktiController', function($scope,$state,$mdDialog,$translate, loginService, aktService){
	$scope.init = function(){

		aktService.getAll(function(response){
			$scope.akti = response.data;
			console.log($scope.akti);
		})
	};
	$scope.dodajNoviAkt = function(){
		var akt = {};
		$mdDialog.show({
	          controller: 'aktDialogController',
	          templateUrl: 'module/akt/aktDialog/aktDialog.html',
	          clickOutsideToClose: true,
	          akt: akt
	       }).then(function(){
	    	   ucitajAkte();
	       });
	}
	
	$scope.ucitajAkte = function(){
		
	}

});