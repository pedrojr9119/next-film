angular.module('app', [ 'ui.router', 'app.controllers' ])

.config(function($stateProvider, $urlRouterProvider) {
	
	$urlRouterProvider.otherwise("/listagem");
	
	$stateProvider
	.state('home', {
		url: "/home",
		templateUrl: "home.html"
	})
	.state('listagem', {
		url: "/listagem",
		templateUrl: "listagem.html",
		controller: "listagemController"
	})
	.state('video', {
		url: "/video/:videoid",
		templateUrl: "video.html",
		controller: "videoController"
	})

});