app.service('amandmanService', function($http){
	return {
        //all
        getAll: function (onSuccess,onError) {
            var req = {
                method: 'GET',
                url: 'amandman',
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
                url: 'amandman/'+id,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        create: function(entity,onSuccess,onError){
            var req = {
                method: 'POST',
                url: 'amandman/',
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
        			url: '/amandman/brisi/'+id,
        			headers: {
                        'Content-Type': 'application/xml'
                    },
        	}
        	 $http(req).then(onSuccess, onError);
        },
        openXTML: function(id, onSuccess, onError){
        	var req = {
        			method: 'GET',
        			url: 'amandman/openXHTML/'+id,
        			headers: {
                        'Content-Type': 'application/xml'
                    },
        	}
        	$http(req).then(onSuccess, onError);
        	
        }
        
    }
});