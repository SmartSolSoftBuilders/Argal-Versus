var LayoutController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	$scope.sendFile=0;
	$rootScope.generarlayout=false;
	
  	$scope.ajax_download=function() {
  		if ($scope.tipoEvento!=null){
		var input_name="id1";
		var input_name2="id2";
		var input_name3="id3";
		var input_name4="id4";
		console.log($scope);

		var id1=$scope.tipoEvento;
		var id2=$scope.finalizado;
		var id3="";	
		var id4="";
		console.log($scope.dt2)
//		console.log(".."+$scope.finalizado)
		if ($scope.finalizado!=true)
			id2=false;
		if ($scope.dt!="" && $scope.dt!=null)
			id3=$scope.dt.getFullYear()+"/"+($scope.dt.getMonth()+1)+"/"+$scope.dt.getDate();
		if ($scope.dt2!="" && $scope.dt2!=null)
			id4=$scope.dt2.getFullYear()+"/"+($scope.dt2.getMonth()+1)+"/"+$scope.dt2.getDate();
		
		var url="mvc/reportes/getFile";
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
	    $scope.sendFile=1;	    
	    $(iframe_doc).find('form').submit();	    
  		}
  		else
  			alert("Debe seleccionar un tipo de evento!");
	}
    		        
    
    $scope.showFormNac = function(){
    	console.log($scope);
    	if ($scope.regreportemedicoselected!=null){
    		if ($scope.regreportemedicoselected.idRegistroMedico==5)
    			document.getElementById("divAltaNacimiento").style.display = 'block';
    		else
    			document.getElementById("divAltaNacimiento").style.display = 'none';
    	}
    };
    
    
    $scope.$on('selectionChange', function (event, args) {    	    	
    	//$rootScope.cliente=args.item;
    	$rootScope.editbitacora={};	
    	$rootScope.editbitacora.idBitacora=args.item.idBitacora;
    	$rootScope.editbitacora.observaciones=args.item.observaciones;
    	console.log("SELECTED");
        console.log($rootScope.editbitacora);        
    });
    
    
    $scope.today = function() {
		$scope.dt = new Date();
		$scope.dt2 = new Date();
	};
	
	$scope.today();
	$scope.showWeeks = false;
	$scope.toggleWeeks = function () {
		$scope.showWeeks = ! $scope.showWeeks;
	};

	$scope.clear = function () {
		$scope.dt = null;
		$scope.dt2 = null;
	};

	// Disable weekend selection
	$scope.disabled = function(date, mode) {
//	  return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 8 ) );
	};

	$scope.toggleMin = function() {
		$scope.minDate = ( $scope.minDate ) ? null : new Date();
	};
	$scope.toggleMin();

	$scope.open = function($event,opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};
	$scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	};
	$scope.generarLayout = function(){
		$rootScope.generarlayout=true;
		$http.post('mvc/reportes/obtenerlayout').
		success(function(data, status, headers, config) {
	    	  alert("Descarga el archivo");	    	  
	    	  //$location.path('/showeventos');
	    }).error(function(data, status, headers, config) {
	    	  alert("Error al registrar la bit�cora"+data);
	    });
	}
	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
	$scope.format = $scope.formats[0];
	

};
