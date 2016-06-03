var PasswordController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	
  	$scope.enviarPassword=function() {
  		if ($scope.password!=null){
  			var user= new Object();
  			user.password=$scope.password;
  			$http.post('mvc/index/cambiarpassword',user).
  			success(function(data, status, headers, config) {
  				alert("El password se ha actualizado con Exito!");
  			}).error(function(data, status, headers, config) {
  				alert("Error! Intente de nuevo!");
  			});
  			
  		}
  		else
  			alert("Debe ingresar un nuevo password!");
  	};
		

};
