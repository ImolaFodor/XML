app.controller('main', function($scope, $state, $mdDialog, $mdUtil,  $mdSidenav, loginService){

    $scope.init = function(){
    	loginService.getProfile(function(response){
			if(response.data.id){
				$scope.loggedUser = response.data;
			}else{
				$state.transitionTo('login');
			}
		},function(response){
			$scope.loggedUser = {};
		});
    	$scope.username = "";
    	$scope.password = "";
    	
    };
    
    $scope.toggleNavigation = $mdUtil.debounce(function(){
        $mdSidenav('left').toggle()
    }, 200);

    $scope.logout = function(){
        loginService.logout()
        $scope.init();
    };
    
    $scope.login = function(ev){
    	loginService.login($scope.username, $scope.password, function(response){
    		$scope.init();
    	}, function(response){
    		$mdDialog.show(
    				$mdDialog.alert()
    		        .parent(angular.element(document.querySelector('#popupContainer')))
    		        .clickOutsideToClose(true)
    		        .title('Greska')
    		        .textContent('Korisnicko ime ili lozinka nije ispravna!')
    		        .ariaLabel('Greska')
    		        .ok('OK')
    		        .targetEvent(ev)
				    );
    	});
    }

});
    	
    	
    	