<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>ARGAL-HSCS</title>
<script src="static/js/v2.0/js/jquery-2.2.0.js"></script>
<script src="static/js/v2.0/js/jquery-ui.js"></script>
<script src="static/js/v2.0/js/jquery.dataTables.min.js"></script>
<script src="static/js/v2.0/js/jquery.plugin.js"></script>		
<script src="static/js/v2.0/js/jquery.form-validator.min.js"></script>
<script src="static/js/v2.0/js/msjvalidator.js"></script>
<script src="static/js/v2.0/js/jquery.form.js"></script>
<script src="static/js/v2.0/app/util/validaciones_registro.js"></script>
<script src="static/js/v2.0/app/evento/Evento.js"></script>
<script src="static/js/v2.0/app/evento/EditarEvento.js"></script>
<script src="static/js/v2.0/app/bitacora/Bitacora.js"></script>
<script src="static/js/v2.0/app/egreso/Egreso.js"></script>
<script src="static/js/v2.0/app/finalizar/Finalizar.js"></script>
<link rel="stylesheet" href="static/css/v2.0/jquery.dataTables.min.css" />
<link rel="stylesheet" href="static/css/v2.0/argal_style.css" />
<link rel="stylesheet" href="static/css/v2.0/jquery-ui.css">
<link rel="stylesheet" href="static/js/v2.0/js/screen.css" />
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
		<jsp:include page="../menu/menu.jsp" />
	</header>
	<div id="pageContainer" style="width: 98%">
		<div id="contentContainer" class="clearfix">
			<nav id="pageNav"></nav>
			<section id="pageSection">					
				Mostrar Eventos Registrados desde:
				<select style="width:200px;" id="showeventosby" name="showeventosby" data-validation="required" onchange="redirectParam()">   																	
								<option value="1">30 DÍAS ANTES</option>
								<option value="2">60 DÍAS ANTES</option>								
								<option value="3">120 DÍAS ANTES</option>
								<option value="4">400 DÍAS ANTES</option>
								<option value="5">TODOS LOS EVENTOS</option>
				</select>
				<header class="sectionHeader">					
				</header>
				<article class="sectionArticle">
					<br>
					<div id="contenidoMenu" align="center"></div>
					<div id="loading" align="center">
						<div><h4>Obteniendo Eventos....</h4></div>
						<div><img src="static/img/v2.0/img/loadingEvent.gif" /></div>
					</div>
					<div id="contenidoTablaEventos" align="center">
						<table id="tablaEventos" class="display" style="display:none;" cellspacing="0" >
							<thead>
								<tr>
									<th>Id Evento</th>
									<th>Folio Hospital</th>
									<th>Folio Argal</th>
									<th>Fecha Ingreso</th>
									<th>Cliente</th>
									<th>Hospital</th>
									<th>Paciente</th>									
									<th>Estado del Evento</th>									
									<th>Editar Datos</th>																		
									<th align="center">Control de Gastos</th>
									<th>Seguimiento Médico</th>
									<th>Egresar Paciente</th>
									<th>Finalizar Evento</th>
									<th>Editado Por</th>
									<%if (Integer.parseInt(""+request.getSession().getAttribute("tipoUsuarioLogin"))==3){%>
									<th>Eliminar</th>			
									<%}%>															
								</tr>
							</thead>
							<tbody align="center">
							</tbody>
						</table>
					</div>					
					<div id="divEditar" title="Editar Datos del Evento" style="display: none"
						align="center">
						<jsp:include page="editar_evento.jsp" />
					</div>
					<div id="divGastos" title="Control de Gastos" style="display: none"
						align="center">	
						<jsp:include page="../gastos/gastos.jsp" />
					</div>
					<div id="divBitacoras" title="Seguimiento Médico"
						style="display: none" align="center">
						<jsp:include page="../bitacoras/bitacoras.jsp" />
					</div>
					<div id="divEgreso" title="Egresar Paciente" style="display: none"
						align="center">
						<jsp:include page="../egreso/egreso.jsp" />
					</div>
					<div id="divFinalizar" title="Finalizar Evento" style="display: none"
						align="center">
						<jsp:include page="../finalizar/finalizar.jsp" />
					</div>
					<div id="dialog-confirm" title="¿Eliminar el evento?"  style="display: none" align="center">
  						<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Se eliminarán todos los gastos, bitácoras, facturas y datos del evento :<div id="divEventoEliminar"></div>Continuar?</p>
					</div>
					<div id="dialog-message" title="Guardar Evento" style="display: none" align="center">					
						<div id="mensajeGuardar"></div>					
					</div>
					<div id="dialog-message-candados" title="AVISO!!!" style="display: none" align="center">
						<div id="mensajeAvisoCandados"></div>
					</div>
				</article>
				<footer class="sectionFooter"></footer>
			</section>
			<aside id="pageAside"></aside>
		</div>
		<footer id="pageFooter"></footer>
	</div>
</body>
</html>