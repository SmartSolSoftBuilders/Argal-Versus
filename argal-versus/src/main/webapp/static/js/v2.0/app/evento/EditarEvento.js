var tMedicos;

$(document).ready(function() {
	validacionesEditarEvento();	
	$( "#fechaIngreso" ).datepicker();
	$( "#fechaNacimiento" ).datepicker();
	
    t=$( "#accordion" ).accordion();
    tHosp=$("#tablaGridHospitales").DataTable({
        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]]
    	});    
    $.validate({
		  form : '#altaeventoform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEvento();			  	
	            return false; // Will stop the submission of the form
	        }
	});    
	$.validate({
		  form : '#altaMedTraform2',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarMedTratEditarEvento();			  	
	      return false; // Will stop the submission of the form
	  }
	});

    var statusEvento=["N/A","EN CURSO","EGRESADO","FINALIZADO"];    
    var idNomina= $("#idNominaHide").val();           
    $.ajax({
		method:"post",
		async:false,
   	  	url: "mvc/cliente/getclientes"
   	}).done(function(data) {
   		var optionsAsString = "";   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idCliente + "'>" + data[i].nombreCorto + "</option>";
		}      		
   		$( 'select[name="clienteSel"]' ).append( optionsAsString );
   	});
    $.ajax({
		method:"post",
		async:false,
   	  	url: "mvc/evento/getcombovalues"
   	}).done(function(data) {
   		var optionsAsString = "";   		
   		for (i=0;i<data[3].length;i++){   			   		
   		    optionsAsString += "<option value='" + data[3][i].idAntecedente + "'>" + data[3][i].descripcion + "</option>";
		}      		
   		$( 'select[name="antecedentesSel"]' ).append( optionsAsString );
   		var optionsAsString = "";   		
   		for (i=0;i<data[4].length;i++){		   		
   		    optionsAsString += "<option>" + data[4][i].nombreImplant +" "+ data[4][i].appImplant+" "+ data[4][i].apmImplant +"</option>";
		}      		
   		$( 'select[name="medicoDictaminadorSel"]' ).append( optionsAsString );
   	});       		   
} );

function loadEvento(idEvento){
	$.ajax({
		method:"post",
		async:false,
		data:{"idEvento":idEvento},
   	  	url: "mvc/evento/obtenereventobyidv2"
   	}).done(function(data) {
   		console.log("Evento Obtenido")   	   		
   		cargarDatosEvento(data);
   	});
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


function showGridHospitales(){
	tHosp.clear().draw();    
	$( "#divGridHospitales" ).dialog({
			  resizable: false,
			  position: { my: "top", at: "top", of: window  },
		      height: 500,
		      width: 1200,
		      modal: true
	});		
	$(window).resize(function() {
      $( "#divGridHospitales" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	
	$.ajax({
		method:"post",   	  	
   	  	url: "mvc/hospital/gethospitales"
   	}).done(function(data) {
   		console.log(data.length)
   		for (i=0;i<data.length;i++){   			   			   		
   			var nombHospTmp=data[i].nombreHospital;
   			tHosp.row.add([
			      data[i].idHospital,			             
			      data[i].nombreHospital,
			      data[i].municipioDelHospital,			             
			      data[i].telModuloHospital,
			      "<a href='#' onclick='seleccionarHospital("+data[i].idHospital+",\""+nombHospTmp+"\");'>Seleccionar</a>"
			]).draw( false );
		}   		   	
   	});
}

function seleccionarHospital(id,nombre){
	$("#idHospital").val(id);
	$("#nombreHospital").val(nombre);
	$("#divGridHospitales").dialog('close');
}

function showFormNacimiento(){	
	if ($("#tipoEventoSel").val()==4){
		$("#formNac").show();
		$("#formPacienteNormal").hide();				
	}
	else{
		$("#formPacienteNormal").show();
		$("#formNac").hide();
	}		
}

function showGridMedicos(tipoMedico){
	if (tipoMedico==2){
		$( "#divGridMedicosNac" ).dialog({
			 resizable: false,
		      height: 600,
		      width: 1200,
		      modal: true
		});
		
		$.ajax({
			method:"post",
			async:false,
			data:{"tipo":tipoMedico},
	  	  	url: "mvc/evento/getmedicostratantesv2"   	  		
	  	}).done(function(data) {   		   		
	  		console.log(data.length)   		   		   		   		
	  		$("#tablaGridMedicosNac").DataTable({
		        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],"data":data, "bDestroy": true	                        	                 
			});
	  		console.log(data.length)
	  	});
	}
	else{
	$( "#divMedicoTratante" ).dialog({
			 resizable: false,
		      height: 600,
		      width: 1200,
		      modal: true
	});		
	$.ajax({
		method:"post",
		async:false,
		data:{"tipo":tipoMedico},
   	  	url: "mvc/evento/getmedicostratantesv2"   	  		
   	}).done(function(data) {   		   		
   		console.log(data.length)   		
   		$("#tablaGridMedicos").DataTable({
	        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],"data":data,   "bDestroy": true	                        	                 
		});
   		console.log(data.length)
   	});
	}
}

function showGridIcd(tipo){	
    //tIcds.clear().draw();    	
	$( "#divIcds" ).dialog({
			 resizable: false,
		      height: 500,
		      width: 1200,
		      modal: true
	});				
	$.ajax({
		method:"post",
		async:false,
		data:{"tipo":tipo},
   	  	url: "mvc/evento/geticdslt"
   	}).done(function(data) {   		   		   			   			   		   	
   		$("#tablaGridIcds").DataTable( {
        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],
        "data": data,"bDestroy": true
    	});
   	});
}

function seleccionarMedicoTratante(idM,nombre,tipo,espc,t){
	if (t==1){
		$("#medicoTratante").val(nombre);
		$("#idMedicoTratante").val(idM);
		$("#especialidad").html(espc);
		$("#divMedicoTratante").dialog('close');
	}
	if (t==2){
		$("#medicoNacimiento").val(nombre);
		$("#idMedicoNacimiento").val(idM);
		$("#divGridMedicosNac").dialog('close');
	}	
		
}

function seleccionarIcd(id,desc,tipo){
	if (tipo==1){
		$("#icd1").val(desc);
		$("#idIcd1").val(id);
	}
	if (tipo==2){
		$("#icd2").val(desc);
		$("#idIcd2").val(id);
	}	
	$("#divIcds").dialog('close');
}

function validacionesEditarEvento(){
$.formUtils.addValidator({
	  name : 'evento_normal',
	  validatorFunction : function(value, $el, config, language, $form) {
		  console.log(value);
		  if ($("#tipoEventoSel").val()!="4"){
			  if (value=="")
				  return false;
			  return true;
		  }	    
	  },
	  errorMessage : 'Seleccione',
	  errorMessageKey: 'badEvenNumber'
	});

$.formUtils.addValidator({
	  name : 'evento_nacimiento',
	  validatorFunction : function(value, $el, config, language, $form) {
		  console.log(value);
		  if ($("#tipoEventoSel").val()=="4"){
			  if (value=="")
				  return false;
			  return true;
		  }	    
	  },
	  errorMessage : 'Seleccione',
	  errorMessageKey: 'badEvenNumber'
	});
}

function guardarEvento(){	
	var fechaNac=$("#fechaIngreso").val();	
	var sexoPaciente="";
	var condicionPaciente="";
	var relacionPaciente="";
	var nombrePaciente="";
	var appPaciente="";
	var apmPaciente="";	
	var edadPaciente="";
	if ($("#tipoEventoSel").val()==4){
		sexoPaciente=$("#sexoRN").val();
		//condicionPaciente=$("#parentescoRN").val();
		relacionPaciente=$("#parentescoRN").val();
		nombrePaciente=$("#nombreRN").val();
		appPaciente=$("#apRN").val();
		apmPaciente=$("#amRN").val();
		fechaNac=$("#fechaNacimiento").val();		
	}
	else{
		sexoPaciente=$("#sexoPaciente").val();
		//condicionPaciente=$("#parentescoPaciente").val();
		relacionPaciente=$("#parentescoPaciente").val();
		nombrePaciente=$("#nombrePaciente").val();
		appPaciente=$("#appPaciente").val();
		apmPaciente=$("#apmPaciente").val();
	}
	console.log($("#horaIngreso").val());
	

	//var horaIngresoDt=document.getElementById('horaIngreso').value;
	
	var diag1=new Object();
	var idIcd1=$("#idIcd1").val();
	var idIcd2=$("#idIcd2").val();
	$.ajax({
		data: {							
			"idEvento":$("#idEventoHid").val(),
			"registroSeguroPersonal.idRegistroSeguroPersonal":$("#idRegSegPersHid").val(),
			"horaIngreso":$("#horaIngreso").val(),
			//"horaIngreso":enteredDate.getHours()+":"+enteredDate.getMinutes(),
			"medicoTratante.idMedicoTratante":$("#idMedicoTratante").val(),
			"folioArgal":$("#folioArgal").val(),
			"folioHospital":$("#idHospitalario").val(),			    
			"implant.idImplant":"100",
			"hospital.idHospital":$("#idHospital").val(),
			"fechaIngreso":$("#fechaIngreso").val(),												
			"cliente.idCliente":$("#clienteSel").val(),
			"tipoSeguro.idTipoSeguro":$("#tipoCliente").val(),
			"tipoEvento.idTipoEvento":$("#tipoEventoSel").val(),			
			"diagnosticoIngreso1.idIcd":idIcd1,			
			"diagnosticoIngreso2.idIcd":idIcd2,			    			
			"antecedente.idAntecedente":$("#antecedentesSel").val(),			
			"numHabitacion":$("#numeroHabitacion").val(),			
			"registroSeguroPersonal.numeroNomina":$("#numeroNomina").val(),
			"medicoDictaminador":$("#medicoDictaminadorSel").val(),
			//"registroSeguroPersonal.institucion":$("#").val(),
			"registroSeguroPersonal.numAutorizacion":$("#numeroAutorizacion").val(),
			"registroSeguroPersonal.censo":$("#censoSel").val(),
			"registroSeguroPersonal.condicionPaciente":$("#condicionAsegSel").val(),
			"registroSeguroPersonal.nombreTitular":$("#nombreTitular").val(),
			"registroSeguroPersonal.appTitular":$("#appTitular").val(),
			"registroSeguroPersonal.apmTitular":$("#apmTitular").val(),								
			"registroSeguroPersonal.edadPaciente":$("#edadPaciente").val(),
	    	"registroSeguroPersonal.nombrePaciente":nombrePaciente,
			"registroSeguroPersonal.appPaciente":appPaciente,
			"registroSeguroPersonal.apmPaciente":apmPaciente,
			"registroSeguroPersonal.sexoPaciente":sexoPaciente,
			"registroSeguroPersonal.relacionPaciente":relacionPaciente,	    	
	    	"registroSeguroPersonal.unidadEdadPaciente":$("#unidadPaciente").val(),
	    	"registroSeguroPersonal.sexoPaciente":sexoPaciente,
	    	"registroSeguroPersonal.nacimientoFechaNacimiento":fechaNac,
	    	"registroSeguroPersonal.nacimientoHoraNacimiento":$("#horaNacimiento").val(),
	    	"registroSeguroPersonal.nacimientoTipoParto":$("#tipoParto").val(),
	    	"registroSeguroPersonal.nacimientoTalla":$("#talla").val(),
	    	"registroSeguroPersonal.nacimientoPeso":$("#peso").val(),
	    	"registroSeguroPersonal.nacimientoApgar":$("#apgar").val(),
	    	"registroSeguroPersonal.nacimientoSilverman":"",
	    	"registroSeguroPersonal.nacimientoMedicoTratante":$("#medicoNacimiento").val()	    		    
		},
		dataType:'json',
		url:   'mvc/evento/guardareventoeditarv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),			
		},
		success:  function (response) {
			showMensaje("El evento se registró correctamente!");
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar el evento!");
			console.log(data)
		}		
	});				
}

function showMensaje(mensaje){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/";
	        }
	      }
	});
}

function cargarDatosEvento(evento){
	console.log(evento)
	var fechaIngr=new Date(evento.fechaIngreso);
	fechaIngr=(fechaIngr.getMonth()+1)+"/"+fechaIngr.getDate()+"/"+fechaIngr.getFullYear();
	$("#idEventoHid").val(evento.idEvento);
	$("#idRegSegPersHid").val(evento.registroSeguroPersonal.idRegistroSeguroPersonal);
		if (evento.tipoEvento.idTipoEvento==4){
			var fechaNac=new Date(evento.registroSeguroPersonal.nacimientoFechaNacimiento);
			fechaNac=fechaNac.getDate()+"/"+(fechaNac.getMonth()+1)+"/"+fechaNac.getFullYear();
			$("#sexoRN").val(evento.registroSeguroPersonal.sexoPaciente);
			$("#parentescoRN").val(evento.registroSeguroPersonal.relacionPaciente);			
			$("#nombreRN").val(evento.registroSeguroPersonal.nombrePaciente);
			$("#apRN").val(evento.registroSeguroPersonal.appPaciente);
			$("#amRN").val(evento.registroSeguroPersonal.apmPaciente);
			$("#fechaNacimiento").val(fechaNac);			
		}
		else{
			$("#edadPaciente").val(evento.registroSeguroPersonal.edadPaciente);		
			$("#sexoPaciente").val(evento.registroSeguroPersonal.sexoPaciente);			
			$("#parentescoPaciente").val(evento.registroSeguroPersonal.relacionPaciente);
			$("#nombrePaciente").val(evento.registroSeguroPersonal.nombrePaciente);
			$("#unidadPaciente").val(evento.registroSeguroPersonal.unidadEdadPaciente);
			$("#appPaciente").val(evento.registroSeguroPersonal.appPaciente);
			$("#apmPaciente").val(evento.registroSeguroPersonal.apmPaciente);
		}
										
		$("#horaIngreso").val(evento.horaIngreso);
		$("#idMedicoTratante").val(evento.medicoTratante.idMedicoTratante);
		$("#medicoTratante").val(evento.medicoTratante.nombre);
		$("#folioArgal").val(evento.folioArgal);
		$("#idHospitalario").val(evento.folioHospital);			    		
		$("#idHospital").val(evento.hospital.idHospital);
		$("#nombreHospital").val(evento.hospital.nombreHospital);		
		$("#fechaIngreso").val(fechaIngr);												
		$("#clienteSel").val(evento.cliente.idCliente);
		$("#tipoCliente").val(evento.tipoSeguro.idTipoSeguro);
		$("#tipoEventoSel").val(evento.tipoEvento.idTipoEvento);
		if (evento.diagnosticoIngreso1!=null){
			$("#icd1").val(evento.diagnosticoIngreso1.descripcion);
			$("#idIcd1").val(evento.diagnosticoIngreso1.idIcd);
		}
		if (evento.diagnosticoIngreso2!=null){
			$("#icd2").val(evento.diagnosticoIngreso2.descripcion);		
			$("#idIcd2").val(evento.diagnosticoIngreso2.idIcd);
		}	
		
		$("#condicionAsegSel").val(evento.registroSeguroPersonal.condicionPaciente);
		if (evento.antecedente!=null)
			$("#antecedentesSel").val(evento.antecedente.idAntecedente);					
		$("#numeroHabitacion").val(evento.numHabitacion);		
		$("#numeroNomina").val(evento.registroSeguroPersonal.numeroNomina);
		$("#medicoDictaminadorSel").val(evento.medicoDictaminador);
		$("#numeroAutorizacion").val(evento.registroSeguroPersonal.numAutorizacion);
		$("#censoSel").val(evento.registroSeguroPersonal.censo);		
		$("#nombreTitular").val(evento.registroSeguroPersonal.nombreTitular);
		$("#appTitular").val(evento.registroSeguroPersonal.appTitular);
		$("#apmTitular").val(evento.registroSeguroPersonal.apmTitular);															
		$("#horaNacimiento").val(evento.registroSeguroPersonal.nacimientoHoraNacimiento);
		$("#tipoParto").val(evento.registroSeguroPersonal.nacimientoTipoParto);
		$("#talla").val(evento.registroSeguroPersonal.nacimientoTalla);
		$("#peso").val(evento.registroSeguroPersonal.nacimientoPeso);
		$("#apgar").val(evento.registroSeguroPersonal.nacimientoApgar);	
		$("#medicoNacimiento").val(evento.registroSeguroPersonal.nacimientoMedicoTratante);		
	    showFormNacimiento();
}

function agregarMedTratShowFromEvento(){
	$( "#divAgregarMedicoTratante3" ).dialog({
		 resizable: false,
	      height: 700,
	      width: 1200,
	      position: { my: "top", at: "top", of: window  },
	      modal: true		     
	});
	$(window).resize(function() {
        $( "#divAgregarMedicoTratante3" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}

function guardarMedTratEditarEvento(){		
	var especialidades= new Array();		
	var med  = new Array();
	med.push($("#apmMedTratAltaEvento").val()+" " +$("#appMedTraAltaEvento").val()+ " " + $("#nombreMedTratAltaEvento").val());
	med.push($("#tipoMedSelAltaEvento").val());
	for(var i=0; i < document.altaMedTraform2.especialidades_altamed_AltaEvento.length; i++){		
		if(document.altaMedTraform2.especialidades_altamed_AltaEvento[i].checked){
			med.push(document.altaMedTraform2.especialidades_altamed_AltaEvento[i].value);			
		}
	}	
	$.ajax({
		async:false,
		data:{
			"valores":med
		},			
		url:   'mvc/evento/guardarmedtratv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
		},
		success:  function (response) {
			//showMensajeSRAM("Médico guardado correctmente!");
			alert("El Médico se agregó correctamente, Por favor selecciónelo  de la lista de médicos tratantes.");
			$( "#divAgregarMedicoTratante3" ).dialog( "close" );
			$( "#divMedicoTratante" ).dialog( "close" );
			showGridMedicos(1);
			console.log(response)					
		},	
		error: function (response) {																	
			//showMensajeSR("Se registró un error al guardar al implant!");
			console.log(response)
			alert("Error al guardar al médico!");
		}		
	});
}
