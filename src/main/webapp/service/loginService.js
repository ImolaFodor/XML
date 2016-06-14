app.service('loginService', function($http){
	return {
        post: function (korisnik, onSuccess, onError) {
                var req = {
                        method: 'POST',
                        url: '/korisnik/dodaj/',
                        //data: korisnik

                }
                $http(req).then(onSuccess, onError);
        },
        
        
        login: function (username, password, onSuccess, onError) {
                $http.post('/korisnik/login/'+username+"/"+password+"/").then(onSuccess,onError);
        },
        
        logout: function () {
                localStorage.removeItem('token');
                $rootScope.authenticated = false;

        },

        checkUser: function(){
                $http.get('/korisnik/current').success(function (user) {
                        if(user.role === 'Gradjanin'){
                                //$scope.authenticated = true;
                                //$scope.username = user.username;
                                //nesto
                        }
                });
        },
        getProfile: function(onSuccess, onError){
			$http.get('korisnik/me').then(onSuccess, onError);
		},
        logout: function(onSuccess, onError){
            $http.post('/logout').then(onSuccess, onError)
        }
}
});