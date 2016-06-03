function altaValidacionesAll(){
	$.validate({
		  form : '#altaedocta',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarGastoEdoCta();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#altacargoobs',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarGastoObs();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#altagastosrelev',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarGastoRel();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#altagastosevitados',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarGastoEvitado();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#altabitacora',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarBitacora();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#editabitacora',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEditarBitacora();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#egresoform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarEgreso();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#altaFacturaform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	guardarFactura();			  	
	            return false; // Will stop the submission of the form
	      }
	});
	$.validate({
		  form : '#finalizarEventoform',
		  language : myLanguage,
		  onSuccess : function($form) {			  
			  	finalizarEvento();			  	
	            return false; // Will stop the submission of the form
	      }
	});
}