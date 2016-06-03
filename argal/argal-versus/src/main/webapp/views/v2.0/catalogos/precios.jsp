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
	<script src="../../static/js/v2.0/app/precios/Precios.js"></script>	
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
			  	<h5><img src="../../static/img/v2.0/img/escribir_evento.png" height="30" width="30" id="imgAltaEvento" name="imgAltaEvento"/>CATÁLOGO DE PRECIOS</h5>
			    <form id="altaeventoform" name="altaeventoform" class="register" method="post">
			    	<fieldset class="row1">
			    	<legend>
                	</legend>                      		          
		                <p>
		                    <label>SELECCIONE EL HOSPITAL:
		                    </label>		                    
		                    <select id="hospitalSelCatPrecios" name="hospitalSelCatPrecios" style="width:180px;" data-validation="required">   									
								<option value="">Seleccione...</option>
							</select>
							<label>SELECCIONE EL CLIENTE:
		                    </label>		                    
		                    <select id="clienteSelCatPrecios" name="clienteSelCatPrecios" style="width:180px;" data-validation="required" onchange="obtenerListas()">   									
								<option value="">Seleccione...</option>
							</select>
							<div align="center"><button type="button" onclick="showDivAlta();" id="buttonGuardarEvento" class="myButton">Agregar Lista</button></div>
						</p>		                    		                       
		            </fieldset>
		            <fieldset class="row1">
			    	<legend>ARCHIVOS REGISTRADOS
                	</legend>	
               		<table id="tablaListaPreciosAll" class="display" cellspacing="0" width="100%">
					 <thead>
			            <tr>
			                <th>Id Lista</th>
			                <th>¿Jubilados?</th>				                				                
			                <th>Ver Archivo</th>
			                <th>Eliminar</th>				                				               
			            </tr>
			    		</thead>
			    		<tbody>
			    		</tbody>
					</table>								                		              
		            </fieldset>	
				</form>								
				<div id="divAgregarListaPrecios" title="Agregar Lista de Precios" style="display: none" align="center">					
					<jsp:include page="alta_lista.jsp" />
				</div>  			  			  			    				
				<div id="dialog-message-borrarlp" title="¿Eliminar Lista de Precios?" style="display: none" align="center">					
					<p>¿Seguro que desea eliminar la lista de precios?</p>					
				</div>
				<div id="dialog-message-lp" title="AVISO" style="display: none" align="center">					
					<div id="mensajeGuardarAviso"></div>					
				</div>
			  </div>			  			
			</section>
			<aside id="pageAside"></aside>
		</div>
		<footer id="pageFooter"></footer>
	</div>
</body>
</html>