var SeguimientoMedicoController = function($scope, $http, $timeout,$rootScope,$location,$modal,$filter,ngTableParams) {
    $scope.letterLimit = 3;
	$scope.interconsultaselected=new Object();
	//$rootScope.editregreportemedicoselected = new Object();
	$scope.interconsultaselected=new Object();
	$scope.eventoEditSelected=$rootScope.eventoEditSelected;	
	$scope.subtotalGastosProcedente="";	
	$scope.subtotalGastosDesviado="";
	
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
	            total: $scope.eventoEditSelected.bitacoras.length, // length of data
	            getData: function ($defer, params) {
	                // use build-in angular filter
	                var filteredData = params.filter() ?
	                        $filter('filter')($scope.eventoEditSelected.bitacoras, params.filter()) :
	                        	$scope.eventoEditSelected.bitacoras;
	                var orderedData = params.sorting() ?
	                        $filter('orderBy')(filteredData, params.orderBy()) :
	                        	$scope.eventoEditSelected.bitacoras;

	                params.total(orderedData.length); // set total for recalc pagination
	                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	            }
	        });
			$rootScope.editbitacora={};
	        //$scope.changeSelection = function(args) {
//	        	console.log("ARGS CH");		        	  	            
	         //}
	
	
	$scope.tipopartos=[
	                    {'idTipoParto':1, 'descripcion':'PARTO NATURAL'},
	                    {'idTipoParto':2, 'descripcion':'CES�REA'}
	                 ];
	$scope.medicostratantes=[
	                    {'id':1, 'nombre':'NICOL�S RIBIERA'},
	                    {'id':2, 'nombre':'ANASTASIO RIBERA'}
	                 ];
	
	$scope.reportesmedicos=[
	   {'idRegistroMedico':1, 'descripcion':'PACIENTE ESTABLE'},
	   {'idRegistroMedico':2, 'descripcion':'EN ESPERA DE RESULTADOS'},
	   {'idRegistroMedico':3, 'descripcion':'PACIENTE EN ESPERA DE ALTA'},
	   {'idRegistroMedico':4, 'descripcion':'PACIENTE GRAVE'},
	   {'idRegistroMedico':5, 'descripcion':'PACIENTE TERMINAL'},	  
	   {'idRegistroMedico':6, 'descripcion':'PACIENTE EN ESPERA DE DIAGNOSTICO'}
	];
	
	$scope.especialidades=[
	           {'idEspecialidad':1, 'descripcion':'NO'},
	       	   {'idEspecialidad':2, 'descripcion':'CARDIOLOGIA'},
	      	   {'idEspecialidad':3, 'descripcion':'CIRUGIA VASCULAR PERIFERICA'},
	      	   {'idEspecialidad':4, 'descripcion':'ENDOCRINOLOGIA'},
	      	   {'idEspecialidad':5, 'descripcion':'GASTROENTEROLOGIA MEDICA'},
	      	   {'idEspecialidad':6, 'descripcion':'GASTROENTEROLOGIA QUIRURGICA'},
	      	   {'idEspecialidad':7, 'descripcion':'GENETICA'},
	      	   {'idEspecialidad':8, 'descripcion':'GINECOLOGIA Y OBSTETRICIA'},
	      	   {'idEspecialidad':9, 'descripcion':'HEMATOLOGIA'},
	      	   {'idEspecialidad':10, 'descripcion':'INMUNOLOGIA'},
	      	   {'idEspecialidad':11, 'descripcion':'MAXILOFACIAL'},
	      	   {'idEspecialidad':12, 'descripcion':'NEFROLOGIA'},
	      	   {'idEspecialidad':13, 'descripcion':'NEUMOLOGIA'},
	      	   {'idEspecialidad':14, 'descripcion':'NEUROLOGIA MEDICA'},
	      	   {'idEspecialidad':15, 'descripcion':'NEUROLOGIA QUIRURGICA'},
	      	   {'idEspecialidad':16, 'descripcion':'ONCOLOGIA'},
	      	   {'idEspecialidad':17, 'descripcion':'OTORRINOLARINGOLOTGIA'},
	      	   {'idEspecialidad':18, 'descripcion':'PEDIATRIA'},
	      	   {'idEspecialidad':19, 'descripcion':'PSIQUIATRIA'},
	      	   {'idEspecialidad':20, 'descripcion':'REUMATOLOGIA'},
	      	   {'idEspecialidad':21, 'descripcion':'TOXICOLOGIA'},
	      	   {'idEspecialidad':22, 'descripcion':'TRAUMATOLOGIA Y ORTOPEDIA	'},
	      	   {'idEspecialidad':23, 'descripcion':'UROLOGIA'},
	      	   {'idEspecialidad':24, 'descripcion':'VARIOS'}
	];
	$rootScope.setLoading(false);
    $scope.showFormNac = function(){
    	console.log($scope);
    	if ($scope.regreportemedicoselected!=null){
    		if ($scope.regreportemedicoselected.idRegistroMedico==5)
    			document.getElementById("divDef").style.display = 'block';
    		else
    			document.getElementById("divDef").style.display = 'none';
    	}
    };
    
    $scope.showAltaSeguimientoMedicoForm = function(){    	
    	document.getElementById("divSeguimientoMedicoController").style.display = 'none';
    	document.getElementById("divAltaSeguimientoMedicoController").style.display = 'block';
    };
    
    
    $scope.showEditaSeguimientoMedicoForm = function(id){
    	console.log("EDITANDO DATOS");
    	console.log($rootScope);
    	console.log(id);
    	$scope.getBitacora=new Object();
    	$scope.getBitacora.idBitacora=id; 
    	$http.post('mvc/evento/getBitacoraById',$scope.getBitacora).
		success(function(args, status, headers, config) {
			console.log(args);
			document.getElementById("divSeguimientoMedicoController").style.display = 'none';
	    	document.getElementById("divEditaSeguimientoMedicoController").style.display = 'block';
	    	
			$rootScope.editbitacora.idBitacora=args.idBitacora;
	    	$rootScope.editbitacora.observaciones=args.observaciones;
	    	$rootScope.editbitacora.idRegistroMedico=args.idBitacora;
	    	$rootScope.editbitacora.interconsulta=args.interconsulta;
	    	
	    	var loadingReporte=new Object();
	    	//loadingReporte.idRegistroMedico=args.idRegistroMedico;
	    	//loadingReporte.descripcion='PACIENTE ESTABLE';	    	
	    	//$rootScope.editbitacora.editregreportemedicoselected=loadingReporte;
			//$rootScope.editregreportemedicoselected=loadingReporte;	
			//$scope.editregreportemedicoselected=loadingReporte;
			console.log(args.idRegistroMedico)
			//document.getElementById("selRM").selectedIndex=args.idRegistroMedico;
			 
	    	console.log("SELECTED");	        	        	        	    	
	    	  //$location.path('/showeventos');
	    }).error(function(data, status, headers, config) {
	        // called asynchronously if an error occurs
	        // or server returns response with an error status.
	    	  alert("Error al registrar la bitacora"+data);
	    });						    	
    	
    	    	
    };
    
    $scope.hideAltaSeguimientoMedicoForm = function(){ 
    	document.getElementById("divSeguimientoMedicoController").style.display = 'block';
    	document.getElementById("divAltaSeguimientoMedicoController").style.display = 'none';
    };
    $scope.hideEditaSeguimientoMedicoForm = function(){
    	document.getElementById("divSeguimientoMedicoController").style.display = 'block';
    	document.getElementById("divEditaSeguimientoMedicoController").style.display = 'none';
    };
    
    $scope.today = function() {
		$scope.dt = new Date();
		$scope.dt2 = new Date();
	};
	$scope.today();
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};

	$scope.clear = function () {
		$scope.dt = null;
		$scope.dt2 = null;
	};
	$scope.valueTime = new Date(0, 0, 1, 14, 57);
	// Disable weekend selection
	$scope.disabled = function(date, mode) {
	  //return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	};

	$scope.toggleMin = function() {
		$scope.minDate = ( $scope.minDate ) ? null : new Date();
	};
	$scope.toggleMin();

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
	$scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	};
	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
	$scope.format = $scope.formats[0];
	
	$scope.guardarEditarBitacora = function (){
		$scope.eventoRegistroBitacora= new Object();
		$scope.eventoRegistroBitacora.idEvento=$rootScope.eventoEditSelected.idEvento;		
		console.log("SAVE VALUES editar");
		console.log($scope.editbitacora);
		$scope.eventoRegistroBitacora.bitacoras=new Array(1);
		var bitacora=new Object();
		bitacora.idBitacora=$rootScope.editbitacora.idBitacora;
		bitacora.idRegistroMedico=$scope.editbitacora.editregreportemedicoselected.idRegistroMedico;
		bitacora.observaciones=$rootScope.editbitacora.observaciones.toUpperCase();
		bitacora.descripcion=$scope.editbitacora.editregreportemedicoselected.descripcion;
		if ($scope.editbitacora.editinterconsultaselected!=null)
			bitacora.interconsulta=$scope.editbitacora.editinterconsultaselected.descripcion;
		else
			bitacora.interconsulta="NO";
		bitacora.fechaBitacora=$scope.dt;
		console.log("BITACORA")
		console.log(bitacora);
		$scope.eventoRegistroBitacora.bitacoras.push(bitacora);
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		console.log("SAVE VALUES");
		console.log($scope.eventoRegistroBitacora);
		$http.post('mvc/evento/guardareditarbitacora',$scope.eventoRegistroBitacora).
			success(function(data, status, headers, config) {
		    	  alert("Se actualiz\u00f3 correctamente la bitácora");
		    	  $scope.eventoEditSelected.bitacoras=data.bitacoras;
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		        // called asynchronously if an error occurs
		        // or server returns response with an error status.
		    	  alert("Error al registrar la bit�cora"+data);
		    });		
				
		$scope.hideEditaSeguimientoMedicoForm();
	};
	
	$scope.eliminarBitacora = function (idBitacoraB){
		if (confirm("Seguro que desea eliminar el registro de la bitácora?")){
		console.log("DELETE BITACORA VALUES");
		console.log(idBitacoraB.idBitacora);
		var bitacora=new Object();
		bitacora.idBitacora=idBitacoraB.idBitacora;
		bitacora.idRegistroMedico=$rootScope.eventoEditSelected.idEvento;
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		$http.post('mvc/evento/eliminarbitacora',bitacora).
			success(function(data, status, headers, config) {
		    	  alert("Se eliminó correctamente la bitácora");
		    	  $scope.eventoEditSelected.bitacoras=data.bitacoras;
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		        // called asynchronously if an error occurs
		        // or server returns response with an error status.
		    	  alert("Error al registrar la bit�cora"+data);
		    });			
		}
	};

	$scope.guardarBitacora = function (){
		$scope.eventoRegistroBitacora= new Object();
		$scope.eventoRegistroBitacora.idEvento=$rootScope.eventoEditSelected.idEvento;
		console.log("SAVE VALUES");
		console.log($scope);
		$scope.eventoRegistroBitacora.bitacoras=new Array(1);
		var bitacora=new Object();
		bitacora.idRegistroMedico=$scope.regreportemedicoselected.idRegistroMedico;
		bitacora.observaciones=$scope.bitacora.observaciones.toUpperCase();
		bitacora.descripcion=$scope.regreportemedicoselected.descripcion.toUpperCase();
		bitacora.fechaBitacora=$scope.dt;
		if ($scope.interconsultaselected!=null)
			bitacora.interconsulta=$scope.interconsultaselected.descripcion.toUpperCase();
		else
			bitacora.interconsulta="";
		$scope.eventoRegistroBitacora.bitacoras.push(bitacora);
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		console.log("SAVE VALUES");
		//console.log($scope.eventoRegistroBitacora);
		$http.post('mvc/evento/guardarbitacora',$scope.eventoRegistroBitacora).
			success(function(data, status, headers, config) {
		    	  alert("Se registr\u00f3 correctamente la bitacora");
		    	  console.log("DATOS");		    	  
		    	  console.log($scope.eventoEditSelected.bitacoras);
		    	  $scope.eventoEditSelected.bitacoras=data.bitacoras;		    	  
		    	  console.log("DATOSDESPUES");		    	  
		    	  console.log($scope.eventoEditSelected.bitacoras);				 
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		        // called asynchronously if an error occurs
		        // or server returns response with an error status.
		    	  alert("Error al registrar la bitacora"+data);
		    });		
				
		$scope.hideAltaSeguimientoMedicoForm();
	};
};
