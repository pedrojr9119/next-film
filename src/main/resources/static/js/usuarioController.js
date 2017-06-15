var app = angular.module("app", []);

app.controller("usuarioController", function($scope, $http) {
	var url = "http://52.67.194.126:8080/";
	var usuario = "";
	
	$scope.cadastrar = function() {
		$http.post(url + "usuarios", $scope.usuario)
		.then(function(response) {
		}, function(response) {
			window.alert("Erro de POST!");
		});
		
		document.getElementById('usuarioLogin').value='';
		document.getElementById('usuarioEmail').value='';
		document.getElementById('usuarioSenha').value='';
		
		//window.alert("Cadastrado com sucesso!");
	};
	
	$scope.entrar = function() {
		sessionStorage.setItem("usuario", $scope.usuario.login);
	}
	
});