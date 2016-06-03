function seleccionarCpt(id,desc,tipo){
	if (tipo==1){
		$("#cpt1").val(desc);
		$("#idCpt1").val(id);
	}
	if (tipo==2){
		$("#cpt2").val(desc);
		$("#idCpt2").val(id);
	}
	$("#divCpts").dialog('close');
}

function seleccionarIcdEgreso(id,desc,tipo){
	if (tipo==3){
		$("#icd3").val(desc);
		$("#idIcd3").val(id);
	}		
	$("#divIcdsEgreso").dialog('close');
}

function seleccionarIcdEgreso(id,desc,tipo){
	if (tipo==3){
		$("#icd3").val(desc);
		$("#idIcd3").val(id);
	}		
	$("#divIcdsEgreso").dialog('close');
}

function showGridIcdEgreso(tipo){	
    //tIcds.clear().draw();    	
	$( "#divIcdsEgreso" ).dialog({
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
   		$("#tablaGridIcdsEgreso").DataTable( {
        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],
        "data": data,"bDestroy": true
    	});
   	});
}

function showGridCpt(tipo){	
    //tIcds.clear().draw();    	
	$( "#divCpts" ).dialog({
			 resizable: false,
		      height: 500,
		      width: 1200,
		      modal: true
	});				
	$.ajax({
		method:"post",
		async:false,
		data:{"tipo":tipo},
		url: "mvc/evento/getcptslt"
   	}).done(function(data) {   		   		   			   			   		   	
   		$("#tablaGridCpts").DataTable( {
        "lengthMenu": [[20, 50, 150, -1], [20, 50, 150, "All"]],
        "data": data,"bDestroy": true
    	});
   	});
}



function validacionesEgreso(){
	$.formUtils.addValidator({
		  name : 'egresoval',
		  validatorFunction : function(value, $el, config, language, $form) {			  
			  console.log("validando");			  
				  if (value=="")
					  return false;
				  return true;			  	   
		  },
		  errorMessage : 'Campo obligatorio *',
		  errorMessageKey: 'badEvenNumber'
		});
	$.formUtils.addValidator({
		  name : 'valtipoevento',
		  validatorFunction : function(value, $el, config, language, $form) {
			  console.log(value);
			  if ($("#motivoEgreso").val()=='DEFUNCION'){
					  if (value=="")
						  return false;
					  return true;				  
			  }	    
		  },
		  errorMessage : 'Seleccione',
		  errorMessageKey: 'badEvenNumber'
	});

}

function guardarEgreso(idEvento){
	var d=new Date($("#fechaEgreso").val());
	dt=d.getDate();
	dt++;
	mn=d.getMonth();
	mn++;
	yy=d.getFullYear();
	var fechaEgr=mn+"/"+dt+"/"+yy;
	
	var horaEgresoDt=new Date(0, 0, 1, 1, 1);
	var horaDefDt=new Date(0, 0, 1, 1, 1);
	var icdEg=0;
	var cpt1=0;
	var cpt2=0;
	if ($("#idIcd3").val!=""){
		icdEg=$("#idIcd3").val();
	}
	if ($("#idCpt1").val()!=""){			
		cpt1=$("#idCpt1").val();
	}
	if ($("#idCpt2").val()!=""){			
		cpt2=$("#idCpt2").val();
	}
	
	var fechaDefEgresoTmp=null;
	var horaDefEgresoTmp=null;
	var causaDefEgresoTmp=null;
	var datos={			
			"idEvento":$("#idEventoEgreso").val(),
			"fechaEgreso":fechaEgr,
	   		"horaEgreso":$("#horaEgreso").val(),
	   		"diagnosticoEgreso1.idIcd":icdEg,	   		
	   		"procedimiento1.idCpt":cpt1,
	   		"procedimiento2.idCpt":cpt2,
	   		"eventosNoDeseablesEntornoHosp":$("#eventosNoDeseables").val(),
	   		"motivoEgreso":$("#motivoEgreso").val(),
	   		"diasIncapacidad":$("#diasIncapacidad").val()
	}
	if ($("#motivoEgreso").val()=='DEFUNCION'){
		var d2=new Date($("#fechaDefEgreso").val());
		dt=d2.getDate();
		dt++;
		mn=d2.getMonth();
		mn++;
		yy=d2.getFullYear();
		var fechaDef=mn+"/"+dt+"/"+yy;		
		fechaDefEgresoTmp=fechaDef;
		horaDefEgresoTmp=$("#horaDefEgreso").val();
		causaDefEgresoTmp=$("#causaDirectaDef").val();	
		datos={			
			"idEvento":$("#idEventoEgreso").val(),
			"fechaEgreso":fechaEgr,
	   		"horaEgreso":$("#horaEgreso").val(),
	   		"diagnosticoEgreso1.idIcd":icdEg,	   		
	   		"procedimiento1.idCpt":cpt1,
	   		"procedimiento2.idCpt":cpt2,
	   		"eventosNoDeseablesEntornoHosp":$("#eventosNoDeseables").val(),
	   		"motivoEgreso":$("#motivoEgreso").val(),
	   		"diasIncapacidad":$("#diasIncapacidad").val(),	   			
			"horaDef":horaDefEgresoTmp,
			"fechaDef":fechaDefEgresoTmp,
			"causaDirectaDef":causaDefEgresoTmp,
		};
	}
	$.ajax({
		async:false,			
		data:datos,
		dataType:'json',
		url:   'mvc/evento/registraraltapacientev2',
		type:  'post',		
		beforeSend: function () {
			//$("#resultado").html("Procesando, espere por favor..."),
			//alert("OK!")
		},
		success:  function (response) {
			showMensaje("Se registró correctamente el egreso!");			
		},	
		error: function (response) {																	
			showMensaje("Se registró un error al guardar el egreso!!");
			console.log(response)
		}		
	});		
}

function showGridDef(){
	if ($("#motivoEgreso").val()=='DEFUNCION'){
		$("#divDefEgreso").show();
	}
	else
		$("#divDefEgreso").hide();
}