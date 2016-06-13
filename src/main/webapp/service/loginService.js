app.service('loginService', function($http){
	return{
		login: function(username, password, onSuccess, onError){
			$http.post('login/loginUser/'+ username+"/"+ password).then(onSuccess, onError);
		},
		registerUser: function(user, onSuccess, onError){
			$http.post('login/registerUser/',user).then(onSuccess, onError);
		},
		activateUser: function(username, token, onSuccess, onError){
			$http.post('login/activateUser/'+username+"/"+token).then(onSuccess, onError);
		},
		getProfile: function(onSuccess, onError){
			$http.get('login/me').then(onSuccess, onError);
		},
        logout: function(onSuccess, onError){
            $http.post('/logout').then(onSuccess, onError)
        },
	}
});