app.controller('login', function($scope, $state, $mdDialog, $translate, loginService, GENDERS){
	$scope.genders = GENDERS;
	$scope.username = "";
	$scope.password = "";
	$scope.nePostojiKorisnik = false;
	$scope.neispravnaLoznika = false;
	$scope.user  = {};
	$scope.activation = {};
	$scope.repeat_password ="";
	$scope.showRegisterForm = false;
	$scope.showLoginForm = true;
	$scope.passNotEqual = true;
	$scope.activationForm = false;
	$scope.userExists = false;
	$scope.user.gender = 'MALE';
    $scope.loginUser = function(ev){
    	/*$state.transitionTo('main.akti');
    	return;*/
    	$scope.user.username = $scope.username;
    	$scope.user.password = $scope.password
        loginService.login ($scope.user, function(response){
        	$state.transitionTo("main.akti");
        	/*loginService.getProfile(function(response){
        		
        		$scope.user = response.data;
        		$state.transitionTo('main.akti');
        	});*/
        }, function(response){
        	console.log(response);
        	if(response.data == "BAD_PASSWORD"){
        		$scope.neispravnaLoznika = true;
        	}else if(response.data == "NO_CONTENT"){
        		console.log("NO USERR")
        		$scope.neispravnaLoznika = true;
        		$scope.nePostojiKorisnik = true;
        	}
        	
        });
   };
   $scope.registerForm = function(){
	   $scope.showRegisterForm = true;
	   $scope.showLoginForm = false;
   };
   $scope.cancelRegister = function (){
	   $scope.showRegisterForm = false;
	   $scope.showLoginForm = true;
	   $scope.user = {};
	   $scope.repeat_password = "";
   };
   
   $scope.registerUser = function(ev){
	   if($scope.user.password != $scope.repeat_password){
		   $scope.repeat_password = "";
		   return;
	   }
	   
	   loginService.post($scope.user, function(){
		   $mdDialog.show(
				      $mdDialog.alert()
				        .parent(angular.element(document.querySelector('#popupContainer')))
				        .clickOutsideToClose(true)
				        .title('Success')
				        .textContent($translate.instant('YOU HAVE SUCCESSFULLY REGISTERD'))
				        .ariaLabel($translate.instant('Success'))
				        .ok($translate.instant('OK'))
				        .targetEvent(ev)
				    ).then(function(){
				    	 $scope.cancelRegister();
				    });
		  
	   },function(response){
		   $scope.userExists = true;
	   });
   };
   
});