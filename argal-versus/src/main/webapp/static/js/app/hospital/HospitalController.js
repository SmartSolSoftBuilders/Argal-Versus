var ModalHospInstanceCtrl = function ($scope, $modalInstance,$route,$location,mensaje,$rootScope) {
  $scope.mensajeDialogoAltaHosp=mensaje;  
  $scope.resetHospForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.cancelHospForm = function () {	 
    $modalInstance.dismiss('cancelHospForm');
    location.reload();
  };  
  $scope.closeShowHosp = function (id,name) {
	  
	  	$rootScope.hospitalSeleccionadoTxt=name;
	  	$rootScope.hospitalSeleccionadoId=id;
	    
	  	$modalInstance.dismiss('selectHospGrid');
	    //$location.path('/showhospitales'); 
	  };  

};



var AltaHospitalController= function($scope, $http,$rootScope,$route,$modal, $log,$location){
		//filtro para poner todo el texto en may�sculas	
		$scope.master = {};
		$scope.showciudadinput=false;
	    $scope.message = 'REGISTRAR UN NUEVO HOSPITAL';
	    $scope.mensajeError ="";
	    $scope.isUnchanged = function(hospital) {	    	
	        return angular.equals(hospital, $scope.master);        
	    };
	    
	    $http.get('static/util/paises.json').success(function (data) {	    
	        $scope.paises = data;	        
	    });

	    $scope.updateCiudadHospForm = function (){	    	
	    	$scope.showciudadinput=true;
	    	if ($scope.paisSel.id==1){
	    		$http.get('static/util/estados.json').success(function (data) {	    
	    	        $scope.estados = data;
	    	    });	    		
	    	}	    		    		
	    };
	    
	    $scope.updateEstadoHospForm = function (){
	    	$scope.hospital.estadoHospital=$scope.estadoSel1.estado;	    		    		    		
	    };
	     
	    $scope.guardarHospital = function (){
	    	console.log($scope.hospital);
	    	$scope.hospital.paisHospital=$scope.paisSel.pais;
	    	$http.post('mvc/hospital/guardarhospital',$scope.hospital).
	    	  success(function(data, status, headers, config) {
	    		  //$scope.openDialogHosp();
	    		  $scope.alerts = 
	    				 [{ type: 'success', msg: 'Hospital Agregado con Éxito!'}];
	    		  alert("HOSPITAL CREADO CON EXITO!");	    		  
	    		  $scope.cancelHospForm();
	    	  }).
	    	  error(function(data, status, headers, config) {
	    		  alert("Error al dar de alta el Hospital, intente de nuevo!");
	    	  });
	    };
	    $scope.cancelImplForm = function () {
	        $modalInstance.dismiss('cancelImplForm');
	      };
	    $scope.openDialogHosp = function () {
	        var modalInstance = $modal.open({
	          templateUrl: 'modalAltaHosp.html',
	          backdrop: 'static',
	          controller: ModalHospInstanceCtrl,
	          resolve: {
	        	  mensaje: function () {
	                  return "SE AGREGÓ CORRECTAMENTE EL HOSPITAL!";
	                }
	          }
	        });
	        modalInstance.result.then(function (selectedItem) {	          
	        }, function () {          
	        });
	      };
};

var EditarHospitalController= function($scope, $http, $rootScope,$route,$modal, $log){
	//filtro para poner todo el texto en may�sculas	
	$http.get('static/util/paises.json').success(function (data) {	    
        $scope.paises = data;        
    });
    $scope.updateCiudadHospEditarForm = function (){	    	
    	$scope.showciudadinput=true;
    	if ($scope.paisSel.id==1){
    		$http.get('static/util/estados.json').success(function (data) {	    
    	        $scope.estados = data;    	        
    	    });	    		
    	}	    		    		
    };
	$scope.hospital = $rootScope.hospitalSel;	
    $scope.message = 'EDITAR DATOS DE HOSPITAL';
    $scope.mensajeError ="";
    $scope.isUnchanged = function(implant) {
    	$scope.mensajeError ="* Los campos de correo electrónico deben escribirse correctamente";
        return angular.equals(implant, $scope.master);        
    };      
    $scope.actualizarHospital = function (){    
    	$scope.hospitalEliminar=$rootScope.hospitalSel;
    	console.log($scope.hospitalEliminar);
    	$http.post('mvc/hospital/actualizarhospital',$scope.hospitalEliminar).
  	  success(function(data, status, headers, config) {    		      		  
		  $scope.alerts = 
			 [{ type: 'success', msg: 'Hospital Actualizado con Éxito!'}];    		                 
		  $route.reload();    		  
	  }).
	  error(function(data, status, headers, config) {
		  $scope.alerts = 
			  [{ type: 'danger', msg: 'ERROR! No se pudieron aplicar los cambios, intente de nuevo.' }];    		                      
	  });
    };
    
    $scope.closeAlertEditarHosp = function(index) {
        $scope.alerts.splice(index, 1);
      };    
};

var HospitalController= function ($scope,$http,$rootScope,$route,$modal,$filter,ngTableParams,HospService) {
	scope=$scope;
	//scope.rowCollection={};
	//$http.post('mvc/hospital/gethospitales',scope.hospital).
	  //success(function(data, status, headers, config) {
		  //setDatosHosps(data);
	  //}).
	  //error(function(data, status, headers, config) {
		  //alert("Error al obtener los hospitales, intente de nuevo!");
	 //});
	
	//var setDatosHosps= function (data){
	console.log("LLenando Hospitales:");			
	$scope.loading=1;
	var data=HospService.data;
	$scope.tableParamsHosp = new ngTableParams(
		      {
		          page: 1,            // show first page
		          count: 20,           // count per page
		          sorting: {name:'asc'}
		        },
		        {
		          total: 0, // length of data
		          getData: function($defer, params) {
		        	  HospService.getData($defer,params,$scope.filter,'mvc/hospital/gethospitales');		            		           
		          }
		      });		      
	  $scope.$watch("filter.$", function () {
	        $scope.tableParamsHosp.reload();
	    });
	  console.log("VALS")
	  console.log(data)
	    /*$scope.data = data;
	        $scope.tableParams = new ngTableParams({
	            page: 1,            // show first page
	            count: 7,          // count per page
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
	        });*/
	        $scope.changeSelection = function(args) {
	        	$rootScope.hospitalSel={};
	        	$rootScope.hospitalSel.idHospital=args.idHospital;
	        	$rootScope.hospitalSel.nombreHospital=args.nombreHospital;
	        	$rootScope.hospitalSel.direccionHospital=args.direccionHospital;    	
	        	$rootScope.hospitalSel.coloniaHospital=args.coloniaHospital;
	        	$rootScope.hospitalSel.municipioDelHospital=args.municipioDelHospital;
	        	$rootScope.hospitalSel.cpHospital=args.cpHospital;
	        	$rootScope.hospitalSel.estadoHospital=args.estadoHospital;
	        	$rootScope.hospitalSel.conmutadorHospital=args.conmutadorHospital;    	    	
	        	$rootScope.hospitalSel.telModuloHospital=args.telModuloHospital;
	        	$rootScope.hospitalSel.ciudadHospital=args.ciudadHospital;
	        	$rootScope.hospitalSel.paisHospital=args.paisHospital;    	
	       
	        }
	//};
	
    scope.columnCollection = [
        {label: 'IdHospital', map: 'idHospital'},
        {label: 'Nombre', map: 'nombreHospital'},        
        {label: 'Estado', map: 'estadoHospital'},        
        {label: 'Mun/del', map: 'municipioDelHospital'}
    ];    
    
    console.log("VALS")
	  console.log(data)
    scope.messageEditarHospital="Hospitales";
  //keep track of selected items
    $rootScope.urlSimpleModal='views/hospital/editar.jsp';
	$rootScope.titleSimpleModal='Editar Hospital';
    scope.$on('selectionChange', function (event, args) {    	
    	//$rootScope.hospital=args.item;    	
    	$rootScope.hospitalSeleccionado=args.item.nombreHospital;
    	$rootScope.tipoTratamientoSel="DOLOR DE PIE";
    	$rootScope.hospitalSel={};
    	$rootScope.hospitalSel.idHospital=args.item.idHospital;
    	$rootScope.hospitalSel.nombreHospital=args.item.nombreHospital;
    	$rootScope.hospitalSel.direccionHospital=args.item.direccionHospital;    	
    	$rootScope.hospitalSel.coloniaHospital=args.item.coloniaHospital;
    	$rootScope.hospitalSel.municipioDelHospital=args.item.municipioDelHospital;
    	$rootScope.hospitalSel.cpHospital=args.item.cpHospital;
    	$rootScope.hospitalSel.estadoHospital=args.item.estadoHospital;
    	$rootScope.hospitalSel.conmutadorHospital=args.item.conmutadorHospital;    	    	
    	$rootScope.hospitalSel.telModuloHospital=args.item.telModuloHospital;
    	$rootScope.hospitalSel.ciudadHospital=args.item.ciudadHospital;
    	$rootScope.hospitalSel.paisHospital=args.item.paisHospital;    	
        console.log($rootScope.hospitalSel);        
    });
        scope.editarHospital = function (){    	
    	alert("Editar");
    };
    scope.eliminarHospital = function (){    	
    	alert("Editar");
    };
    $scope.cancelHospForm = function () {
    	alert("OK")
        $modalInstance.dismiss('cancelHospForm');
      };    
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    $scope.eliminarDesactHosp = function (hosp){   
    	if (confirm("Desea eliminar el Hospital: "+hosp.nombreHospital+"?")){
    		delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";	    	
    		$http.post('mvc/hospital/eliminarhospital',hosp).
    		  success(function(data, status, headers, config) { 
    			  //$scope.openEditarHosp('SE ELIMINO CORRECTAMENTE EL HOSPITAL!','eliminardesachospdialog.html');    			  
    			  alert("Hospital eliminado correctamente!");
    			  $route.reload();
    		  }).
    		  error(function(data, status, headers, config) {
    			  alert("ERROR AL ELIMINAR AL HOSPITAL! Intente de nuevo");
    		 });	
    	}
    	
    };
    $scope.openDialogHosp2 = function (msg,url) {
    	 var modalInstance = $modal.open({
             templateUrl: url,
             controller: ModalHospInstanceCtrl,
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
    $scope.openEditarHosp = function (hosp,msg,url) {
    	
        var modalInstance = $modal.open({
            templateUrl: url,
            backdrop: 'static',

            controller: ModalHospInstanceCtrl,
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
        $scope.openShowHosp = function (msg,url,$scope) {
             var modalInstance = $modal.open({
            	windowClass:'modal2',
                templateUrl: url,
                backdrop: 'static',
                controller: ModalHospInstanceCtrl,
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

 