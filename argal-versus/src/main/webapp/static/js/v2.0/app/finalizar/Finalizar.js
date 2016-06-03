function validacionesFactura(){
	$.formUtils.addValidator({
		  name : 'facturaval',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando:"+$("#aprobadaFacturaSubir").val());			  
			  if ($("#aprobadaFacturaSubir").val()=="SI"){
				  if (value=="")
					  return false;
				  return true;
			  }			  	   
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
	});
	$.formUtils.addValidator({
		  name : 'facturaval2',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando:"+$("#aprobadaFacturaSubir").val());			  
			  if ($("#aprobadaFacturaSubir").val()=="NO"){
				  if (value=="")
					  return false;
				  return true;
			  }			  	   
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
	});
	$.formUtils.addValidator({
		  name : 'finalizareventoval',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando:"+$("#aprobadaFacturaSubir").val());			  
			  
				  if (value=="")
					  return false;
				  return true;
			  			  	   
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
	});	
}

function showTipoFacturaDiv(){
	if ($("#aprobadaFacturaSubir").val()=="SI"){
		$("#facturaAprobadaDiv").show();
		$("#facturaNoAprobadaDiv").hide();		
	}
	else{
		$("#facturaAprobadaDiv").hide();
		$("#facturaNoAprobadaDiv").show();
	}
}

function guardarFactura(){
	var fileName=document.getElementById("file4").value;	
	var numeroFacturaTmp="";
	var observaciones="";
	var monto="";
	if ($("#aprobadaFacturaSubir").val()=='SI'){
		numeroFacturaTmp=$("#numeroFacturaAprob").val();
		observaciones=$("#obsFacturaAprob").val();
		monto=$("#montoFacturaAprob").val();
	}
	else{
		numeroFacturaTmp=$("#numeroFacturaNoAprob").val();
		observaciones=$("#obsFacturaNoAprob").val();
		monto=$("#montoFacturaNoAprob").val();
	}
	if (fileName!=""){					
		$.ajax({
			async:false,			
			data:{
				"idFactura":$("#idEventoFinalizarHid").val(),
				"numeroFactura":numeroFacturaTmp,
				"aprobada":$("#aprobadaFacturaSubir").val(),			
				"observaciones":observaciones,
				"rutaFactura":fileName,
				"monto":monto,
				"ajusteFactura":$("#ajusteFacturaNoAprob").val(),
				"tipoComprobanteFiscalCorregido":$("#tipoCompFacturaNoAprob").val(),
				"montoComprobanteFiscalCorregido":$("#montoCompFacturaNoAprob").val(),
				"folioComprobanteFiscalCorregido":$("#folioCompFacturaNoAprob").val()
			},
			dataType:'json',
			url:   'mvc/evento/guardarfacturav2',
			type:  'post',		
			beforeSend: function () {
				//$("#resultado").html("Procesando, espere por favor..."),
				//alert("OK!")
			},
			success:  function (response) {				
				uploadJqueryFormFactura("form4");			
			},	
			error: function (response) {																	
				showMensaje("Se registró un error al guardar la factura!");
				console.log(response)
			}		
		});
	}
	else
		showMensajeSR("Por favor seleccione el archivo de la factura!");			
}

function eliminarFactura(idFactura,aprobada){
	var aprob="SI";
	if (aprob==2)
		aprob="NO";
	$( "#dialog-eliminar-factura" ).dialog({
	      resizable: false,
	      width:400,
	      height:240,
	      modal: true,
	      buttons: {
	        "Eliminar": function() {	        	
	    		$.ajax({
	    			async:false,			
	    			data:{
	    				"id":$("#idEventoFinalizarHid").val(),
	    				"idFactura":idFactura,
	    				"aprobada":aprob
	    			},
	    			dataType:'json',
	    			url:   'mvc/evento/eliminarfacturav2',
	    			type:  'post',		
	    			beforeSend: function () {
	        			$( "#dialog-eliminar-factura" ).dialog( "close" );
	    			},
	    			success:  function (response) {				
	        			showMensajeSR("Se eliminó correctamente la factura!",idFactura);
	        			updateBalanceModal($("#idEventoFinalizarHid").val());
	    			},	
	    			error: function (response) {																	
	    				showMensaje("Se registró un error al eliminar la factura!");
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


function uploadJqueryFormFactura(form){	
   $('#result').html('');
   document.getElementById("waitUpload").style.display="block";	   	   	  
   $("#"+form).ajaxForm({
    success:function(data) { 
          //$('#result').html(data); 	  
    	  document.getElementById("waitUpload").style.display="none";
    	  showMensajeSR("La factura se registró correctamente!");
    	  updateBalanceModal($("#idEventoFinalizarHid").val());
          //alert("Se registr\u00f3 correctamente la evidencia");	    	  
    	  return false;
     },dataType:"text",
     error:function(data) {
    	 showMensajeSR("Error al registrar la factura!");
    	 return false;
     }  
   }).submit();
}

function ajax_downloadFactura(id,id2) {	
	var input_name="id1";
	var input_name2="id2";
	var url="mvc/file/getFacturaFile";
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
                  $("#idEventoFinalizarHid").val() +"'/>" +
                  "<input type=hidden name='" + input_name2 + "' value='" +
                  id +"'/></form>" +
                  "</body></html>";

    iframe_doc.open();
    iframe_doc.write(iframe_html);
    $(iframe_doc).find('form').submit();
}
//------------------------------FIN AJAX DOWNLOAD---------------------------------


function finalizarEvento(){						
	//montoAntesDesvios-totalDesvios
	/*var capitaTmp="";
	var cantidadCubTmp="";
	if (data.registroSeguroPersonal.condicionPaciente=='JUBILADO')*/	
	$.ajax({
		async:false,		
		data:{
			"idEvento":$("#idEventoFinalizarHid").val(),
			"montoAntesDesvios":$("#divMontoAntesDesvios").html(),
			"numFacturasAprobadas":$("#divFacturasAprobadas").html(),
			"totalDesvios":$("#divTotalCargosObservados").html(),
			"numFacturasRechazadas":$("#divFacturasRechazadas").html(),
			"totalDesviosComprobados":$("#desviosComprobados").val(),
			"montoDesviosFacturacion":$("#divMontoTotalDesviosFacturacion").html(),			
			"montoAjusteFacturacion":$("#montoAjusteFacturacion").val(),
			"ivaFinalizarEvento":$("#ivaFinalizarEvento").val(),
			"montoFacturacionCorregido":$("#montoFacturacionCorregido").val(),
			"coaseguroFinalizarEvento":$("#coaseguroFinalizarEvento").val(),
			"montoFinalFacturacion":$("#divMontoFinalFacturacion").html(),
			"deducibleFinalizarEvento":$("#deducibleFinalizarEvento").val(),
			"descuentoHospFinalizarEvento":$("#descuentoHospFinalizarEvento").val(),
			"registroSeguroPersonal.cantidadCubiertaConvenio":$("#capitaCantidadFinalizarEvento").val(),
			"registroSeguroPersonal.capita":$("#capitaFinalizarEvento").val()
		},
		dataType:'json',
		url:   'mvc/evento/finalizareventov2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {				
			showMensaje("Se finalizó correctamente el evento!");
			updateBalanceModal($("#idEventoFinalizarHid").val());
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar la factura!");
			console.log(response)
		}		
	});
}

function limpiarFacturaForm(){
	$("#numeroFacturaAprob").val("");
	$("#facturaNoAprobadaDiv").hide();
	$("#obsFacturaAprob").val("");	
	$("#montoFacturaAprob").val("");
	$("#aprobadaFacturaSubir").val("SI");	
	$("#ajusteFacturaNoAprob").val("");
	$("#tipoCompFacturaNoAprob").val("");
	$("#folioCompFacturaNoAprob").val("");
	$("#montoCompFacturaNoAprob").val("");	
}

function updateBalanceModal(idEvento){
	$("#divFinalizarMain").hide();
	limpiarFacturaForm();
	$("#idEventoFinalizarHid").val(idEvento);
	$("#idEventoHidden4").val(idEvento);	
	$( "#tabsFinalizar" ).tabs();	
	$.ajax({
		method:"post",
   	  	data:{"idEvento":idEvento},
   	  	url: "mvc/evento/obtenereventobyidv2"
   	}).done(function(data) {   		
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
		$("#desviosComprobados").val(data.totalDesviosComprobados);				
		$("#montoAjusteFacturacion").val(data.montoAjusteFacturacion);
		$("#ivaFinalizarEvento").val(data.ivaFinalizarEvento);
		$("#montoFacturacionCorregido").val(data.montoFacturacionCorregido);
		$("#coaseguroFinalizarEvento").val(data.coaseguroFinalizarEvento);		
		//$("#deducibleFinalizarEvento").val(data.deducibleFinalizarEvento);
		$("#deducibleFinalizarEvento").val(0);
		$("#descuentoHospFinalizarEvento").val(data.descuentoHospFinalizarEvento);
		$("#divMontoFinalFacturacion").html(data.montoFinalFacturacion);
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