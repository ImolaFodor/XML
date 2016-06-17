app.controller('XHTMLDialogController', function($scope,$state,$stateParams,$window, $sce, aktService, htmlToShow){
	$scope.init = function(){
		$scope.htmlToShow = $scope.to_trusted(htmlToShow);
	};
	
	$scope.to_trusted = function(html_code) {
	    return $sce.trustAsHtml(html_code);
 }
	
	$scope.clickOnDiv = function(event){
		if ($window.event) {
		    $window.event.stopPropagation();
		}
	}
});