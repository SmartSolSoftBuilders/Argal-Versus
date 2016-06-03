var tMedicos;
$(document).ready(function() {	          
    $.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/cliente/getclientes"
   	}).done(function(data) {
   		var optionsAsString = "";   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idCliente + "'>" + data[i].nombreCorto + "</option>";
		}      		
   		$( 'select[name="clienteSelCatPrecios"]' ).append( optionsAsString );
   	});
    $.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/hospital/gethospitales"
   	}).done(function(data) {
   		var optionsAsString = "";   		   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idHospital + "'>" + data[i].nombreHospital + "</option>";
		}      		
   		$( 'select[name="hospitalSelCatPrecios"]' ).append( optionsAsString );
   	});	   
} );


function obtenerListas(){
	tPrecios=$("#tablaListaPreciosAll").DataTable();
	tPrecios.clear().draw();
	$.ajax({
		method:"post",
		data:{
			"cliente.idCliente":$("#clienteSelCatPrecios").val(),
			"hospital.idHospital":$("#hospitalSelCatPrecios").val()
		},
		async:false,
   	  	url: "../../mvc/listaprecios/getlistaspreciosparams"
   	}).done(function(data) {
   		console.log(data)
   		for (i=0;i<data.length;i++){   			
   			tPrecios.row.add([
   		             data[i].idListaPrecios,
      				 data[i].jubilados,
      				 "<a href='#' onclick='ajax_download_lp(" +data[i].idListaPrecios+")'>ver archivo</a>",
      				 "<a href='#' onclick='eliminarListaPrecios(" +data[i].idListaPrecios+")'>Eliminar</a>",
      				]).draw( false );
   		}
   	});	   
	
}

function showDivAlta(){
	$( "#divAgregarListaPrecios" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 800,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarListaPrecios" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});
	$.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/cliente/getclientes"
   	}).done(function(data) {
   		var optionsAsString = "";   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idCliente + "'>" + data[i].nombreCorto + "</option>";
		}      		
   		$( 'select[name="clienteSelCatPrecios2"]' ).append( optionsAsString );
   	});
    $.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/hospital/gethospitales"
   	}).done(function(data) {
   		var optionsAsString = "";   		   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idHospital + "'>" + data[i].nombreHospital + "</option>";
		}      		
   		$( 'select[name="hospitalSelCatPrecios2"]' ).append( optionsAsString );
   	});
    $("#clienteSelCatPrecios2").val($("#clienteSelCatPrecios").val());
	$("#hospitalSelCatPrecios2").val($("#hospitalSelCatPrecios").val());
}

function eliminarListaPrecios(idListaPrecios){	
	$( "#dialog-message-borrarlp" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
				$.ajax({
					async:false,
					data:{
						"idListaPrecios":idListaPrecios				
					},
					dataType:'json',
					url:   '../../mvc/listaprecios/eliminarlistapreciosv2',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarlp" ).dialog( "close" );
					},
					success:  function (response) {
						showMensajeSR("Lista eliminada correctamente!");
						obtenerListas();
						console.log(response)						
					},	
					error: function (response) {																	
						showMensajeSR("Se registró un error al eliminar la lista!");
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

function guardarListaPrecios(){	
	var fileName=document.getElementById("file6").value;
	console.log($("#jubilados").val())
	console.log(fileName)
	console.log($("#clienteSelCatPrecios2").val())
	console.log($("#hospitalSelCatPrecios2").val())
	if (fileName!=""){
		$.ajax({
			async:false,
			data:{
				
				"rutaLista":fileName,
				"cliente.idCliente":$("#clienteSelCatPrecios2").val(),
				"hospital.idHospital":$("#hospitalSelCatPrecios2").val()				
			},
			dataType:'json',
			url:   '../../mvc/listaprecios/guardarlistapreciosv2',
			type:  'post',		
			beforeSend: function () {
				//$("#resultado").html("Procesando, espere por favor..."),
				//alert("OK!")
			},
			success:  function (response) {				
				console.log(response)
				$("#idArchivoLista").val(response.id);
				uploadJqueryFormLista("form6");			
			},	
			error: function (response) {																	
				showMensajeSR("Se registró un error al guardar la lista!");
				console.log(response)
			}		
		});	
	}
	else
		showMensajeSR("Por favor adjunte el archivo de la lista de precios.");
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

function uploadJqueryFormLista(form){	
	   $('#result').html('');
	   document.getElementById("waitUpload").style.display="block";	   
	   $("#"+form).ajaxForm({
	    success:function(data) { 
	          //$('#result').html(data); 	  
	    	  document.getElementById("waitUpload").style.display="none";
	    	  showMensajeSR("La lista se registró correctamente!");
				$( "#divAgregarListaPrecios" ).dialog( "close" );
	    	  obtenerListas();
	          //alert("Se registr\u00f3 correctamente la evidencia");	    	  
	    	  return false;
	     },dataType:"text",
	     error:function(data) {
	    	 showMensajeSR("Error al registrar el gasto!");
	    	 return false;
	     }  
	   }).submit();
}

function ajax_download_lp(id) {	
	var input_name="id1";
	var input_name2="id2";
	var url="../../mvc/file/getFileListaPrecios";
	var data={idListaPrecios:id};
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
                  id +"'/></form>" +
                  "</body></html>";

    iframe_doc.open();
    iframe_doc.write(iframe_html);
    $(iframe_doc).find('form').submit();
}
//------------------------------FIN AJAX DOWNLOAD---------------------------------