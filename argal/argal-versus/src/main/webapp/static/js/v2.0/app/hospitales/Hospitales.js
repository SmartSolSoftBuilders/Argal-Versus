var tHospitales;
$(document).ready(function() {
	//tablaListaImplants	
	$.validate({
		  form : '#editarHospform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEditarHospital();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.validate({
		  form : '#altaHospform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarHospital();			  	
	            return false; // Will stop the submission of the form
	        }
	});
	$.ajax({
		method:"post",		
		async:false,
   	  	url: "../../mvc/hospital/gethospitales"
   	}).done(function(data) {
   		console.log(data)   	
   		tHospitales=$("#tablaListaHospitales").DataTable();
   		for (i=0;i<data.length;i++){   			
   			tHospitales.row.add([   		
      				 data[i].idHospital,
      				 data[i].nombreHospital,
      				 data[i].estadoHospital,
      				 data[i].municipioDelHospital,
      				 data[i].telModuloHospital,      				 
      				 "<a href='#' onclick='editarHospitalShow(" +data[i].idHospital+")'>Editar</a>",
      				 "<a href='#' onclick='eliminarHospital(" +data[i].idHospital+")'>Eliminar</a>",
      				]).draw( false );
   		}
   	});		   
} );

function editarHospitalShow(idHospital){		
	$( "#divEditarHospital" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divEditarHospital" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});	
	$.ajax({
		method:"post",
		data:{"idHospital":idHospital},
		async:false,
   	  	url: "../../mvc/hospital/gethospitalbyidv2"
   	}).done(function(data) {   		   		
   		$("#idHospEditar").val(data.idHospital);
   		$("#nombreHospEditar").val(data.nombreHospital);
		$("#calleHospEditar").val(data.direccionHospital);
		$("#colHospEditar").val(data.coloniaHospital);
		$("#mundelHospEditar").val(data.municipioDelHospital);
		$("#cpHospEditar").val(data.cpHospital);
		$("#conmutadorHospEditar").val(data.conmutadorHospital);
		$("#telmoduloHospEditar").val(data.telModuloHospital);
		$("#ciudadHospEditar").val(data.ciudadHospital);
		$("#estadoHospEditar").val(data.estadoHospital);
		$("#paisHospEditar").val(data.paisHospital);		
   	});
}

function agregarHospitalShow(){
	$( "#divAgregarHospital" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarHospital" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
}


function eliminarHospital(idHospital){	
	$( "#dialog-message-borrarhospital" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
				$.ajax({
					async:false,
					data:{
						"idHospital":idHospital				
					},
					dataType:'json',
					url:   '../../mvc/hospital/eliminarhospitalv2',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarhospital" ).dialog( "close" );
					},
					success:  function (response) {						
						showMensajeAltaHosp("Hospital eliminado correctamente!");						
					},	
					error: function (response) {																	
						showMensajeAltaHosp("Ocurrió un error al eliminar al Hospital!");
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

function guardarHospital(){	
	$.ajax({
		async:false,
		data:{
			"nombreHospital":$("#nombreHosp").val(),
			"direccionHospital":$("#calleHosp").val(),
			"coloniaHospital":$("#colHosp").val(),
			"municipioDelHospital":$("#mundelHosp").val(),
			"cpHospital":$("#cpHosp").val(),
			"conmutadorHospital":$("#conmutadorHosp").val(),
			"telModuloHospital":$("#telmoduloHosp").val(),
			"ciudadHospital":$("#ciudadHosp").val(),
			"estadoHospital":$("#estadoHosp").val(),
			"paisHospital":$("#paisHosp").val()			
		},
		dataType:'json',
		url:   '../../mvc/hospital/guardarhospitalv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaHosp("Hospital guardado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeAltaHosp("Se registró un error al guardar el hospital!");
			console.log(response)
		}		
	});
}

function guardarEditarHospital(){	
	$.ajax({
		async:false,
		data:{		
			"idHospital":$("#idHospEditar").val(),
			"nombreHospital":$("#nombreHospEditar").val(),
			"direccionHospital":$("#calleHospEditar").val(),
			"coloniaHospital":$("#colHospEditar").val(),
			"municipioDelHospital":$("#mundelHospEditar").val(),
			"cpHospital":$("#cpHospEditar").val(),
			"conmutadorHospital":$("#conmutadorHospEditar").val(),
			"telModuloHospital":$("#telmoduloHospEditar").val(),
			"ciudadHospital":$("#ciudadHospEditar").val(),
			"estadoHospital":$("#estadoHospEditar").val(),
			"paisHospital":$("#paisHospEditar").val() 
		},
		dataType:'json',
		url:   '../../mvc/hospital/actualizarhospitalv2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensajeAltaHosp("Hospital actualizado correctmente!");
			console.log(response)					
		},	
		error: function (response) {																	
			showMensajeAltaHosp("Se registró un error al actualizar el Hospital!");
			console.log(response)
		}		
	});
}

function showMensajeAltaHosp(mensaje){
	$("#mensajeGuardarHospital").html(mensaje);
	$( "#dialog-message-hospital" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/mvc/index/getpage?page=3";
	        }
	      }
	});
}