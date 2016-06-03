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
	<script src="../../static/js/v2.0/app/reportes/Layout.js"></script>		
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
			  	<h5><img src="../../static/img/v2.0/img/escribir_evento.png" height="30" width="30" id="imgAltaEvento" name="imgAltaEvento"/>Generar Layout General:</h5>
				<div id="divLayoutController" align="center">
				<table width="880" border=2>																	
						<tr>
							<td><span style="color: #6688a6;">TIPO DE EVENTO:</span></td>
							<td>
								<select  id="tipoEventoLayout" name="tipoEventoLayout" required>
									<option value="">Seleccione...</option>
									<option value="1">BANCO</option>
									<option value="2">ASEGURADORA</option>
								</select>
							</td>
							<td><span style="color: #6688a6;">¿EVENTOS FINALIZADOS?:</span></td>
							<td><input type="checkbox" name="finalizadoLayout" id="finalizadoLayout" /></td>
							</tr>
							<tr>
							<td><span style="color: #6688a6;">FECHA DE INICIO:</span></td>
							<td>
								<input type="date" id="fechaBuscarInicioLayout" data-validation="required" />		        				        				   
							</td>
							<td><span style="color: #6688a6;">FECHA DE FIN:</span></td>
							<td>
								<input type="date" id="fechaBuscarFinLayout" data-validation="required" />		        				        				   	
							</td>		  	
							
						</tr>		
					</table>
					<!-- button ng-show="sendFile!=1" ng-click="ajax_download()">Generar</button -->														  	
					<br>
					<button class="myButton" onclick="ajax_download_reporte()" >GENERAR</button>
				</div>
				<h5><img src="../../static/img/v2.0/img/escribir_evento.png" height="30" width="30" id="imgAltaEvento" name="imgAltaEvento"/>Generar Layout Diario:</h5>
				<div id="divLayoutDiarioController" align="center">
				<table width="880" border=2>																	
						<tr>
							<td><span style="color: #6688a6;">TIPO DE EVENTO:</span></td>
							<td>
								<select  id="tipoEventoLayoutDiario" name="tipoEventoLayoutDiario" required>									
									<option value="1">BANCO</option>									
								</select>
							</td>														
						</tr>		
					</table>
					<!-- button ng-show="sendFile!=1" ng-click="ajax_download()">Generar</button -->														  	
					<br>
					<button class="myButton" onclick="ajax_download_reporte_diario()" >GENERAR</button>
				</div>
				<h5><img src="../../static/img/v2.0/img/escribir_evento.png" height="30" width="30" id="imgAltaEvento" name="imgAltaEvento"/>Generar Layout Mensual:</h5>
				<div id="divLayoutDiarioController" align="center">
				<table width="880" border=2>																	
						<tr>
							<td><span style="color: #6688a6;">FECHA DE INICIO:</span></td>
							<td>
								<input type="date" name="fechaBuscarInicioLayoutMensual" id="fechaBuscarInicioLayoutMensual" />		        				        				   
							</td>
							<td><span style="color: #6688a6;">FECHA DE FIN:</span></td>
							<td>
								<input type="date" id="fechaBuscarFinLayoutMensual" name="fechaBuscarFinLayoutMensual" />		        				        				   	
							</td>		  							
							<td><span style="color: #6688a6;">TIPO DE EVENTO:</span></td>
							<td>
								<select  id="tipoEventoLayoutMensual" name="tipoEventoLayoutMensual" required>									
									<option value="1">BANCO</option>									
								</select>
							</td>														
						</tr>		
					</table>
					<!-- button ng-show="sendFile!=1" ng-click="ajax_download()">Generar</button -->														  	
					<br>
					<button class="myButton" onclick="ajax_download_reporte_mensual()" >GENERAR</button>
				</div>
				</section>
			<aside id="pageAside"></aside>
		</div>
		<footer id="pageFooter"></footer>
	</div>
</body>
</html>				