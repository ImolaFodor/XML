app.controller('aktiController', function($scope,$state,$mdDialog,$translate, loginService, aktService){
	$scope.init = function(){
		/*aktiService.delete(3, function(response){
			
		});
aktiService.delete(2, function(response){
			
		});*/
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
			$state.transitionTo('main.viewAkt', {id: akt.id});
			/*$mdDialog.show({
		          templateUrl: 'module/akt/aktXHTML.html',
		          clickOutsideToClose: true,
		          akt: akt
		       }).then(function(){
		    	   ucitajAkte();
		       });*/
		})
	}
	$scope.openPDF = function(akt){
		aktService.openPDF(akt.id, function(response){
			alert("PDF");
		})
	}
	$scope.createAmandman = function(akt){
		$mdDialog.show({
	          controller: 'amandmanDialogController',
	          templateUrl: 'module/amandman/amandmanDialog/amandmanDialog.html',
	          clickOutsideToClose: true,
	          akt: akt
	       });
	}
	
	
});