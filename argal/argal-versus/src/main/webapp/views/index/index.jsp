<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Argal</title>

<!-- script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.5/angular.js"></script> -->

<!-- <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script> -->
    
<!-- <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet"> -->
<script src="static/js/lib/angular.js"></script>
<script src="static/js/lib/angular_resource.js"></script>
<script src="static/js/lib/ng-table.src.js"></script>
<link rel="stylesheet" href="static/js/lib/ng-table.css">

<!--<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.5.0.js"></script-->
 <script src="static/js/lib/ui-bootstrap-tpls-0.10.0.js"></script>
 <script src="static/js/lib/moment.js"></script> 

<link href="static/css/bootstrap-combined.min.css" rel="stylesheet">

<link rel="stylesheet" href="static/css/app.css" />
<link rel="stylesheet" href="static/css/bootstrap3.css" />
<link rel="stylesheet" href="static/css/menu.css" />
<link rel="stylesheet" href="static/css/style.css" />
<!-- script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<!-- script src="http://malsup.github.com/jquery.form.js"></script> -->
<script src="static/js/lib/jquery-1.9.1.js" type="text/javascript"></script>
<script src="static/js/lib/jquery.form.js" type="text/javascript"></script>

<script src="static/js/app/index/index.js" type="text/javascript"></script>
<script src="static/js/lib/Smart-Table.debug.js" type="text/javascript"></script>
<script src="static/js/lib/createDialog.js" type="text/javascript"></script>
<script src="http://vitalets.github.io/checklist-model/checklist-model.js"></script>

<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js" type="text/javascript"></script>
    <script src="http://m-e-conroy.github.io/angular-dialog-service/javascripts/dialogs.min.js" type="text/javascript"></script>
</head>

<body ng-app="App">
	<div class="row" ng-app="App">		
	  <div class="large-12 columns" ng-controller="NavCtrl">
	  <table bgcolor="#6688a6" border=0 width="1000px" >
			<tr>
				<td bgcolor="#6688a6"><img src="static/img/argallogo.jpg" width="60" height="35" ></img></td>
				<td bgcolor="#6688a6" align="center">
					<span style="color: #ffffff;"><%=session.getAttribute("msj") %>
					<br></br><a style="color: #ffffff;" class="logout" href="logout">Cerrar sesión</a>					
					</span>
				</td>
				<td bgcolor="#6688a6"><nav class="nav-menu" menu-data="menu"></nav></td>         
          	<!--             <nav class="top-bar">
          	<section class="anav-menu top-bar-section" menu-data="menu"></section>
             		</nav> 
			--> 			
				 
			</tr>
	  </table>
	  
	  </div>	  
	</div>	
	<div id="MainContainer">
		<div ng-view>	  
		</div>	
	</div>
	<!-- Se incluyen los js de los controllers -->
	<script src="static/js/app/implant/ImplantController.js" type="text/javascript"></script>
	<script src="static/js/app/implant/MedicoTratanteController.js" type="text/javascript"></script>
	<script src="static/js/app/hospital/HospitalController.js" type="text/javascript"></script>
	<script src="static/js/app/permisos/PermisosController.js" type="text/javascript"></script>
	<script src="static/js/app/cliente/ClienteController.js" type="text/javascript"></script>
	<script src="static/js/app/evento/EventoController.js" type="text/javascript"></script>
	<script src="static/js/app/evento/estado_cuenta/EstadoCuentaController.js" type="text/javascript"></script>
	<script src="static/js/app/evento/seguimiento_medico/SeguimientoMedicoController.js" type="text/javascript"></script>
	<script src="static/js/app/evento/alta_medica/AltaMedicaController.js" type="text/javascript"></script>
	<!-- script src="static/js/app/evento/finalizar_evento/AltaMedicaController.js" type="text/javascript"></script> -->
	<script src="static/js/app/evento/cedula_control/CedulaControlController.js" type="text/javascript"></script>
	<script src="static/js/app/evento/poliza/PolizaController.js" type="text/javascript"></script>
	<script src="static/js/app/cat_icd/cat_icd.js" type="text/javascript"></script>
	<script src="static/js/app/cat_cpt/cat_cpt.js" type="text/javascript"></script>
	<script src="static/js/app/reportes/LayoutController.js" type="text/javascript"></script>
	<script src="static/js/app/password/PasswordController.js" type="text/javascript"></script>
</body>
</html>