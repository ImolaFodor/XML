app.controller('viewAktController', function($scope,$state , $sce, $stateParams,aktService){
	$scope.init = function(){
		$scope.ucitavanje = true;
		aktService.openXTML($stateParams.id, function(response){
			console.log(response);
			$scope.htmlToShow = $scope.to_trusted(response.data);
			$scope.showNoFile = false;
			$scope.ucitavanje = false;
		}, function(response){
			$scope.ucitavanje = false;
			$scope.showNoFile = true;
		})
	};
	
	 $scope.to_trusted = function(html_code) {
		    return $sce.trustAsHtml(html_code);
	 }
});