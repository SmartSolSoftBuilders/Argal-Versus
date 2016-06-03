$(document).ready(function() {
	//$( "#fechaBuscarInicioLayout" ).datepicker();
	//$( "#fechaBuscarFinLayout" ).datepicker();
});

function ajax_download_reporte(){
  	if ($("#tipoEventoLayout").val()!=""){
		var input_name="id1";
		var input_name2="id2";
		var input_name3="id3";
		var input_name4="id4";		
		var id1=$("#tipoEventoLayout").val();
		var id2=false;
		var id3="";	
		var id4="";		
		if ($("#finalizadoLayout").prop( "checked"))
			id2=true;
		if ($("#fechaBuscarInicioLayout").val()!="" && $("#fechaBuscarInicioLayout").val()!=null){ 
			fecha1=new Date($("#fechaBuscarInicioLayout").val());
			id3=fecha1.getFullYear()+"/"+(fecha1.getMonth()+1)+"/"+fecha1.getDate();			
		}
		if ($("#fechaBuscarFinLayout").val()!="" && $("#fechaBuscarFinLayout").val()!=null){ 
			fecha2=new Date($("#fechaBuscarFinLayout").val());
			id4=fecha2.getFullYear()+"/"+(fecha2.getMonth()+1)+"/"+fecha2.getDate();			
		}
		
		var url="../reportes/getFile";
		var data={idFactura:id1,rutaFactura:id2};
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
	                  id1 +"'/>" +
	                  "<input type=hidden name='" + input_name2 + "' value='" +
	                  id2 +"'/>" +
	                  "<input type=hidden name='" + input_name3 + "' value='" +
	                  id3 +"'/>" +        
	                  "<input type=hidden name='" + input_name4 + "' value='" +
	                  id4 +"'/></form>" +
	                  "</body></html>";

	    iframe_doc.open();
	    iframe_doc.write(iframe_html);	    	   
	    $(iframe_doc).find('form').submit();	    
	}
  		else
  			alert("Debe seleccionar un tipo de evento!");
}


function ajax_download_reporte_diario(){
  	if ($("#tipoEventoLayoutDiario").val()!=""){
		var input_name="id1";
		var input_name2="id2";
		var input_name3="id3";
		var input_name4="id4";		
		var id1=$("#tipoEventoLayoutDiario").val();
		var id2=1;
		var id3="";	
		var id4="";								
		var url="../reportes/getFileDiario";
		var data={idFactura:id1,rutaFactura:id2};
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
	                  id1 +"'/>" +
	                  "<input type=hidden name='" + input_name2 + "' value='" +
	                  id2 +"'/>" +
	                  "<input type=hidden name='" + input_name3 + "' value='" +
	                  id3 +"'/>" +        
	                  "<input type=hidden name='" + input_name4 + "' value='" +
	                  id4 +"'/></form>" +
	                  "</body></html>";

	    iframe_doc.open();
	    iframe_doc.write(iframe_html);	    	   
	    $(iframe_doc).find('form').submit();	    
	}
  		else
  			alert("Debe seleccionar un tipo de evento!");
}


function ajax_download_reporte_mensual(){
  	if ($("#tipoEventoLayoutMensual").val()!=""){
		var input_name="id1";
		var input_name2="id2";
		var input_name3="id3";
		var input_name4="id4";		
		var id1=$("#tipoEventoLayoutDiario").val();
		var id2=2;
		var id3="";	
		var id4="";								
		var url="../reportes/getFileDiario";
		if ($("#fechaBuscarInicioLayoutMensual").val()!="" && $("#fechaBuscarInicioLayoutMensual").val()!=null){									
			id3=document.getElementById("fechaBuscarInicioLayoutMensual").value;
		}
		else return false;
		if ($("#fechaBuscarFinLayoutMensual").val()!="" && $("#fechaBuscarFinLayoutMensual").val()!=null){ 
			id4=document.getElementById("fechaBuscarFinLayoutMensual").value;
		}
		else return false;
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
	                  id1 +"'/>" +
	                  "<input type=hidden name='" + input_name2 + "' value='" +
	                  id2 +"'/>" +
	                  "<input type=hidden name='" + input_name3 + "' value='" +
	                  id3 +"'/>" +        
	                  "<input type=hidden name='" + input_name4 + "' value='" +
	                  id4 +"'/></form>" +
	                  "</body></html>";

	    iframe_doc.open();
	    iframe_doc.write(iframe_html);	    	   
	    $(iframe_doc).find('form').submit();	    
	}
  		else
  			alert("Debe seleccionar un tipo de evento!");
}