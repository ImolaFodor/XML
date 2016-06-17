app.controller('amandmaniController', function($scope,$state,$mdDialog,$translate,$window, loginService, amandmanService){
	$scope.init = function(){
		loginService.getProfile(function(response){
			if(response.data.id){
				$scope.loggedUser = response.data;
			}else{
				$scope.loggedUser = {};
			}
		},function(response){
			$scope.loggedUser = {};
		});
		$scope.isDodajNovi  = false;
		amandmanService.getAll(function(response){
			$scope.amandmani = response.data;
		})
	};
	
	$scope.kreirajAmandman = function(){
		amandmanService.create($scope.noviAmandman, function(response){
			$scope.init();
		})
	},
	
	$scope.deleteAmandman = function(amandman){
		amandmanService.delete(amandman.id, function(response){
			$scope.init();
		});
	}
	
	$scope.openXTML = function(amandman){
		$state.transitionTo('main.viewAmandman', {id: amandman.id});
	}
	$scope.openPDF = function(akt){
		amandmanService.openPDF(akt.id, function(response){
			 var file = new Blob([response.data], {type: 'application/pdf'});
		      var fileURL = URL.createObjectURL(file);
		      $window.open(fileURL);
		})
	}
});