var tHosp;
var tMedicos;
var tIcds;
var t;
var tVersus;
$(document).ready(function() {
	var param = getParameter("p");
	if (param==null)
		param=1;
	$("#showeventosby").val(param);
	validacionesAltaGasto();
	validacionesBitacoras();
	validacionesEgreso();
	validacionesFactura();
	$( "#fechaGastoEdoCta" ).datepicker();
	$( "#fechaGastoObs" ).datepicker();
	$( "#fechaGastoRel" ).datepicker();	
	$( "#fechaGastoEvitado" ).datepicker();	
	//$( "#fechaReporteMedico" ).datepicker();
	//$( "#fechaReporteMedicoEdit" ).datepicker();
	//$( "#fechaEgreso" ).datepicker();
	//$( "#fechaDefEgreso" ).datepicker();	
	altaValidacionesAll();			
    var statusEvento=["N/A","EN CURSO","EGRESADO","FINALIZADO"];    
    obtenerEventos(param);    
} );

function redirectParam(){
	location.href="/argal-web?p="+$("#showeventosby").val();
}
function obtenerEventos(param){
	console.log(param)	
	$("#tablaEventos").hide();
	$("#loading").show();		
	$.ajax({
  	  async:false,
  	  data:{"mesLimite":param},
  	  method:"GET",    	  
  	  url: "mvc/evento/geteventosltv2"
  	}).done(function(data) {
  		//console.log(data);  		
  		t=$("#tablaEventos").DataTable( { 
     		    "data": data,"order": [ 0, "desc" ],"bDestroy": true});       		
  		//console.log(data)
  		$("#tablaEventos").show();
  		$("#loading").hide();
  	}
  );   
}
function verEventos(){
	$("#contenidoMenu").hide();
	$("#contenidoTablaEventos").show();	
}

function showHome(){
	$("#contenidoMenu").show();
	$("#contenidoTablaEventos").hide();	
}
function calcularDias(fecha1, fecha2){
	var dias=0;
	return dias;
}
function obtenerEstadoActual(bitacoras){
	var estados=["algo","algo2"];
	return estados[i];
}
function obtenerMontoActual(statusEvento,gastos){
	var monto=0.0;
	return monto;
}

function verBitacoras(idEvento){	
	$("#loadBitacorasGif").show();
	$("#idEventoHidBit").val(idEvento);	
	$( "#divBitacoras" ).dialog({
			 resizable: false,
		      height: 600,
		      width: 1200,
		      position: { my: "top", at: "top", of: window  },
		      modal: true
	});	
	$('div#divBitacoras').on('dialogclose', function(event) {
	    limpiarFormGastosBitacoras();
	    window.location="/argal-web?p=1/";
	});
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/getbitacorasv2"
   	}).done(function(data) {
   		$("#loadBitacorasGif").hide();
   		$("#divBitacorasContenido").show();
   		console.log(data.length)
   		var Evento=data[0];
   		$("#idEventoBarBit").html("<font color='#46C2BC'>Id Evento:</font>"+data[0].idEvento);
   		$("#titularEventoBarBit").html("<font color='#46C2BC'>Titular:</font>"+data[0].registroSeguroPersonal.nombreTitular+" "+data[0].registroSeguroPersonal.appTitular+" "+data[0].registroSeguroPersonal.apmTitular);
   		$("#pacienteEventoBarBit").html("<font color='#46C2BC'>Paciente:</font>"+data[0].registroSeguroPersonal.nombrePaciente+" "+data[0].registroSeguroPersonal.appPaciente+" "+data[0].registroSeguroPersonal.apmPaciente);
   		$("#tipoClienteEventoBarBit").html("<font color='#46C2BC'>Tipo Cliente:</font> BANCO");
   		$("#clienteEventoBarBit").html("<font color='#46C2BC'>Cliente:</font> "+data[0].cliente.nombreCorto); 
   		$("#numAutorizacionEventoBarBit").html("<font color='#46C2BC'>No. Autorización:</font> "+data[0].registroSeguroPersonal.numAutorizacion);   		
   		$("#tablaBitacoras").DataTable( { 
     		    "data": data[1],"order": [ 0, "desc" ],"bDestroy": true});		
   		console.log(data);   		
   	});	
}

function verGastos(idEvento){
	$("#loadGastosGif").show();
	$("#tituloGastos").hide();
	$("#tabs").hide();
	$("#divRegistroGasto").show();
	var causascargos=[["NA"],['NO UTILIZADO'],['DUPLICADO'],['NO INDICADO POR DIAGN\u00d3STICO'],
    	       	 ['NO APARECE EN INDICACIONES M\u00c9DICAS'],
    	       	 ['idCausaCargo'],['COSTO INDEBIDO']
    	];		
	var tiposcargoedocuenta=[['N/A'],
    	    	 ['ESTADO DE CUENTA PARCIAL'],	         
    	         ['ESTADO DE CUENTA FINAL'],
    	         ['ESTADO DE CUENTA DE EGRESO']
    ];
	var tiposcargos=[['N/A'],
    	         ['ESTADO DE CUENTA'],	         
    	         ['CARGOS OBSERVADOS'],
    	         ['DESGLOSE DE GASTOS RELEVANTES']
				 ['GASTOS EVITADOS']
        ];
	var areascargosEdoCuenta=[['N/A'],
    	         ['ESTADO DE CUENTA PARCIAL'],
    	         ['ESTADO DE CUENTA FINAL']
    	];
	
	var areascargos=[['N/A'],
    	         ['PISO'],
    	         ['QUIROFANO'],
    	         ['TERAPIA INTENSIVA, INTERMEDIA, NEONATAL'],
    	         ['URGENCIAS'],
    	         ['GASTOS PERSONALES'],
    	 ];	
	
	var rubroscargos=[['N/A'],  	 
    	      	 ['MEDICAMENTOS'],
    	       	 ['MATERIAL'],
    	      	 ['LABORATORIO Y RX'],
    	      	 ['TERAPIA RESPIRATORIA'],
    	      	 ['HABITACIONES'],
    	         ['ANESTESIA (MAQ Y GAS)'],
    	         ['RENTAS DE EQUIPOS'],
    	         ['INSUMOS PROVEEDOR EXTERNO'],
    	         ['CUBICULOS'],
    	         ['TERAPIA INTENSIVA'],
    	         ['BANCO DE SANGRE'],
    	         ['QUIROFANO'],
    	         ['TIEMPOS DE SALA']	  
    	         ];
	$( "#tabs" ).tabs();
	$( "#divGastos" ).dialog({
		 resizable: false,
	      height: 600,
	      width: 1250,
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$('div#divGastos').on('dialogclose', function(event) {
	     limpiarFormGastos();
	     window.location="/argal-web?p=1/";
	 });
	$(window).resize(function() {
	   $( "#divGastos" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	//tEdoCta=$("#tablaEdosCuenta").DataTable();
	//tEdoCta.clear().draw();	
	//tCObs=$("#tablaCargosObservados").DataTable();
	//tCObs.clear().draw();
	//tGRel=$("#tablaGastosRelevantes").DataTable();
	//tGRel.clear().draw();	
	$("#idEventoHid").val(idEvento);
	$("#idEventoHidden").val(idEvento);	
	$("#idEventoHidden2").val(idEvento);
	$("#idEventoHidden3").val(idEvento);
		
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/getgastosv2"
   	}).done(function(data) {
   		$("#loadGastosGif").hide();
   		$("#tabs").show();
   		$("#tituloGastos").show();
   		console.log(data[0])
   		var Evento=data[0];
   		$("#idEventoBar").html("<font color='#46C2BC'>Id Evento:</font>"+data[0].idEvento);
   		if (data[0].registroSeguroPersonal.condicionPaciente=="JUBILADO"){
   			$("#esJubilado").val("1");	
   		}
   		else
   			$("#esJubilado").val("0");
   		$("#titularEventoBar").html("<font color='#46C2BC'>Titular:</font>"+data[0].registroSeguroPersonal.nombreTitular+" "+data[0].registroSeguroPersonal.appTitular+" "+data[0].registroSeguroPersonal.apmTitular);
   		$("#pacienteEventoBar").html("<font color='#46C2BC'>Paciente</font>:"+data[0].registroSeguroPersonal.nombrePaciente+" "+data[0].registroSeguroPersonal.appPaciente+" "+data[0].registroSeguroPersonal.apmPaciente);
   		$("#tipoClienteEventoBar").html("<font color='#46C2BC'>Tipo Cliente:</font> BANCO");
   		$("#clienteEventoBar").html("<font color='#46C2BC'>Cliente:</font> "+data[0].cliente.nombreCorto);
   		$("#numAutorizacionEventoBar").html("<font color='#46C2BC'>No. Autorización:</font> "+data[0].registroSeguroPersonal.numAutorizacion);
   		$("#idHospitalHid").val(data[0].hospital.idHospital);
   		$("#idClienteHid").val(data[0].cliente.idCliente);
   		$("#nameHospitalHid").val(data[0].hospital.nombreHospital);
   		console.log("CHECAR:"+$("#nameHospitalHid").val());
   		$("#nameClienteHid").val(data[0].cliente.nombreCorto);
   		var edosCta=data[1];
   		var gastosObs=data[2];
   		var gastosRelev=data[3];
   		var gastosEvitados=data[4];
   		console.log("VALOR");
		console.log(edosCta)		
   		//Se cargan los edos de cuenta
			   		
		$("#totalEdoCtaEventoBar").html("<font color='#46C2BC'>Total Estado de Cuenta:</font>$"+data[0].montoAntesDesvios);		
		if (edosCta.length<=0){			
			$("#totalEdoCtaEventoBar").html("<font color='#46C2BC'>Total Estado de Cuenta:</font>$"+"0.0");
		}
	   	
		$("#tablaEdosCuenta").DataTable( { 
   			"data": edosCta,"order": [ 0, "desc" ],"columnDefs": [
   		  { "width": "30px", "targets": 0 },
   		],"bDestroy": true});				
		
		var sumaCargsObs=0;
		$("#totalCargObsEventoBar").html("<font color='#46C2BC'>Total Gastos Observados:</font>$"+data[0].totalDesvios);
		if (gastosObs.length<=0){
			console.log("GASTOS")
			$("#totalCargObsEventoBar").html("<font color='#46C2BC'>Total Gastos Observados:</font> $0.0");
		}
		
		$("#tablaCargosObservados").DataTable( { 
			"data": gastosObs,"order": [ 0, "desc" ],"columnDefs": [
		   	{ "width": "30px", "targets": 0 }
		],"bDestroy": true});					   								
   		
		$("#tablaGastosRelevantes").DataTable( { 
				"data": gastosRelev,"order": [ 0, "desc" ],"columnDefs": [
				           { "width": "30px", "targets": 0 }
				],"bDestroy": true});
		
		$("#tablaGastosEvitados").DataTable( { 
			"data": gastosEvitados,"order": [ 0, "desc" ],"columnDefs": [
			           { "width": "30px", "targets": 0 }
			],"bDestroy": true});
		
		if (gastosEvitados.length>0){
			console.log(gastosEvitados)
			var totGEvitados=0;
			for (h=0; h<gastosEvitados.length; h++){				
				totGEvitados=totGEvitados+parseFloat(gastosEvitados[h][2]);
			}			
			$("#totalGastosEvitadosEventoBar").html("<font color='#46C2BC'>Total Gastos Evitados:</font>$"+totGEvitados);
		}
		else
			$("#totalGastosEvitadosEventoBar").html("<font color='#46C2BC'>Total Gastos Evitados:</font>$0.0");
		
		if (gastosRelev.length>0){
			var totGRel=0;
			console.log(gastosRelev)
			for (k=0; k<gastosRelev.length; k++){
				//console.log("GRLEV")
				//console.log(parseInt(gastosRelev[k][3]))
				totGRel=totGRel+parseFloat(gastosRelev[k][3]);
			}			
			$("#totalGastosRelEventoBar").html("<font color='#46C2BC'>Total Gastos Relevantes:</font>$"+totGRel);
		}
		else
			$("#totalGastosRelEventoBar").html("<font color='#46C2BC'>Total Gastos Relevantes:</font> $0.0");
		
   		console.log(data)
   	}
   );
}

function verEgreso(idEvento){
	$("#loadEgresoGif").show();
	$("#divEgresoContenido").hide();
	$("#idEventoEgreso").val(idEvento);	
	var motivosEgreso=[["N/A"],['ALTA POR MEJORIA CLINICA'],['ALTA VOLUNTARIA'],['ALTA POR TRASLADO A OTRA UNIDAD HOSPITALARIA'],
	       	       	 ['DEFUNCION']];
	$( "#divEgreso" ).dialog({
		 resizable: false,
	      height: 600,
	      width: 1200,
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});		
	 $(window).resize(function() {
	        $( "#divEgreso" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	    });
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/obtenereventobyidv2"
   	}).done(function(data) { 
   		$("#loadEgresoGif").hide();
   		$("#divEgresoContenido").show();
   		console.log(eval(data));
   		$("#idEventoBarEgr").html("<font color='#46C2BC'>Id Evento:</font>"+data.idEvento);
   		$("#titularEventoBarEgr").html("<font color='#46C2BC'>Titular:</font>"+data.registroSeguroPersonal.nombreTitular+" "+data.registroSeguroPersonal.appTitular+" "+data.registroSeguroPersonal.apmTitular);
   		$("#pacienteEventoBarEgr").html("<font color='#46C2BC'>Paciente:</font>"+data.registroSeguroPersonal.nombrePaciente+" "+data.registroSeguroPersonal.appPaciente+" "+data.registroSeguroPersonal.apmPaciente);
   		$("#tipoClienteEventoBarEgr").html("<font color='#46C2BC'>Tipo Cliente:</font> BANCO");
   		$("#clienteEventoBarEgr").html("<font color='#46C2BC'>Cliente:</font> "+data.cliente.nombreCorto); 
   		$("#numAutorizacionEventoBarEgr").html("<font color='#46C2BC'>No. Autorización:</font> "+data.registroSeguroPersonal.numAutorizacion);
   		
   		if (data.fechaEgreso!=null && data.fechaEgreso!=""){
   			var anioTmp=new Date(data.fechaEgreso).getFullYear();
			var mesTmp=(new Date(data.fechaEgreso).getMonth()+1);
			if (mesTmp<10)
				mesTmp="0"+mesTmp;
			var diaTmp=new Date(data.fechaEgreso).getDate();
			if (diaTmp<10)
				diaTmp="0"+diaTmp;			
			var fechaEgr=anioTmp+"-"+mesTmp+"-"+diaTmp;			
	   		
	   		$("#fechaEgreso").val(fechaEgr);
	   		$("#horaEgreso").val(data.horaEgreso);
	   		if (data.diagnosticoEgreso1!=null){
	   			$("#icd3").val(data.diagnosticoEgreso1.descripcion);
	   			$("#idIcd3").val(data.diagnosticoEgreso1.idIcd);
	   		}
	   		if (data.procedimiento1!=null){
	   			$("#cpt1").val(data.procedimiento1.descripcion);
	   			$("#idCpt1").val(data.procedimiento1.idCpt);
	   		}
	   		if (data.procedimiento2!=null){
	   			$("#cpt2").val(data.procedimiento2.descripcion);
	   			$("#idCpt2").val(data.procedimiento2.idCpt);
	   		}
	   		$("#eventosNoDeseables").val(data.eventosNoDeseablesEntornoHosp);
	   		$("#motivoEgreso").val(data.motivoEgreso);
	   		$("#diasIncapacidad").val(data.diasIncapacidad);   		
	   	}
   	}
   );	
}

function verEditar(idEvento){
	$( "#divEditar" ).dialog({
		 resizable: false,
	      height: 800,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
	        $( "#divEditar" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	loadEvento(idEvento);
}

function verBalance(idEvento){
	$("#loadFinalizarGif").show();
	$("#divFinalizarMain").hide();
	limpiarFacturaForm();
	$("#idEventoFinalizarHid").val(idEvento);
	$("#idEventoHidden4").val(idEvento);	
	$( "#tabsFinalizar" ).tabs();
	$( "#divFinalizar" ).dialog({
		 resizable: false,
	      height: 600,
	      width: 1200,
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});		
	 $(window).resize(function() {
	        $( "#divFinalizar" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	    });
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/obtenereventobyidv2"
   	}).done(function(data) { 
   		$("#loadFinalizarGif").hide();
   		if (data.registroSeguroPersonal!=null){
   			if (data.registroSeguroPersonal.condicionPaciente=='JUBILADO'){   			
   				$("#jubiladoCenso").show();   			
   			}
   			else
   				$("#jubiladoCenso").hide();
   		}
   		$("#divFinalizarMain").show();
   		console.log(data)   		
   		tFactAprob=$("#facturasAprobadasTabla").DataTable();
   		tFactRech=$("#facturasRechazadasTabla").DataTable();
   		$("#idEventoBarBal").html("<font color='#46C2BC'>Id Evento:</font>"+data.idEvento);
   		$("#titularEventoBarBal").html("<font color='#46C2BC'>Titular:</font>"+data.registroSeguroPersonal.nombreTitular+" "+data.registroSeguroPersonal.appTitular+" "+data.registroSeguroPersonal.apmTitular);
   		$("#pacienteEventoBarBal").html("<font color='#46C2BC'>Paciente:</font>"+data.registroSeguroPersonal.nombrePaciente+" "+data.registroSeguroPersonal.appPaciente+" "+data.registroSeguroPersonal.apmPaciente);
   		$("#tipoClienteEventoBarBal").html("<font color='#46C2BC'>Tipo Cliente:</font> BANCO");
   		$("#clienteEventoBarBal").html("<font color='#46C2BC'>Cliente:</font> "+data.cliente.nombreCorto); 
   		$("#numAutorizacionEventoBarBal").html("<font color='#46C2BC'>No. Autorización:</font> "+data.registroSeguroPersonal.numAutorizacion);   		
   		$("#divMontoAntesDesvios").html(data.montoAntesDesvios);
		$("#divFacturasAprobadas").html(data.numFacturasAprobadas);
		$("#divTotalCargosObservados").html(data.totalDesvios);
		$("#divFacturasRechazadas").html(data.numFacturasRechazadas);
		$("#divMontoTotalDesviosFacturacion").html(data.montoDesviosFacturacion);
		$("#divMontoDespuesDesvios").html(data.montoAntesDesvios-data.totalDesvios);
		$("#divMontoFinalFacturacion").html(data.montoFinalFacturacion);		
		$("#desviosComprobados").val(data.totalDesviosComprobados);				
		$("#montoAjusteFacturacion").val(data.montoAjusteFacturacion);
		$("#ivaFinalizarEvento").val(data.ivaFinalizarEvento);
		$("#montoFacturacionCorregido").val(data.montoFacturacionCorregido);
		$("#coaseguroFinalizarEvento").val(data.coaseguroFinalizarEvento);		
		//$("#deducibleFinalizarEvento").val(data.deducibleFinalizarEvento);
		$("#deducibleFinalizarEvento").val(0);
		$("#descuentoHospFinalizarEvento").val(data.descuentoHospFinalizarEvento);
		if (data.registroSeguroPersonal!=null){
			$("#capitaCantidadFinalizarEvento").val(data.registroSeguroPersonal.cantidadCubiertaConvenio);		
			$("#capitaFinalizarEvento").val(data.registroSeguroPersonal.capita);
		}

   			tFactAprob.clear().draw();
			tFactRech.clear().draw();
   			for (i=0;i<data.facturas.length;i++){   				
   				if (data.facturas[i].aprobada=="SI"){
   					tFactAprob.row.add([
   					     data.facturas[i].numeroFactura,
   					     data.facturas[i].monto,
   					     "<a href='#' onclick='ajax_downloadFactura(" +data.facturas[i].idFactura+",\""+data.facturas[i].rutaFactura+"\")'>ver archivo</a>",
   					     "<a href='#' onclick='eliminarFactura("+data.facturas[i].idFactura+",1)'>Eliminar</a>"
   						]).draw( false );
   				}
   				else{
   					tFactRech.row.add([
   					     data.facturas[i].numeroFactura,
                         data.facturas[i].monto,
                         "<a href='#' onclick='ajax_downloadFactura(" +data.facturas[i].idFactura+",\""+data.facturas[i].rutaFactura+"\")'>ver archivo</a>",
   					     "<a href='#' onclick='eliminarFactura("+data.facturas[i].idFactura+",2)'>Eliminar</a>"
   				   		]).draw( false );
   				}   					   				
   			}
   		
   		$( "#montoAntesDesviosDiv").html(data.montoAntesDesvios);
   		$( "#numFactAprobDiv").html(data.numFacturasAprobadas);
   		$( "#totalCargObsDiv").html(data.totalDesvios);
   		$( "#numFactRechDiv").html(data.numFacturasRechazadas);
   		$( "#desviosCompDiv").html(data.totalDesvios);
   		$( "#montoTotalFactDiv").html(data.montoFinalFacturacion);
   		$( "#montoDespDesviosDiv").html(data.montoDesviosFacturacion);
   		$( "#montoAjusteFactDiv").html(data.montoAjusteFacturacion);
   		$( "#ivaDiv").html(data.ivaFinalizarEvento);
   		$( "#montoFactCorregidoDiv").html(data.montoComprobanteFiscalCorregido);
   		$( "#coaseguroDiv").html(data.coaseguroFinalizarEvento);
   		$( "#montoFinalFactuDiv").html(data.montoFinalFacturacion);
   		//$( "#deducibleDiv").html(data.deducibleFinalizarEvento);
   		$( "#deducibleDiv").html(0);
   		$( "#descHospDiv").html(data.descuentoHospFinalizarEvento);
   	}
   );
}

function eliminarEvento(idEvento){
	$("#divEventoEliminar").html(idEvento);
	 $( "#dialog-confirm" ).dialog({
	      resizable: false,
	      width:400,
	      height:240,
	      modal: true,
	      buttons: {
	        "Eliminar": function() {	        	
	        	$.ajax({
	        		async:false,
	        		method:"post",
	           	  	data:{"idEvento":idEvento},
	           	  	url: "mvc/evento/eliminareventov2"
	           	}).done(function(data) {
	           		$("#dialog-confirm").dialog( "close" );
	           		showMensaje("Se eliminó correctamente el evento!");	
	           		}
	           	);	           	
	        },
	        "Cancelar": function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
}
function loadPage(page){
	//location.href="views/v2.0/eventos/alta_evento.jsp";
	location.href="mvc/index/getpage?page="+page;
}

function showMensaje(mensaje){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web?p=1/";
	        }
	      }
	});
}

function showMensajeSR(mensaje){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message" ).dialog({
		position: { my: "top", at: "top", of: window  },
	    modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );	          
	        }
	      }
	});
$(window).resize(function() {
      $( "#dialog-message" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
});
}

function showMensajeSREliminar(mensaje,idEvento){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {	        	
	          $( this ).dialog( "close" );	          
	          verGastos(idEvento);	          
	        }
	      }
	});
}

function showDivGasto(){	
	
	if ($("#tipoRegistroGasto").val()=="1"){
		$("#divRegEdoCta").show();
		$("#divRegGastoObs").hide();
		$("#divGastosRelev").hide();
		$("#divGastosEvitados").hide();
	}
	if ($("#tipoRegistroGasto").val()=="2"){
		$("#divRegGastoObs").show();
		$("#divRegEdoCta").hide();
		$("#divGastosRelev").hide();
		$("#divGastosEvitados").hide();
	}
	if ($("#tipoRegistroGasto").val()=="3"){
		$("#divGastosRelev").show();
		$("#divRegGastoObs").hide();
		$("#divRegEdoCta").hide();
		$("#divGastosEvitados").hide();
	}
	if ($("#tipoRegistroGasto").val()=="4"){
		$("#divGastosRelev").hide();
		$("#divRegGastoObs").hide();
		$("#divRegEdoCta").hide();
		$("#divGastosEvitados").show();
		
	}
	if ($("#tipoRegistroGasto").val()==""){		
		$("#divGastosRelev").hide();
		$("#divRegGastoObs").hide();
		$("#divRegEdoCta").hide();
		$("#divGastosEvitados").hide();
	}
}

function validacionesAltaGasto(){
	$.formUtils.addValidator({
		  name : 'edoctaval',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando");
			  if ($("#tipoRegistroGasto").val()=="1"){
				  if (value=="")
					  return false;
				  return true;
			  }	    
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
		});

	$.formUtils.addValidator({
		  name : 'gastobsval',
		  validatorFunction : function(value, $el, config, language, $form) {
			  console.log(value);
			  if ($("#tipoRegistroGasto").val()=="2"){
				  if (value=="")
					  return false;
				  return true;
			  }	    
		  },
		  errorMessage : 'Seleccione',
		  errorMessageKey: 'badEvenNumber'
		});
	$.formUtils.addValidator({
		  name : 'gastrelval',
		  validatorFunction : function(value, $el, config, language, $form) {
			  console.log(value);
			  if ($("#tipoRegistroGasto").val()=="3"){
				  if (value=="")
					  return false;
				  return true;
			  }	    
		  },
		  errorMessage : 'Seleccione',
		  errorMessageKey: 'badEvenNumber'
		});
	
	$.formUtils.addValidator({
		  name : 'gastevitadoval',
		  validatorFunction : function(value, $el, config, language, $form) {
			  console.log(value);
			  if ($("#tipoRegistroGasto").val()=="4"){
				  if (value=="")
					  return false;
				  return true;
			  }	    
		  },
		  errorMessage : 'Seleccione',
		  errorMessageKey: 'badEvenNumber'
		});
}

function guardarGastoEdoCta(){
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);	
	var fileName=document.getElementById("file1").value;
	var esJubilado=false;
	if ($("#esJubilado").val()=="1"){
		esJubilado=true;
	}
	if (fileName!=""){
		$.ajax({
			async:false,		
			data:{
				"idGasto":$("#idEventoHid").val(),
				"idTipoCargo":1,
				"idArea":$("#tipoEdoCuenta").val(),
				"idRubro":1,
				"idRazon":1,				
				"cantidad":1,
				"rutaEvidencia":fileName,
				"nombre":"ESTADO DE CUENTA",
				"montoUnitario":$("#montoGasto").val(),
				"fechaIngreso":$("#fechaGastoEdoCta").val()
			},
			dataType:'json',
			url:   'mvc/evento/guardargastov2',
			type:  'post',		
			beforeSend: function () {
				//$("#resultado").html("Procesando, espere por favor..."),
				//alert("OK!")
				$("#divRegEdoCta").hide();
				$("#loadingEdoCta").show();
			},
			success:  function (response) {
				uploadJqueryFormEvidencia("form1",1,$("#idEventoHid").val(),esJubilado);			
			},	
			error: function (response) {																	
				showMensaje("Se registró un error al guardar el gasto!");
				console.log(response)
			}		
		});	
	}
	else
		showMensajeSR("Por favor adjunte la evidencia para registrar el Estado de Cuenta.");	
}

function guardarGastoObs(){
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);	
	var fileName=document.getElementById("file2").value;
	if (fileName!=""){
		$.ajax({
			async:false,		
			data:{
				"idGasto":$("#idEventoHid").val(),
				"idTipoCargo":2,
				"idArea":$("#areaGastoObs").val(),
				"idRubro":$("#rubroGastoObs").val(),
				"idRazon":$("#causaGastoObs").val(),				
				"cantidad":$("#cantidadGastoObs").val(),
				"rutaEvidencia":fileName,
				"nombre":$("#productoGastoObs").val(),
				"montoUnitario":$("#montoGastoObs").val(),
				"fechaIngreso":$("#fechaGastoObs").val()
			},
			dataType:'json',
			url:   'mvc/evento/guardargastov2',
			type:  'post',		
			beforeSend: function () {
				//$("#resultado").html("Procesando, espere por favor..."),
				//alert("OK!")
				 $("#divRegGastoObs").hide();
				 $("#loadingEdoCta").show();
			},
			success:  function (response) {
				uploadJqueryFormEvidencia("form2",2,$("#idEventoHid").val());			
			},	
			error: function (response) {																	
				showMensaje("Se registró un error al guardar el gasto!");
				console.log(response)
			}		
		});	
	}
	else
		showMensajeSR("Por favor adjunte la evidencia para registrar el gasto observado.");	
}

function guardarGastoRel(){
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);	
	var fileName=document.getElementById("file3").value;
	if (fileName!=""){
		$.ajax({
			async:false,		
			data:{
				"idGasto":$("#idEventoHid").val(),
				"idTipoCargo":3,
				"idArea":$("#areaGastoRel").val(),
				"idRubro":1,
				"idRazon":1,				
				"cantidad":1,
				"rutaEvidencia":fileName,
				"nombre":"GASTO RELEVANTE",
				"montoUnitario":$("#montoGastoRel").val(),
				"fechaIngreso":$("#fechaGastoRel").val()
			},
			dataType:'json',
			url:   'mvc/evento/guardargastov2',
			type:  'post',		
			beforeSend: function () {
				$("#loadingEdoCta").show();
				$("#divGastosRelev").hide();
			},
			success:  function (response) {
				uploadJqueryFormEvidencia("form3",3,$("#idEventoHid").val());			
			},	
			error: function (response) {																	
				showMensaje("Se registró un error al guardar el gasto relevante!");
				console.log(response)
			}		
		});	
	}
	else
		showMensajeSR("Por favor adjunte la evidencia para registrar el gasto relevante.");	
	
}

function guardarGastoEvitado(){	
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);		
		$.ajax({
			async:false,		
			data:{
				"idGasto":$("#idEventoHid").val(),
				"idTipoCargo":4,
				"idArea":1,
				"idRubro":1,
				"idRazon":1,				
				"cantidad":1,
				"rutaEvidencia":$("#justificacionGastoEvitado").val(),
				"nombre":"GASTO EVITADO",
				"montoUnitario":$("#montoGastoEvitado").val(),
				"fechaIngreso":$("#fechaGastoEvitado").val()
			},
			dataType:'json',
			url:   'mvc/evento/guardargastov2',
			type:  'post',		
			beforeSend: function () {
				$("#divGastosEvitados").hide();
			},
			success:  function (response) {
				//uploadJqueryFormEvidencia("form3",3,$("#idEventoHid").val());
				updateGastosModal($("#idEventoHid").val());
				showMensajeSR("El gasto se registró correctamente!");
	    		limpiarFormGastos();
			},	
			error: function (response) {																	
				showMensaje("Se registró un error al guardar el gasto evitado!");
				console.log(response)
			}		
		});		
	
}

function eliminarGasto(idGasto){		
	$( "#dialog-confirm2" ).dialog({
	      resizable: false,
	      width:400,
	      height:240,
	      modal: true,
	      buttons: {
	        "Eliminar": function() {	        	
	        	$.ajax({
	        		async:false,		
	        		data:{
	        			"id":$("#idEventoHid").val(),
	        			"idGasto":idGasto
	        		},
	        		dataType:'json',
	        		url:   'mvc/evento/eliminargastov2',
	        		type:  'post',		
	        		beforeSend: function () {
	        			//$("#resultado").html("Procesando, espere por favor..."),
	        			//alert("OK!")
	        			$( "#dialog-confirm2" ).dialog( "close" );
	        		},
	        		success:  function (response) {
	        			showMensajeSREliminar("Se eliminó correctamente el gasto!",$("#idEventoHid").val());	        				        				        		        			
	        		},	
	        		error: function (response) {
	        			$( "#dialog-confirm2" ).dialog( "close" );
	        			showMensajeSR("Se registró un error al guardar el gasto!");	        				        				        		
	        			console.log(response)
	        		}		
	        	});	           	
	        },
	        "Cancelar": function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
		
	
}

function uploadJqueryFormEvidencia(form,idform,idEvento,esJubilado){	   
	   $('#result').html('');
	   document.getElementById("waitUpload").style.display="block";	   	   	  
	   $("#"+form).ajaxForm({
	    success:function(data) { 
	    	  $("#loadingEdoCta").hide();
	          //$('#result').html(data);
	    	  if (idform==1)
	    		  $("#divRegEdoCta").show();
	    	  if (idform==2)
	    		  $("#divRegGastoObs").show();
	    	  if (idform==3)
	    		  $("#divGastosRelev").show();
	    	  document.getElementById("waitUpload").style.display="none";
	    	  updateGastosModal(idEvento);
	    	  if ($('#usarVersus').val()==1){
	    		  showMensajeSR("El estado de cuenta se registró correctamente, a continuación se realizará el comparativo!");
	    		  $('#usarVersus').val(0);
	    		  console.log(data)
	    		  activarVersus(data,$("#idHospitalHid").val(),$("#idClienteHid").val(),esJubilado);	    	   		
	    	  }
	    	  else{
	    		  showMensajeSR("El gasto se registró correctamente!");
	    		  limpiarFormGastos();
	    	  }
	    	  return false;
	     },dataType:"text",
	     error:function(data) {
	    	 showMensajeSR("Error al registrar el gasto!");
	    	 return false;
	     }  
	   }).submit();
	}

function ajax_download2(id,id2) {	
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

function getParameter(parameter){
	// Obtiene la cadena completa de URL
	var url = location.href;
	/* Obtiene la posicion donde se encuentra el signo ?, 
	ahi es donde empiezan los parametros */
	var index = url.indexOf("?");
	/* Obtiene la posicion donde termina el nombre del parametro
	e inicia el signo = */
	index = url.indexOf(parameter,index) + parameter.length;
	/* Verifica que efectivamente el valor en la posicion actual 
	es el signo = */ 
	if (url.charAt(index) == "="){
	// Obtiene el valor del parametro
	var result = url.indexOf("&",index);
	if (result == -1){result=url.length;};
	// Despliega el valor del parametro
	return url.substring(index + 1,result);
	}
} 

function updateGastosModal(idEvento){
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/getgastosv2"
   	}).done(function(data) {
   		console.log(data[0])
   		var Evento=data[0];
   		$("#idEventoBar").html("<font color='#46C2BC'>Id Evento:</font>"+data[0].idEvento);
   		$("#titularEventoBar").html("<font color='#46C2BC'>Titular:</font>"+data[0].registroSeguroPersonal.nombreTitular+" "+data[0].registroSeguroPersonal.appTitular+" "+data[0].registroSeguroPersonal.apmTitular);
   		$("#pacienteEventoBar").html("<font color='#46C2BC'>Paciente</font>:"+data[0].registroSeguroPersonal.nombrePaciente+" "+data[0].registroSeguroPersonal.appPaciente+" "+data[0].registroSeguroPersonal.apmPaciente);
   		$("#tipoClienteEventoBar").html("<font color='#46C2BC'>Tipo Cliente:</font> BANCO");
   		$("#clienteEventoBar").html("<font color='#46C2BC'>Cliente:</font> "+data[0].cliente.nombreCorto);
   		$("#numAutorizacionEventoBar").html("<font color='#46C2BC'>No. Autorización:</font> "+data[0].registroSeguroPersonal.numAutorizacion);
   		$("#totalEdoCtaEventoBar").html("<font color='#46C2BC'>Total Estado de Cuenta:</font>$"+data[0].montoAntesDesvios);   		
   		
   		var edosCta=data[1];
   		var gastosObs=data[2];
   		var gastosRelev=data[3];
   		var gastosEvitados=data[4];
   		
   		if (gastosEvitados.length>0){
			console.log(gastosEvitados)
			var totGEvitados=0;
			for (h=0; h<gastosEvitados.length; h++){				
				totGEvitados=totGEvitados+parseFloat(gastosEvitados[h][2]);
			}			
			$("#totalGastosEvitadosEventoBar").html("<font color='#46C2BC'>Total Gastos Evitados:</font>$"+totGEvitados);
		}
		else
			$("#totalGastosEvitadosEventoBar").html("<font color='#46C2BC'>Total Gastos Evitados:</font>$0.0");
   		
   		$("#tablaGastosEvitados").DataTable( { 
			"data": gastosEvitados,"order": [ 0, "desc" ],"columnDefs": [
			           { "width": "30px", "targets": 0 }
			],"bDestroy": true});

   		console.log("VALOR");
		console.log(edosCta)		
   		//Se cargan los edos de cuenta
		if (edosCta.length>0){	   		
			$("#totalEdoCtaEventoBar").html("<font color='#46C2BC'>Total Estado de Cuenta:</font>$"+data[0].montoAntesDesvios);
	   		$("#tablaEdosCuenta").DataTable( { 
   				"data": edosCta,"order": [ 0, "desc" ],"columnDefs": [
   			  { "width": "30px", "targets": 0 },
   		    ],"bDestroy": true});				
		}
		else
			$("#totalEdoCtaEventoBar").html("<font color='#46C2BC'>Total Estado de Cuenta:</font>$"+"0.0");
		var sumaCargsObs=0;
		if (gastosObs.length>0){
			console.log("GASTOS")			
		   	$("#tablaCargosObservados").DataTable( { 
		   		"data": gastosObs,"order": [ 0, "desc" ],"columnDefs": [
		   		                    { "width": "30px", "targets": 0 }
		   	            ],"bDestroy": true});					   					
		   	$("#totalCargObsEventoBar").html("<font color='#46C2BC'>Total Cargos Observados:</font>$"+data[0].totalDesvios);
   		}
		else
			$("#totalCargObsEventoBar").html("<font color='#46C2BC'>Total Cargos Observados:</font> $0.0");		
		if (gastosRelev.length>0){
			var totGRel=0;
			for (k=0; k<gastosRelev.length; k++){
				console.log("GRLEV")				
				totGRel=totGRel+parseInt(gastosRelev[k][3]);
			}
			$("#tablaGastosRelevantes").DataTable( { 
   				"data": gastosRelev,"order": [ 0, "desc" ],"columnDefs": [
   				           { "width": "30px", "targets": 0 }
   				],"bDestroy": true});
			$("#totalGastosRelEventoBar").html("<font color='#46C2BC'>Total Gastos Relevantes:</font>$"+totGRel);
		}
		else
			$("#totalGastosRelEventoBar").html("<font color='#46C2BC'>Total Gastos Relevantes:</font> $0.0");
		
   		console.log(data)
   	}
   );
}

function activarVersus(idGasto,idHospital,idCliente,esJubilado){	
	$("#divRegEdoCta").hide();
	$("#divRegistroGasto").hide();	
	$("#divRegEdoCta").hide();
	$("#divVersus").show();		
	$.ajax({
		async:false,		
		data:{
			"idGasto":idGasto,
			"idHospital":idHospital,
			"idCliente":idCliente,
			"jubilado":esJubilado
		},
		dataType:'json',
		url:   'mvc/versus/doversus',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),			
		},
		success:  function (data) {
			 tVersus=$("#tablaResultadoVersus").DataTable( { 
     		    "data": data[3],"order": [ 7, "desc" ],"bDestroy": true});
			console.log(data)	
			$("#subTotalVersus").html("SubTotal:$"+data[0]);
			$("#desviosVersus").html("SubTotal con Descuento:$"+data[1]);
			$("#DifTotalDesviosVersus").html("Diferencia:$"+data[2]);
			$('#tablaResultadoVersus tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');			  
			} );
			$("#finalizarVersusButton").show();
		},	
		error: function (response) {				        				        				        	
			console.log(response)
		}		
	});
	
}

function guardarVersus(){
	var datosArreglo=new Array();
	var numRows=tVersus.rows().data().length;
	console.log(tVersus.rows().data())
	var numRows2=0;
	for (i=0; i<numRows;i++){		
		console.log(tVersus.rows().data()[i][7]);
		if (tVersus.rows().data()[i][7]!=""){
			if ($("#check"+tVersus.rows().data()[i][0]).val()=='on'){
				datosArreglo[i]=tVersus.rows().data()[i];
				numRows2++;
			}
		}
	}
	console.log(datosArreglo);
	var valores=new Array();
	valores[0]=$("#idEventoHid").val();
	valores[1]=numRows2;
	var inicial=2;
	for (i=0; i<numRows2;i++){
		valores[inicial]=datosArreglo[i][2];
		inicial++;
		valores[inicial]=$("#area"+datosArreglo[i][0]).val();
		inicial++;
		valores[inicial]=$("#rubro"+datosArreglo[i][0]).val();
		inicial++;
		valores[inicial]=datosArreglo[i][6];
		inicial++;
		valores[inicial]=datosArreglo[i][3];
		inicial++;
	}
	console.log(valores)
	$.ajax({
		async:false,		
		data:{
			"valores":valores	
		},
		dataType:'json',
		url:   'mvc/evento/guardargastosversusv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")		
		},
		success:  function (response) {			
			ajax_downloadPDF('mvc/evento/htmltopdf',$("#subTotalVersus").html(),$("#desviosVersus").html(),$("#DifTotalDesviosVersus").html());
			//showMensajeSR("Se guardaron correctamente los gastos que deseó desviar. Se le entregará en PDF el acuse del VERSUS!");
  		    verGastos($("#idEventoHid").val());
			$("#finalizarVersusButton").hide();
			//Checar
			$("#divVersus").hide();
		},	
		error: function (response) {																	
			alert("Se registró un error al guardar el gasto!");
			console.log(response)
		}		
	});	
}

function ajax_downloadPDF(url,total,descuento,totalMenosDescuento) {       
	var areascargos=[['N/A'],
	    	 ['PISO'],
	    	 ['QUIROFANO'],
	    	 ['TERAPIA INTENSIVA, INTERMEDIA, NEONATAL'],
	    	 ['URGENCIAS'],
	    	 ['GASTOS PERSONALES'],
	];	
	var rubroscargos=[['N/A'],  	 
	   	   	 ['MEDICAMENTOS'],
	   	   	 ['MATERIAL'],
	   	 	 ['LABORATORIO Y RX'],
	   	   	 ['TERAPIA RESPIRATORIA'],
	   	  	 ['HABITACIONES'],
	   	     ['ANESTESIA (MAQ Y GAS)'],
	   	     ['RENTAS DE EQUIPOS'],
	   	     ['INSUMOS PROVEEDOR EXTERNO'],
	   	     ['CUBICULOS'],
	   	     ['TERAPIA INTENSIVA'],
	   	     ['BANCO DE SANGRE'],
	   	     ['QUIROFANO'],
	   	     ['TIEMPOS DE SALA']	  
	];
	input_name="txt";
	input_name2="txt2";
	input_name3="txt3";
	input_name4="txt4";
	
	var data=$("#divVersus").html();
	var numRows=tVersus.rows().data().length;
	var datosArreglo= new Array();
	for (i=0; i<numRows;i++){
		datosArreglo[i]=tVersus.rows().data(0)[i];
	}
	console.log(datosArreglo);
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	   dd='0'+dd
	} 
	if(mm<10){
	    mm='0'+mm
	} 
	var today = dd+'/'+mm+'/'+yyyy;
	var html="<h4>ARGAL- HSCS  Acuse de Resultados Comparativo Versus</h4><font color=#46C2BC>Fecha:</font>"+today+"<br><font color=#46C2BC>Id Evento:</font>"+$("#idEventoHid").val()+"<br><font color=#46C2BC>Cliente:</font>"+$("#nameClienteHid").val()+"<br><font color=#46C2BC>Hospital:</font>"+$("#nameHospitalHid").val()+"<br><br>";
	html=html+"<table border=1 style=font-size:7px;><tr><td bgcolor=#46C2BC><font color=white>#RENGLON</font></td><td bgcolor=#46C2BC><font color=white>CODIGO</font></td><td bgcolor=#46C2BC><font color=white>FECHA</font></td><td bgcolor=#46C2BC><font color=white>PRODUCTO</font></td><td bgcolor=#46C2BC><font color=white>COSTO LISTA PRECIOS</font></td><td bgcolor=#46C2BC><font color=white>COSTO EDO.CUENTA</font></td><td bgcolor=#46C2BC><font color=white>DIFERENCIA</font></td><td bgcolor=#46C2BC><font color=white>GASTO OBSERVADO?</font></td><td bgcolor=#46C2BC><font color=white>AREA</font></td><td bgcolor=#46C2BC><font color=white>RUBRO</font></td></tr>";
	for (i=0; i<numRows;i++){
		html=html+"<tr>";
		html=html+"<td>"+datosArreglo[i][0]+"</td>";
		html=html+"<td>"+datosArreglo[i][1]+"</td>";
		html=html+"<td>"+datosArreglo[i][2]+"</td>";
		html=html+"<td>"+datosArreglo[i][3]+"</td>";
		html=html+"<td>$"+datosArreglo[i][4]+"</td>";
		html=html+"<td>$"+datosArreglo[i][5]+"</td>";
		if (datosArreglo[i][6]!=""){
			html=html+"<td>$"+datosArreglo[i][6]+"</td>";	
		}
		else{
			html=html+"<td>"+datosArreglo[i][6]+"</td>";
		}
			
		if (datosArreglo[i][7]!=""){
			if ($("#check"+(datosArreglo[i][0])).is(':checked')){
				html=html+"<td>Desviado</td>";
				html=html+"<td>"+areascargos[$("#area"+(datosArreglo[i][0])).val()]+"</td>";
				html=html+"<td>"+rubroscargos[$("#rubro"+(datosArreglo[i][0])).val()]+"</td>";
			}  
			else{
				html=html+"<td></td>";		
				html=html+"<td></td>";		
				html=html+"<td></td>";
			}
		}
		else{
			html=html+"<td></td>";		
			html=html+"<td></td>";		
			html=html+"<td></td>";
		}
		html=html+"</tr>";
	}
	html=html+"</table>";	
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
                  url +"'>" 
                  +
                  "<input type=hidden name='" + input_name4 + "' value='" +totalMenosDescuento+"'/>"
                  +
                  "<input type=hidden name='" + input_name3 + "' value='" +descuento+"'/>"
                  +
                  "<input type=hidden name='" + input_name2 + "' value='" +total+"'/>"
                  +
                  "<input type=hidden name='" + input_name + "' value='" +
                  html +"'/></form>" +
                  "</body></html>";
    iframe_doc.open();
    iframe_doc.write(iframe_html);
    $(iframe_doc).find('form').submit();    
}

function limpiarFormGastos(){	   
   	$("#divRegGastoObs").hide();
   	$("#divRegEdoCta").hide();
   	$("#divGastosRelev").hide();
   	$("#divVersus").hide();  
   	$("#usarVersus").val("");
   	$("#fechaGastoEdoCta").val("");
   	$("#montoGasto").val("");
   	$("#tipoRegistroGasto").val("");
   	$("#tipoEdoCuenta").val("");   	
   	$("#montoGastoEvitado").val("");
   	$("#justificacionGastoEvitado").val("");
   	$("#fechaGastoEvitado").val("");   	
   	$("#divButtonVersus").hide();
}

function showButtonVersus(){	
	if ($("#tipoEdoCuenta").val()=="2")
		$("#divButtonVersus").show();
	else
		$("#divButtonVersus").hide();
}


function cerrarVentana(){
	location.href="/argal-web?p="+$("#showeventosby").val();
}

function showMsjCandado(msj){
	var mensaje="";
	if (msj==2){
		mensaje="No se puede Egresar un Paciente que no tiene un estado de cuenta de Egreso";
	}
	if (msj==3){
		mensaje="No se puede Finalizar un evento que no tiene un Egreso Registrado";
	}
	if (msj==4){
		mensaje="No se puede Finalizar un evento que no tiene Estado de Cuenta Final";
	}
	$("#mensajeAvisoCandados").html(mensaje);
	$( "#dialog-message-candados" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );	          
	        }
	      }
	});
}