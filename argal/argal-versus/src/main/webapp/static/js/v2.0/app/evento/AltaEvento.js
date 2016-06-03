var tMedicos;
$(document).ready(function() {
	$( "#fechaIngreso" ).datepicker({maxDate: '0'});
	$("#buttonGuardarEvento").show();

	validacionesAltaEvento();
	$.validate({
		  form : '#altaMedTraform2',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarMedTratAltaEvento();			  	
	      return false; // Will stop the submission of the form
	  }
	});
	//$( "#fechaIngreso" ).datepicker();
	//$( "#fechaNacimiento" ).datepicker();	
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
    var statusEvento=["N/A","EN CURSO","EGRESADO","FINALIZADO"];    
    var idNomina= $("#idNominaHide").val();           
    $.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/cliente/getclientes"
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
   	  	url: "../../mvc/evento/getcombovalues"
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
   	  	url: "../../mvc/hospital/gethospitales"
   	}).done(function(data) {
   		console.log(data.length)
   		for (i=0;i<data.length;i++){   			   			   		
   			var nombHospTmp=data[i].nombreHospital;
   			tHosp.row.add([
			      data[i].idHospital,			             
			      data[i].nombreHospital,
			      data[i].municipioDelHospital,			             
			      data[i].telModuloHospital,
			      "<a href='#' onclick='seleccionarHospital("+data[i].idHospital+",\""+nombHospTmp+"\");'>" +
			      		"<img src='../../static/img/v2.0/img/checked.png' width=30 height=30/>" +
			      		"</a>"
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
		      position: { my: "top", at: "top", of: window  },
		      modal: true
		});		
		$(window).resize(function() {
	        $( "#divGridMedicosNac" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
		});
		$.ajax({
			method:"post",
			async:false,
			data:{"tipo":tipoMedico},
	  	  	url: "../../mvc/evento/getmedicostratantesv2"   	  		
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
		      position: { my: "top", at: "top", of: window  },
		      modal: true
	});		
	$(window).resize(function() {
        $( "#divMedicoTratante" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	
	$.ajax({
		method:"post",
		async:false,
		data:{"tipo":tipoMedico},
   	  	url: "../../mvc/evento/getmedicostratantesv2"   	  		
   	}).done(function(data) {   		   		
   		console.log(data.length)   		
   		$("#tablaGridMedicos").DataTable({
	        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],"data":data,   "bDestroy": true	                        	                 
		});
   		console.log(data.length)
   	});
	}
}

function agregarMedTratShowFromEvento(){
	$( "#divAgregarMedicoTratante2" ).dialog({
		 resizable: false,
	      height: 700,
	      width: 1200,
	      position: { my: "top", at: "top", of: window  },
	      modal: true		     
	});
	$(window).resize(function() {
        $( "#divAgregarMedicoTratante2" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}

function showGridIcd(tipo){	   
	$( "#divIcds" ).dialog({
			 resizable: false,
		      height: 700,
		      width: 1200,
		      position: { my: "top", at: "top", of: window  },
		      modal: true		     
	});					
	$(window).resize(function() {
        $( "#divIcds" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	
	$.ajax({
		method:"post",
		async:false,
		data:{"tipo":tipo},
   	  	url: "../../mvc/evento/geticdslt"
   	}).done(function(data) {   		   		   			   			   		   	
   		$("#tablaGridIcds").DataTable( {
        "lengthMenu": [[15, 30, 150, -1], [15, 30, 150, "All"]],
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

function validacionesAltaEvento(){
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
	$("#buttonGuardarEvento").hide();
	/*var d=new Date($("#fechaIngreso").val());
	dt=d.getDate();
	dt++;
	mn=d.getMonth();
	mn++;
	yy=d.getFullYear();
	var fechaNac=mn+"/"+dt+"/"+yy;
	var fechaIni=mn+"/"+dt+"/"+yy;	
	var fechaIni=$("#fechaIngreso").val().substr(5,2)+"/"+$("#fechaIngreso").val().substr(8,2)+"/"+$("#fechaIngreso").val().substr(0,4);
	fechaNac=$("#fechaIngreso").val().substr(5,2)+"/"+$("#fechaIngreso").val().substr(8,2)+"/"+$("#fechaIngreso").val().substr(0,4);
	*/	
	var fechaIni=$("#fechaIngreso").val();
	fechaNac=$("#fechaIngreso").val();
	console.log(fechaIni)
	//var fechaNac=$("#fechaIngreso").val(new Date().toISOString().substring(0, 10));
	//console.log(fechaNac);
	var sexoPaciente="";
	var condicionPaciente="";
	var relacionPaciente="";
	var nombrePaciente="";
	var appPaciente="";
	var apmPaciente="";	
	var edadPaciente="";
	var icdUno=new Object();
	icdUno.idIcd=$("#idIcd1").val();
	if ($("#tipoEventoSel").val()==4){
		sexoPaciente=$("#sexoRN").val();
		//condicionPaciente=$("#parentescoRN").val();
		relacionPaciente=$("#parentescoRN").val();
		nombrePaciente=$("#nombreRN").val();
		appPaciente=$("#apRN").val();
		apmPaciente=$("#amRN").val();
		//fechaNac=$("#fechaNacimiento").val();
		var d2=new Date($("#fechaNacimiento").val());
		dt=d2.getDate();
		dt++;
		mn=d2.getMonth();
		mn++;
		yy=d2.getFullYear();
		fechaNac=mn+"/"+dt+"/"+yy
	}
	else{
		sexoPaciente=$("#sexoPaciente").val();
		//condicionPaciente=$("#parentescoPaciente").val();
		relacionPaciente=$("#parentescoPaciente").val();
		nombrePaciente=$("#nombrePaciente").val();
		appPaciente=$("#appPaciente").val();
		apmPaciente=$("#apmPaciente").val();
	}
	console.log($("#idIcd1").val());
	console.log($("#idIcd2").val());
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);
	
	$.ajax({
		data: {							
			"horaIngreso":$("#horaIngreso").val(),			
			"medicoTratante.idMedicoTratante":$("#idMedicoTratante").val(),
			"folioArgal":$("#folioArgal").val(),
			"folioHospital":$("#idHospitalario").val(),			    
			"implant.idImplant":"100",
			"hospital.idHospital":$("#idHospital").val(),
			//"fechaIngreso":$("#fechaIngreso").val(),
			"fechaIngreso":fechaIni,
			"cliente.idCliente":$("#clienteSel").val(),
			"tipoSeguro.idTipoSeguro":$("#tipoCliente").val(),
			"tipoEvento.idTipoEvento":$("#tipoEventoSel").val(),			
			"diagnosticoIngreso1.idIcd":$("#idIcd1").val(),
			"diagnosticoIngreso2.idIcd":$("#idIcd2").val(),
			"antecedente.idAntecedente":$("#antecedentesSel").val(),			
			"numHabitacion":$("#numeroHabitacion").val(),
			"registroSeguroPersonal.registroGastosMayores":null,
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
	    	//"registroSeguroPersonal.condicionPaciente":condicionPaciente,			
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
		url:   '../../mvc/evento/guardareventov2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),			
		},
		success:  function (response) {
			showMensaje("El evento se registró correctamente!");
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar el evento!");
			console.log(response)
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

function showMensajeSRAM(mensaje){
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

function guardarMedTratAltaEvento(){		
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
		url:   '../../mvc/evento/guardarmedtratv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
		},
		success:  function (response) {
			//showMensajeSRAM("Médico guardado correctmente!");
			alert("El Médico se agregó correctamente, Por favor selecciónelo  de la lista de médicos tratantes.");
			$( "#divAgregarMedicoTratante2" ).dialog( "close" );
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
