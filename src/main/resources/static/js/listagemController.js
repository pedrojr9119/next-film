var app = angular.module("listagemControllers", []);

app.controller("listagemController", function($scope, $http) {
	var url = "http://localhost:8080/";
	$scope.usuario = {};
	
	$scope.listarVideos = function() {
		$http.get(url + "videos", $scope.videos)
		.then(function(response) {
			$scope.videos = response.data;
		}, function(response) {
			window.alert("Erro de POST!");
		});
	};
	
	$scope.buscaUsuarioLogado = function() {
		$scope.usuario.login = sessionStorage.getItem("usuario");
	};
	
	$scope.listarVideos();
	$scope.buscaUsuarioLogado();
	
});