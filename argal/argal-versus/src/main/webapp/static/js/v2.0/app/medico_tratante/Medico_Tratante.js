var tMedTrat;
$(document).ready(function() {	
	var tiposMed=["RED","STAFF","INTERINO"];
	$.validate({
		  form : '#altaMedTraform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarMedTrat();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.ajax({
		method:"post",		
		async:false,
   	  	url: "../../mvc/evento/getmedicostratantesv3"
   	}).done(function(data) {
   		console.log(data)   	   		   	
   			$("#tablaListaMedicosTratantes").DataTable( {
   		        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],
   		        "data": data,"bDestroy": true
   		});   		
   	});		   
} );


function agregarMedTratShow(){
	$( "#divAgregarMedicoTratante4" ).dialog({
		 resizable: false,
	      height: 650,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarMedicoTratante4" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}

function eliminarMed(idMed){	
	$( "#dialog-message-borrarmed" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
				$.ajax({
					async:false,
					data:{
						"idMedicoTratante":idMed				
					},
					dataType:'json',
					url:   '../../mvc/evento/eliminarmedtrat',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarmed" ).dialog( "close" );
					},
					success:  function (response) {						
						showMensajeAltaMed("Médico eliminado correctamente!");						
					},	
					error: function (response) {																	
						showMensajeAltaMed("Ocurrió un error al eliminar al Médico!");
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

function showMensajeSR(mensaje){
	$("#mensajeGuardarAviso").html(mensaje);
	$( "#dialog-message-lp" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );	          
	        }
	      }
	});
}

function showMensajeEliminar(mensaje){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message-borrarmed" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );	          
	        }
	      }
	});
}


function guardarMedTrat(){	
	var especialidades= new Array();		
	var med  = new Array();
	med.push($("#appMedTrat").val()+" " +$("#apmMedTrat").val()+ " " + $("#nombreMedTrat").val());
	med.push($("#tipoMedSel").val());
	for(var i=0; i < document.altaMedTraform.especialidades_altamed.length; i++){		
		if(document.altaMedTraform.especialidades_altamed[i].checked){
			med.push(document.altaMedTraform.especialidades_altamed[i].value);			
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
			showMensajeAltaMed("Médico guardado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			//showMensajeSR("Se registró un error al guardar al implant!");
			console.log(response)
			showMensajeAltaMed("Error al guardar al médico!");
		}		
	});
}

function showMensajeAltaMed(mensaje){
	$("#mensajeGuardarMed").html(mensaje);
	$( "#dialog-message-med" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/mvc/index/getpage?page=5";
	        }
	      }
	});
}