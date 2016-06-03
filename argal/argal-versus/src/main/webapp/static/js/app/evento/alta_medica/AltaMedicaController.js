function uploadJqueryForm(){	
   $('#result').html('');
   document.getElementById("waitUpload").style.display="block";
   $("#form2").ajaxForm({
    success:function(data) { 
          //$('#result').html(data); 	  
    	  document.getElementById("waitUpload").style.display="none";
          alert("Se registr\u00f3 correctamente la factura");      
     },dataType:"text",
     error:function(data) {
    	 alert("error:"+data);
     }  
   }).submit();
}

//using FormData() object
function uploadFormData(){
	alert("OK3");
    $('#result').html(''); 
  	var oMyForm = new FormData();
  	oMyForm.append("file", file2.files[0]);
 
  	$.ajax({
    	url: 'http://localhost:8080/argal-web/mvc/cont/upload',
    	data: oMyForm,
    	dataType: 'text',
    	processData: false,
    	contentType: false,
    	type: 'POST',
    	success: function(data){
	    	alert("OK4");
      		$('#result').html(data);
    	}
  });
}

var AltaMedicaController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	$rootScope.setLoading(false);
	//$rootScope.showFormNac=0;
	$scope.showFormNac0=0;
	$rootScope.eventoEgreso=new Object();
	console.log("GET VALUES ALTA MEDICA CONTROLLER!!!");
	$scope.datepickers = {
			dt: false,
			dtSecond: false
	}
	$scope.today = function() {
	    $scope.dt = new Date();
	    // ***** Q1  *****
	    $scope.dtSecond= new Date();
	};
	$scope.today();
	$scope.valueTime = new Date(0, 0, 1, 14, 57);
	$scope.valueTime3 = new Date(0, 0, 1, 14, 57);
	console.log($rootScope.eventoEditSelected);
	$rootScope.eventoEgreso=$rootScope.eventoEditSelected;
	console.log("COPY VALUES")
	console.log($rootScope.eventoEditSelected)
	$scope.evento=$rootScope.eventoEditSelected;
	
	if ($rootScope.eventoEgreso.diagnosticoEgreso1!=null){
		$scope.tipoIDCEgreso=$rootScope.eventoEgreso.diagnosticoEgreso1.descripcion;
		$scope.tipoIcd3=$rootScope.eventoEgreso.diagnosticoEgreso1.descripcion;
	}
	if ($rootScope.eventoEgreso.procedimiento1!=null){
		$scope.tipoProced1=$rootScope.eventoEgreso.procedimiento1.descripcion;
		$scope.tipoCpt1=$rootScope.eventoEgreso.procedimiento1.descripcion;
	}
	if ($rootScope.eventoEgreso.procedimiento2!=null){
		$scope.tipoProced2=$rootScope.eventoEgreso.procedimiento2.descripcion;
		$scope.tipoCpt2=$rootScope.eventoEgreso.procedimiento2.descripcion;
	}
	
		
	console.log("GET VALUES ALTA MEDICA CONTROLLER!!!");
	console.log($scope);
	$rootScope.evento=new Object();
	$scope.showSeleccionarICD = function(){
	  	document.getElementById("divShowDiagEgreso").style.display = 'block';
	 	document.getElementById("divEgresoFormulario").style.display = 'none';
	};
	

	$scope.showSeleccionarCPT = function(id){
		$rootScope.tipoCPTSelec = id;
		document.getElementById("divShowProcedimiento").style.display = 'block';
	 	document.getElementById("divEgresoFormulario").style.display = 'none';
	};
	    
	$scope.showMainAltaMedicaForm = function(){
	 	  document.getElementById("divShowDiagEgreso").style.display = 'none';
	  	  document.getElementById("divShowProcedimiento").style.display = 'none';
	  	  document.getElementById("divEgresoFormulario").style.display = 'block';
	};
	
	$scope.showFormNac = function(){	
    	if ($scope.motivoEgresoSelected!=null){
    		if (document.getElementById("divDef")!=null){
    			if ($scope.motivoEgresoSelected.idMotivoEgreso==4)
    				document.getElementById("divDef").style.display ='block';
    			else
    				document.getElementById("divDef").style.display ='none';
    		}
    		if ($scope.motivoEgresoSelected.idMotivoEgreso==4)
    			$scope.showFormNac0=1;
    		else
    			$scope.showFormNac0=0;
    	}
    };
    
	$scope.columnCollectionSeguimientoMedico = [
        {label: 'IdBitacora', map: 'idBitacora'},
        {label: 'Fecha', map: 'fechaBitacora',formatFunction: 'date'},
        {label: 'Registro', map: 'idRegistroMedico' },               
        {label: 'Observaciones', map: 'observaciones' }
    ];
	$scope.motivosegresos= [
	      {idMotivoEgreso: '1', descripcion: 'ALTA POR MEJORIA CLINICA'},	      
	      {idMotivoEgreso: '2', descripcion: 'ALTA VOLUNTARIA'},
	      {idMotivoEgreso: '3', descripcion: 'ALTA POR TRASLADO A OTRA UNIDAD HOSPITALARIA'},
	      {idMotivoEgreso: '4', descripcion: 'DEFUNCION'}
	];	        

	if ($rootScope.eventoEgreso.motivoEgreso!=null){
		for (i=0;i<$scope.motivosegresos.length;i++){
			if ($scope.motivosegresos[i].descripcion==$rootScope.eventoEgreso.motivoEgreso){
				$scope.motivoEgresoSelected=$scope.motivosegresos[i];
				break;
			}
			$scope.showFormNac();
		}	
	}   
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};

	$scope.clear = function () {
		$scope.dt = null;
		$scope.dtSecond = null;		
	};
	$scope.valueTime=$scope.evento.horaEgreso;
	$scope.valueTime3=$scope.evento.horaDef;
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

    console.log("Set fecha eGreso")
    console.log($scope.evento.fechaEgreso)
    $scope.dt=$scope.evento.fechaEgreso;
    $scope.dtSecond=$scope.evento.fechaDef;
    
	$scope.guardarAltaPaciente = function (){
		$scope.eventoRegistroAltaPaciente= new Object();
		$scope.eventoRegistroAltaPaciente.idEvento=$rootScope.eventoEditSelected.idEvento;
		/*console.log("SAVE VALUES");
		console.log($scope.valueTime);
		console.log($scope.valueTime3);
		console.log($scope.dt);
		console.log($scope.dtSecond);*/
		$scope.eventoRegistroAltaPaciente.fechaEgreso=$scope.dt;
		$scope.eventoRegistroAltaPaciente.horaEgreso=$scope.valueTime;
		$scope.eventoRegistroAltaPaciente.eventosNoDeseablesEntornoHosp=$scope.evento.eventosNoDeseablesEntornoHosp;
		$scope.eventoRegistroAltaPaciente.motivoEgreso=$scope.motivoEgresoSelected.descripcion;
		$scope.eventoRegistroAltaPaciente.diagnosticoEgreso1= new Object();
		$scope.eventoRegistroAltaPaciente.diagnosticoEgreso1.idIcd=$rootScope.idtipoIcd3;
		$scope.eventoRegistroAltaPaciente.procedimiento1= new Object();
		$scope.eventoRegistroAltaPaciente.procedimiento1.idCpt=$rootScope.idtipoCpt1;
		$scope.eventoRegistroAltaPaciente.procedimiento2= new Object();
		$scope.eventoRegistroAltaPaciente.procedimiento2.idCpt=$rootScope.idtipoCpt2;
		$scope.eventoRegistroAltaPaciente.fechaDef=$scope.dtSecond;
		$scope.eventoRegistroAltaPaciente.horaDef=$scope.valueTime3;
		$scope.eventoRegistroAltaPaciente.causaDirectaDef=$scope.evento.causaDirectaDef;
		$scope.eventoRegistroAltaPaciente.diasIncapacidad=$scope.evento.diasIncapacidad;
			
		delete Array.prototype.toJSON;	    	
		$http.defaults.headers.post["Content-Type"] = "application/json";				
		console.log("SAVE VALUES");
		console.log($scope.eventoRegistroAltaPaciente);
		$http.post('mvc/evento/registraraltapaciente',$scope.eventoRegistroAltaPaciente).
			success(function(data, status, headers, config) {
				  alert("Se registr\u00f3 correctamente el alta!");
				  location.reload(true);				  		    	 
		    	  //$location.path('/showeventos');
		    }).error(function(data, status, headers, config) {
		        // called asynchronously if an error occurs
		        // or server returns response with an error status.
		    	  alert("Error al registrar el Alta M�dica"+data);
		    });				
		//$scope.hideAltaSeguimientoMedicoForm();
	};
	console.log("SE CARGAN TODOS LOS VALORES")	
};

var  AccordionDemoCtrl=function ($scope,$rootScope,$filter,ngTableParams,$http,$dialogs,$timeout) {
	$scope.currencyVal;
	$scope.currencyVal2;
    $scope.savingFile=0;
	String.prototype.splice = function(idx, rem, s) {
	    return (this.slice(0, idx) + s + this.slice(idx + Math.abs(rem)));
	};
	$rootScope.setLoading(false);
    $scope.isSaving=false;	
	$scope.oneAtATime = true;
	$scope.subirFacturaVar=false;
	$rootScope.eventoFactura=new Object();
	$scope.capitas = [
	                    {"idCapita":1,"nombre":"CH"},
		          	  	{"idCapita":2,"nombre":"NA"},
		          	  	{"idCapita":3,"nombre":"CH/NA"},
		          	  	{"idCapita":4,"nombre":"CH/NC"},
		          	  	{"idCapita":5,"nombre":"CH/NA/NC"},
		          	  	{"idCapita":6,"nombre":"NC/NA"}			  			 
	];
	$scope.facturaAprobadaCombo=[
		  			   {"idFacturaAprobada":1,"nombre":"SI"},
		  			   {"idFacturaAprobada":2,"nombre":"NO"},			  			 
		  			];
	$scope.groups = [
	    {
	      title: "Dynamic Group Header - 1",
	      content: "Dynamic Group Body - 1"
	    },
	    {
	      title: "Dynamic Group Header - 2",
	      content: "Dynamic Group Body - 2"
	    }
	  ];

	  $scope.items = ['Item 1', 'Item 2', 'Item 3'];

	  $scope.addItem = function() {
	    var newItemNo = $scope.items.length + 1;
	    $scope.items.push('Item ' + newItemNo);
	  };
	  
	  var data= new Array();
	  var data0=
	  {
		      noFactura: "1",
		      aprobada: "SI",
		      monto: "10033.9",
		      detalle:"factura aprobada",
		      archivo:"ver"		    
	  };
	  //Variables rootPara los input
	  $rootScope.eventoFinalizarEventoForm=new Object();	  	
	  data.push(data0);
	  //
	  data=$rootScope.eventoEditSelected.facturas;
	  console.log("FACTURAS");
	  console.log(data);
		
  	  if ($rootScope.eventoEditSelected.tipoEvento.idTipoEvento==1 && $rootScope.eventoEditSelected.registroSeguroPersonal!=null){
		  $scope.tipoSeguroTmp=1;
		  $rootScope.eventoConCenso=false;
		  if ($rootScope.eventoEditSelected.registroSeguroPersonal.condicionPaciente=="JUBILADO" && $rootScope.eventoEditSelected.registroSeguroPersonal.censo=="SI")
			  $rootScope.eventoConCenso=true;
	  }
	  if ($rootScope.eventoEditSelected.tipoEvento.idTipoEvento==2 && $rootScope.eventoEditSelected.registroGastosMayores!=null){		
		  $scope.tipoSeguroTmp=2;
		  $rootScope.eventoConCenso=false;
	  }
						
	  $scope.eventoFinalizarEventoForm=$rootScope.eventoEditSelected;
	  //Checar si tiene capita
	  if ($scope.eventoFinalizarEventoForm.registroSeguroPersonal!=null){
		for (i=0;i<$scope.capitas.length;i++){
			if ($scope.capitas[i].nombre==$scope.eventoFinalizarEventoForm.registroSeguroPersonal.capita)
				$scope.capitaSelected=$scope.capitas[i];			
		}
	  }
	  
	  $rootScope.dataFacturas = data;
	  $scope.dataFacturas = data;
      $scope.tableParams = new ngTableParams({
          page: 1,            // show first page
          count: 4,          // count per page
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
      

      $scope.showFile=function(d1,d2){
    	  console.log("DATOS VER FILE");
    	  console.log(d1);
    	  console.log(d2);
    	  var url='views/evento/finalizar_evento/viewfacturaFile.jsp?d1='+d1+'&d2='+d2;
    	  window.open(url,"nueva");    	  
      }
      
      $scope.subirFactura=function (){	
    	  //ocument.getElementById('boton');
    	  console.log("Revisar datos de la Factura");
    	  console.log($scope);
    	  console.log($scope.eventoFacturaForm);    	  
    	  if ($scope.factaprobSelected!=null){
    		  var check=true;
    		  var msjError="";
    		  if ($scope.eventoFacturaForm==null){
    			  check=false;
    			  msjError="Ingrese los datos de la factura! ";
    		  }
    		  else{
    			  if ($scope.factaprobSelected.idFacturaAprobada==1){
    				  if ($scope.eventoFacturaForm==null){
    					  check=false;
    					  msjError="Ingrese los datos de la factura! ";
    				  }
    				  else{
    					  if ($scope.eventoFacturaForm.numeroFactura==null){
    						  check=false;
        					  msjError+="Ingrese el num de la factura! ";
    					  }
    					  if ($scope.currencyVal==null){
    						  check=false;
        					  msjError+="Ingrese el monto de la factura! ";
    					  }
    					  if (document.getElementById("file2").value==""){
    						  check=false;
        					  msjError+="Ingrese el archivo de la factura!";
    					  }    					  
    				  }
        		  }
    			  else{
    				  if ($scope.eventoFacturaForm==null){
    					  check=false;
    					  msjError="Ingrese los datos de la factura! ";
    				  }
    				  else{
    					  if ($scope.eventoFacturaForm.numeroFactura==null){
    						  check=false;
        					  msjError+="Ingrese el num de la factura! ";
    					  }
    					  if ($scope.currencyVal==null){
    						  check=false;
        					  msjError+="Ingrese el monto de la factura! ";
    					  }
    					  if (document.getElementById("file2").value==""){
    						  check=false;
        					  msjError+="Ingrese el archivo de la factura!";
    					  }
    					  if ($scope.currencyVal2==null){
    						  check=false;
        					  msjError+="Ingrese el ajuste de la factura! ";
    					  }
    					  if ($scope.eventoFacturaForm.tipoComprobanteFiscalCorregido==null){
    						  check=false;
        					  msjError+="Ingrese el TIPO DE COMPROBANTE FISCAL de la factura! ";
    					  }
    					  if ($scope.eventoFacturaForm.folioComprobanteFiscalCorregido==null){
    						  check=false;
        					  msjError+="Ingrese el FOLIO COMPROBANTE FISCAL! ";
    					  }
    					  if ($scope.eventoFacturaForm.montoComprobanteFiscalCorregido==null){
    						  check=false;
        					  msjError+="Ingrese el MONTO COMPROBANTE FISCAL! ";
    					  }    				   	  
    				  }
    			  }
    		  }
    		  if (check){    	  
    			  $scope.eventoFactura= new Object();
			   	 //$scope.eventoRegistroBitacora.idEvento=$rootScope.eventoEditSelected.idEvento;
			   	  $scope.eventoFactura.idEvento=$rootScope.eventoEditSelected.idEvento;
			   	  console.log("SAVE FACTURA!!");
			   	  console.log($scope);   		  
			   	  $scope.eventoFactura.facturas = new Array(1);
			   	  var factura=new Object();
			   	  var fileName=document.getElementById("file2").value;
			   	  document.getElementById("idEventoHidden").value=$scope.eventoFactura.idEvento;
			   	  $scope.isSaving=true;
			   	  //var resultFileName = fileName.match(/[-_\w]+[.][\w]+$/i)[0];
			   	  //factura.idEvento=$scope.eventoFactura.idEvento;
			   	  factura.numeroFactura=$scope.eventoFacturaForm.numeroFactura;
			   	  //alert($rootScope.factaprobSelected.nombre);
			   	  factura.aprobada=$scope.factaprobSelected.nombre;
			   	  factura.monto=$scope.currencyVal.replace(",","");
			   	  factura.detalle=$scope.eventoFacturaForm.detalle;
			   	  factura.observaciones=$scope.eventoFacturaForm.observaciones;
			   	  factura.rutaFactura=fileName;
			   	  if ($scope.factaprobSelected.idFacturaAprobada==2){
			   		  factura.ajusteFactura=$scope.currencyVal2.replace(",","");
			   		  factura.tipoComprobanteFiscalCorregido=$scope.eventoFacturaForm.tipoComprobanteFiscalCorregido;
			   		  factura.montoComprobanteFiscalCorregido=$scope.eventoFacturaForm.montoComprobanteFiscalCorregido;
			   		  factura.folioComprobanteFiscalCorregido=$scope.eventoFacturaForm.folioComprobanteFiscalCorregido;
			   	  }else{
			   		  factura.ajusteFactura=0;
			   		  factura.tipoComprobanteFiscalCorregido="";
			   		  factura.montoComprobanteFiscalCorregido=0;
			   		  factura.folioComprobanteFiscalCorregido="";
			   	  }
			   	  $scope.eventoFactura.facturas.push(factura);
			   	  delete Array.prototype.toJSON;	    	
			   	  $http.defaults.headers.post["Content-Type"] = "application/json";				
			   	  console.log("SAVE VALUES FACTURA");
			   	  console.log($scope.eventoFactura);			      	  
			   	  $http.post('mvc/evento/guardarfactura',$scope.eventoFactura).
			   	  		success(function(data, status, headers, config) {   		  			
			   	  			uploadJqueryForm();
			   	  			$scope.isSaving=false;
			   	  			$scope.eventoFinalizarEventoForm=data;
			   	  			$scope.dataFacturas=data.facturas;		
			   	  			$rootScope.dataFacturas.push(factura);
			   	  			if (factura.aprobada=='SI'){
			   	  				$scope.cuentaFinalFacturacion=parseFloat($scope.cuentaFinalFacturacion)+parseFloat(factura.monto);
			   	  				$scope.numeroFacturasAprobadas=$scope.numeroFacturasAprobadas+1;
			   	  			}else{
			   	  				$scope.cuentaFinalDesviosFacturacion=parseFloat($scope.cuentaFinalDesviosFacturacion)+parseFloat(factura.ajusteFactura);
			   	  				$scope.numeroFacturasRechazadas=$scope.numeroFacturasRechazadas+1;
			   	  			}
			   	  			//$location.path('/showeventos');
			   	  		}).error(function(data, status, headers, config) {
			   	    	  alert("Error al registrar la Factura"+data);
			   	    });    	   
			       //$rootScope.dataFacturas.push(data2);
    		  }
    		  else{
    			  alert(msjError);
    		  }
    	  }
    	  else{
    		  alert("Ingrese si la factura es aprobada o rechazada.")
    	  }
      };
    //------------------------------INICIA AJAX DOWNLOAD-------------------------------//  
  	$scope.ajax_download=function(id,id2) {	
		var input_name="id1";
		var input_name2="id2";
		
		var url="mvc/file/getFacturaFile";
		var data={idFactura:id,rutaFactura:id2};
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
  	//------------------------------ELIMINAR FACTURA----------------------------------
      $scope.eliminarFactura=function(fact){
    	  if (confirm("¿Seguro que desea eliminar la factura?")){
    	  console.log("Revisar datos de la Factura");
    	  console.log($scope);
    	  console.log($rootScope);
    	
    	  $scope.eventoFactura= new Object();
   		  //$scope.eventoRegistroBitacora.idEvento=$rootScope.eventoEditSelected.idEvento;
   		  $scope.eventoFactura.idEvento=$rootScope.eventoEditSelected.idEvento;
   		  console.log("Eliminar Factura!!");
   		  console.log(fact);   		  
   		  $scope.eventoFactura.facturas = new Array(1);
   		  var factura=new Object();
   		  factura.idFactura=fact.idFactura;
   		  factura.aprobada=fact.aprobada;
   		  factura.monto=fact.monto;
   		  factura.ajusteFactura=fact.ajusteFactura;
   		  factura.montoComprobanteFiscalCorregido=fact.montoComprobanteFiscalCorregido;
		  $scope.eventoFactura.facturas.push(factura);
   		  delete Array.prototype.toJSON;	    	
   		  $http.defaults.headers.post["Content-Type"] = "application/json";				
   		  console.log("DELETE VALUES FACTURA");
   		  console.log($scope.eventoFactura);
   		  $http.post('mvc/evento/eliminarfactura',$scope.eventoFactura).
   		  		success(function(data, status, headers, config) {
   		  			alert("Se eliminó correctamente la factura!");
   		  			$scope.eventoFinalizarEventoForm=data;
   		  			$scope.dataFacturas=data.facturas;
   		  			if (factura.aprobada=='SI'){
   		  				$scope.cuentaFinalFacturacion=parseFloat($scope.cuentaFinalFacturacion)-parseFloat(data.monto);
   		  				$scope.numeroFacturasAprobadas=$scope.numeroFacturasAprobadas-1;
   		  			}else{
   		  				$scope.cuentaFinalDesviosFacturacion=parseFloat($scope.cuentaFinalDesviosFacturacion)-parseFloat(data.ajusteFactura);
   		  				$scope.numeroFacturasRechazadas=$scope.numeroFacturasRechazadas-1;
   		  			}
   		  			//$location.path('/showeventos');
   		  		}).error(function(data, status, headers, config) {
   		    	  alert("Error al eliminar"+data);
   		    });    	   
    	  }
      };

      
      $scope.nuevaFacturaShow=function (){
    	 if ($scope.subirFacturaVar==false)
    		 $scope.subirFacturaVar=true;
    	 else
    		 $scope.subirFacturaVar=false;
      };
      
      $scope.finalizarEvento = function (evento){
  		$http.post('mvc/evento/finalizarevento',$scope.eventoRegistroFinalizarEvento).
  			success(function(data, status, headers, config) {
  		    	  alert("Se finaliz\u00f3 correctamente el Alta Medica");		    	  
  		    	  $location.path('/showeventos');
  		    }).error(function(data, status, headers, config) {
  		        // called asynchronously if an error occurs
  		        // or server returns response with an error status.
  		    	  alert("Error al finalizar el evento"+data);
  		    });				
  		//$scope.hideAltaSeguimientoMedicoForm();
  	};
  	
  	$scope.finalizarMontosEvento = function (evento){
  		$scope.eventoRegistroFinalizarEvento= new Object();
  		$scope.eventoRegistroFinalizarEvento.idEvento=$rootScope.eventoEditSelected.idEvento;
  		console.log("SAVE VALUES");
  		console.log($scope.eventoFinalizarEventoForm)
  		if ($rootScope.eventoEditSelected.registroSeguroPersonal!=null){
  			$scope.eventoRegistroFinalizarEvento.registroSeguroPersonal= new Object();
  			if ($scope.capitaSelected!=null)
  				$scope.eventoRegistroFinalizarEvento.registroSeguroPersonal.capita=$scope.capitaSelected.nombre;
  			$scope.eventoRegistroFinalizarEvento.registroSeguroPersonal.cantidadCubiertaConvenio=$scope.eventoFinalizarEventoForm.registroSeguroPersonal.cantidadCubiertaConvenio;
  		}
  		$scope.eventoRegistroFinalizarEvento.montoAntesDesvios=$scope.eventoFinalizarEventoForm.montoAntesDesvios;
  		$scope.eventoRegistroFinalizarEvento.totalDesvios=$scope.eventoFinalizarEventoForm.totalDesvios;//Gastos Observados
  		$scope.eventoRegistroFinalizarEvento.totalDesviosComprobados=$scope.eventoFinalizarEventoForm.totalDesviosComprobados;
  		$scope.eventoRegistroFinalizarEvento.ivaFinalizarEvento=$scope.eventoFinalizarEventoForm.ivaFinalizarEvento;
  		$scope.eventoRegistroFinalizarEvento.coaseguroFinalizarEvento=$scope.eventoFinalizarEventoForm.coaseguroFinalizarEvento;
  		$scope.eventoRegistroFinalizarEvento.deducibleFinalizarEvento=$scope.eventoFinalizarEventoForm.deducibleFinalizarEvento;
  		$scope.eventoRegistroFinalizarEvento.descuentoHospFinalizarEvento=$scope.eventoFinalizarEventoForm.descuentoHospFinalizarEvento;
  		//eventoFinalizarEventoForm		
  		$scope.eventoRegistroFinalizarEvento.numFacturasAprobadas=$scope.eventoFinalizarEventoForm.numFacturasAprobadas;
  		$scope.eventoRegistroFinalizarEvento.numFacturasRechazadas=$scope.eventoFinalizarEventoForm.numFacturasRechazadas;
  		$scope.eventoRegistroFinalizarEvento.montoDesviosFacturacion=$scope.eventoFinalizarEventoForm.montoDesviosFacturacion;
  		$scope.eventoRegistroFinalizarEvento.montoAjusteFacturacion=$scope.eventoFinalizarEventoForm.montoAjusteFacturacion;
  		$scope.eventoRegistroFinalizarEvento.montoFacturacionCorregido=$scope.eventoFinalizarEventoForm.montoFacturacionCorregido;	
  		$scope.eventoRegistroFinalizarEvento.montoFinalFacturacion=$scope.eventoFinalizarEventoForm.montoFinalFacturacion;
  		  		  		
  		delete Array.prototype.toJSON;	    	
  		$http.defaults.headers.post["Content-Type"] = "application/json";				
  		console.log("SAVE VALUES");
  		console.log($scope.eventoRegistroFinalizarEvento);
  		$http.post('mvc/evento/finalizarmontosevento',$scope.eventoRegistroFinalizarEvento).
  			success(function(data, status, headers, config) {
  		    	alert("Se finaliz\u00f3 correctamente el Evento!");		    	  
  		        location.reload(true); 
  		    }).error(function(data, status, headers, config) {
  		        // called asynchronously if an error occurs
  		        // or server returns response with an error status.
  		    	  alert("Error al finalizar el evento"+data);
  		    });				
  		//$scope.hideAltaSeguimientoMedicoForm();
  	};
  	
  	
  	$scope.launch = function(which){
  	    var dlg = null;
  	    switch(which){
  	        
  	      // Error Dialog
  	      case 'error':
  	        dlg = $dialogs.error('This is my error message');
  	        break;
  	        
  	      // Wait / Progress Dialog
  	      case 'wait':
  	        dlg = $dialogs.wait(msgs[i++],progress);
  	        fakeProgress();
  	        break;
  	        
  	      // Notify Dialog
  	      case 'notify':
  	        dlg = $dialogs.notify('Something Happened!','Something happened that I need to tell you.');
  	        break;
  	        
  	      // Confirm Dialog
  	      case 'confirm':
  	        dlg = $dialogs.confirm('Please Confirm','Is this awesome or what?');
  	        dlg.result.then(function(btn){
  	          $scope.confirmed = 'You thought this quite awesome!';
  	        },function(btn){
  	          $scope.confirmed = 'Shame on you for not thinking this is awesome!';
  	        });
  	        break;
  	    }; // end switch
    }; // end launch
   
    // for faking the progress bar in the wait dialog
    var progress = 25;
    var msgs = [
      'Hey! I\'m waiting here...',
      'About half way done...',
      'Almost there?',
      'Woo Hoo! I made it!'
    ];
    var i = 0;
    
    var fakeProgress = function(){
      $timeout(function(){
        if(progress < 100){
          progress += 25;
          $rootScope.$broadcast('dialogs.wait.progress',{msg: msgs[i++],'progress': progress});
          fakeProgress();
        }else{
          $rootScope.$broadcast('dialogs.wait.complete');
        }
      },1000);
    }; // end fakeProgress    
  
};

var Cat_ICD_Controller_Egreso = function ($scope,$http,$rootScope) {
	scope=$scope;	
	$rootScope.rowCollectionICD_Egreso={};
	$rootScope.rowCollectionCPT_Egreso={};
	$http.post('mvc/evento/geticds').
	  success(function(data, status, headers, config) {
	    // this callback will be called asynchronously
	    // when the response is available
		  $rootScope.rowCollectionICD_Egreso=data;
		  $http.post('mvc/evento/getcpts').
		  success(function(data2, status, headers, config) {
		    // this callback will be called asynchronously
		    // when the response is available
			  console.log("CPTS");
			  console.log(data2);
			  $rootScope.rowCollectionCPT_Egreso=data2;
		  }).
		  error(function(data, status, headers, config) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		 });
	  }).
	  error(function(data, status, headers, config) {
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	 });
	$rootScope.columnCollectionICD_Egreso = [
        {label: 'Id', map: 'idIcd'},
        {label: 'Descripci�n', map: 'descripcion'}
    ];    
	$rootScope.columnCollectionCPT = [
	      {label: 'Id', map: 'idCpt'},
	      {label: 'Descripci�n', map: 'descripcion'}
	     ];
    scope.globalConfig = {    	        
        isPaginationEnabled: true,
        isGlobalSearchActivated: true,        
        itemsByPage: 10,
        selectionMode: 'single',
        actions: {
            list: { url: '/GetUsers' },
            edit: { url: '/EditUser', title: 'Edit User', desc: 'Edit', iconClass: '' }, 
            add: { url: '/AddUser', title: 'Add User', buttonClass: 'pull-right', iconClass: 'icon-plus', desc: ' Add User' }, // TODO: zkontrolovat default description
            remove: { url: '/DelUser', title: 'Confirmation Dialog', msg: 'Do you really want to delete the user?' }
        }
    }    
    scope.messageEditarImplant="Implants";
  //keep track of selected items
    scope.$on('selectionChange', function (event, args) {    	
    	if (args.item.idIcd!=null){
    		$rootScope.idTipoIDCEgreso=args.item.idIcd;
    		$rootScope.tipoIDCEgreso=args.item.descripcion;
    	}
    	if (args.item.idCpt!=null){
    		if ($rootScope.tipoCPTSelec==1){
    			$rootScope.idTipoProced1=args.item.idCpt;
    			$rootScope.tipoProced1=args.item.descripcion;
    		}
    		if ($rootScope.tipoCPTSelec==2){
    			$rootScope.idTipoProced2=args.item.idCpt;
        		$rootScope.tipoProced2	=args.item.descripcion;
    		}
    	}
        console.log(args);        
    });
    
    scope.editarImplant = function (){    	
    	alert("Editar");
    };
    scope.eliminarImplant2 = function (){    	
    	alert("Editar");
    };
};

