function uploadJqueryFormEvidencia(){	
   $('#result').html('');
   document.getElementById("waitUpload").style.display="block";
   $("#form2").ajaxForm({
    success:function(data) { 
          //$('#result').html(data); 	  
    	  document.getElementById("waitUpload").style.display="none";
          alert("Se registr\u00f3 correctamente la evidencia");      
     },dataType:"text",
     error:function(data) {
    	 alert("error:"+data);
     }  
   }).submit();
}

var CedulaControlController = function($scope, $http, $timeout,$rootScope,$location,$modal,$filter,ngTableParams) {
    $scope.currencyVal;
    $scope.savingFile=0;
	String.prototype.splice = function(idx, rem, s) {
	    return (this.slice(0, idx) + s + this.slice(idx + Math.abs(rem)));
	};

	$scope.causascargos=[
	     	 {'idCausaCargo':1, 'descripcion':'NO UTILIZADO'},
	      	 {'idCausaCargo':2, 'descripcion':'DUPLICADO'},
	      	 {'idCausaCargo':3, 'descripcion':'NO INDICADO POR DIAGN\u00d3STICO'},
	       	 {'idCausaCargo':4, 'descripcion':'NO APARECE EN INDICACIONES M\u00c9DICAS'},
	       	 {'idCausaCargo':5, 'descripcion':'COSTO INDEBIDO'}
	];		
	$scope.tiposcargoedocuenta=[
	    	 {'idTipoCargoEdoCuenta':1, 'descripcion':'ESTADO DE CUENTA PARCIAL'},	         
	         {'idTipoCargoEdoCuenta':3, 'descripcion':'ESTADO DE CUENTA FINAL'},
	         {'idTipoCargoEdoCuenta':2, 'descripcion':'ESTADO DE CUENTA DE EGRESO'}
	];
	$scope.tiposcargos=[
	         {'idTipoCargo':1, 'descripcion':'ESTADO DE CUENTA'},	         
	         {'idTipoCargo':2, 'descripcion':'CARGOS OBSERVADOS'},
	         {'idTipoCargo':3, 'descripcion':'DESGLOSE DE GASTOS RELEVANTES'}
    ];
	$scope.areascargosEdoCuenta=[
	         {'idArea':1, 'descripcion':'ESTADO DE CUENTA PARCIAL'},
	         {'idArea':1, 'descripcion':'ESTADO DE CUENTA FINAL'}
	];
	$scope.areascargos=[
	         {'idArea':1, 'descripcion':'PISO'},
	         {'idArea':2, 'descripcion':'QUIROFANO'},
	         {'idArea':3, 'descripcion':'TERAPIA INTENSIVA, INTERMEDIA, NEONATAL'},
	         {'idArea':4, 'descripcion':'URGENCIAS'},
	         {'idArea':5, 'descripcion':'GASTOS PERSONALES'}
	 ];	
	 $scope.rubroscargos=[	       	 
	      	 {'idRubro':1, 'descripcion':'MEDICAMENTOS'},
	       	 {'idRubro':2, 'descripcion':'MATERIAL'},
	      	 {'idRubro':3, 'descripcion':'LABORATORIO Y RX'},
	      	 {'idRubro':4, 'descripcion':'TERAPIA RESPIRATORIA'},
	      	 {'idRubro':5, 'descripcion':'HABITACIONES'},
	         {'idRubro':6, 'descripcion':'ANESTESIA (MAQ Y GAS)'},
	         {'idRubro':7, 'descripcion':'RENTAS DE EQUIPOS'},
	         {'idRubro':8, 'descripcion':'INSUMOS PROVEEDOR EXTERNO'},
	         {'idRubro':9, 'descripcion':'CUBICULOS'},
	         {'idRubro':10, 'descripcion':'TERAPIA INTENSIVA'},
	         {'idRubro':11, 'descripcion':'BANCO DE SANGRE'},
	         {'idRubro':12, 'descripcion':'QUIROFANO'},
	         {'idRubro':13, 'descripcion':'TIEMPOS DE SALA'}	  
	  ];
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
	//CONSTRUYENDO OBJETOS
	$scope.eventoEditSelected=$rootScope.eventoEditSelected;
	console.log($rootScope.eventoEditSelected);
	$scope.subtotalGastosProcedente="";	
	$scope.subtotalGastosDesviado="";
	$scope.editarEdoCuenta=false;
	$scope.editarCargosObservados=false;
	$scope.editarGastosRelevantes=false;
	
	//CON ÉSTA FUNCIÓN OBTENEMOS LAS COLECCIONES PARA CADA TIPO DE CARGO
	$scope.obtenerColeccionTipoCargo=function (evento,tipoCargo){
		var arrayTmp=new Array();
		if (evento.gastos!=null){
			for (i=0;i<evento.gastos.length;i++){
				if (evento.gastos[i].idTipoCargo==tipoCargo){
					arrayTmp.push(evento.gastos[i]);
				}
			}
		}
		return arrayTmp;
	};
	$scope.coleccionEdosCuenta=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,1);
	$scope.coleccionCargosObservados=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,2);
	$scope.coleccionDesgloces=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,3);
	//Tablas para los Gastos
	$scope.tableParamsEdoCuenta = new ngTableParams({
        page: 1,            // show first page
        count: 4,          // count per page
        filter: {
            //name: 'M'       // initial filter
        },
        sorting: {
            //name: 'asc'     // initial sorting
        }
    }, {
        total: $scope.coleccionEdosCuenta.length, // length of data
        getData: function ($defer, params) {
            // use build-in angular filter
            var filteredData = params.filter() ?
                    $filter('filter')($scope.coleccionEdosCuenta, params.filter()) :
                    	$scope.coleccionEdosCuenta;
            var orderedData = params.sorting() ?
                    $filter('orderBy')(filteredData, params.orderBy()) :
                    	$scope.coleccionEdosCuenta;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });
	$scope.tableParamsCargosObservados = new ngTableParams({
        page: 1,            // show first page
        count: 4,          // count per page
        filter: {
            //name: 'M'       // initial filter
        },
        sorting: {
            //name: 'asc'     // initial sorting
        }
    }, {
        total: $scope.coleccionCargosObservados.length, // length of data
        getData: function ($defer, params) {
            // use build-in angular filter
            var filteredData = params.filter() ?
                    $filter('filter')($scope.coleccionCargosObservados, params.filter()) :
                    	$scope.coleccionCargosObservados;
            var orderedData = params.sorting() ?
                    $filter('orderBy')(filteredData, params.orderBy()) :
                    	$scope.coleccionCargosObservados;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });
	$scope.tableParamsDesgloceGastosRelevantes = new ngTableParams({
        page: 1,            // show first page
        count: 4,          // count per page
        filter: {
            //name: 'M'       // initial filter
        },
        sorting: {
            //name: 'asc'     // initial sorting
        }
    }, {
        total: $scope.coleccionDesgloces.length, // length of data
        getData: function ($defer, params) {
            // use build-in angular filter
            var filteredData = params.filter() ?
                    $filter('filter')($scope.coleccionDesgloces, params.filter()) :
                    	$scope.coleccionDesgloces;
            var orderedData = params.sorting() ?
                    $filter('orderBy')(filteredData, params.orderBy()) :
                    	$scope.coleccionDesgloces;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });
    
	//CON ÉSTA FUNCIÓN OBTENEMOS LOS SUBTOTALES PARA CADA TIPO DE CARGO	
	$scope.obtenerSubTotales=function (gastos,tipoCargo){
		var arrayTmp = new Array();
		arrayTmp = gastos;
		var total=0.0;
		var tipoCargoInteres=1;
		if ($rootScope.eventoEditSelected.statusEvento==2){
			tipoCargoInteres=2;
		}
		if ($rootScope.eventoEditSelected.statusEvento==3){
			tipoCargoInteres=2;
		}		
		for (i=0;i<arrayTmp.length;i++){
			if (arrayTmp[i]!=null){				
				if (arrayTmp[i].idTipoCargo!=null){
					if (tipoCargo==1){
						if (arrayTmp[i].idTipoCargo==tipoCargo && arrayTmp[i].idArea==tipoCargoInteres){				
							var montoTmp=arrayTmp[i].cantidad*arrayTmp[i].montoUnitario;					
							//total=total+montoTmp;					
							total=montoTmp;
						}
					}
					else{
						if (arrayTmp[i].idTipoCargo==tipoCargo){				
							var montoTmp=arrayTmp[i].cantidad*arrayTmp[i].montoUnitario;
							total=total+montoTmp;					
						}
					}
				}
			}
		}
		console.log("Subtotal:"+total);
		return total;
	};
	
	//Obtener el total de gastos seg�n el tipo seleccionado
	$scope.subTotalTipoGastoResumen1=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,1);
	$scope.subTotalTipoGastoResumen2=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,2);
	$scope.subTotalTipoGastoResumen3=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,3);	
	//-----------------------------------------
	//Al dar clic en editar un cargo
	$scope.idGastoAEditar=0;
	//Objeto para las Ediciones
	$scope.editarGastoForm=new Object();
	$scope.editarGastoForm.idGasto=0;
	$scope.editarGastoForm.fechaIngreso="";
	$scope.editarGastoForm.montoUnitario=0;
	$scope.editarGastoForm.idTipoCargo=1;	
	
	$scope.showEditarGastoForm = function(gasto){
		$rootScope.edittiposcargoedocuentaselected=new Object();
    	$scope.editarEdoCuenta=true;
		$scope.idGastoAEditar=gasto.idGasto;
		$scope.editarGastoForm.idTipoCargo=gasto.idTipoCargo;
		$scope.editarGastoForm.idGasto=gasto.idGasto;
		$scope.editarGastoForm.fechaIngreso=gasto.fechaIngreso;
		$scope.editarGastoForm.montoUnitario=gasto.montoUnitario;
		$scope.editarGastoForm.idArea=gasto.idArea;
		$scope.editarGastoForm.idRubro=gasto.idRubro;
		$scope.editarGastoForm.idRazon=gasto.idRazon;
		$scope.editarGastoForm.fechaIngreso=gasto.fechaIngreso;
		$scope.editarGastoForm.nombre=gasto.nombre;
		$scope.editarGastoForm.montoUnitario=gasto.montoUnitario;
		$scope.editarGastoForm.cantidad=gasto.cantidad;
		$scope.editarGastoForm.rutaEvidencia=gasto.rutaEvidencia;
     };
     $rootScope.setLoading(false);
     $scope.cancelarGastoForm = function (){
    	 $scope.editarEdoCuenta=false;
   		$scope.idGastoAEditar=0;
   		$scope.savingFile=0;
     }
     $scope.guardarGastoForm = function (tipo){
     	$scope.eventoRegistroGasto= new Object(); 							
  		var idGasto=$rootScope.gastoSelected;
  		console.log("SAVING"); 		
  		console.log($scope);
  		console.log($rootScope);
   		var eventoRegistroGasto= new Object();  			
  		eventoRegistroGasto.idEvento=$rootScope.eventoEditSelected.idEvento; 			 		
  		eventoRegistroGasto.gastos=new Array(1); 			 			
  		var gastoEdit= new Object();
  			gastoEdit.idGasto=$scope.idGastoAEditar;
  			gastoEdit.idTipoCargo=$scope.editarGastoForm.idTipoCargo;
  			if (tipo==1){
  				gastoEdit.idArea=$scope.editarGastoForm.editTipoCargo.idTipoCargoEdoCuenta;
  				gastoEdit.idRubro=$scope.editarGastoForm.idRubro;
  				gastoEdit.idRazon=$scope.editarGastoForm.editTipoCargo.idTipoCargoEdoCuenta;
  	  			gastoEdit.nombre=$scope.editarGastoForm.editTipoCargo.descripcion;
  	  			gastoEdit.cantidad=1;
  			}
  			if (tipo==2){
  	  			gastoEdit.nombre=$scope.editarGastoForm.nombre;
  	  			gastoEdit.idArea=$scope.editarGastoForm.editArea.idArea;  			
  				gastoEdit.idRubro=$scope.editarGastoForm.editRubro.idRubro;
  				gastoEdit.idRazon=$scope.editarGastoForm.editCausa.idCausaCargo;
  				gastoEdit.cantidad=$scope.editarGastoForm.cantidad;
  			}
  			if (tipo==3){
  	  			gastoEdit.nombre=$scope.editarGastoForm.nombre;
  	  			gastoEdit.idArea=$scope.editarGastoForm.editArea.idArea;  			
  				gastoEdit.idRubro=1;
  				gastoEdit.idRazon=1;			
  				gastoEdit.cantidad=1;
  			}
  			gastoEdit.fechaIngreso=$scope.dt;
  			gastoEdit.montoUnitario=$scope.editarGastoForm.montoUnitario;		
  			gastoEdit.rutaEvidencia="";
  			eventoRegistroGasto.gastos.push(gastoEdit); 
  			
  		$rootScope.eventoEditSelected.gastos=$scope.editarGastoForm.idTipoCargo;
  		delete Array.prototype.toJSON;	    	
  		$http.defaults.headers.post["Content-Type"] = "application/json";
  		console.log("SAVE VALUES");
  		console.log(eventoRegistroGasto);
  		//console.log($rootScope.eventoEditSelected);
  		$scope.savingFile=1;
  		$http.post('mvc/evento/guardareditargasto',eventoRegistroGasto).
  		    	success(function(data, status, headers, config) {
  		    		$rootScope.eventoEditSelected=data;
  		    		$scope.coleccionEdosCuenta=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,1);
  		    		$scope.coleccionCargosObservados=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,2);
  		    		$scope.coleccionDesgloces=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,3);
  		    		alert("Se actualiz\u00f3 correctamente el gasto!");
  		    		console.log("DATOS DEL EVENT_");
  		    		console.log(data);
  		    		$scope.subTotalTipoGastoResumen1=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,1);
  		    		$scope.subTotalTipoGastoResumen2=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,2);
  		    		$scope.subTotalTipoGastoResumen3=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,3);
  		    		$scope.editarEdoCuenta=false;
  		    		$scope.idGastoAEditar=0;
  		    		$scope.savingFile=0;
  		    		  //$location.path('/showeventos');
  		    	}).error(function(data, status, headers, config) {
  		    	    // called asynchronously if an error occurs
  		    	    // or server returns response with an error status.
  		    		alert("ERROR AL ACTUALIZAR EL GASTO!"+data);
  		    		$scope.savingFile=0;
  		    		$scope.editarEdoCuenta=false;
  		    	});
    };						
     
     $scope.eliminarGastoForm = function (gasto){
    	 if (confirm("Seguro que desea eliminar el registro?")){ 		
	      	$scope.eventoRegistroGasto= new Object(); 							
	   		var idGasto=$rootScope.gastoSelected;
	   		var eventoRegistroGasto= new Object();  			
	   		eventoRegistroGasto.idEvento=$rootScope.eventoEditSelected.idEvento; 			 		
	   		eventoRegistroGasto.gastos=new Array(1); 			 			
	   		var gastoEdit= new Object();
	   		gastoEdit.idGasto=gasto.idGasto;
	   		eventoRegistroGasto.gastos.push(gastoEdit);
	   		$rootScope.eventoEditSelected.gastos=$scope.editarGastoForm.idTipoCargo;
	   		delete Array.prototype.toJSON;	    	
	   		$http.defaults.headers.post["Content-Type"] = "application/json";
	   		console.log("DELETE VALUES");
	   		console.log(eventoRegistroGasto);
	   		//console.log($rootScope.eventoEditSelected);
	   		$http.post('mvc/evento/eliminargasto',eventoRegistroGasto).
	   		    	success(function(data, status, headers, config) {
	   		    		$rootScope.eventoEditSelected=data;
	   		    		$scope.coleccionEdosCuenta=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,1);
	   		    		$scope.coleccionCargosObservados=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,2);
	   		    		$scope.coleccionDesgloces=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,3);
	   		    		alert("Se elimin\u00f3 correctamente el gasto!");
	   		    		console.log("DATOS DEL EVENT_");
	   		    		console.log(data);
	   		    		$scope.subTotalTipoGastoResumen1=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,1);
	   		    		$scope.subTotalTipoGastoResumen2=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,2);
	   		    		$scope.subTotalTipoGastoResumen3=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,3);
	   		    		$scope.editarEdoCuenta=false;
	   		    		$scope.idGastoAEditar=0;
	   		    		  //$location.path('/showeventos');
	   		    	}).error(function(data, status, headers, config) {
	   		    	    // called asynchronously if an error occurs
	   		    	    // or server returns response with an error status.
	   		    		alert("ERROR AL ELIMINAR EL GASTO!"+data);
	   		    		$scope.editarEdoCuenta=false;
	   		    	});
    	 }
      };						

    	$scope.ajax_download2=function(id,id2) {	
    		var input_name="id1";
    		var input_name2="id2";
    	
    		var url="mvc/file/getFileGasto";
    		var data={idFactura:5,rutaFactura:5};
    	    var $iframe,
    	        iframe_doc,
    	        iframe_html;

    	    if (($iframe = $('#download_iframe')).length === 0) {
    	        $iframe = $("<iframe id='download_iframe'" +
    	                    " style='display: none' src='about:blank'></iframe>"
    	                   ).appendTo("body");
    	    }

    	    iframe_doc = $iframe[0].contentWindow || $iframe[0].contentDocument;
    	    if (iframe_doc.document) {
    	        iframe_doc = iframe_doc.document;
    	    }

    	    iframe_html = "<html><head></head><body><form method='POST' action='" +
    	                  url +"'>" +
    	                  "<input type=hidden name='" + input_name + "' value='" +
    	                  id +"'/>" +
    	                  "<input type=hidden name='" + input_name2 + "' value='" +
    	                  id2 +"'/></form>" +
    	                  "</body></html>";

    	    iframe_doc.open();
    	    iframe_doc.write(iframe_html);
    	    $(iframe_doc).find('form').submit();
    	}
        //------------------------------FIN AJAX DOWNLOAD---------------------------------
	
	$scope.guardarGasto = function (){
		var msj="";
		console.log("Valor"+document.getElementById("file2").value.length)		
		console.log($scope.regtipocargoselected);
    	if (document.getElementById("file2").value.length!=0 && $scope.regtipocargoselected!=null){
    		if ($scope.regtipocargoselected!="" && $scope.regtipocargoselected!=""){
	    		$scope.eventoRegistroGasto= new Object();
	    		$scope.eventoRegistroGasto.idEvento=$rootScope.eventoEditSelected.idEvento;		
	    		if ($rootScope.eventoEditSelected!=null){
					$scope.eventoRegistroGasto.gastos=new Array(1);						
					var gasto=new Object();
					gasto.idTipoCargo=$scope.regtipocargoselected.idTipoCargo;
					$rootScope.idTipoCargoRegistros=$scope.regtipocargoselected.idTipoCargo;
					//Si el gasto no es desvio
					console.log
					if (gasto.idTipoCargo>=2){
						gasto.idArea=$scope.regtipoareaselected.idArea;
					}
					else{
						gasto.idArea=1;
					}
					if (gasto.idTipoCargo==1){					
						gasto.nombre="EDO DE CUENTA";
						console.log("!TIPO DE GASTO");
						console.log($scope.tiposcargoedocuentaselected);
						gasto.idArea=$scope.tiposcargoedocuentaselected.idTipoCargoEdoCuenta;
					}
					if (gasto.idTipoCargo==3) 
						gasto.nombre="GASTO RELEVANTE";
					if (gasto.idTipoCargo==2){
						gasto.idRubro=$scope.regtiporubroselected.idRubro;
						gasto.idRazon=$scope.regrazonesAltaGastoImproSelected.idCausaCargo;
						gasto.cantidad=$scope.cargo.cantidad;
						gasto.nombre=$scope.cargo.nombre;
					}
					else{
						gasto.idRubro=1;
						gasto.idRazon=1;				
						gasto.cantidad=1;
					}
					
					var cantid=""+$scope.currencyVal;
					//console.log($scope.currencyVal);
					//console.log("ct"+cantid);
					gasto.montoUnitario=cantid.replace(/,/g , "");
					//console.log(gasto.montoUnitario);
					gasto.fechaIngreso=$scope.dt;	
					console.log("FECHA INGRESO")
					console.log(gasto.fechaIngreso)
					var fileName=document.getElementById("file2").value;
			   		document.getElementById("idEventoHidden").value=$scope.eventoRegistroGasto.idEvento;
					gasto.rutaEvidencia=fileName;
					$scope.eventoRegistroGasto.gastos.push(gasto);
					
					delete Array.prototype.toJSON;	    	
				    $http.defaults.headers.post["Content-Type"] = "application/json";
				    console.log("SAVE VALUES");
				    console.log($scope);
				    $scope.savingFile=1;
				    $http.post('mvc/evento/guardargasto',$scope.eventoRegistroGasto).    			    			
				    	success(function(data, status, headers, config) {
				    			uploadJqueryFormEvidencia();		
				    			console.log("DATOS DEL EVENTO_");	 		
				    			console.log($scope);
				    			$rootScope.eventoEditSelected=data;
			   		    		$scope.coleccionEdosCuenta=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,1);
			   		    		$scope.coleccionCargosObservados=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,2);
			   		    		$scope.coleccionDesgloces=$scope.obtenerColeccionTipoCargo($rootScope.eventoEditSelected,3);  			
			   		    		$scope.subTotalTipoGastoResumen1=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,1);
			   		    		$scope.subTotalTipoGastoResumen2=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,2);
			   		    		$scope.subTotalTipoGastoResumen3=$scope.obtenerSubTotales($rootScope.eventoEditSelected.gastos,3);
			   		    		$scope.editarEdoCuenta=false;
			   		    		$scope.idGastoAEditar=0;
			   		    		$scope.regtipocargoselected="";
			   		    		console.log("DESPUES");
			   				    console.log($scope);
			   				    $scope.savingFile=0;		   				    
		
				    	}).error(function(data, status, headers, config) {			    	
				    		  alert("Error guardando el registro"+data);
				    		  $scope.savingFile=0;
				    });
	    		}
    		}
    		else{
    			alert("Debe seleccionar los datos del gasto.");
    		}
		}
    	else{    		    	
    		if ($scope.regtipocargoselected==null)    			
    			msj+=" Debe agregar un tipo de Cargo!";			
    		if (document.getElementById("file2").value.length==0)
    			msj+=" Debe agregar una evidencia!";
    		alert(msj);
    	}
	};
	    
    $scope.showAltaCedulaControlForm = function(){
    	document.getElementById("divTableCedulaControl").style.display = 'none';
    	document.getElementById("divAltaCedulaControlController").style.display = 'block';
    };
    
    $scope.hideAltaCedulaControlForm = function(){
    	document.getElementById("divTableCedulaControl").style.display = 'block';
    	document.getElementById("divAltaCedulaControlController").style.display = 'none';
    };    
    
	 
};


