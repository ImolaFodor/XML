app.controller('XHTMLDialogController', function($scope,$state,$stateParams,aktService, htmlToShow){
	$scope.init = function(){
		$scope.htmlToShow = htmlToShow;
	};
	
});