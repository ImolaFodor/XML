app.controller('main', function($scope, $state, $mdUtil, $mdSidenav, loginService){

    $scope.init = function(){
    	/*loginService.getProfile(function(response){
			if(response.data.id){
				$scope.loggedUser = response.data;
			}else{
				$state.transitionTo('login');
			}
		},function(response){
			$state.transitionTo('login');
		});*/
    };

    $scope.toggleNavigation = $mdUtil.debounce(function(){
        $mdSidenav('left').toggle()
    }, 200);

    $scope.logout = function(){
        loginService.logout()
        $state.transitionTo('login')
    };

});