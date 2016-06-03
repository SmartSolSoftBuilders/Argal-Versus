var AltaMedicaController = function($scope, $http, $timeout,$rootScope,$location,$modal,$route,$modalInstance) {
	$rootScope.setLoading(false);	
	$rootScope.regreportemedicoselected=new Object();
	$rootScope.editregreportemedicoselected = new Object();
	$scope.eventoEditSelected=$rootScope.eventoEditSelected;	
	$scope.subtotalGastosProcedente="";	
	$scope.subtotalGastosDesviado="";
	
	$rootScope.rowCollectionSeguimientoMedico=$rootScope.eventoEditSelected.bitacoras;
	$scope.eventoConCenso=true;
	$scope.closeWindow=function(){
	    $modalInstance.close();
	    $route.reload();
	};
	
	$scope.capitas = [
	    {"idCapita":1,"nombre":"CH"},
	  	{"idCapita":2,"nombre":"NA"},
	  	{"idCapita":3,"nombre":"CH/NA"},
	  	{"idCapita":4,"nombre":"CH/NC"},
	  	{"idCapita":5,"nombre":"CH/NA/NC"},
	  	{"idCapita":6,"nombre":"NC/NA"}			  			 
	];
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
	    {'idRegistroMedico':4, 'descripcion':'PACIENTE EN ESPERA DE DIAGNOSTICO'},	   
	    {'idRegistroMedico':5, 'descripcion':'REGISTRO DE NACIMIENTO'}
	];
			
    $scope.columnCollectionSeguimientoMedico = [
        {label: 'IdBitacora', map: 'idBitacora'},
        {label: 'Fecha', map: 'fechaBitacora',formatFunction: 'date'},
        {label: 'Registro', map: 'idRegistroMedico' },               
        {label: 'Observaciones', map: 'observaciones' }
    ];
    		        
    $scope.globalConfigSeguimientoMedico= {    	        
          isPaginationEnabled: true,
          isGlobalSearchActivated: true,        
          itemsByPage: 5,
          selectionMode: 'single',
          actions: {
               list: { url: '/GetUsers' },
               edit: { url: '/EditUser', title: 'Edit User', desc: 'Edit', iconClass: '' }, 
               add: { url: '/AddUser', title: 'Add User', buttonClass: 'pull-right', iconClass: 'icon-plus', desc: ' Add User' }, // TODO: zkontrolovat default description
               remove: { url: '/DelUser', title: 'Confirmation Dialog', msg: 'Do you really want to delete the user?' }
          }
    };       
    
    $scope.showFormNac = function(){
    	console.log($scope);
    	if ($scope.regreportemedicoselected!=null){
    		if ($scope.regreportemedicoselected.idRegistroMedico==5)
    			document.getElementById("divAltaNacimiento").style.display = 'block';
    		else
    			document.getElementById("divAltaNacimiento").style.display = 'none';
    	}
    };
    
    $scope.showAltaSeguimientoMedicoForm = function(){
    	document.getElementById("divSeguimientoMedicoController").style.display = 'none';
    	document.getElementById("divAltaSeguimientoMedicoController").style.display = 'block';
    };
    
    $scope.$on('selectionChange', function (event, args) {    	    	
    	//$rootScope.cliente=args.item;
    	$rootScope.editbitacora={};	
    	$rootScope.editbitacora.idBitacora=args.item.idBitacora;
    	$rootScope.editbitacora.observaciones=args.item.observaciones;    
        console.log($rootScope.editbitacora);        
    });
    
    $scope.showEditaSeguimientoMedicoForm = function(){
    	console.log($scope);
    	document.getElementById("divSeguimientoMedicoController").style.display = 'none';
    	document.getElementById("divEditaSeguimientoMedicoController").style.display = 'block';
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
	};
	$scope.today();
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};

	$scope.clear = function () {
		$scope.dt = null;
	};

	// Disable weekend selection
	$scope.disabled = function(date, mode) {
	  return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
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
		console.log("SAVE VALUES");
		console.log($scope);
		$scope.eventoRegistroBitacora.bitacoras=new Array(1);
		var bitacora=new Object();
		bitacora.idBitacora=$rootScope.editbitacora.idBitacora;
		bitacora.idRegistroMedico=$scope.editregreportemedicoselected.idRegistroMedico;
		bitacora.observaciones=$rootScope.editbitacora.observaciones;		
		$scope.eventoRegistroBitacora.bitacoras.push(bitacora);
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		console.log("SAVE VALUES");
		console.log($scope.eventoRegistroBitacora);
		$http.post('mvc/evento/guardareditarbitacora',$scope.eventoRegistroBitacora).
			success(function(data, status, headers, config) {
		    	  alert("Se actualiz� correctamente la bit�cora");
		    	  $rootScope.rowCollectionSeguimientoMedico=data.bitacoras;
		    	  $rootScope.eventoEditSelected.bitacoras=data.bitacoras;
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		    	  alert("Error al registrar la bit�cora"+data);
		    });		
				
		$scope.hideEditaSeguimientoMedicoForm();
	};
	$scope.guardarBitacora = function (){
		$scope.eventoRegistroBitacora= new Object();
		$scope.eventoRegistroBitacora.idEvento=$rootScope.eventoEditSelected.idEvento;
		console.log("SAVE VALUES");
		console.log($scope);
		$scope.eventoRegistroBitacora.bitacoras=new Array(1);
		var bitacora=new Object();
		bitacora.idRegistroMedico=$scope.regreportemedicoselected.idRegistroMedico;
		bitacora.observaciones=$scope.bitacora.observaciones;		
		$scope.eventoRegistroBitacora.bitacoras.push(bitacora);
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		console.log("SAVE VALUES");
		console.log($scope.eventoRegistroBitacora);
		$http.post('mvc/evento/guardarbitacora',$scope.eventoRegistroBitacora).
			success(function(data, status, headers, config) {
		    	  alert("Se registr� correctamente la bit�cora");
		    	  $rootScope.rowCollectionSeguimientoMedico=data.bitacoras;
		    	  $rootScope.eventoEditSelected.bitacoras=data.bitacoras;
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		    	  alert("Error al registrar la bit�cora"+data);
		    });						
		$scope.hideAltaSeguimientoMedicoForm();
	};
};
