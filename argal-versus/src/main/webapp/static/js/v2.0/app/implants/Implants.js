var tImplants;
$(document).ready(function() {
	//tablaListaImplants	
	$.validate({
		  form : '#editarimplantform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEditarImplant();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.validate({
		  form : '#altaimplantform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarImplant();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.ajax({
		method:"post",		
		async:false,
   	  	url: "../../mvc/implant/getimplants"
   	}).done(function(data) {
   		console.log(data)   	
   		tImplants=$("#tablaListaImplants").DataTable();
   		for (i=0;i<data.length;i++){   			
   			tImplants.row.add([
   		             data[i].nombreImplant+" "+data[i].appImplant+" "+data[i].apmImplant,
      				 data[i].nextelImplant,
      				 data[i].idNextelImplant,
      				 data[i].celularImplant,
      				 data[i].emailInstImplant,
      				 data[i].nickImplant,      				 
      				 "<a href='#' onclick='editarImplantShow(" +data[i].idImplant+")'>Editar</a>",
      				 "<a href='#' onclick='eliminarImplant(" +data[i].idImplant+")'>Eliminar</a>",
      				]).draw( false );
   		}
   	});		   
} );

function editarImplantShow(idImplant){		
	$( "#divEditarImplant" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divEditarImplant" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});	
	$.ajax({
		method:"post",
		data:{"idImplant":idImplant},
		async:false,
   	  	url: "../../mvc/implant/getimplantbyidv2"
   	}).done(function(data) {   		
   		$("#idImp").val(data.idImplant);   		   		   		
   		$("#nombreImpEditar").val(data.nombreImplant);
		$("#appImpEditar").val(data.appImplant);
		$("#apmImpEditar").val(data.apmImplant);
		$("#nextelImpEditar").val(data.nextelImplant);
		$("#idNextImpEditar").val(data.idNextelImplant);
		$("#celularImpEditar").val(data.celularImplant);
		$("#telOfImpEditar").val(data.telOfiImplant);
		$("#puestoImpEditar").val(data.puestoImplant);
		$("#emailInstImpEditar").val(data.emailInstImplant);
		$("#emailPersImpEditar").val(data.emailPersImplant);
   		if (data.superMedico){
   			$("#supermedicoImpEditar").val('on');
   		}		
   	});
}

function agregarImplantShow(){
	$( "#divAgregarImplant" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarImplant" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}


function eliminarImplant(idImplant){	
	$( "#dialog-message-borrarimplant" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
				$.ajax({
					async:false,
					data:{
						"idImplant":idImplant				
					},
					dataType:'json',
					url:   '../../mvc/implant/eliminarimplantv2',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarimplant" ).dialog( "close" );
					},
					success:  function (response) {						
						showMensajeAltaImp("Implant eliminado correctamente!");						
					},	
					error: function (response) {																	
						showMensajeAltaImp("Ocurrió un error al eliminar al Implant!");
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

function showMensaje(mensaje){
	$("#mensajeGuardar").html(mensaje);
	$( "#dialog-message-file" ).dialog({
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
	$( "#dialog-message-borrarlp" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );	          
	        }
	      }
	});
}


function guardarImplant(){
	var superMed=false;
	if ($("#supermedicoImp").is(':checked')){
		superMed=true;
	}
	console.log("SUPER:"+superMed)
	$.ajax({
		async:false,
		data:{		
			"nombreImplant":$("#nombreImp").val(),
			"appImplant":$("#appImp").val(),
			"apmImplant":$("#apmImp").val(),
			"nextelImplant":$("#nextelImp").val(),
			"idNextelImplant":$("#idNextImp").val(),
			"celularImplant":$("#celularImp").val(),
			"telOfiImplant":$("#telOfImp").val(),
			"puestoImplant":$("#puestoImp").val(),
			"emailInstImplant":$("#emailInstImp").val(),
			"emailPersImplant":$("#emailPersImp").val(),
			"superMedico":superMed
		},
		dataType:'json',
		url:   '../../mvc/implant/guardarimplantv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaImp("Implant guardado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeSR("Se registró un error al guardar al implant!");
			console.log(response)
		}		
	});
}

function guardarEditarImplant(){
	var superMed=false;
	if ($("#supermedicoImpEditar").is(':checked')){
		superMed=true;
	}
	console.log("SUPER:"+superMed)
	console.log("SUPER:"+$("#idImp").val())
	$.ajax({
		async:false,
		data:{		
			"idImplant":$("#idImp").val(),
			"nombreImplant":$("#nombreImpEditar").val(),
			"appImplant":$("#appImpEditar").val(),
			"apmImplant":$("#apmImpEditar").val(),
			"nextelImplant":$("#nextelImpEditar").val(),
			"idNextelImplant":$("#idNextImpEditar").val(),
			"celularImplant":$("#celularImpEditar").val(),
			"telOfiImplant":$("#telOfImpEditar").val(),
			"puestoImplant":$("#puestoImpEditar").val(),
			"emailInstImplant":$("#emailInstImpEditar").val(),
			"emailPersImplant":$("#emailPersImpEditar").val(),
			"superMedico":superMed
		},
		dataType:'json',
		url:   '../../mvc/implant/actualizarimplantv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaImp("Implant actualizado correctamente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeSR("Se registró un error al actualizar al implant!");
			console.log(response)
		}		
	});
}

function showMensajeAltaImp(mensaje){
	$("#mensajeGuardarImplant").html(mensaje);
	$( "#dialog-message-implant" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/mvc/index/getpage?page=2";
	        }
	      }
	});
}