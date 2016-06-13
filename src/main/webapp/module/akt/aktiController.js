app.controller('aktiController', function($scope,$state,$mdDialog,$translate, loginService, aktService){
	$scope.init = function(){
		alert("DADAS");
		aktService.getAll(function(response){
			$scope.akti = response.data;
			console.log($scope.akti);
		})
	};
	
});