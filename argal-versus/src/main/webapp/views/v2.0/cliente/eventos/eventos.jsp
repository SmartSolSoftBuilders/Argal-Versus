<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="../../../static/js/v2.0/app/evento/Evento.js"></script>
<link rel="stylesheet" href="../../../static/css/v2.0/jquery.dataTables.min.css" />
<link rel="stylesheet" href="../../../static/css/v2.0/argal_style.css" />
<link rel="stylesheet" href="../../../static/css/v2.0/jquery-ui.css">
<style>
</style>
</head>
<body bgcolor="#6688a6">
	<header id="pageHeader">
		<table width="90%" class="pageHeader">
			<tr>
				<td align="center">
					<font color="white">HOSPITAL SYSTEM CONTROL SPENDING</font>
				</td>
			</tr>
		</table>
		<jsp:include page="../../menu/menu.jsp" />
	</header>
	<div id="pageContainer" style="width: 1250px;">

		<div id="contentContainer" class="clearfix">
			<nav id="pageNav"></nav>
			<section id="pageSection">
				<h2>Eventos Registrados</h2>
				<header class="sectionHeader">
					
				</header>
				<article class="sectionArticle">
					<br>
					<div id="contenidoMenu" align="center"></div>
					<div id="contenidoTablaEventos" align="center">
						<table id="tablaEventos" class="display" cellspacing="0"
							width="90%">
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
									<th>Control de Gastos</th>
									<th>Seguimiento Médico</th>
									<th>Egresar Paciente</th>
									<th>Finalizar Evento</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div id="divGastos" title="Resumen de Gastos" style="display: none"
						align="center">
						<jsp:include page="../gastos/view_gastos.jsp" />
					</div>
					<div id="divBitacoras" title="Bitácoras Médicas del Evento"
						style="display: none" align="center">
						<jsp:include page="../bitacoras/view_bitacoras.jsp" />
					</div>
					<div id="divEgreso" title="Datos de Egreso" style="display: none"
						align="center">
						<jsp:include page="../egreso/view_egreso.jsp" />
					</div>
					<div id="divBalance" title="Datos de Egreso" style="display: none"
						align="center">
						<jsp:include page="../balance/view_balance.jsp" />
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