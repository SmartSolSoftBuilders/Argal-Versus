var ModalInstanceIcd = function ($scope, $modalInstance,$route,$location,mensaje,$rootScope) {
  $scope.mensajeDialogoAltaHosp=mensaje;  
  $scope.resetHospForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.closeIcdW = function () {	 
	  $modalInstance.dismiss('openAddIcd');  
	 };  
  $scope.closeIcdW2 = function () {	 
	  $modalInstance.dismiss('openAddIcd');  
  };  
  $scope.closeIcdW3 = function () {	 
	  $modalInstance.dismiss('openAddIcd');  
  };  

  $scope.closeShowHosp = function (id,name) {
	  	$rootScope.hospitalSeleccionado=name;
	  	$rootScope.hospitalSeleccionadoId=id;	    
	  	$modalInstance.dismiss('selectHospGrid');
	    //$location.path('/showhospitales'); 
	  };  

};

var Cat_ICD_Controller = function ($modal,$scope,$http,$rootScope,ngTableParams,$filter,IcdService) {
	var data=IcdService.data;
	$scope.tableParamsICD = new ngTableParams(
		      {
		          page: 1,            // show first page
		          count: 20,           // count per page
		          sorting: {name:'asc'}
		        },
		        {
		          total: 0, // length of data
		          getData: function($defer, params) {
		        	 IcdService.getData($defer,params,$scope.filter,'mvc/evento/geticds');		            
		            console.log("DIN!")		           
		          }
		      });
		      
	 $scope.$watch("filter.$", function () {
	      $scope.tableParamsICD.reload();
	 });


	/*$http.post('mvc/evento/geticds').
	////console.log($scope);
	////console.log($rootScope);
	  success(function(data, status, headers, config) {
	    // this callback will be called asynchronously
	    // when the response is available
		  setDatosIcds(data);
	  }).
	  error(function(data, status, headers, config) {
		  alert("Error al obtener los Icds");
		  // called asynchronously if an error occurs
	    // or server returns response with an error status.
	 });
	*/
	$scope.openAddIcd2 = function (msg,url,idT) {
		$rootScope.idT=idT;
		var modalInstance = $modal.open({
			windowClass:'modal1',
	        templateUrl: url,
	        controller: ModalInstanceIcd,
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

	var setDatosIcds= function (data){
		$scope.data = data;       	
		//console.log(data);
		//console.log($rootScope);
		//console.log($scope);
	    
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
	        	$rootScope.tipoTratamientoSel=args.descripcion;
	        }
	};
    $scope.selectICD1 = function(id,descripcion) {
    	if ($rootScope.idT==1){
    		$rootScope.tipoIcd1=descripcion;
    		$rootScope.idtipoIcd1=id;		
    		$scope.closeIcdW();

    		}
    	if ($rootScope.idT==2){
        	$rootScope.tipoIcd2=descripcion;
    		$rootScope.idtipoIcd2=id;		
    		$scope.closeIcdW2();
    		}
    	if ($rootScope.idT==3){
        	$rootScope.tipoIcd3=descripcion;
    		$rootScope.idtipoIcd3=id;		
    		$scope.closeIcdW3();
    	}

    }
    $scope.selectICD2s = function(args) {
    	$rootScope.tipoIcd2=descripcion;
    	$scope.closeIcdW2();
    	
    }
	$scope.messageEditarImplant="Implants";
  //keep track of selected items
    
    $scope.editarImplant = function (){    	
    	alert("Editar");
    };
    
    $scope.eliminarImplant2 = function (){    	
    	alert("Editar");
    };
};

