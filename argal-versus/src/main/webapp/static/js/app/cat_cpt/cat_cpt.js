var ModalInstanceCpt = function ($scope, $modalInstance,$route,$location,mensaje,$rootScope) {
  $scope.mensajeDialogoAltaHosp=mensaje;  
  $scope.resetHospForm = function () {
    $modalInstance.close();
    $route.reload();
  };
  $scope.closeCptW = function () {	 
	  $modalInstance.dismiss('openAddCpt');  
	 };  
  $scope.closeCptW2 = function () {	 
	  $modalInstance.dismiss('openAddCpt2');  
  };  
  $scope.closeCptW3 = function () {	 
	  $modalInstance.dismiss('openAddCpt2');  
  };  
  $scope.closeShowHosp = function (id,name) {
	  	$rootScope.hospitalSeleccionado=name;
	  	$rootScope.hospitalSeleccionadoId=id;	    
	  	$modalInstance.dismiss('selectHospGrid');
	    //$location.path('/showhospitales'); 
   };  
};

var Cat_Cpt_Controller = function ($modal,$scope,$http,$rootScope,ngTableParams,$filter,CptService) {
	var data = CptService.data;
	$scope.tableParamsCPT = new ngTableParams(
		      {
		          page: 1,            // show first page
		          count: 20,           // count per page
		          sorting: {name:'asc'}
		        },
		        {
		          total: 0, // length of data
		          getData: function($defer, params) {
		        	CptService.getData($defer,params,$scope.filter,'mvc/evento/getcpts');
		            console.log("DIN!")
		            $scope.loading=0;
		          }
		      });
		      
	  $scope.$watch("filter.$", function () {
	        $scope.tableParamsCPT.reload();
	    });

	/*$http.post('mvc/evento/getcpts').
	////console.log($scope);
	////console.log($rootScope);
	  success(function(data, status, headers, config) {
	    // this callback will be called asynchronously
	    // when the response is available
		  setDatosCpts(data);
	  }).
	  error(function(data, status, headers, config) {
		  alert("Error al obtener los Cpts");
		  // called asynchronously if an error occurs
	    // or server returns response with an error status.
	 });
	*/
	$scope.openAddCpt2 = function (msg,url,idT) {
		$rootScope.idT=idT;
		var modalInstance = $modal.open({
			windowClass:'modal1',
	        templateUrl: url,	        
	        controller: ModalInstanceCpt,
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

	var setDatosCpts= function (data){
		$scope.data = data;       	
		////console.log(data);
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
    $scope.selectCpt1 = function(id,descripcion) {
    	console.log("SELECCIONADO!")
    	console.log($rootScope.idT);
    	if ($rootScope.idT==1){
    		$rootScope.tipoCpt1=descripcion;
    		$rootScope.idtipoCpt1=id;		
    		$scope.closeCptW();

    		}
    	if ($rootScope.idT==2){
        	$rootScope.tipoCpt2=descripcion;
    		$rootScope.idtipoCpt2=id;		
    		$scope.closeCptW2();
    		}
    	if ($rootScope.idT==3){
        	$rootScope.tipoCpt3=descripcion;
    		$rootScope.idtipoCpt3=id;		
    		$scope.closeCptW3();
    	}

    }
    $scope.selectCpt2s = function(args) {
    	$rootScope.tipoCpt2=descripcion;
    	$scope.closeCptW2();
    	
    }
	
};

