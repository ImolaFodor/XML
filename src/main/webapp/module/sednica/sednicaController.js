app.controller('sednicaController', function($scope,$state,$mdDialog,$translate, loginService, aktService){
	$scope.init = function(){
		aktService.getPredlozeni(function(response){
			$scope.predlozeni = response.data;
		})
	};
	
});