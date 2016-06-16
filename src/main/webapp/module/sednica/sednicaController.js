app.controller('sednicaController', function($scope, $state, $mdDialog,
		$translate, loginService, aktService, amandmanService) {
	$scope.init = function() {
		aktService.getPredlozeni(function(response) {
			$scope.predlozeni = response.data;
		})
	};

	$scope.openGlasanje = function(akt) {
		$scope.currAkt = akt;
		$scope.glasanje = true;
	}
	
	$scope.prihvatiUnacelu = function(ev){
		/*
		 * aktService->prihvati
		 */
		var confirm = $mdDialog.confirm()
        .title('Da li želite da prihvatite akt u načelu?')
        .textContent('Odabirom ove opcije akt će biti prihvaćen u načelu i počinje operacija prihvatanja u celosti, kao i prihvatanja amandmana na ovaj akt.')
        .ariaLabel('Prihvatanje akta u načelu')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  amandmanService.getByAkt($scope.currAkt.id, function(response){
					$scope.aktAmandmani = response.data;
					$scope.neobradjeniAmandmani = $scope.aktAmandmani.length;
				})
				$scope.prihvacenUnacelu = true;
		});
		
	}
	$scope.odbijUnacelu = function(ev){
		/*
		 * aktService->odbij
		 */
		var confirm = $mdDialog.confirm()
        .title('Da li želite da odbijete akt u načelu?')
        .textContent('Odabirom ove opcije akt će biti odbijen, a samim tim i obrisan, kao i svi njegovi amandmani.')
        .ariaLabel('Odbijanje akta u načelu')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  $scope.glasanje = false;
		});
		
	}
	
	$scope.prihvatiAmandman = function(amandman, ev){
		var confirm = $mdDialog.confirm()
        .title('Da li želite da prihvatite amandman?')
        .textContent('Odabirom ove opcije prihvatate amandman i on će biti primenjem na pravni akt.')
        .ariaLabel('Prihvatanje amandmana')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  amandman.obradjen = true;
			  $scope.neobradjeniAmandmani--;
			  amandman.trenutniStatus = "PRIHVAĆEN";
		});
		
	}
	$scope.odbijAmandman = function(amandman, ev){
		var confirm = $mdDialog.confirm()
        .title('Da li želite da odbijete amandman?')
        .textContent('Odabirom ove opcije amandman se odbacuje i neće imati efekta na pravni akt (briše se)')
        .ariaLabel('Odbijanje amandmana')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  amandman.obradjen = true;
			  $scope.neobradjeniAmandmani--;
			  amandman.trenutniStatus = "ODBIJEN";
		});
	}
	
	$scope.odbijUcelosti = function(ev){
		var confirm = $mdDialog.confirm()
        .title('Da li želite da odbijete akt u celosti?')
        .textContent('Odabirom ove opcije akt se odbacuje (briše se)')
        .ariaLabel('Odbijanje akta u celosti')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  $scope.glasanje = false;
			  $scope.currAkt = {};
			  $scope.aktAmandmani = [];
			  $scope.prihvacenUnacelu = false;
		});
	}
	
	$scope.prihvatiUcelosti = function(ev){
		var confirm = $mdDialog.confirm()
        .title('Da li želite da prihvatite akt u celosti?')
        .textContent('Odabirom ove opcije akt se prihvata u celosti i postaje punosnazan.')
        .ariaLabel('Prihvatanje akta u celosti')
        .targetEvent(ev)
        .ok('U redu')
        .cancel('Odustani');
		  $mdDialog.show(confirm).then(function() {
			  $scope.glasanje = false;
			  $scope.currAkt = {};
			  $scope.aktAmandmani = [];
			  $scope.prihvacenUnacelu = false;
			  /*
			   * aktService -> prihvati u celosti
			   */
		});
	}
	
	$scope.openXTML = function(akt, ev){
		aktService.openXTML(akt.id, function(response){
			$mdDialog.show({
		          templateUrl: 'module/akt/aktXHTML.html',
		          controller: 'XHTMLDialogController',
		          clickOutsideToClose: true,
		          htmlToShow: response.data
		       });
		})
		
	}
});