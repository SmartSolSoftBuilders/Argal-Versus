var tClientes;
$(document).ready(function() {
	//tablaListaImplants	
	$.validate({
		  form : '#editarclienteform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEditarCliente();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.validate({
		  form : '#altaclienteform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarCliente();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.ajax({
		method:"post",
		data:{
			"cliente.idCliente":$("#clienteSelCatPrecios").val(),
			"hospital.idHospital":$("#hospitalSelCatPrecios").val()
		},
		async:false,
   	  	url: "../../mvc/cliente/getclientes"
   	}).done(function(data) {
   		console.log(data)   	
   		tClientes=$("#tablaListaClientes").DataTable();
   		for (i=0;i<data.length;i++){   			
   			tClientes.row.add([
   			         data[i].idCliente,
   		             data[i].nombreCorto,
      				 data[i].nombreCliente,
      				 data[i].telOfiCliente,
      				 data[i].emailCliente,			 
      				 "<a href='#' onclick='editarClienteShow(" +data[i].idCliente+")'>Editar</a>",
      				 "<a href='#' onclick='eliminarCliente(" +data[i].idCliente+")'>Eliminar</a>",
      				]).draw( false );
   		}
   	});		   
} );

function editarClienteShow(idCliente){		
	$( "#divEditarCliente" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divEditarCliente" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});	
	$.ajax({
		method:"post",
		data:{"idCliente":idCliente},
		async:false,
   	  	url: "../../mvc/cliente/getclientebyidv2"
   	}).done(function(data) {   		
   		$("#idClienteClienteEditar").val(data.idCliente);
   		$("#razonSocialClienteEditar").val(data.razonSocialCliente);
		$("#domicilioClienteEditar").val(data.domicilioCliente);
		$("#directorMedicoClienteEditar").val(data.directorMedicoCliente);
		$("#conmutadorClienteEditar").val(data.conmutadorCliente);
		$("#nombreCortoClienteEditar").val(data.nombreCorto);
		$("#nombreClienteEditar").val(data.nombreCliente);			
		$("#telOficinaClienteEditar").val(data.telOfiCliente);
		$("#telCelularClienteEditar").val(data.telCelCliente);
		$("#emailClienteEditar").val(data.emailCliente);		
   	});
}

function agregarClienteShow(){
	$( "#divAgregarCliente" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarCliente" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}


function eliminarCliente(idCliente){	
	$( "#dialog-message-borrarcliente" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
				$.ajax({
					async:false,
					data:{
						"idCliente":idCliente				
					},
					dataType:'json',
					url:   '../../mvc/cliente/eliminarclientev2',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarcliente" ).dialog( "close" );
					},
					success:  function (response) {						
						showMensajeAltaCliente("Cliente eliminado correctamente!");						
					},	
					error: function (response) {																	
						showMensajeAltaCliente("Ocurrió un error al eliminar al Clientes!");
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

function guardarCliente(){
	$.ajax({
		async:false,
		data:{
			"razonSocialCliente":$("#razonSocialCliente").val(),
			"domicilioCliente":$("#domicilioCliente").val(),
			"directorMedicoCliente":$("#directorMedicoCliente").val(),
			"conmutadorCliente":$("#conmutadorCliente").val(),
			"nombreCorto":$("#nombreCortoCliente").val(),
			"nombreCliente":$("#nombreCliente").val(),			
			"telOfiCliente":$("#telOficinaCliente").val(),
			"telCelCliente":$("#telCelularCliente").val(),
			"emailCliente":$("#emailCliente").val()
		},
		dataType:'json',
		url:   '../../mvc/cliente/guardarclientev2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaCliente("Cliente guardado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeSR("Se registró un error al guardar al implant!");
			console.log(response)
		}		
	});
}

function guardarEditarCliente(){	
	$.ajax({
		async:false,
		data:{		
			"idCliente":$("#idClienteClienteEditar").val(),
			"razonSocialCliente":$("#razonSocialClienteEditar").val(),
			"domicilioCliente":$("#domicilioClienteEditar").val(),
			"directorMedicoCliente":$("#directorMedicoClienteEditar").val(),
			"conmutadorCliente":$("#conmutadorClienteEditar").val(),
			"nombreCorto":$("#nombreCortoClienteEditar").val(),
			"nombreCliente":$("#nombreClienteEditar").val(),			
			"telOfiCliente":$("#telOficinaClienteEditar").val(),
			"telCelCliente":$("#telCelularClienteEditar").val(),
			"emailCliente":$("#emailClienteEditar").val()
		},
		dataType:'json',
		url:   '../../mvc/cliente/actualizarclientev2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaCliente("Cliente actualizado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeAltaCliente("Se registró un error al actualizar al cliente!");
			console.log(response)
		}		
	});
}

function showMensajeAltaCliente(mensaje){
	$("#mensajeGuardarCliente").html(mensaje);
	$( "#dialog-message-cliente" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/mvc/index/getpage?page=6";
	        }
	      }
	});
}