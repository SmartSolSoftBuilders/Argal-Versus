var ModalMedicoTratanteInstanceCtrl = function ($scope, $modalInstance,$route,$location,mensaje) {
  $scope.mensajeDialogoAltaImplant=mensaje;  
  $scope.resetImplForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.closeMedTrat = function () {
    $modalInstance.dismiss('openAltaMedicoTratante');
    //$scope.listaMedTrat=new Object();
    //$location.path('/showimplants');  
  };  

};

var AltaMedicoTratanteController= function($scope, $http, $rootScope,$route,$modal, $log,$filter,ngTableParams){
		//filtro para poner todo el texto en may�sculas
	$scope.user = {
		especialidadesMedTrat: []
	};	
	$scope.filterEvenStartFrom = function (index) {
	    return function (item) {
	    	var ret=false;
	    	if (index<12)
	    		ret=true;	    	
	    	index++;
	        return ret;
	    };
	};
	$scope.filterEvenStartFrom2 = function (index2) {
	    return function (item) {
	    	var ret=false;
	    	if (index2>=12 && index2<24)
	    		ret=true;	    	
	    	index2++;
	        return ret;
	    };
	};
	$scope.filterEvenStartFrom3 = function (index3) {
	    return function (item) {
	    	var ret=false;
	    	if (index3>24)
	    		ret=true;	    	
	    	index3++;
	        return ret;
	    };
	};	
		$http.post('mvc/especialidad/obtenerespecialidades').
		  success(function(data, status, headers, config) {
		    // this callback will be called asynchronously
		    // when the response is available
			  $scope.especialidades=data;
			  $scope.tableParams = new ngTableParams({
		            page: 1,            // show first page
		            count: 0,          // count per page
		            filter: {
		                //name: 'M'       // initial filter
		            },
		            sorting: {
		                //name: 'asc'     // initial sorting
		            }
		        }, {
		        	counts: [], // hides page sizes
		            total: $scope.especialidades.length, // length of data
		            getData: function ($defer, params) {
		                // use build-in angular filter
		                var filteredData = params.filter() ?
		                        $filter('filter')($scope.especialidades, params.filter()) :
		                        	$scope.especialidades;
		                var orderedData = params.sorting() ?
		                        $filter('orderBy')(filteredData, params.orderBy()) :
		                        	$scope.especialidades;

		                params.total(orderedData.length); // set total for recalc pagination
		                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
		            }
		        });

		  }).
		  error(function(data, status, headers, config) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		 });
		$scope.tiposMT=[
		  			   {"idTipoMT":1,"descripcion":"RED"},
		  			   {"idTipoMT":2,"descripcion":"STAFF"},
		  			   {"idTipoMT":3,"descripcion":"INTERINO"}		  			   	 
		  			  ];
			    //$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";	    
	    $scope.guardarMedicoTratante = function (){    		    		 
	    	if ($scope.user.especialidadesMedTrat.length>0){
	    	console.log("MEDCOSTRAT");
	    	console.log($scope);
	    	console.log("MEDCOSTRAT"); 	
	    	console.log($scope.valores);	    	 	
	    	$scope.medicoTratante=new Object();
	    	$scope.medicoTratante.idMedicoTratante=0;
	    	$scope.medicoTratante.nombre=$scope.appMT.toUpperCase()+  " " + $scope.apmMT.toUpperCase() + " " + $scope.nombreMT.toUpperCase();
	    	$scope.medicoTratante.tipo=$scope.tipoMTSelected.idTipoMT;
	    	$scope.medicoTratante.especialidades = new Array();
	    	var especialidades = new Array();
	    	for (var indx=0;indx<$scope.user.especialidadesMedTrat.length;indx++){
	    		var especialidad = new Object();
	    		especialidad.idEspecialidad=$scope.user.especialidadesMedTrat[indx];
	    		especialidades.push(especialidad);
	    	}
	    	$scope.medicoTratante.especialidades=especialidades;
	    	console.log($scope.medicoTratante);
	    	delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";
	    	$http.post('mvc/evento/guardarmedtrat',$scope.medicoTratante).
	    	  success(function(data, status, headers, config) {
	    		  //$scope.openDialogImpl();
	    		  alert("Se registr\u00f3 correctamente!");
	    		  $scope.closeMedTrat();
	    		  $scope.alerts = 
	     			 [{ type: 'success', msg: 'Implant Agregado con Exito!'}];    		                 
	     		  //$route.reload();    	
	    	  }).
	    	  error(function(data, status, headers, config) {
	    		  alert("Error! Intente de nuevo!");
	    	  });
	    	}
	    	else
	    		alert("Debe seleccionar por lo menos una especialidad!");
	    };

	    
	    $scope.openEditarMedTrat = function () {
	    	alert("ok")
	        var modalInstance = $modal.open({
	        	backdrop: 'static',
				windowClass: clase,
				controller: ModalMedicoTratanteInstanceCtrl,
				resolve: {
	        	  mensaje: function () {
	                  return "SE AGREG� CORRECTAMENTE AL IMPLANT!";
	                }
	          }
	        });
	        modalInstance.result.then(function (selectedItem) {	          
	        }, function () {          
	        });
	      };	     
};

var EditarMedicoTratanteController= function($scope, $http, $rootScope,$route,$modal, $log){
	$scope.implant = $rootScope.implant;					
    $scope.message = 'EDITAR DATOS DE UN IMPLANT';
    $scope.mensajeError ="";
    $scope.isUnchanged = function(implant) {
    	$scope.mensajeError ="* Los campos de correo electr�nico deben escribirse correctamente";
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

var MedicoTratanteController= function ($scope,$http,$rootScope,$route,$modal,$filter,ngTableParams) {	
	$http.post('mvc/evento/getmedicostratantes',$scope.implant).
	  success(function(data, status, headers, config) {		 		  
		  setDatosMedTrat(data);
	  }).
	  error(function(data, status, headers, config) {
		  alert("Error al cargar los implants! Intente de nuevo.");
	 });
	
    $scope.eliminarMedicoTratante = function (idMedTrat){
    	if (confirm ("Seguro que desea eliminar al médico tratante?")){
    	console.log("MEDCOSTRAT");
    	console.log(idMedTrat);
    	$scope.medicoTratante=new Object();
    	$scope.medicoTratante.idMedicoTratante=idMedTrat;
    	console.log($scope.medicoTratante);
    	delete Array.prototype.toJSON;	    	
    	$http.defaults.headers.post["Content-Type"] = "application/json";
    	$http.post('mvc/evento/eliminarmedtrat',$scope.medicoTratante).
    	  success(function(data, status, headers, config) {
    		  //$scope.openDialogImpl();
    		  alert("Se eliminó correctamente");     		                 
     		  $route.reload();    	
    	  }).
    	  error(function(data, status, headers, config) {
    		  alert("Error! Intente de nuevo!");
    	  });
    	}
    };

	
	var setDatosMedTrat= function (data){
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
	        	$rootScope.medicoTratanteSel={};
	        	}
	};
    
    $scope.editarImplant = function (){    	
    	alert("Editar Implant");
    };
    $scope.cancelImplForm = function () {
        $modalInstance.dismiss('cancelImplForm');
      };
    $scope.eliminarDesactImplant = function (){    	
    	if (confirm("Desea eliminar al Implant: "+$scope.implant.nombreImplant+"?")){
    		delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";	    	
    		$http.post('mvc/implant/eliminarimplant',$scope.implant).
    		  success(function(data, status, headers, config) {    		
    			  $scope.openEditarImplant('SE ELIMINÓ CORRECTAMENTE AL IMPLANT!','eliminardesacimpldialog.html');    			  
    			  $route.reload();    			  
    		  }).
    		  error(function(data, status, headers, config) {
    			  alert("ERROR AL ELIMINAR AL IMPLANT! Intente de nuevo");
    		 });	
    	}    	
    };
    $scope.openEditarMedTrat = function (msg,url) {
        var modalInstance = $modal.open({
        	backdrop: 'static',
        	windowClass: "modal1",
        	templateUrl: url,
        	controller: ModalMedicoTratanteInstanceCtrl,
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

    $scope.openEditarImplant = function (msg,url) {
        var modalInstance = $modal.open({
        	backdrop: 'static', 
        	windowClass: "modal1",
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
};

 