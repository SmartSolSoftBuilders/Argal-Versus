function showDivBitacora(seleccion,idBitacora){	
	if (seleccion=="1"){		
		$("#divTablaBitacoras").show();
		$("#divRegistrarBitacora").hide();
		$("#divEditarBitacora").hide();
	}
	if (seleccion=="2"){		
		limpiarFormGastosBitacoras();
		$("#divTablaBitacoras").hide();
		$("#divRegistrarBitacora").show();
		$("#divEditarBitacora").hide();
		
	}
	if (seleccion=="3"){
		limpiarFormGastosBitacoras();
		$("#divTablaBitacoras").hide();
		$("#divRegistrarBitacora").hide();
		$("#divEditarBitacora").show();
		getSetDatosBitacoraEvento(idBitacora);
	}
	
}

function validacionesBitacoras(){
	$.formUtils.addValidator({
		  name : 'altabitacoraval',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando");			  
				  if (value=="")
					  return false;
				  return true;			  	   
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
		});
}

function guardarBitacora(){
	var d=new Date($("#fechaReporteMedico").val());
	dt=d.getDate();
	dt++;
	mn=d.getMonth();
	mn++;
	yy=d.getFullYear();
	var fechaBit=mn+"/"+dt+"/"+yy;	
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);		
	$.ajax({
		async:false,			
		data:{
			"id":$("#idEventoHidBit").val(),
			"idRegistroMedico":$("#reporteMedico").val(),
			"descripcion":"",
			"observaciones":$("#observacionesReporteMedico").val(),
			"interconsulta":$("#interconsulta").val(),							
			"fechaBitacora":fechaBit
		},
		dataType:'json',
		url:   'mvc/evento/guardarbitacorav2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeSR("Se registró correctamente la bitácora");
			updateBitacora($("#idEventoHidBit").val());
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar el gasto!");
			console.log(response)
		}		
	});		
}

function getSetDatosBitacoraEvento(idBitacora){
	$.ajax({
		async:false,			
		data:{
			"idBitacora":idBitacora			
		},
		dataType:'json',
		url:   'mvc/evento/getBitacoraByIdV2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {			
			var anioTmp=new Date(response.fechaBitacora).getFullYear();
			var mesTmp=(new Date(response.fechaBitacora).getMonth()+1);
			if (mesTmp<10)
				mesTmp="0"+mesTmp;
			var diaTmp=new Date(response.fechaBitacora).getDate();
			if (diaTmp<10)
				diaTmp="0"+diaTmp;			
			var fechaTmp=anioTmp+"-"+mesTmp+"-"+diaTmp;
			$("#idBitacoraHid").val(response.idBitacora),
			$("#reporteMedicoEdit").val(response.idRegistroMedico),			
			$("#observacionesReporteMedicoEdit").val(response.observaciones),
			$("#interconsultaEdit").val(response.interconsulta),		
			$("#fechaReporteMedicoEdit").val(fechaTmp)										
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar el gasto!");
			console.log(response)
		}		
	});
}

function guardarEditarBitacora(){
	var d=new Date($("#fechaReporteMedicoEdit").val());
	dt=d.getDate();
	dt++;
	mn=d.getMonth();
	mn++;
	yy=d.getFullYear();
	var fechaBit=mn+"/"+dt+"/"+yy;	
	var horaIngresoDt=new Date(0, 0, 1, 1, 1);		
	$.ajax({
		async:false,			
		data:{
			"idBitacora":$("#idBitacoraHid").val(),
			"id":$("#idEventoHidBit").val(),			
			"idRegistroMedico":$("#reporteMedicoEdit").val(),
			"descripcion":"",
			"observaciones":$("#observacionesReporteMedicoEdit").val(),
			"interconsulta":$("#interconsultaEdit").val(),							
			"fechaBitacora":fechaBit
		},
		dataType:'json',
		url:   'mvc/evento/guardareditarbitacorav2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeSR("Se actualizó correctamente la bitácora");
			updateBitacora($("#idEventoHidBit").val());
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar la bitácora!");
			console.log(response)
		}		
	});		
}

function eliminarBitacora(idBitacora){
	$( "#dialog-confirm-eliminarbitac" ).dialog({
	      resizable: false,
	      width:400,
	      height:240,
	      modal: true,
	      buttons: {
	        "Eliminar": function() {	        	
			$.ajax({
				async:false,			
				data:{
					"id":$("#idEventoHidBit").val(),
					"idBitacora":idBitacora
				},
				dataType:'json',
				url:   'mvc/evento/eliminarbitacorav2',
				type:  'post',		
				beforeSend: function () {
					$( "#dialog-confirm-eliminarbitac" ).dialog( "close" );
				},
				success:  function (response) {
					showMensajeSR("Se eliminó correctamente la bitácora");
					updateBitacora($("#idEventoHidBit").val());
				},	
				error: function (response) {
					$( "dialog-confirm-eliminarbitac" ).dialog( "close" );
					showMensaje("Se registró un error al eliminar la bitácora!");
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

function updateBitacora(idEvento){	
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/getbitacorasv2"
   	}).done(function(data) {
   		console.log(data.length)
   		var Evento=data[0];
   		limpiarFormGastosBitacoras();
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

function limpiarFormGastosBitacoras(){
	$("#divRegistrarBitacora").hide();
	$("#divEditarBitacora").hide();
	$("#divTablaBitacoras").show();	
	$("#reporteMedico").val("");
	$("#observacionesReporteMedico").val("");
	$("#fechaReporteMedico").val("");
	$("#interconsulta").val("");
	$("#reporteMedicoEdit").val("");
	$("#observacionesReporteMedicoEdit").val("");
	$("#fechaReporteMedicoEdit").val("");
	$("#interconsultaEdit").val("");
}
