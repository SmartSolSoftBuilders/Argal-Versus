var ModalImplantInstanceCtrl = function ($scope, $modalInstance,$route,$location,mensaje) {
  $scope.mensajeDialogoAltaImplant=mensaje;  
  $scope.resetImplForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.cancelImplForm = function () {
    $modalInstance.dismiss('cancelImplForm');
    $location.path('/showimplants');  
  };  

};

var AltaImplantController= function($scope, $http, $rootScope,$route,$modal, $log){
		//filtro para poner todo el texto en may�sculas	
		$scope.master = {};	 
	    $scope.message = 'REGISTRAR UN NUEVO IMPLANT';	    
	    $scope.mensajeError ="";
	    $scope.isUnchanged = function(implant) {
	    	$scope.mensajeError ="* Los campos de correo electr\u00f3nico deben escribirse correctamente";
	        return angular.equals(implant, $scope.master);        
	    };          
	    //$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	    
	    $scope.guardarImplant = function (){    		    		  
	    	console.log($scope);
	    	delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";
	    	$http.post('mvc/implant/guardarimplant',$scope.implant).
	    	  success(function(data, status, headers, config) {
	    		  //$scope.openDialogImpl();
	    		  $scope.alerts = 
	     			 [{ type: 'success', msg: 'Implant d con Exito!'}];    		                 
	     		  $route.reload();
	     		 alert("Implant agregado con éxito!");
	     		 $scope.cancelImplForm();
	    	  }).
	    	  error(function(data, status, headers, config) {
	    		  alert("Error! Intente de nuevo!");
	    	  });
	    };
	    	    
	    $scope.openDialogImpl = function () {
	        var modalInstance = $modal.open({
	          templateUrl: 'modalAltaImplant.html',
	          controller: ModalImplantInstanceCtrl,
	          backdrop: 'static',
	          resolve: {
	        	  mensaje: function () {
	                  return "SE AGREGO CORRECTAMENTE AL IMPLANT!";
	                }
	          }
	        });
	        modalInstance.result.then(function (selectedItem) {	          
	        }, function () {          
	        });
	      };	     
};

var EditarImplantController= function($scope, $http, $rootScope,$route,$modal, $log){
	$scope.implant = $rootScope.implant;					
    $scope.message = 'EDITAR DATOS DE UN IMPLANT';
    $scope.mensajeError ="";
    $scope.isUnchanged = function(implant) {
    	$scope.mensajeError ="* Los campos de correo electr\u00f3nico deben escribirse correctamente";
        return angular.equals(implant, $scope.master);        
    };          
        
    
    $scope.actualizarImplant = function (){    	    	
    	$http.post('mvc/implant/actualizarimplant',$scope.implant).
    	  success(function(data, status, headers, config) {
    		  $scope.alerts = 
    			 [{ type: 'success', msg: 'Implant Actualizado con Exito!'}];    		                 
    		  $route.reload();    		  
    	  }).
    	  error(function(data, status, headers, config) {
    		  $scope.alerts = 
   			  [{ type: 'danger', msg: 'ERROR! No se pudieron aplicar los cambios, intente de nuevo.' }];    		                      
    	  });
    };
    
    $scope.closeAlertEditarImplant = function(index) {
      $scope.alerts.splice(index, 1);
    };    
                
};

var ImplantController= function ($scope,$http,$rootScope,$route,$modal,$filter,ngTableParams) {	
	$http.post('mvc/implant/getimplants',$scope.implant).
	  success(function(data, status, headers, config) {		 		  
		  setDatosImplants(data);
	  }).
	  error(function(data, status, headers, config) {
		  alert("Error al cargar los implants! Intente de nuevo.");
	 });
	
	var setDatosImplants= function (data){
	    $scope.data = data;
	        $scope.tableParams = new ngTableParams({
	            page: 1,            // show first page
	            count: 10,          // count per page
	            filter: {
	                //name: 'M'       // initial filter
	            },
	            sorting: {
	                //name: 'asc'     // initial sorting
	            }
	        }, {
	            total: data.length, // length of data
	            getData: function ($defer, params) {
	                // use build-in angular filter
	                var filteredData = params.filter() ?
	                        $filter('filter')(data, params.filter()) :
	                        data;
	                var orderedData = params.sorting() ?
	                        $filter('orderBy')(filteredData, params.orderBy()) :
	                        data;

	                params.total(orderedData.length); // set total for recalc pagination
	                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	            }
	        });

	        $scope.changeSelection = function(args) {
	        }
	};
    
    $scope.editarImplant = function (){    	
    	alert("Editar Implant");
    };
    $scope.cancelImplForm = function () {
        $modalInstance.dismiss('cancelImplForm');
      };
    $scope.eliminarDesactImplant = function (implant){    	
    	if (confirm("Desea eliminar al Implant: "+implant.nombreImplant+"?")){
    		delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";	    	
    		$http.post('mvc/implant/eliminarimplant',implant).
    		  success(function(data, status, headers, config) {
    			  alert("Se ha eliminado correctamente al implant!");
    			  $route.reload();    			  
    		  }).
    		  error(function(data, status, headers, config) {
    			  alert("ERROR AL ELIMINAR AL IMPLANT! Intente de nuevo");
    		 });	
    	}    	
    };
    $scope.openEditarImplant2 = function (msg,url) {
        var modalInstance = $modal.open({
        	windowClass: "modal",
          templateUrl: url,
          controller: ModalImplantInstanceCtrl,
          resolve: {
        	  mensaje: function () {
                  return msg;
                }
          }
        });
        modalInstance.result.then(function (selectedItem) {          
        }, function () {          
        });
      };        
    $scope.openEditarImplant = function (impl,msg,url) {
    	$rootScope.implant=impl;
        var modalInstance = $modal.open({
        	windowClass: "modal",
            templateUrl: url,
            controller: ModalImplantInstanceCtrl,
            backdrop: 'static',

            resolve: {
        	  mensaje: function () {
                  return msg;
                }
          }
        });
        modalInstance.result.then(function (selectedItem) {          
        }, function () {          
        });
      };
};

 