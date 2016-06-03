<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ARGAL-HSCS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="../../static/js/v2.0/app/implants/Implants.js"></script>	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.8/jquery.form-validator.min.js"></script>
	<script src="../../static/js/v2.0/js/msjvalidator.js"></script>	
	<link rel="stylesheet" href="../../static/js/v2.0/js/screen.css" />
	<link rel="stylesheet" href="../../static/css/v2.0/jquery.dataTables.min.css" />
	<link rel="stylesheet" href="../../static/css/v2.0/argal_style.css" />
	<link rel="stylesheet" href="../../static/css/v2.0/form.css" />
	<link rel="stylesheet" href="../../static/css/v2.0/jquery-ui.css">
	<script src="../../static/js/v2.0/js/jquery.plugin.js"></script>	
	<script src="../../static/js/v2.0/js/jquery.form.js"></script>
	
</head>
<body bgcolor="#46C2BC">
	<header id="pageHeader">
		<table width="90%" class="pageHeader">
			<tr>
				<td align="center">
					<font color="white">HOSPITAL SYSTEM CONTROL SPENDING</font>
				</td>
			</tr>
		</table>
		<jsp:include page="../menu/menu2.jsp" />
	</header>
	<div id="pageContainer" style="width: 1250px;">
		<div id="contentContainer" class="clearfix" align="center">
			<nav id="pageNav"></nav>
			<section id="pageSection" >			
			  <div class="form-group">
			  	<h5><img src="../../static/img/v2.0/img/implants.png" height="30" width="30" id="imgImplants" name="imgImplants"/>CATÁLOGO DE IMPLANTS
			  		
			  		<br><a href="#" onclick="agregarImplantShow()"><img src="../../static/img/v2.0/img/agregarimplant.jpg" height="30" width="30" id="imgImplants2" name="imgImplants2"/>Nuevo Implant</a>
			  	</h5>
			  	
			    <form id="allimplantsforms" name="allimplantsforms" class="register" method="post">
               		<table id="tablaListaImplants" class="display" cellspacing="0" width="100%">
					 <thead>
			            <tr>
			                <th>Nombre</th>
			                <th>Nextel</th>				                				                
			                <th>Id Nextel</th>
			                <th>Celular</th>				                				               
			                <th>Email Institucional</th>
			                <th>Nick Implant</th>
			                <th>Editar</th>
			                <th>Eliminar</th>
			            </tr>
			    		</thead>
			    		<tbody>
			    		</tbody>
					</table>								                		              

				</form>								
				<div id="divAgregarImplant" title="Agregar Implant" style="display: none" align="center">					
					<jsp:include page="alta_implant.jsp" />
				</div>
				<div id="divEditarImplant" title="Editar Implant" style="display: none" align="center">					
					<jsp:include page="editar_implant.jsp" />
				</div>  			  			  			    				
				<div id="dialog-message-borrarimplant" title="¿Eliminar Implant?" style="display: none" align="center">					
					<p>¿Seguro que desea eliminar al Implant?</p>					
				</div>
				<div id="dialog-message-implant" title="Aviso" style="display: none" align="center">					
					<div id="mensajeGuardarImplant"></div>					
				</div>
			  </div>			  			
			</section>
			<aside id="pageAside"></aside>
		</div>
		<footer id="pageFooter"></footer>
	</div>
</body>
</html>