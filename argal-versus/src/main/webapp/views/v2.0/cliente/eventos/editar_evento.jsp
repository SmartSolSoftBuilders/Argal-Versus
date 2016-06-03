<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="../../../static/js/v2.0/app/evento/EditarEvento.js"></script>
	<link rel="stylesheet" href="../../../static/css/v2.0/jquery.dataTables.min.css" />
	<link rel="stylesheet" href="../../../static/css/v2.0/argal_style.css" />
	<link rel="stylesheet" href="../../../static/css/v2.0/form.css" />
	<link rel="stylesheet" href="../../../static/css/v2.0/jquery-ui.css">
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
		<div id="contentContainer" class="clearfix" align="center">
			<nav id="pageNav"></nav>
			<section id="pageSection" >			
			  <div class="form-group">
			    <form name="altaeventoform" class="register">
			    	<fieldset class="row1">
			    	<legend>1) DATOS GENERALES DEL INGRESO
                	</legend>                	
		                <p>
		                    <label>HOSPITAL:
		                    </label>		                    
		                    <input type="text" onclick="openShowHosp('','views/hospital/gridhosp.jsp')" style="cursor: pointer;width: 120px;"/>
							<img src="../../../static/img/v2.0/img/buscar.jpg"  style="cursor: pointer;float:left; " height="25" width="25" onclick="openShowHosp('','views/hospital/gridhosp.jsp')">		                    
		                    <label>TIPO DE CLIENTE:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                    <label>ID HOSPITALARIO:
		                    </label>
		                    <input type="text" id="idHospitalario" name="idHospitalario"/>
		                    <label>FOLIO ARGAL:
		                    </label>
		                    <input type="text" id="folioHospitalario" name="folioHospitalario"/>
		                </p>
		                <p>
		                    <label>CLIENTE:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                    <label>TIPO DE EVENTO:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                    <label>FECHA INGRESO:
		                    </label>
		                    <input type="text"/>
		                    <label>HORA INGRESO:
		                    </label>
		                    <input type="text"/>
		                </p>
		            </fieldset>
		            <fieldset class="row1">
			    	<legend>2) DATOS DEL PACIENTE (BANCO)
                	</legend>
                		<p>
		                <h4>DATOS DEL TITULAR</h4>
		                </p>                	                	                
		                <p>
		                    <label>NO. NÓMINA:
		                    </label>		                    
		                    <input type="text" id="numeroNomina" name="numeroNomina"/>									                   
		                    <label>NO. AUTORIZACIÓN:
		                    </label>
		                    <input type="text" id="numeroAutorizacion" name="numeroAutorizacion"/>		                
		                    <label>CONDICIÓN DEL ASEGURADO:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                    <label>CENSO:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                </p>
		                <p>
		                <h4>DATOS DEL PACIENTE</h4>
		                </p>                    		                		
		                <p>
		                    <label>NOMBRE:
		                    </label>
		                    <input type="text" id="idHospitalario" name="idHospitalario"/>
		                    <label>APELLIDO PATERNO:
		                    </label>
		                    <input type="text" id="folioHospitalario" name="folioHospitalario"/>
		                   	<label>APELLIDO MATERNO:
		                    </label>
		                    <input type="text" id="folioHospitalario" name="folioHospitalario"/>
		                </p>		                            			               
		                <p>
		                	<label>EDAD:
		                    </label>
		                    <input type="text" id="idHospitalario" name="idHospitalario"/>
		                    <label>UNIDAD:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                   	<label>SEXO:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
							<label>PARENTESCO:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
		                </p>		                
		            </fieldset>
		            <fieldset class="row1">
			    	<legend>3) DATOS DE INGRESO HOSPITALARIO
                	</legend>	
                		<p>
		                	<label>NÚMERO HABITACIÓN:
		                    </label>
		                    <input type="text" id="idHospitalario" name="idHospitalario"/>
		                    <label>ANTECENTES PERSONALES PATOLÓGICOS:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
						</p>
						<p>
		                   	<label>DIAGNÓSTICO DE INGRESO 1:
		                    </label>
		                    <input type="text" onclick="openShowHosp('','views/hospital/gridhosp.jsp')" style="cursor: pointer;width: 120px;"/>
							<img src="../../../static/img/v2.0/img/buscar.jpg"  style="cursor: pointer;float:left; " height="25" width="25" onclick="openShowHosp('','views/hospital/gridhosp.jsp')">		                    
							<label>DIAGNÓSTICO DE INGRESO 2:
		                    </label>
		                    <input type="text" onclick="openShowHosp('','views/hospital/gridhosp.jsp')" style="cursor: pointer;width: 120px;"/>
							<img src="../../../static/img/v2.0/img/buscar.jpg"  style="cursor: pointer;float:left; " height="25" width="25" onclick="openShowHosp('','views/hospital/gridhosp.jsp')">
						</p>
						<p>
							<label>MÉDICO TRATANTE:
		                    </label>
		                    <select style="width:140px;" onchange="showTipoSeguroView()">   									
								<option value="">Seleccione...</option>
							</select>
							<label>ESPECIALIDAD:
		                    </label>
		                </p>
		            </fieldset>		            				
				</form>			  			  			  			    
			  </div>			  			
			</section>
			<aside id="pageAside"></aside>
		</div>
		<footer id="pageFooter"></footer>
	</div>
</body>
</html>