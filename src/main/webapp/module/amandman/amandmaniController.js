app.controller('amandmaniController', function($scope,$state,$mdDialog,$translate, loginService, amandmanService){
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
		amandmanService.openXTML(amandman.id, function(response){
			$mdDialog.show({
		          templateUrl: 'module/amandman/amandmanXHTML.html',
		          controller: 'XHTMLDialogController',
		          htmlToShow: response.data,
		          clickOutsideToClose: true,
		       });
		})
	}
});