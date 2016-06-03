var ModalClienteInstanceCtrl = function ($scope, $modalInstance,$route,$location,mensaje) {
  $scope.mensajeDialogoAltaCliente=mensaje;  
  $scope.resetClienteForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.cancelClienteForm = function () {
    $modalInstance.dismiss('cancelClienteForm');
    $location.path('/showclientes');  
  };  

};


var AltaClienteController= function($scope, $http, $rootScope,$route,$modal, $log){
	//filtro para poner todo el texto en may�sculas	
	$scope.master = {};	 
    $scope.message = 'REGISTRAR UN NUEVO CLIENTE';
    $scope.mensajeError ="";
    $scope.isUnchanged = function(cliente) {
    	$scope.mensajeError ="* Los campos de correo electrónico deben escribirse correctamente";
        return angular.equals(cliente, $scope.master);        
    };          
     
    $scope.guardarCliente = function (){     	    
    	$http.post('mvc/cliente/guardarcliente',$scope.cliente).
    	  success(function(data, status, headers, config) {
 //   		  $scope.openDialogCliente();
    		  $scope.alerts = 
	     			 [{ type: 'success', msg: 'Cliente Agregado con Exito!'}];
    		  alert("Cliente agregado con éxito!");
    		  $scope.cancelClienteForm(); 
    	  }).
    	  error(function(data, status, headers, config) {
    		  alert("Error! Intente de nuevo!");
    	  });
    };
    
    $scope.openDialogCliente = function () {
        var modalInstance = $modal.open({
            backdrop: 'static',
          templateUrl: 'modalAltaCliente.html',
          controller: ModalClienteInstanceCtrl,
          resolve: {
        	  mensaje: function () {
                  return "SE AGREGÓ CORRECTAMENTE AL CLIENTE!";
                }
          }
        });
        modalInstance.result.then(function (selectedItem) {	          
        }, function () {          
        });
      };
        
};

var EditarClienteController= function($scope, $http, $rootScope,$route,$modal, $log){
	//filtro para poner todo el texto en may�sculas
	$scope.cliente = $rootScope.cliente;				
	console.log($scope.cliente)
    $scope.message = 'EDITAR CLIENTE';
    $scope.mensajeError ="";
    $scope.isUnchanged = function(cliente) {
    	$scope.mensajeError ="* Los campos de correo electrónico deben escribirse correctamente";
        return angular.equals(cliente, $scope.master);        
    };          
     
    $scope.actualizarCliente = function (){    	    	
    	//delete $scope.cliente['id'];    	
    	console.log($scope.cliente);
    	$http.post('mvc/cliente/actualizarcliente',$scope.cliente).
    	  success(function(data, status, headers, config) {    		
        		  $scope.alerts = 
        			 [{ type: 'success', msg: 'Cliente Actualizado con �xito!'}];    		                 
        		  $route.reload();    		  
        	  }).
        	  error(function(data, status, headers, config) {
        		  $scope.alerts = 
       			  [{ type: 'danger', msg: 'ERROR! No se pudieron aplicar los cambios, intente de nuevo.' }];    		                      
        	  });
        };
        
        $scope.closeAlertEditarCliente = function(index) {
        $scope.alerts.splice(index, 1);
     };    
};

var ClienteController= function ($scope,$http,$rootScope,$route,$modal,$filter,ngTableParams) {
	scope=$scope;
	$http.post('mvc/cliente/getclientes').
	  success(function(data, status, headers, config) {
	    // this callback will be called asynchronously
	    // when the response is available
		  setDatosClientes(data);	  
	  }).
	  error(function(data, status, headers, config) {
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	 });		
	var setDatosClientes= function (data){
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
	
    scope.messageEditarImplant="Implants";       
    
    scope.editarImplant = function (){    	
    	alert("Editar");
    };
    scope.eliminarImplant3 = function (){    	
    	alert("Editar");
    };
    
    $scope.cancelClienteForm = function () {
        $modalInstance.dismiss('cancelClienteForm');
      };
    
    $scope.eliminarDesactCliente = function (){    	
    	if (confirm("�Desea eliminar al Cliente: "+scope.cliente.nombreCliente+"?")){
    		delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";	    	
    		$http.post('mvc/cliente/eliminarcliente',scope.cliente).
    		  success(function(data, status, headers, config) {    		
    			  $scope.openEditarCliente('SE ELIMIN� CORRECTAMENTE AL CLIENTE!','eliminardesacclientedialog.html');    			  
    			  $route.reload();    			  
    		  }).
    		  error(function(data, status, headers, config) {
    			  alert("ERROR AL ELIMINAR AL CLIENTE! Intente de nuevo");
    		 });	
    	}    	
    };
        
    $scope.openEditarCliente2 = function (msg,url) {
        var modalInstance = $modal.open({
        	windowClass: "modal",
          templateUrl: url,
          controller: ModalClienteInstanceCtrl,
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
      
    $scope.openEditarCliente = function (cliente,msg,url) {
    	$rootScope.cliente=cliente;
        var modalInstance = $modal.open({
          backdrop: 'static',
          templateUrl: url,
          controller: ModalClienteInstanceCtrl,
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



