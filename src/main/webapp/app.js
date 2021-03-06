var app = angular.module('app',['ui.router', 'ngSanitize', 'ngMessages', 'ngMaterial', 'translation', 'flow']); 

app.constant('PRIORITY', ['BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR', 'TRIVIAL']);
app.constant('STATUS', ['TO_DO' , 'IN_PROGRESS', 'VERIFY', 'DONE']);
app.constant('GENDERS', ['MALE', 'FEMALE']);
app.constant('AKT_STATES', ['Predlozen', 'Usvojen', 'Nije usvojen']);
app.config(function($stateProvider, $urlRouterProvider, $translateProvider, $httpProvider, $mdThemingProvider,$mdDateLocaleProvider ){
	
	$urlRouterProvider.otherwise('/akti');
	$stateProvider
    .state('login', {
        url: '/login',
        templateUrl: 'module/login.html',
        controller: 'login'
    }).state('main', {
        url: '/',
        abstract: true,
        templateUrl: 'module/main.html',
        controller: 'main'
    })
    .state('main.akti', {
        url: 'akti',
        templateUrl: 'module/akt/akti.html',
        controller: 'aktiController'
    })
    .state('main.amandmani', {
        url: 'amandmani',
        templateUrl: 'module/amandman/amandmani.html',
        controller: 'amandmaniController'
    })
    .state('main.sednica', {
        url: 'sednica',
        templateUrl: 'module/sednica/sednica.html',
        controller: 'sednicaController'
    }).state('main.viewAkt',{
    	url:'viewAkt/{id}',
    	templateUrl: 'module/akt/viewAkt.html',
    	controller: 'viewAktController'
    }).state('main.viewAmandman',{
    	url:'viewAmandman/{id}',
    	templateUrl: 'module/amandman/viewAmandman.html',
    	controller: 'viewAmandmanController'
    });
	$mdThemingProvider.theme('default')
    .primaryPalette('blue')
    .accentPalette('indigo');
	
	$mdDateLocaleProvider.formatDate = function(date) {
        return moment(date).format('DD-MM-YYYY');
    };
});