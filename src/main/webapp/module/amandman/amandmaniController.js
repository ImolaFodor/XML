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
			alert("ADDASDASD");
			console.log(response);
			$scope.amandmani = response.data;
		})
	};
	
	$scope.kreirajAmandman = function(){
		amandmanService.create($scope.noviAmandman, function(response){
			$scope.init();
		})
	}
});