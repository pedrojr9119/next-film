var app = angular.module("videoControllers", []);

app.controller("videoController", function($scope, $http, $stateParams, $interval) {
	var url = "http://localhost:8080/";
	$scope.sessao = {};
	$scope.usuario = {};
	var videoId = "";
	var usuarioLogin = "";
	
	videos = document.getElementsByTagName("video");
    video = videos[0];
	
	$scope.listarVideo = function(id) {
		$http.get(url + "videos/" + id, $scope.video)
		.then(function(response) {
			$scope.video = response.data;
		}, function(response) {
			window.alert("Erro de POST!");
		});
	};
	
	/*$scope.dadosVideo = function(id) {
		$http.get(url + "session/" + id)
		.then(function mySucces(response) {
	        $scope.sessao = response.data;
	        video.currentTime = $scope.sessao.tempo;
	    }, function myError(response) {
	        window.alert("Erro de GET!");
	    });
	};*/
	
	$scope.salvar = function() {
		$http.post(url + "sessao", $scope.sessao)
		.then(function(response) {
		}, function(response) {
			window.alert("Erro de POST!");
		});
	};
	
	$scope.atualizar = function(id) {
		$http.put(url + "sessao/" + id, $scope.sessao)
		.then(function(response) {
		}, function(response) {
			//window.alert("Erro de PUT!");
		});
	};
	
	video.addEventListener('ended', function() {
		$scope.buscarSessao(videoId, usuarioLogin);
		id = $scope.sessao.identifier;
		$scope.sessao.status = 'assistido';
		$scope.atualizar(id);
	});
	
	video.addEventListener('play', function() {
		$interval(function () {
			$scope.buscarSessao(videoId, usuarioLogin);
			id = $scope.sessao.identifier;
			$scope.sessao.tempo = video.currentTime;
			
			//if(typeof id === "undefined" || id === null) {
			if(typeof $scope.sessao.status === "undefined") {
				$scope.sessao.usuarioRepresentation = $scope.usuario;
				$scope.sessao.videoRepresentation = $scope.video;	
				$scope.sessao.status = 'assistindo';
				$scope.salvar();
			} else {
				$scope.atualizar(id);
			}
		}, 3000);
	});
	
	$scope.buscarSessao = function(idVideo, usuarioLogin) {
		$http.get(url + "sessao/video/" + idVideo + "/usuario/" + usuarioLogin, $scope.sessao)
		.then(function(response) {
			$scope.sessao = response.data;
			video.currentTime = $scope.sessao.tempo;
		}, function(response) {
			//window.alert("Erro de GET!");
		});
	};
	
	$scope.buscarUsuarioPorLogin = function(login) {
		$http.get(url + "usuarios/" + login, $scope.usuario)
		.then(function(response) {
			$scope.usuario = response.data;
		}, function(response) {
			window.alert("Erro de POST!");
		});
	};
	
	videoId = $stateParams.videoid;
	$scope.listarVideo(videoId);
	
	usuarioLogin = sessionStorage.getItem("usuario");
	$scope.buscarUsuarioPorLogin(usuarioLogin);
	
	$scope.buscarSessao(videoId, usuarioLogin);
	
});