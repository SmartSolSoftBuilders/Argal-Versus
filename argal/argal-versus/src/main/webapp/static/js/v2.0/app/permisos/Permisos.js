var tPermisosImplants;
var tNoPermisosImplant;
$(document).ready(function() {
	//tablaListaImplants		
	$("#agregarPermisoLiga").hide();	
	$.ajax({
		method:"post",
		async:false,
   	  	url: "../../mvc/implant/getimplants"
   	}).done(function(data) {
   		console.log(data)
   		var optionsAsString = "";   		
   		for (i=0;i<data.length;i++){   			   		
   		    optionsAsString += "<option value='" + data[i].idImplant + "'>" + data[i].nombreImplant+" "+data[i].appImplant+" "+data[i].apmImplant + "</option>";
		}      		   		
   		$( 'select[name="implantPerSel"]' ).append( optionsAsString );   		
   	});		   
} );

function cargarPermisos(){
	var idImplant=$("#implantPerSel").val();
	if (idImplant!=""){
		$("#agregarPermisoLiga").show();
	}
	tPermisosImplants=$("#permisosimplanttabla").DataTable();
	tPermisosImplants.clear().draw();
	$.ajax({
		method:"post",
		async:false,
		data:{"idImplant":idImplant},
   	  	url: "../../mvc/implant/getimplantbyidv2"
   	}).done(function(data) {
   		console.log(data)
   		var hosp=data.hospitalesConPermisos;
   		for (i=0;i<hosp.length;i++){   			
   			tPermisosImplants.row.add([
		             hosp[i].idHospital,
		             hosp[i].nombreHospital,
		             hosp[i].estadoHospital,
		             hosp[i].municipioDelHospital,
		             hosp[i].telModuloHospital,  				 
		             "<a href='#' onclick='eliminarPermisoImplant(" +hosp[i].idHospital+","+idImplant+")'>Eliminar</a>",
  				]).draw( false );
		}   		
   	});		   	
	
}

function agregarPermisoShow(){
	$( "#divAgregarHospPermisoImplant" ).dialog({
		 resizable: false,
	      height: 400,
	      width: 1200,	      
	      position: { my: "top", at: "top", of: window  },
	      modal: true
	});	
	$(window).resize(function() {
		$( "#divAgregarHospPermisoImplant" ).dialog( "option", "position", { my: "top", at: "top", of: window } );
	});		
	tNoPermisosImplant=$("#tablaGridHospitalesPermisos").DataTable();
	tNoPermisosImplant.clear().draw();
	$.ajax({
		method:"post",
		async:false,
		data:{"idImplant":$("#implantPerSel").val()},
   	  	url: "../../mvc/hospital/gethospitalessinpermisoimplantv2"
   	}).done(function(data) {
   		console.log(data)
   		var hosp=data;
   		for (i=0;i<hosp.length;i++){   			
   			tNoPermisosImplant.row.add([
		             hosp[i].idHospital,
		             hosp[i].nombreHospital,		             
		             hosp[i].municipioDelHospital,
		             hosp[i].telModuloHospital,  				 
		             "<a href='#' onclick='agregarPermisoHospSel(" +hosp[i].idHospital+","+$("#implantPerSel").val()+")'>Agregar Permiso</a>",
  				]).draw( false );
		}   		
   	});		   	
}


function agregarPermisoHospSel(idHospital, idImplant){
	$.ajax({
		method:"post",
		async:false,
		data:{
			"idImplant":idImplant,
			"id":idHospital
			},
   	  	url: "../../mvc/implant/agregarpermisohospimpV2",
   	}).done(function(data) {
   		console.log(data)
   		showMensajePermisos("Permiso agregado correctamente!");   		   	
   	});
}

function eliminarPermisoImplant(idHospital, idImplant){	
	$( "#dialog-message-borrarpermiso" ).dialog({
	    resizable: false,
	    width:400,
	    height:240,
	    modal: true,
	    buttons: {
	        "Eliminar": function() {
	        	$.ajax({
					async:false,
					data:{
	        			"idImplant":idImplant,
	        			"id":idHospital
	        			},
	           	  	url: "../../mvc/implant/eliminarpermisohospimpv2",
					dataType:'json',
					type:  'post',		
					beforeSend: function () {
						//$("#resultado").html("Procesando, espere por favor..."),
						//alert("OK!")
						$( "#dialog-message-borrarpermiso" ).dialog( "close" );
					},
					success:  function (response) {						
						showMensajePermisos("Permiso eliminado correctamente!");						
					},	
					error: function (response) {																	
						showMensajePermisos("Ocurrió un error al eliminar el permiso!");
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

function showMensajePermisos(mensaje){
	$("#mensajeGuardarPermisos").html(mensaje);
	$( "#dialog-message-permisos" ).dialog({
	      modal: true,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	          window.location="/argal-web/mvc/index/getpage?page=4";
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
	if ($("#supermedicoImp").val()=='on'){
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
	if ($("#supermedicoImpEditar").val()=='on'){
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
			showMensajeAltaImp("Implant actualizado correctmente!");
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