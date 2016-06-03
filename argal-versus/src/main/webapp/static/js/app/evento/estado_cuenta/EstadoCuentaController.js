var EstadoCuentaController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	scope=$scope;
	scope.rowCollectionEstadoCuentaImpro=[
			{'idCargo':1, 'descripcion':'SILICON', 'fecha':'2014/02/02','monto':100.00,'docref':'word.doc'},
			{'idCargo':2, 'descripcion':'SILICON', 'fecha':'2014/02/02','monto':100.00,'docref':'docurew432.doc'}
	];
	scope.rowCollectionEstadoCuentaPro=[		
	        {'idCargo':1, 'descripcion':'BOTOX', 'fecha':'2014/02/02','monto':100.00,'docref':'4234.doc'},
			{'idCargo':2, 'descripcion':'BOTOX', 'fecha':'2014/02/02','monto':100.00,'docref':'rewfd.xls'}
	];
	alert($rootScope.eventoEditSelected.idEvento);
	/*$http.post('mvc/evento/geteventos',scope.implant).
	  success(function(data, status, headers, config) {
		  scope.rowCollectionEstadoCuentaPro=data;
	  }).
	  error(function(data, status, headers, config) {
	 });*/
	/*$http.post('mvc/evento/geteventos',scope.implant).
	  success(function(data, status, headers, config) {
		  scope.rowCollectionEstadoCuentaImpro=data;
	  }).
	  error(function(data, status, headers, config) {
	 });*/
	
    scope.columnCollectionEstadoCuentaPro = [
        {label: 'IdCargo', map: 'idCargo'},
        {label: 'Descripci�n', map: 'fecha',formatFunction: 'date'},
        {label: 'Fecha', map: 'descripcion' },
        {label: 'Monto', map: 'monto', formatFunction: 'currency', formatParameter: '$'},
        {label: 'Doc. Referencia', map: 'docref'}               
    ];
    		
    scope.columnCollectionEstadoCuentaImpro = [
        {label: 'IdCargo', map: 'idCargo'},
        {label: 'Descripci�n', map: 'fecha' ,formatFunction: 'date'},
        {label: 'Fecha', map: 'descripcion'},        
        {label: 'Monto', map: 'monto' , formatFunction: 'currency', formatParameter: '$'},
        {label: 'Doc. Referencia', map: 'docref'}        
    ];   
    
    scope.globalConfigImpro = {    	        
            isPaginationEnabled: true,
            isGlobalSearchActivated: true,        
            itemsByPage: 5,
            selectionMode: 'single',
            actions: {
                list: { url: '/GetUsers' },
                edit: { url: '/EditUser', title: 'Edit User', desc: 'Edit', iconClass: '' }, 
                add: { url: '/AddUser', title: 'Add User', buttonClass: 'pull-right', iconClass: 'icon-plus', desc: ' Add User' }, // TODO: zkontrolovat default description
                remove: { url: '/DelUser', title: 'Confirmation Dialog', msg: 'Do you really want to delete the user?' }
            }
        };   
    scope.globalConfig = {    	        
        isPaginationEnabled: true,
        isGlobalSearchActivated: true,        
        itemsByPage: 5,
        selectionMode: 'single',
        actions: {
            list: { url: '/GetUsers' },
            edit: { url: '/EditUser', title: 'Edit User', desc: 'Edit', iconClass: '' }, 
            add: { url: '/AddUser', title: 'Add User', buttonClass: 'pull-right', iconClass: 'icon-plus', desc: ' Add User' }, // TODO: zkontrolovat default description
            remove: { url: '/DelUser', title: 'Confirmation Dialog', msg: 'Do you really want to delete the user?' }
        }
    };
    
    scope.showAltaGastoImproForm = function(){
    	document.getElementById("divTableGastosImpro").style.display = 'none';
    	document.getElementById("divFormTableGastosImpro").style.display = 'block';
    };
    
    scope.hideAltaGastoImproForm = function(){
    	document.getElementById("divTableGastosImpro").style.display = 'block';
    	document.getElementById("divFormTableGastosImpro").style.display = 'none';
    };
    
    scope.showEditGastoImproForm = function(){
    	document.getElementById("divTableGastosImpro").style.display = 'none';
    	document.getElementById("divEditFormTableGastosImpro").style.display = 'block';
    };
    
    scope.hideEditGastoImproForm = function(){
    	document.getElementById("divTableGastosImpro").style.display = 'block';
    	document.getElementById("divEditFormTableGastosImpro").style.display = 'none';
    };
    
    scope.eliminarGastoImproForm = function(){
    	confirm("Seguro desea eliminar el gasto?");
    };
    
};


var AltaCargoImproController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	$scope.tiposCargo=[
               	    {'idRazonGastoImpro':1, 'descripcion':'IMPROCEDENTE'},
               		{'idRazonGastoImpro':2, 'descripcion':'PROCEDENTE'}
 	 ];
	
	$scope.razonesGastoImpros=[
	    {'idRazonGastoImpro':1, 'descripcion':'NO NECESARIO'},
		{'idRazonGastoImpro':2, 'descripcion':'COSTOSO'}
	 ];
	 
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();
	  $scope.showWeeks = false;
	  $scope.toggleWeeks = function () {
	    $scope.showWeeks = ! $scope.showWeeks;
	  };

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = ( $scope.minDate ) ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	  };
 
	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
	  $scope.format = $scope.formats[0];
};


var EditarCargoImproController = function($scope, $http, $timeout,$rootScope,$location,$modal) {
	$scope.tipoCargoEdit="DIRECTO";
	$scope.montoEdit="$100";
	
	$scope.razonesGastoImpros=[
	    {'idRazonGastoImpro':1, 'descripcion':'NO NECESARIO'},
		{'idRazonGastoImpro':2, 'descripcion':'COSTOSO'}
	 ];
	 
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();
	  $scope.showWeeks = false;
	  $scope.toggleWeeks = function () {
	    $scope.showWeeks = ! $scope.showWeeks;
	  };

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = ( $scope.minDate ) ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	  };
 
	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
	  $scope.format = $scope.formats[0];
};