var PolizaController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	$rootScope.regreportemedicoselected=new Object();
	$rootScope.editregreportemedicoselected = new Object();
	$scope.eventoEditSelected=$rootScope.eventoEditSelected;	
	$scope.subtotalGastosProcedente="";	
	$scope.subtotalGastosDesviado="";
	$rootScope.tipoEventoSeguroPersonalPoliza=false;
	$rootScope.tipoEventoGastosMayoresPoliza=false;
	$rootScope.showNoDataTipoEvento=false;
	console.log($scope.eventoEditSelected);
	$http.post('mvc/evento/getcombovalues').
	success(function(data, status, headers, config) {					 
		$scope.clientes=data[0];
		$scope.tiposseguro=data[1];
		$scope.tiposevento=data[2];
		$scope.unidadesedades=[{descripcion:'AÑOS'},{descripcion:'MESES'},{descripcion:'DIAS'}];
		$scope.sexodatoscombo=[{descripcion:'MASCULINO'},{descripcion:'FEMENINO'}];
		$scope.parentescos=[{descripcion:'TITULAR'},{descripcion:'HIJA'},{descripcion:'HIJO'},{descripcion:'CONYUGE'},{descripcion:'MADRE'},{descripcion:'PADRE'}];
		$rootScope.nombreLogin="FAUSTO ORTEGA";		
	}).error(function(data, status, headers, config) {
	}
	);
	
	if ($scope.eventoEditSelected.registroSeguroPersonal!=null){
		$rootScope.tipoEventoSeguroPersonalPoliza=true;
	}
	if ($scope.eventoEditSelected.registroGastosMayores!=null){
		$rootScope.tipoEventoGastosMayoresPoliza=true;
	}
	$rootScope.alertaNoTipoEvento="";
	if ($scope.eventoEditSelected.registroGastosMayores==null && $scope.eventoEditSelected.registroSeguroPersonal==null){
		$rootScope.alertaNoTipoEvento="El evento aún no cuenta con un registro de Tipo de Evento. Indique el tipo de Evento para Continuar!";
		$rootScope.showNoDataTipoEvento=true;
	}
	
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
	
	$scope.oneAtATime = true;
	//$scope.listaTipoClienteSelected={idTipoSeguro:'',descripcion:''};		  	
	//filtro para poner todo el texto en mayúsculas	
	$scope.master = {};		
	$scope.message = 'DATOS DEL INGRESO';
	$scope.mensajeError ="";
	$scope.companias={};
	$scope.hospSeleccionado="ok"+$rootScope.hospitalSeleccionado;
	$scope.tipoTratSl=$rootScope.tipoTratSl;	    
	$http.post('mvc/evento/getcombovalues').
		success(function(data, status, headers, config) {					 
			$scope.clientes=data[0];
			$scope.tiposseguro=data[1];
			$scope.tiposevento=data[2];
			$scope.unidadesedades=[{descripcion:'AÑOS'},{descripcion:'MESES'},{descripcion:'DIAS'}];
			$scope.sexodatoscombo=[{descripcion:'MASCULINO'},{descripcion:'FEMENINO'}];
			$scope.parentescos=[{descripcion:'TITULAR'},{descripcion:'HIJA'},{descripcion:'HIJO'},{descripcion:'CONYUGE'},{descripcion:'MADRE'},{descripcion:'PADRE'}];
			$rootScope.nombreLogin="FAUSTO ORTEGA";
			$scope.antecedentes=[
			       {"idAntecedente":1,"nombre":"DIABETES MELLITUS"},
			       {"idAntecedente":2,"nombre":"CARDIOPATIA ISQUEMICA"},
			       {"idAntecedente":3,"nombre":"CARDIOPATIA VALVULAR"},
			       {"idAntecedente":4,"nombre":"HIPERTENSION ARTERIAL"},
			       {"idAntecedente":5,"nombre":"NEUMOPATIA CRONICA"},
			       {"idAntecedente":6,"nombre":"ENFERMEDAD VASCULAR CEREBRAL"},
			       {"idAntecedente":7,"nombre":"NEOPLASIAS"},
			       {"idAntecedente":8,"nombre":"HEPATOPATIAS"},
			       {"idAntecedente":9,"nombre":"NEFROPATIAS"},
			       {"idAntecedente":10,"nombre":"OTROS"},
			       {"idAntecedente":10,"nombre":"NINGUNO"}
			];
			$scope.espmedictratantes=[	    
			       {"idEspMedicoTratante":1,"nombre":"MAXILOFACIAL"},	    	   
			       //{"idEspMedicoTratante":1,"nombre":"GENETICA"},
			       {"idEspMedicoTratante":1,"nombre":"TOXICOLOGIA"}	    	   
			];						
			$http.post('mvc/evento/getmedicostratantes').
			success(function(data, status, headers, config) {
				  $scope.medictratantes=data;			  
			}).
			  error(function(data, status, headers, config) {
			});
		}).error(function(data, status, headers, config) {
		}
	);
	    
	$scope.showTipoSeguroView = function(id) {
	   	console.log($scope.listaTipoClienteSelected.idTipoSeguro);
	   	$scope.evento="";
	   	$scope.evento.tipoSeguro=$scope.listaTipoClienteSelected.idTipoSeguro;
	   	if ($scope.listaTipoClienteSelected.idTipoSeguro==1){
	   		$rootScope.tipoSeguroSel=" Banco";
	   		document.getElementById("tipoSeguroPersonalDIV").style.display = 'block';
	   		document.getElementById("tipoSeguroGastosMayoresDIV").style.display = 'none';
	   	}
	   	else{
	   		$rootScope.tipoSeguroSel=" Aseguradora";
	   		document.getElementById("tipoSeguroPersonalDIV").style.display = 'none';
	   		document.getElementById("tipoSeguroGastosMayoresDIV").style.display = 'block';
	   	}
	};
	
	$scope.isUnchanged = function(evento) {
	   	//$scope.mensajeError ="* Los campos de correo electrónico deben escribirse correctamente";
	    //return angular.equals(evento, $scope.master);        
	};          

};
