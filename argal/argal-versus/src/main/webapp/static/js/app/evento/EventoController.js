var AltaEventoController= function($scope, $http, $timeout,$rootScope,$location,$modal){
	//OK
	$scope.datepickers = {
		dt: false,
		dtSecond: false
	}
	$scope.today = function() {
	    $scope.dt = new Date();
	    // ***** Q1  *****
	    $scope.dtSecond = new Date();
	};
	$scope.today();
	$scope.showWeeks = true;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};

	$scope.clear = function () {
		$scope.dt = null;
	};
	
	// Disable weekend selection
	$scope.disabled = function(date, mode) {
	   // return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	};

	$scope.toggleMin = function() {
	    $scope.minDate = ( $scope.minDate ) ? null : new Date();
	};
	
	$scope.toggleMin();

	$scope.open = function($event, which) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.datepickers[which]= true;
	};

	$scope.dateOptions = {
	   'year-format': "'yy'",
	   'starting-day': 1
	};

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.valueTime = new Date(0, 0, 1, 14, 57);
    $scope.valueTime2 = new Date(0, 0, 1, 14, 57);

    $scope.today();
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};
	
	$scope.updateMedTrat = function(){
		//console.log($scope.listaMedTrat);
		$rootScope.especialidadMedTratSel="";
		if ($scope.listaMedTrat.especialidades.length>0){
			for (var i=0; i<$scope.listaMedTrat.especialidades.length;i++){
				$rootScope.especialidadMedTratSel=$rootScope.especialidadMedTratSel+$scope.listaMedTrat.especialidades[i].descripcion+",";
			}
		}
		switch ($scope.listaMedTrat.tipo){
			case 1:
				$rootScope.tipoMedTratSel="RED";
				break;
			case 2:
				$rootScope.tipoMedTratSel="STAFF";
				break;
			case 3:
				$rootScope.tipoMedTratSel="INTERINO";					
				break;
			default:
				$rootScope.tipoMedTratSel="NINGUNA";
				break;
		}
		
	}
	

	
	$scope.oneAtATime = true;
	//$scope.listaTipoClienteSelected={idTipoSeguro:'',descripcion:''};		  	
	//filtro para poner todo el texto en may�sculas	
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
			$scope.antecedentes=data[3];
			$scope.medicosdictaminadores=data[4];		
			$scope.unidadesedades=[{descripcion:'A\u00d1OS'},{descripcion:'MESES'},{descripcion:'DIAS'}];
			$scope.sexodatoscombo=[{descripcion:'MASCULINO'},{descripcion:'FEMENINO'}];
			$scope.parentescos=[{descripcion:'TITULAR'},{descripcion:'HIJA'},{descripcion:'HIJO'},{descripcion:'CONYUGE'},{descripcion:'MADRE'},{descripcion:'PADRE'}];
			$rootScope.nombreLogin=$rootScope.implantRoot.nombreImplant;
			$scope.censos=[
			  			   {"idCenso":1,"nombre":"SI"},
			  			   {"idCenso":2,"nombre":"NO"},			  			 
			  			];			
			$scope.jubilados=[
				  			   {"idJubilado":1,"nombre":"SI"},
				  			   {"idJubilado":2,"nombre":"NO"},			  			 
				  			];
			$scope.condicionesasegurados=[
				  			   {"idCondicion":1,"nombre":"ACTIVO"},
				  			   {"idCondicion":2,"nombre":"JUBILADO"},			  			
				  			   {"idCondicion":3,"nombre":"EX-EMPLEADO"}
				  			];
			$scope.tiposparto=[
				  			   {"idTipoParto":1,"descripcion":"NATURAL"},
				  			   {"idTipoParto":2,"descripcion":"CES\u00c1REA"},			  			 
				  			];
			$http.post('mvc/evento/getmedicostratantes').
					success(function(data, status, headers, config) {
						$scope.medictratantes=data;			  
						//console.log(data)
					}).
					error(function(data, status, headers, config) {
						alert(data);
					});
		}).error(function(data, status, headers, config) {
		}
	);
	    
	$scope.showTipoSeguroView = function(id) {		
	   	//console.log($scope.listaTipoClienteSelected.idTipoSeguro);
	   	$scope.evento="";
	   	$scope.evento.tipoSeguro=$scope.listaTipoClienteSelected.idTipoSeguro;
	   	if ($scope.listaTipoClienteSelected.idTipoSeguro==1){
	   		$rootScope.idtipoSeguroSel="1";
	   		document.getElementById("tipoSeguroPersonalDIV").style.display = 'block';
	   		document.getElementById("tipoSeguroGastosMayoresDIV").style.display = 'none';
	   	}
	   	else{
	   		$rootScope.tipoSeguroSel=" Aseguradora";
	   		$rootScope.idtipoSeguroSel="2";
	   		document.getElementById("tipoSeguroPersonalDIV").style.display = 'none';
	   		document.getElementById("tipoSeguroGastosMayoresDIV").style.display = 'block';
	   	}
	   	
	};
	
	$scope.isUnchanged = function(evento) {
	};          
	
	$scope.cargarHosp = function (){	    
	};	   
	$scope.cancEvento = function (){
		$location.path('/showeventos');
	};
	$scope.guardarEvento = function (){
				$scope.eventoInsert=new Object();
				//Datos generales en ambos tipos de seguro
				$scope.eventoInsert.horaIngreso=$scope.valueTime;   
				$scope.eventoInsert.folioArgal=$scope.evento.folioArgal; 
			    $scope.eventoInsert.folioHospital=$scope.evento.folioHospitalario;    
			    $scope.eventoInsert.implant=new Object();
			    $scope.eventoInsert.implant.idImplant=100;
			    $scope.eventoInsert.hospital=new Object();
			    $scope.eventoInsert.hospital.idHospital=$rootScope.hospitalSeleccionadoId;	    
			    $scope.eventoInsert.fechaIngreso=$scope.dt;
			    $scope.eventoInsert.medicoTratante=new Object();			    
			    $scope.eventoInsert.cliente=new Object();
			    $scope.eventoInsert.cliente.idCliente=$scope.listaClienteSelected.idCliente;	    	
			    $scope.eventoInsert.tipoSeguro=new Object();
			    $scope.eventoInsert.tipoSeguro.idTipoSeguro=$scope.listaTipoClienteSelected.idTipoSeguro;
			    $scope.eventoInsert.tipoEvento=new Object();
			    $scope.eventoInsert.tipoEvento.idTipoEvento=$scope.listaTipoEventoSelected.idTipoEvento;			    
  			   // if ($scope.eventoInsert.tipoSeguro.idTipoSeguro==2){ 	
			    	$scope.eventoInsert.medicoTratante.nombre=$scope.listaMedTrat.nombre;
			    	$scope.eventoInsert.medicoTratante.idMedicoTratante=$scope.listaMedTrat.idMedicoTratante;
			   //  }
			   // else{
			    	//$scope.eventoInsert.medicoTratante.nombre="";
			    //	$scope.eventoInsert.medicoTratante.idMedicoTratante=0;
			   // }
			    $scope.eventoInsert.diagnosticoIngreso1=new Object();
			    $scope.eventoInsert.diagnosticoIngreso2=new Object();
			    $scope.eventoInsert.diagnosticoIngreso1.idIcd=$rootScope.idtipoIcd1;
			    $scope.eventoInsert.diagnosticoIngreso2.idIcd=$rootScope.idtipoIcd2;
			    if ($scope.eventoInsert.tipoSeguro.idTipoSeguro==2)
			    	$scope.eventoInsert.medicoDictaminador=$scope.medDictaminadorSelected.nombreImplant+ " " +$scope.medDictaminadorSelected.appImplant + " "+ $scope.medDictaminadorSelected.apmImplant;
			    $scope.eventoInsert.antecedente= new Object();
			    $scope.eventoInsert.antecedente.idAntecedente=$scope.antecedenteSelected.idAntecedente;
			    $scope.eventoInsert.antecedente.descripcion=$scope.antecedenteSelected.nombre;
			  
			    //$rootScope.idtipoIcd1=id;		
			
			    if ($scope.eventoInsert.tipoSeguro.idTipoSeguro==1){
			    	$scope.eventoInsert.registroSeguroPersonal=new Object();
			    	$scope.eventoInsert.registroSeguroPersonal.numeroNomina=$scope.evento.registroSeguroPersonal.numeroNomina;
			    	$scope.eventoInsert.registroSeguroPersonal.institucion=$scope.evento.registroSeguroPersonal.institucion;
			    	$scope.eventoInsert.registroSeguroPersonal.numAutorizacion=$scope.evento.registroSeguroPersonal.numAutorizacion;
			    	$scope.eventoInsert.registroSeguroPersonal.censo=$scope.censoSelected.nombre;
			    	$scope.eventoInsert.registroSeguroPersonal.condicionPaciente=$scope.condicionesaseguradosSelected.nombre;
			    	$scope.eventoInsert.registroSeguroPersonal.nombreTitular=$scope.evento.registroSeguroPersonal.nombreTitular.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.appTitular=$scope.evento.registroSeguroPersonal.appTitular.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.apmTitular=$scope.evento.registroSeguroPersonal.apmTitular.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.nombrePaciente=$scope.evento.registroSeguroPersonal.nombrePaciente.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.appPaciente=$scope.evento.registroSeguroPersonal.appPaciente.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.apmPaciente=$scope.evento.registroSeguroPersonal.apmPaciente.toUpperCase();
			    	$scope.eventoInsert.registroSeguroPersonal.sexoPaciente=$scope.sexoPacienteSegPersonalSelected.descripcion;
		    		$scope.eventoInsert.registroSeguroPersonal.relacionPaciente=$scope.parentescoPacienteSegPersonalSelected.descripcion;
			    	if ($scope.listaTipoEventoSelected.idTipoEvento!=4){
			    		$scope.eventoInsert.registroSeguroPersonal.edadPaciente=$scope.evento.registroSeguroPersonal.edadPaciente;
			    		$scope.eventoInsert.registroSeguroPersonal.unidadEdadPaciente=$scope.unidadEdadPacienteSegPersonalSelected.descripcion;			    		
			    	}
			    	else{
			    		//console.log("DATOS;;;;");
			    		//console.log($scope);			    		
			    		$scope.eventoInsert.registroSeguroPersonal.condicionPaciente="";
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoFechaNacimiento=$scope.dtSecond;
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoHoraNacimiento=$scope.valueTime; 
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoTipoParto=$scope.tipoPartoSelected.descripcion;			    																	
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoTalla=$scope.evento.registroSeguroPersonal.nacimientoTalla;
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoPeso=$scope.evento.registroSeguroPersonal.nacimientoPeso;
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoApgar=$scope.evento.registroSeguroPersonal.nacimientoApgar;
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoSilverman=$scope.evento.registroSeguroPersonal.nacimientoSilverman;
			    		$scope.eventoInsert.registroSeguroPersonal.nacimientoMedicoTratante=$scope.listaMedTrat2.nombre;
			    	}
			    }
			    if ($scope.eventoInsert.tipoSeguro.idTipoSeguro==2){
			    	$scope.eventoInsert.registroGastosMayores=new Object();	    		
			    	$scope.eventoInsert.registroGastosMayores.numeroPoliza=$scope.evento.registroGastosMayores.numeroPoliza;	    		    			    		
			    	$scope.eventoInsert.registroGastosMayores.deduciblePoliza=$scope.evento.registroGastosMayores.deduciblePoliza;
			    	$scope.eventoInsert.registroGastosMayores.coaseguroMedico=$scope.evento.registroGastosMayores.coaseguroMedico;
			    	$scope.eventoInsert.registroGastosMayores.sumaAsegurada=$scope.evento.registroGastosMayores.sumaAsegurada;
			    	$scope.eventoInsert.registroGastosMayores.montoCartaAutInicial=$scope.evento.registroGastosMayores.montoCartaAutInicial;
			    	$scope.eventoInsert.registroGastosMayores.tablaHonorariosMedicos=$scope.evento.registroGastosMayores.tablaHonorariosMedicos;
			    	$scope.eventoInsert.registroGastosMayores.nombreTitular=$scope.evento.registroGastosMayores.nombreTitular;
			    	$scope.eventoInsert.registroGastosMayores.appTitular=$scope.evento.registroGastosMayores.appTitular;
			    	$scope.eventoInsert.registroGastosMayores.apmTitular=$scope.evento.registroGastosMayores.apmTitular;
			    	$scope.eventoInsert.registroGastosMayores.nombrePaciente=$scope.evento.registroGastosMayores.nombrePaciente;
			    	$scope.eventoInsert.registroGastosMayores.appPaciente=$scope.evento.registroGastosMayores.appPaciente;
			    	$scope.eventoInsert.registroGastosMayores.apmPaciente=$scope.evento.registroGastosMayores.apmPaciente;
			    	if ($scope.listaTipoEventoSelected.idTipoEvento!=4){
			    		$scope.eventoInsert.registroGastosMayores.edadPaciente=$scope.evento.registroGastosMayores.edadPaciente;
			    		$scope.eventoInsert.registroGastosMayores.unidadEdadPaciente=$scope.unidadEdadPacienteGastosMayoresSelected.descripcion;
			    		$scope.eventoInsert.registroGastosMayores.sexoPaciente=$scope.sexoPacienteGastosMayoresSelected.descripcion;
			    		$scope.eventoInsert.registroGastosMayores.relacionPaciente=$scope.parentescoPacienteGastosMayoresSelected.descripcion;
			    	}
			    	else{
			    		//console.log("DATOS;;;;");
			    		//console.log($scope);			    		
			    		$scope.eventoInsert.registroGastosMayores.nacimientoFechaNacimiento=$scope.dtSecond;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoHoraNacimiento=$scope.valueTime;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoTipoParto=$scope.tipoPartoSelected2.descripcion;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoTalla=$scope.evento.registroGastosMayores.nacimientoTalla;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoPeso=$scope.evento.registroGastosMayores.nacimientoPeso;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoApgar=$scope.evento.registroGastosMayores.nacimientoApgar;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoSilverman=$scope.evento.registroGastosMayores.nacimientoSilverman;
			    		$scope.eventoInsert.registroGastosMayores.nacimientoMedicoTratante=$scope.listaMedTrat3.nombre;
			    	}	    			    		
			    }
			    $scope.eventoInsert.numHabitacion=$scope.evento.numHabitacion;
			    //alert($scope.evento.numHabitacion);
			    delete Array.prototype.toJSON;	    	
			    $http.defaults.headers.post["Content-Type"] = "application/json";
			    $http.post('mvc/evento/guardarevento',$scope.eventoInsert).
			    		success(function(data, status, headers, config) {
			    			alert("Se registr\u00f3 correctamente el evento!");
			    			$location.path('/showeventos');
			    		}).error(function(data, status, headers, config) {
			    	    // called asynchronously if an error occurs
			    	    // or server returns response with an error status.
			    		  alert(data);
			  });
		
	};	    
	    
	$scope.openAddIcd = function (msg,url,idT) {
		$rootScope.idT=idT;
		var modalInstance = $modal.open({
	    	windowClass:'modal1',
			templateUrl: url,
			backdrop: 'static',
	        controller: Cat_ICD_Controller,
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
	    
	$scope.openAltaMedicoTratante = function (msg,url) {
	   	//$scope.espmedictratantes.push({"idEspMedicoTratante":1,"nombre":"GENETICA"});
	    var modalInstanceAMT = $modal.open({
	    	backdrop: 'static',
	    	windowClass:'modal1',
	    	templateUrl: url,
	            controller: ModalMedicoTratanteInstanceCtrl,
	            resolve: {
	          	  mensaje: function () {
	                    return msg;
	                  }
	            }
	    });
	    modalInstanceAMT.result.then(function (selectedItem) {          
	        	}, function () {   	        			        			        		
	        		$http.post('mvc/evento/getmedicostratantes').
					success(function(data, status, headers, config) {
						$scope.medictratantes=data;			  
						alert("Se actualiz\u00f3 la lista de medicos tratantes!");
					}).
					error(function(data, status, headers, config) {
						alert(data);
					});	        		
	    });
	};
	    
	$scope.openEditarMedicoTratante = function (msg,url) {
	    var modalInstanceAMT = $modal.open({
	    	backdrop: 'static',
	    	windowClass:'modal1',
	        templateUrl: url,
	        controller: AltaMedTratanteInstanceCtrl,
	        resolve: {
				mensaje: function () {
						return msg;
	            }
			}
	    });
	    modalInstanceAMT.result.then(function (selectedItem) {          
	        }, function () {          
	    });
	};
};


var EditarEventoController= function($scope, $http, $timeout,$rootScope,$location,$modal){
	console.log("Editando el evento:");
	console.log($rootScope.eventoEditSelected);
	$rootScope.setLoading(false);
	$scope.eventoEditSelected=$rootScope.eventoEditSelected;
	if ($rootScope.eventoEditSelected.horaIngreso!="" && $rootScope.eventoEditSelected.horaIngreso!=null)
		$scope.valueTime=$rootScope.eventoEditSelected.horaIngreso;
	//console.log("FECHA->>>>>>"+$rootScope.eventoEditSelected.fechaIngreso);

	//console.log("FECHA->>>>>>"+new Date($rootScope.eventoEditSelected.fechaIngreso));
	if ($rootScope.eventoEditSelected.diagnosticoIngreso1!=null){
		$scope.tipoIcd1=$rootScope.eventoEditSelected.diagnosticoIngreso1.descripcion;
		$rootScope.idtipoIcd1=$rootScope.eventoEditSelected.diagnosticoIngreso1.idIcd;
	}
	if ($rootScope.eventoEditSelected.diagnosticoIngreso2!=null){
		$scope.tipoIcd2=$rootScope.eventoEditSelected.diagnosticoIngreso2.descripcion;
		$rootScope.idtipoIcd2=$rootScope.eventoEditSelected.diagnosticoIngreso2.idIcd;
	}

	//Cargando los valores independientes
	$rootScope.hospitalSeleccionadoId=$rootScope.eventoEditSelected.hospital.idHospital;
	$rootScope.hospitalSeleccionadoTxt=$scope.eventoEditSelected.hospital.nombreHospital;
	
	//console.log($scope);
	$scope.today = function() {
		$scope.dt = new Date();
		$scope.dt2 = new Date();
	};
	$scope.today();
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};
	
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
	$scope.open2 = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened2 = true;    
	};
	
	
	$scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	};
 
	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
	$scope.format = $scope.formats[0];
	
	$scope.oneAtATime = true;
	//$scope.listaTipoClienteSelected={idTipoSeguro:'',descripcion:''};		  	
	//filtro para poner todo el texto en may�sculas	
	$scope.master = {};		
	$scope.message = 'DATOS DEL INGRESO';
	$scope.mensajeError ="";
	$scope.companias={};
	$scope.hospSeleccionado="ok"+$rootScope.hospitalSeleccionado;
	$scope.tipoTratSl=$rootScope.tipoTratSl;	    
	if ($rootScope.eventoEditSelected.fechaIngreso!="" && $rootScope.eventoEditSelected.fechaIngreso!=null)
		$scope.dt=new Date($rootScope.eventoEditSelected.fechaIngreso);
	
	
	$http.post('mvc/evento/getcombovalues').
		success(function(data, status, headers, config) {					 
			$scope.clientes=data[0];
			$scope.tiposseguro=data[1];
			$scope.tiposevento=data[2];
			$scope.medicosdictaminadores=data[4];
			$scope.unidadesedades=[{descripcion:'A\u00d1OS'},{descripcion:'MESES'},{descripcion:'DIAS'}];
			$scope.sexodatoscombo=[{descripcion:'MASCULINO'},{descripcion:'FEMENINO'}];
			$scope.parentescos=[{descripcion:'TITULAR'},{descripcion:'HIJA'},{descripcion:'HIJO'},{descripcion:'CONYUGE'},{descripcion:'MADRE'},{descripcion:'PADRE'}];
			$rootScope.nombreLogin="FAUSTO ORTEGA";
			$scope.antecedentes=data[3];				
						
			//console.log($scope.eventoEditSelected.antecedente);
			//Llenar los combos con los valores
			if ($scope.eventoEditSelected.tipoEvento!=null){
				for (i=0;i<$scope.tiposevento.length;i++){
					if ($scope.tiposevento[i].idTipoEvento==$scope.eventoEditSelected.tipoEvento.idTipoEvento){
						$scope.listaTipoEventoSelected=$scope.tiposevento[i];
						break;
					}
				}				
			}
			if ($scope.eventoEditSelected.tipoSeguro!=null){
				for (i=0;i<$scope.tiposseguro.length;i++){
					if ($scope.tiposseguro[i].idTipoSeguro==$scope.eventoEditSelected.tipoSeguro.idTipoSeguro){
						$scope.listaTipoClienteSelected=$scope.tiposseguro[i];
						break;
					}
				}				
			}
			if ($scope.eventoEditSelected.cliente!=null){				
				for (i=0;i<$scope.clientes.length;i++){
					if ($scope.clientes[i].idCliente==$scope.eventoEditSelected.cliente.idCliente){
						$scope.listaClienteSelected=$scope.clientes[i];
						break;
					}
				}								
			}
			if ($scope.eventoEditSelected.antecedente!=null){
				for (i=0;i<$scope.antecedentes.length;i++){
					if ($scope.antecedentes[i].idAntecedente==$scope.eventoEditSelected.antecedente.idAntecedente){
						$scope.antecedenteSel=$scope.antecedentes[i];
						break;
					}
				}				
			}
						
			$scope.showTipoSeguroView();
			
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
			$scope.censos=[
			  			   {"idCenso":1,"nombre":"SI"},
			  			   {"idCenso":2,"nombre":"NO"},			  			 
			  			  ];			
			$scope.jubilados=[
			               {"idJubilado":1,"nombre":"SI"},
			  			   {"idJubilado":2,"nombre":"NO"},			  			 
			];
			$scope.condicionesasegurados=[
			               {"idCondicion":1,"nombre":"ACTIVO"},
			  			   {"idCondicion":2,"nombre":"JUBILADO"},			  			
			  			   {"idCondicion":3,"nombre":"EX-EMPLEADO"}
			];

			$scope.tiposparto=[
						   {"idTipoParto":1,"descripcion":"NATURAL"},
			 			   {"idTipoParto":2,"descripcion":"CES\u00c1REA"},			  			 
			];
			
			$scope.espmedictratantes=[	    
			  			   {"idEspMedicoTratante":1,"nombre":"MAXILOFACIAL"},	    	   
			  			   //{"idEspMedicoTratante":1,"nombre":"GENETICA"},
			  			   {"idEspMedicoTratante":1,"nombre":"TOXICOLOGIA"}	    	   
			];

			if ($scope.eventoEditSelected.tipoEvento!=null){
				if ($scope.eventoEditSelected.tipoEvento.idTipoEvento==4){								
					//COMPLEMENTAR!!
					
					for (i=0;i<$scope.tiposparto.length;i++){
						//console.log($scope.tiposparto[i].descripcion);
						//console.log($scope.eventoEditSelected.registroSeguroPersonal.tipoParto);
						$scope.dt2=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoFechaNacimiento;
						$scope.valueTime2=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoHoraNacimiento;
						if($scope.eventoEditSelected.registroSeguroPersonal!=null){
							if ($scope.tiposparto[i].descripcion==$scope.eventoEditSelected.registroSeguroPersonal.nacimientoTipoParto){
									$scope.tipoPartoSelected=$scope.tiposparto[i];
								break;
							}
						}
						if($scope.eventoEditSelected.registroGastosMayores!=null){
							if($scope.eventoEditSelected.registroGastosMayores!=null){
								if ($scope.tiposparto[i].descripcion==$scope.eventoEditSelected.registroGastosMayores.nacimientoTipoParto){
									$scope.tipoPartoSelected=$scope.tiposparto[i];
									break;
								}
							}
						}									
					}												
				} 
			}
			
			//registroGastosMayores
			if ($scope.eventoEditSelected.registroGastosMayores!=null){
				for (i=0;i<$scope.parentescos.length;i++){
					if ($scope.parentescos[i].descripcion==$scope.eventoEditSelected.registroGastosMayores.relacionPaciente){						
						$scope.parentescoPacienteGastosMayoresSelected=$scope.parentescos[i];
						break;
					}
				}
				for (i=0;i<$scope.antecedentes.length;i++){
					if ($scope.antecedentes[i].idAntecedente==$scope.eventoEditSelected.antecedente.idAntecedente){						
						$scope.antecedenteSelected=$scope.antecedentes[i];
						break;
					}
				}
				for (i=0;i<$scope.medicosdictaminadores.length;i++){
					if (($scope.medicosdictaminadores[i].nombreImplant + ' ' + $scope.medicosdictaminadores[i].appImplant + ' ' + $scope.medicosdictaminadores[i].apmImplant)		
						==$scope.eventoEditSelected.medicoDictaminador){						
						$scope.medDictaminadorSelected=$scope.medicosdictaminadores[i];
						break;
					}
				}

				for (i=0;i<$scope.unidadesedades.length;i++){
					if ($scope.unidadesedades[i].descripcion==$scope.eventoEditSelected.registroGastosMayores.unidadEdadPaciente){						
						$scope.unidadEdadPacienteGastosMayoresSelected=$scope.unidadesedades[i];
						break;
					}
				}
				for (i=0;i<$scope.sexodatoscombo.length;i++){
					if ($scope.sexodatoscombo[i].descripcion==$scope.eventoEditSelected.registroGastosMayores.sexoPaciente){						
						$scope.sexoPacienteGastosMayoresSelected=$scope.sexodatoscombo[i];
						break;
					}
				}
				//unidadEdadPacienteSegPersonalSelected "sexoPacienteSegPersonalSelected" unidadEdadPacienteGastosMayoresSelected	
				//sexoPacienteGastosMayoresSelected
			}
			if ($scope.eventoEditSelected.registroSeguroPersonal!=null){
				for (i=0;i<$scope.condicionesasegurados.length;i++){
					if ($scope.condicionesasegurados[i].nombre==$scope.eventoEditSelected.registroSeguroPersonal.condicionPaciente){						
						$scope.condicionesaseguradosSelected=$scope.condicionesasegurados[i];
						break;
					}
				}
				for (i=0;i<$scope.censos.length;i++){
					if ($scope.censos[i].nombre==$scope.eventoEditSelected.registroSeguroPersonal.censo){						
						$scope.censoSelected=$scope.censos[i];
						break;
					}
				}
	
				for (i=0;i<$scope.parentescos.length;i++){
					if ($scope.parentescos[i].descripcion==$scope.eventoEditSelected.registroSeguroPersonal.relacionPaciente){						
						$scope.parentescoPacienteSegPersonalSelected=$scope.parentescos[i];
						break;
					}
				}
				for (i=0;i<$scope.unidadesedades.length;i++){
					if ($scope.unidadesedades[i].descripcion==$scope.eventoEditSelected.registroSeguroPersonal.unidadEdadPaciente){						
						$scope.unidadEdadPacienteSegPersonalSelected=$scope.unidadesedades[i];
						break;
					}
				}
				for (i=0;i<$scope.sexodatoscombo.length;i++){
					if ($scope.sexodatoscombo[i].descripcion==$scope.eventoEditSelected.registroSeguroPersonal.sexoPaciente){						
						$scope.sexoPacienteSegPersonalSelected=$scope.sexodatoscombo[i];
						break;
					}
				}
				
			}
			$http.post('mvc/evento/getmedicostratantes').
				success(function(data, status, headers, config) {
					$scope.medictratantes=data;					
					if ($scope.eventoEditSelected.medicoTratante!=null){
						for (i=0;i<$scope.medictratantes.length;i++){
							//console.log($scope.medictratantes[i].nombre)
							//console.log($scope.eventoEditSelected.medicoTratante.nombre)		
							if ($scope.medictratantes[i].nombre==$scope.eventoEditSelected.medicoTratante.nombre){
								$scope.listaMedTrat=$scope.medictratantes[i];
								$scope.especialidadMedTratanteEdit=$scope.medictratantes[i].especialidadMedTratante;
								break;
							}
						}				
					}
					if ($scope.eventoEditSelected.tipoEvento.idTipoEvento==4){
						for (i=0;i<$scope.medictratantes.length;i++){						
							console.log($scope.eventoEditSelected)
							if ($scope.eventoEditSelected.registroSeguroPersonal!=null){
								console.log($scope.eventoEditSelected.registroSeguroPersonal.nacimientoMedicoTratante)
								if ($scope.medictratantes[i].nombre==$scope.eventoEditSelected.registroSeguroPersonal.nacimientoMedicoTratante){									
									$scope.listaMedTrat2=$scope.medictratantes[i];
									//$scope.especialidadMedTratanteEdit=$scope.medictratantes[i].especialidadMedTratante;
									break;
								}	
							}
							if ($scope.eventoEditSelected.registroGastosMayores!=null){
								if ($scope.medictratantes[i].nombre==$scope.eventoEditSelected.registroGastosMayores.nacimientoMedicoTratante){
									$scope.listaMedTrat2=$scope.medictratantes[i];
									//$scope.especialidadMedTratanteEdit=$scope.medictratantes[i].especialidadMedTratante;
									break;
								}	
							}
						}
					}										
				}).
				error(function(data, status, headers, config) {
					alert("ERROR!")
				});
			}).error(function(data, status, headers, config) {
		}
	);
	    
	$scope.showTipoSeguroView = function(id) {
		//console.log("Show view tipo seguro");
	   	//console.log($scope.listaTipoClienteSelected.idTipoSeguro);
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
	   	//$scope.mensajeError ="* Los campos de correo electr�nico deben escribirse correctamente";
	    //return angular.equals(evento, $scope.master);        
	};          
	
	$scope.cargarHosp = function (){	    
	};	   
	
	$scope.guardarEventoEditar = function (){
		var valida=true;
		if (valida){	
			$scope.eventoEditar=new Object();	    	
		    $scope.eventoEditar.implant=new Object();
		    $scope.eventoEditar.implant.idImplant=100;
		    $scope.eventoEditar.hospital=new Object();
		    $scope.eventoEditar.hospital.idHospital=$rootScope.hospitalSeleccionadoId;	    	    	   
		    $scope.eventoEditar.cliente=new Object();
		    $scope.eventoEditar.cliente.idCliente=$scope.listaClienteSelected.idCliente;	    	
		    $scope.eventoEditar.tipoSeguro=new Object();
		    $scope.eventoEditar.tipoSeguro.idTipoSeguro=$scope.listaTipoClienteSelected.idTipoSeguro;
		    $scope.eventoEditar.tipoEvento=new Object();
		    $scope.eventoEditar.tipoEvento.idTipoEvento=$scope.listaTipoEventoSelected.idTipoEvento;
		    $scope.eventoEditar.medicoTratante=new Object();		    
		    $scope.eventoEditar.medicoTratante.nombre=$scope.listaMedTrat.nombre;
		    $scope.eventoEditar.medicoTratante.idMedicoTratante=$scope.listaMedTrat.idMedicoTratante;
		    if ($scope.eventoEditar.tipoSeguro.idTipoSeguro==1){
		    	$scope.eventoEditar.registroSeguroPersonal=new Object();
		    	$scope.eventoEditar.registroSeguroPersonal.censo=$scope.censoSelected.nombre;
		    	$scope.eventoEditar.registroSeguroPersonal.condicionPaciente=$scope.condicionesaseguradosSelected.nombre;
		    	$scope.eventoEditar.registroSeguroPersonal.idRegistroSeguroPersonal=$rootScope.eventoEditSelected.registroSeguroPersonal.idRegistroSeguroPersonal;			    
		    	$scope.eventoEditar.registroSeguroPersonal.numeroNomina=$scope.eventoEditSelected.registroSeguroPersonal.numeroNomina;
		    	$scope.eventoEditar.registroSeguroPersonal.institucion=$scope.eventoEditSelected.registroSeguroPersonal.institucion;
		    	$scope.eventoEditar.registroSeguroPersonal.numAutorizacion=$scope.eventoEditSelected.registroSeguroPersonal.numAutorizacion;
		    	$scope.eventoEditar.registroSeguroPersonal.nombreTitular=$scope.eventoEditSelected.registroSeguroPersonal.nombreTitular.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.appTitular=$scope.eventoEditSelected.registroSeguroPersonal.appTitular.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.apmTitular=$scope.eventoEditSelected.registroSeguroPersonal.apmTitular.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.nombrePaciente=$scope.eventoEditSelected.registroSeguroPersonal.nombrePaciente.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.appPaciente=$scope.eventoEditSelected.registroSeguroPersonal.appPaciente.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.apmPaciente=$scope.eventoEditSelected.registroSeguroPersonal.apmPaciente.toUpperCase();
		    	$scope.eventoEditar.registroSeguroPersonal.sexoPaciente=$scope.sexoPacienteSegPersonalSelected.descripcion;
		    	$scope.eventoEditar.registroSeguroPersonal.relacionPaciente=$scope.parentescoPacienteSegPersonalSelected.descripcion;
		    	if ($scope.listaTipoEventoSelected.idTipoEvento!=4){
		    		$scope.eventoEditar.registroSeguroPersonal.edadPaciente=$scope.eventoEditSelected.registroSeguroPersonal.edadPaciente;
			    	$scope.eventoEditar.registroSeguroPersonal.unidadEdadPaciente=$scope.unidadEdadPacienteSegPersonalSelected.descripcion;			    	
			    }
		    	else{
		    		//console.log("DATOS;;;;");
		    		//console.log($scope);			    		
		    		$scope.eventoEditar.registroSeguroPersonal.condicionPaciente="";
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoFechaNacimiento=$scope.dt2;
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoHoraNacimiento=$scope.valueTime; 
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoTipoParto=$scope.tipoPartoSelected.descripcion;			    																	
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoTalla=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoTalla;
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoPeso=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoPeso;
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoApgar=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoApgar;
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoSilverman=$scope.eventoEditSelected.registroSeguroPersonal.nacimientoSilverman;
		    		$scope.eventoEditar.registroSeguroPersonal.nacimientoMedicoTratante=$scope.listaMedTrat2.nombre;
		    	}
		    }
		    if ($scope.eventoEditar.tipoSeguro.idTipoSeguro==2){
		    	$scope.eventoEditar.registroGastosMayores=new Object();	    		
		    	$scope.eventoEditar.registroGastosMayores.idRegistroGastosMayores=$rootScope.eventoEditSelected.registroGastosMayores.idRegistroGastosMayores;			    
		    	$scope.eventoEditar.registroGastosMayores.numeroPoliza=$scope.eventoEditSelected.registroGastosMayores.numeroPoliza;	    		    			    		
		    	$scope.eventoEditar.registroGastosMayores.deduciblePoliza=$scope.eventoEditSelected.registroGastosMayores.deduciblePoliza;
		    	$scope.eventoEditar.registroGastosMayores.coaseguroMedico=$scope.eventoEditSelected.registroGastosMayores.coaseguroMedico;
		    	$scope.eventoEditar.registroGastosMayores.sumaAsegurada=$scope.eventoEditSelected.registroGastosMayores.sumaAsegurada;
		    	$scope.eventoEditar.registroGastosMayores.montoCartaAutInicial=$scope.eventoEditSelected.registroGastosMayores.montoCartaAutInicial;
		    	$scope.eventoEditar.registroGastosMayores.tablaHonorariosMedicos=$scope.eventoEditSelected.registroGastosMayores.tablaHonorariosMedicos;
		    	$scope.eventoEditar.registroGastosMayores.nombreTitular=$scope.eventoEditSelected.registroGastosMayores.nombreTitular;
		    	$scope.eventoEditar.registroGastosMayores.appTitular=$scope.eventoEditSelected.registroGastosMayores.appTitular;
		    	$scope.eventoEditar.registroGastosMayores.apmTitular=$scope.eventoEditSelected.registroGastosMayores.apmTitular;
		    	$scope.eventoEditar.registroGastosMayores.nombrePaciente=$scope.eventoEditSelected.registroGastosMayores.nombrePaciente;
		    	$scope.eventoEditar.registroGastosMayores.appPaciente=$scope.eventoEditSelected.registroGastosMayores.appPaciente;
		    	$scope.eventoEditar.registroGastosMayores.apmPaciente=$scope.eventoEditSelected.registroGastosMayores.apmPaciente;
		    	if ($scope.listaTipoEventoSelected.idTipoEvento!=4){
		    		$scope.eventoEditar.registroGastosMayores.edadPaciente=$scope.eventoEditSelected.registroGastosMayores.edadPaciente;
			    	$scope.eventoEditar.registroGastosMayores.unidadEdadPaciente=$scope.unidadEdadPacienteGastosMayoresSelected.descripcion;;
			    	$scope.eventoEditar.registroGastosMayores.sexoPaciente=$scope.sexoPacienteGastosMayoresSelected.descripcion;	    		
			    	$scope.eventoEditar.registroGastosMayores.relacionPaciente=$scope.parentescoPacienteGastosMayoresSelected.descripcion;
			    }
		    	else{
		    		//console.log("DATOS;;;;");
		    		//console.log($scope);			    		
		    		$scope.eventoEditar.registroGastosMayores.nacimientoFechaNacimiento=$scope.dtSecond;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoHoraNacimiento=$scope.valueTime;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoTipoParto=$scope.tipoPartoSelected2.descripcion;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoTalla=$scope.eventoEditSelected.registroGastosMayores.nacimientoTalla;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoPeso=$scope.eventoEditSelected.registroGastosMayores.nacimientoPeso;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoApgar=$scope.eventoEditSelected.registroGastosMayores.nacimientoApgar;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoSilverman=$scope.eventoEditSelected.registroGastosMayores.nacimientoSilverman;
		    		$scope.eventoEditar.registroGastosMayores.nacimientoMedicoTratante=$scope.listaMedTrat2.nombre;
		    	}	
		    }
		    $scope.eventoEditar.cliente=new Object();
		    $scope.eventoEditar.cliente.idCliente=$scope.listaClienteSelected.idCliente;
		    $scope.eventoEditar.antecedente=new Object();
		    $scope.eventoEditar.antecedente=$scope.antecedenteSelected;
		    
		    $scope.eventoEditar.diagnosticoIngreso1=new Object();
		    $scope.eventoEditar.diagnosticoIngreso2=new Object();
		    $scope.eventoEditar.diagnosticoIngreso1.idIcd=$rootScope.idtipoIcd1;
		    $scope.eventoEditar.diagnosticoIngreso2.idIcd=$rootScope.idtipoIcd2;
		    $scope.eventoEditar.idEvento=$rootScope.eventoEditSelected.idEvento;
		    $scope.eventoEditar.numHabitacion=$scope.eventoEditSelected.numHabitacion;
		    $scope.eventoEditar.folioArgal=$scope.eventoEditSelected.folioArgal;
		    $scope.eventoEditar.folioHospital=$scope.eventoEditSelected.folioHospital;
		    
		    $scope.eventoEditar.fechaIngreso=$scope.dt;
		    $scope.eventoEditar.horaIngreso=$scope.valueTime;

		    if ($scope.eventoEditar.tipoSeguro.idTipoSeguro==2)
		    	$scope.eventoEditar.medicoDictaminador=$scope.medDictaminadorSelected.nombreImplant+ " " +$scope.medDictaminadorSelected.appImplant + " "+ $scope.medDictaminadorSelected.apmImplant;
		    $scope.eventoEditar.antecedente= new Object();
		    if ($scope.eventoEditar.tipoSeguro.idTipoSeguro!=2 && $scope.antecedenteSelected!="" && $scope.antecedenteSelected!=null){
		    	$scope.eventoEditar.antecedente.idAntecedente=$scope.antecedenteSelected.idAntecedente;
		    	$scope.eventoEditar.antecedente.descripcion=$scope.antecedenteSelected.nombre;
		    }
		    //console.log("GUARDANDO!!");
		    //console.log($scope);
	
		    delete Array.prototype.toJSON;	    	
		    $http.defaults.headers.post["Content-Type"] = "application/json";
		    $http.post('mvc/evento/guardareventoeditar',$scope.eventoEditar).
		    	success(function(data, status, headers, config) {
		    		  alert("Se actualiz\u00f3 correctamente el evento!. Para salir presione el botón cerrar");
		    		  $location.path('/showeventos');
		    	}).error(function(data, status, headers, config) {
		    	    // called asynchronously if an error occurs
		    	    // or server returns response with an error status.
		    		  alert("Error al guardar el evento:"+data);
		    	});
		}
	};	    	
	    
	$scope.openAddIcd = function (msg,url) {
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
	    
	$scope.openAltaMedicoTratante = function (msg,url) {
	   	$scope.espmedictratantes.push({"idEspMedicoTratante":1,"nombre":"GENETICA"});
	    var modalInstanceAMT = $modal.open({
	    		backdrop: 'static',
	    		windowClass:'modal1',
	    		templateUrl: url,	    	
	            controller: AltaMedTratanteInstanceCtrl,
	            resolve: {	            		
	          	  		mensaje: function () {
	          	  			return msg;
	                  }
	            }	    		
	    });
	    modalInstanceAMT.result.then(function (selectedItem) {          
    	}, function () {   	        			        			        		
    		$http.post('mvc/evento/getmedicostratantes').
			success(function(data, status, headers, config) {
				$scope.medictratantes=data;			  
				alert("Se actualiz\u00f3 la lista de medicos tratantes!");
			}).
			error(function(data, status, headers, config) {
				alert(data);
			});	        		
    	});
	    	    
	};
	    
	$scope.openEditarMedicoTratante = function (msg,url) {
	    var modalInstanceAMT = $modal.open({
	    	backdrop: 'static',
	    	windowClass:'modal',
	        templateUrl: url,
	        controller: AltaMedTratanteInstanceCtrl,
	        resolve: {
				mensaje: function () {
						return msg;
	            }
			}
	    });
	    modalInstanceAMT.result.then(function (selectedItem) {          
	        }, function () {          
	    });
	};
};


var AltaMedTratanteInstanceCtrl = function($scope, $http, $rootScope) {

}

function LoadingController($scope, $http,$timeout) {
	  // This LoadingController is attached to the DOM inside the ng-repeat
	  // so it has access to the "group" object, which is provided by the ng-repeat

	  // We are going to watch the current scope for changes to group.anyOldValue
	  $scope.$watch(
	  
	    // This is the expression (on $scope) to watch
	    'group.anyOldValue',
	    
	    // This is the handler function that will be run "only" when the watched expression changes
	    function(value) {
	      // This first parameter is the changed value of group.anyOldValue
	      // A second parameter would hold the previous value if you wanted it
	      if ( value ) {
	        //console.log('loading');
	        $timeout(function() {
	          //console.log('loaded');
	          $scope.isLoaded = 'loaded';	          	          	          
	        }, 1000);
	      }
	    }
	    
	  );
	}


var EventoController= function ($scope,$http,$rootScope,$location,$modal,$document,ngTableParams,$filter,EventoService){

	$rootScope.setLoading = function(loading) {
		$rootScope.isLoading = loading;
	}	
	$scope.loading=1;
	$scope.fecha= function (evento){
		var d1 = new Date(evento.fechaIngreso);
		if (evento.fechaEgreso!="" && evento.fechaEgreso!=null )
			var d2 = new Date(evento.fechaEgreso);
		else
			var d2 = new Date();		
		var d1 = moment([d1.getFullYear(), d1.getMonth(), d1.getDate()]);
		var d2 = moment([d2.getFullYear(), d2.getMonth(), d2.getDate()]);
		var days = d2.diff(d1, 'days'); 		
			return days;
	};
	var bodyRef = angular.element( $document[0].body );	
    $scope.dateToday = new Date();
	$http.post('mvc/evento/gettipousuario').success(function(data, status, headers, config) {		
		if (data!=0){
			$rootScope.tipoUsuarioLogin=data;
			$scope.tipoUsuarioLogin=data;
		}
    console.log($scope.implant)
	}).error(function(data, status, headers, config) {
	});
		//$http.post('mvc/evento/geteventoslt').success(function(data, status, headers, config) {
	console.log("LLenando Eventos:");			
	$scope.loading=1;
	var data=EventoService.data;
	$rootScope.loadingEventos=1;
	$rootScope.setLoading(true);
	$scope.tableParamsEvento = new ngTableParams(	
			{
				page: 1,            // show first page
				count: 15,           // count per page
				sorting: {name:'asc'}
			},
			{
				total: 0, // length of data
				getData: function($defer, params) {
					EventoService.getData($defer,params,$scope.filter,'mvc/evento/geteventoslt');									
				}
			});
				      
			$scope.$watch("filter.$", function () {
			        $scope.tableParamsEvento.reload();
			    });

			$scope.changeSelection = function(args) {
				$rootScope.evento={};
				$rootScope.evento.idEvento=args.idEvento;	        	      
		    };	
		    
	console.log("LLenando Eventos- done");					 
	
    $scope.messageEditarImplant="Eventos";
    //keep track of selected items
    $scope.$on('selectionChange', function (event, args) {    	    
    	$rootScope.eventoEditSelected=args.item;        
    });	
    
    $scope.editarImplant = function (){    	
    	alert("Editar");
    };
    
    $scope.eliminarImplant4 = function (){    	
    	alert("Editar");
    };
    //bodyRef.addClass('ovh');
    $scope.openEventoById = function (clase,ctr,url,id) {
    	//$rootScope.eventoEditSelected=    	
    	$rootScope.setLoading(true);
    	var evento=new Object();
    	evento.idEvento=id;
    	$http.post('mvc/evento/obtenereventobyid',evento).
    		success(function(data, status, headers, config) {
    			$rootScope.eventoEditSelected=data;
    			$scope.openViewEvento(clase,ctr, url);
    		}).error(function(data, status, headers, config) {
    	});    	
   };
   
   $scope.openViewEvento = function (clase,ctr,url) {	        
 			var modalInstanceEstadoCuenta = $modal.open({
				backdrop: 'static',
				windowClass: clase,
				templateUrl: url,
				controller: ctr,
				//keyboard: false,
				resolve: {          	  
   			}
   });
   modalInstanceEstadoCuenta.result.then(function (selectedItem) {
        //bodyRef.removeClass('ovh');
   }, function () {                   
      });
   };   
};