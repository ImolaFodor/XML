app.service('aktService', function($http){
	return {
        //all
        getAll: function (onSuccess,onError) {
            var req = {
                method: 'GET',
                url: 'akt',
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        //byId
        get: function(id,onSuccess,onError){
            var req = {
                method: 'GET',
                url: 'akt/'+id,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        create: function(entity,onSuccess,onError){
            var req = {
                method: 'POST',
                url: 'akt',
                headers: {
                    'Content-Type': 'application/xml'
                },
                data: entity
            }
            $http(req).then(onSuccess, onError);
        },
        delete : function(id, onSuccess, onError){
        	var req = {
        			method: 'DELETE',
        			url: '/akt/brisi/'+id,
        			headers: {
                        'Content-Type': 'application/xml'
                    },
        	}
        	 $http(req).then(onSuccess, onError);
        },
        openXTML: function(id, onSuccess, onError){
        	var req = {
        			method: 'GET',
        			url: '/akt/openXHTML/'+id,
        			headers: {
                        'Content-Type': 'application/xml'
                    },
        	}
        	$http(req).then(onSuccess, onError);
        	
        }
        
    }
});