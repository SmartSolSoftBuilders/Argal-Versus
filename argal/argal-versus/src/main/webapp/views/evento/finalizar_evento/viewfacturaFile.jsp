<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script  language="javascript">

	function getQueryVariable(variable) {
	  var query = window.location.search.substring(1);
	  var vars = query.split("&");
	  for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=");
	    if (pair[0] == variable) {
    	  return pair[1];
    	}
  	   } 
  	   //alert('Query Variable ' + variable + ' not found');
	}
	
	function loadFactura(){
		var idEvento = getQueryVariable("d1");
		var idFile = getQueryVariable("d2");
		var factura = new Object();
		factura.idFactura=idEvento;
		factura.rutaFactura=idFile;
		//alert(idEvento+idFile);
		 $.ajax({
			 async: false,
			 data : {
					"idFactura" : idEvento,
					"rutaFactura":idFile
				},
				type:'post',
				dataType : 'json',
             url: '../../../mvc/file/getFile',   
             success: function (data) {
                 alert(data)                     
             },
             error:function (xhr, ajaxOptions, thrownError) {
            	 alert("ERROR");
                 console.log("in error");
             } 
         });
	//	window.location="../../../static/img/factura1.pdf";
	}
</script>
<body onload="loadFactura()">
El Archivo es:
<a href="" onclick="">Ver</a>	
</body>