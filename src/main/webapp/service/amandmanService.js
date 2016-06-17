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
        getByAkt: function (aktId, onSuccess,onError) {
            var req = {
                method: 'GET',
                url: 'amandman/getByAkt/'+ aktId,
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
        create: function(entity, aktId, onSuccess,onError){
            var req = {
                method: 'POST',
                url: 'amandman/'+aktId,
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
        	
        },openPDF: function(id, onSuccess, onError){
        	var req = {
        			method: 'GET',
        			url: 'amandman/openPDF/'+id,
        			responseType: 'arraybuffer',
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
        adoptAmandman: function(id, onSuccess, onError){
        	var req = {
        			method: 'PUT',
        			url: 'amandman/prihvati/'+id,
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
        refuseAmandman: function(id, onSuccess, onError){
        	var req = {
        			method: 'PUT',
        			url: 'amandman/odbij/'+id,
        	}
        	$http(req).then(onSuccess, onError);
        	
        },
    }
});