var PermisosController= function ($scope,$http,$rootScope,$route,$modal, $log,$filter,ngTableParams){
	scope=$scope;
	$scope.implants={};
	$rootScope.indexSeleccionado=0;
	$http.post('mvc/implant/getimplants',scope.implant).
	  success(function(data, status, headers, config) {	
		  $scope.implants=data;
		  
	  }).
	  error(function(data, status, headers, config) { 
	 });	
           
    scope.messageEditarImplant="Implants";
           
    scope.eliminarPermisoHospImplant=function (){
    	var implantTmp=new Object();
	  	implantTmp.idImplant=$rootScope.implant.idImplant;		  	
	  	implantTmp.hospitalesConPermisos=new Array(1);
	  	var hospTmp = new Object();
	  	hospTmp.idHospital=$rootScope.hospitalSel.idHospital;
	  	implantTmp.hospitalesConPermisos[0]=hospTmp;	  	
    	if (confirm("Eliminar Permiso para el implant:"+scope.implantSel.nombreImplant+"?")){
    		$http.post('mvc/implant/eliminarpermisohospimp',implantTmp).    	
        	success(function(data, status, headers, config) {
        		alert("El permiso ha sido eliminado!");    	
        		$scope.updateHospImplants();
        	}).
        	error(function(data, status, headers, config) {
        	});
    	}
    	else
    		return false;
    };    
    scope.updateHospImplants=function (){
    	//$route.reload();
    	//
    	$rootScope.var1 = $rootScope.var1+"->";
    	$rootScope.implant=$scope.implantSel;
    	console.log("CHECK");
    	console.log($scope.data);
    	//alert($rootScope.implant.idImplant);
    	$http.post('mvc/hospital/gethospitalesbyimplant',scope.implantSel).    	
    		success(function(data, status, headers, config) {
    		  //scope.rowCollection=data;
    		//
    			$rootScope.data = data;
    	    	
    		$scope.data = data;
    			   
    		setDatosImplantsPermisos(data);
    	}).
    	error(function(data, status, headers, config) {
    		alert("Error obteniendo los hospitales del implant");
    	});
    };
    $rootScope.data=new Array();
    $scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10,          // count per page
        sorting: {
            name: 'asc'     // initial sorting
        }
    }, {
        total: function () { return getData().length; }, // length of data
        getData: function($defer, params) {
            var filteredData = getData();
            var orderedData = params.sorting() ?
                                $filter('orderBy')(filteredData, params.orderBy()) :
                                filteredData;

            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        },
		$scope: { $data: {} }
    });
    
    $scope.$watch("data", function () {
		$scope.tableParams.reload();
	});	
    $scope.data = {};
	
	var getData = function() {
		return $rootScope.data;
	};
    var setDatosImplantsPermisos= function (data){
	    $scope.data = data;
	   // alert($scope.data)	   
	       $scope.changeSelection = function(args) {
	        	$rootScope.hospitalSeleccionado=args.nombreHospital;    	
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
	            console.log($rootScope.hospitalSel);          
	        }
	};

    
    /*$route.reload();
	$scope.implantSel=100;
	$scope.updateHospImplants();*/
	
    $scope.openEditarPermisosImplant = function (msg,url) {
    	
        var modalInstance = $modal.open({
            templateUrl: url,
            windowClass:'modal1',
        	backdrop: 'static',
            controller: ModalPermisosInstanceCtrl,
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

var ModalPermisosInstanceCtrl = function ($scope, $modalInstance,$route,$location,mensaje,$rootScope,$http) {
	$scope.mensajeDialogoAltaImplant=mensaje;
	$scope.agregarPermisoHospImplt = function (hosp) {
		console.log("AGREGANDO PERMISO");
		console.log(hosp);		
		var implantTmp=new Object();
		implantTmp.idImplant=$rootScope.implant.idImplant;		  	
		implantTmp.hospitalesConPermisos=new Array(1);
		var hospTmp = new Object();	   	
		//alert($rootScope.hospitalSelPer.idHospital)
		hospTmp.idHospital=hosp.idHospital;
		implantTmp.hospitalesConPermisos[0]=hospTmp;
		$http.post('mvc/implant/agregarpermisohospimp',implantTmp).
		success(function(data, status, headers, config) {
			$scope.alerts = 
				[{ type: 'success', msg: 'Se agreg� el permiso con �xito!'}];
			//data.push($rootScope.hospitalSelPer);
			//$modalInstance.dismiss('cancel');
//			$rootScope.implant=data;
			
			$route.reload();
//			alert( "valor"+document.getElementById('implantSel').value)
			
	//	    document.getElementById('implantSel').selectedIndex = "2";
			
		//	alert( "valor"+document.getElementById('implantSel').value)
			
			//$scope.updateHospImplants();
		}).error(function(data, status, headers, config) {
			alert("Error! Intente de nuevo!");
		});		    
	};
	
    $scope.updateHospImplants=function (){
    	$rootScope.var1 = $rootScope.var1+"->";
     	console.log($rootScope.implant);
    	//alert($rootScope.implant.idImplant);
    	$http.post('mvc/hospital/gethospitalesbyimplant',$rootScope.implant).    	
    		success(function(data, status, headers, config) {
    		  //scope.rowCollection=data;
    		//
    			$rootScope.data = data;
    	    	
    		$scope.data = data;
    			   
    		setDatosImplantsPermisos(data);
    	}).
    	error(function(data, status, headers, config) {
    		alert("Error obteniendo los hospitales del implant");
    	});
    };

	
	$scope.resetImplForm = function () {
		$modalInstance.close();
	    $route.reload();
	  };	
};


var ShowHospitalController = function ($scope,$http,$rootScope,$route,$modal,$filter,ngTableParams) {
	implant = new Object();	
	implant.idImplant=$rootScope.implant.idImplant;
	$http.post('mvc/hospital/gethospitalessinpermisoimplant',implant).
	  success(function(data, status, headers, config) {
		  //scope.rowHospCollection=data;
		  setDatosHospImplantsPermisos(data);
	  }).
	  error(function(data, status, headers, config) {
		  alert("Error al obtener los hospitales, intente de nuevo!");
	});
    
	$scope.tableParamsPermisos = new ngTableParams({
		page: 1,            // show first page
		count: 10,          // count per page
		sorting: {
				name: 'asc'     // initial sorting
				}
		}, {
        total: function () { return getData2().length; }, // length of data
        getData: function($defer, params) {
            var filteredData = getData2();
            var orderedData = params.sorting() ?
                                $filter('orderBy')(filteredData, params.orderBy()) :
                                filteredData;

            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        },
		$scope: { $data2: {} }
    });
	$scope.data2={};
	$scope.$watch("data2", function () {
		$scope.tableParamsPermisos.reload();
	});	
    
	var getData2 = function() {
		return $scope.data2;
	};
    

	var setDatosHospImplantsPermisos= function (data){		
	    $rootScope.data = data;
	    $scope.data2 = data;
	    
	    
	};
	$scope.changeSelection = function(args) {
    	console.log("argumentos")
    		console.log($scope)
        	console.log(args)
    	//alert("ok1")
        	$rootScope.hospitalSeleccionadoPer=args.nombreHospital;    	
        	$rootScope.hospitalSelPer={};
        	$rootScope.hospitalSelPer.idHospital=args.idHospital;
        	for (i=0;i<$scope.data.length;i++){
        		if ($scope.data[i].idHospital==args.idHospital)
        			break;
        	}	  	
        	//alert(i);
        	$scope.data2.splice(i, 1); 
        	$scope.$watch("data2", function () {
        		//alert("Sch12s")
        		$scope.tableParamsPermisos.reload();
        	});	
            
    }
    
	$scope.cancelHospForm = function () {    	
        $modalInstance.dismiss('cancelHospForm');
    };        
    
    $scope.eliminarDesactHosp = function (){   
    	$scope.hospitalEliminar=$rootScope.hospitalSel;
    	if (confirm("�Desea eliminar el Hospital: "+$scope.hospitalEliminar.nombreHospital+"?")){
    		delete Array.prototype.toJSON;	    	
	    	$http.defaults.headers.post["Content-Type"] = "application/json";	    	
    		$http.post('mvc/hospital/eliminarhospital',$scope.hospitalEliminar).
    		  success(function(data, status, headers, config) {    		
    			  $scope.openEditarHosp('SE ELIMIN� CORRECTAMENTE EL HOSPITAL!','eliminardesachospdialog.html');    			  
    			  $route.reload();
    		  }).
    		  error(function(data, status, headers, config) {
    			  alert("ERROR AL ELIMINAR AL HOSPITAL! Intente de nuevo");
    		 });	
    	}
    	
    };
        
    $scope.openEditarHosp = function (msg,url) {
    	
        var modalInstance = $modal.open({
        	windowClass:'modal1',
        	backdrop: 'static',
            templateUrl: url,
            controller: ModalPermisosInstanceCtrl,
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