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
        getPredlozeni: function (onSuccess,onError) {
            var req = {
                method: 'GET',
                url: 'akt/getPredlozeni/',
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
        			url: 'akt/openXHTML/'+id,
        			headers: {
                        'Content-Type': 'application/xml'
                    },
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
        openPDF: function(id, onSuccess, onError){
        	var req = {
        			method: 'GET',
        			url: 'akt/openPDF/'+id,
        			responseType: 'arraybuffer',
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
        adoptAkt: function(id, onSuccess, onError){
        	var req = {
        			method: 'PUT',
        			url: '/akt/prihvati/'+id,
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
        refuseAkt: function(id, onSuccess, onError){
        	var req = {
        			method: 'PUT',
        			url: '/akt/odbij/'+id,
        	}
        	$http(req).then(onSuccess, onError);
        	
        }
    }
});