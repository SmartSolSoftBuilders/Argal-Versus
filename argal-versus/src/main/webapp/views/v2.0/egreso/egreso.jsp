<div id="loadEgresoGif">
	<img src="static/img/v2.0/img/load2.gif" height="100" width="100" id="imgEgreso" name="imgEgreso"/>
	<h3>Por favor Espere...</h3>
</div>
<div id="divEgresoContenido">
<fieldset>
<table>
	<tr>
		<td><div id="idEventoBarEgr"></div></td>
		<td><div id="titularEventoBarEgr"></div></td>
		<td><div id="pacienteEventoBarEgr"></div></td>	
		<!--  td><div id="tipoClienteEventoBar"></div></td-->
		<td><div id="clienteEventoBarEgr"></div></td>
	</tr>
	<tr>		
		<td><div id="numAutorizacionEventoBarEgr"></div></td>			
	</tr>
</table>
</fieldset>
<div class="form-group">	
	<h3><img src="static/img/v2.0/img/egresopaciente1.jpg" height="30" width="30" id="imgEgreso" name="imgEgreso"/>EGRESO DE PACIENTE</h3>
	<form id="egresoform" name="egresoform" class="register" method="post">
	<fieldset class="row1">
	   	<legend>INGRESE LOS DATOS DEL EGRESO
        </legend>      
        <p>
        	<input type="hidden" id="idEventoEgreso" name="idEventoEgreso" data-validation="required" readonly/>
			<label>FECHA EGRESO:
		    </label>
		    <input type="date" id="fechaEgreso" data-validation="required"/>
		    <label>HORA EGRESO:
		    </label>
		    <input type="time" id="horaEgreso" data-validation="required"/>
		 </p>
		 <p>		                  
		    <label>DIAGNÓSTICO DE EGRESO:
		    </label>
		    <input type="text" id="icd3" name="icd3" onclick="showGridIcdEgreso(3)" data-validation="required" readonly>
		    <input type="hidden" id="idIcd3" name="idIcd3" data-validation="required">
			<img src="static/img/v2.0/img/buscar.jpg"  style="cursor: pointer;" height="25" width="25" onclick="showGridIcdEgreso(3)">		                    
			
			<label>TIPO DE PROCEDIMIENTO 1:
		    </label>
		    <input type="text" id="cpt1" name="cpt1" onclick="showGridCpt(1)" data-validation="required" readonly>
		    <input type="hidden" id="idCpt1" name="idCpt1" data-validation="required">
			<img src="static/img/v2.0/img/buscar.jpg"  style="cursor: pointer; " height="25" width="25" onclick="showGridCpt(1)">																
		  </p>
		  <p>
			<label>TIPO DE PROCEDIMIENTO 2:
			</label>
			<input type="text" id="cpt2" name="cpt2" onclick="showGridCpt(2)" readonly data-validation="required">
			<input type="hidden" id="idCpt2" name="idCpt2" data-validation="required">
			<img src="static/img/v2.0/img/buscar.jpg"  style="cursor: pointer; " height="25" width="25" onclick="showGridCpt(2)">										
			<label>EVENTOS NO DESEABLES DEL ENTORNO HOSPITALARIO:</label>	
	   		<textarea size="300" id="eventosNoDeseables" name="eventosNoDeseables" data-validation="egresoval">
	   		</textarea>
	   	  </p>
	   	  <p>				
	   		<label>MOTIVO DE EGRESO:</label>
	   		<select id="motivoEgreso" name="motivoEgreso" onchange="showGridDef();" data-validation="egresoval"> 
		    	<option value="">seleccione...</option>
		    	<option>ALTA POR MEJORIA CLINICA</option>
		    	<option>ALTA VOLUNTARIA</option>
		    	<option>ALTA POR TRASLADO A OTRA UNIDAD HOSPITALARIA</option>
		    	<option>DEFUNCION</option>		    		
		    </select>
		    <label>DÍAS DE INCAPACIDAD:</label>		   		
		   	<input type="text" id="diasIncapacidad" name="diasIncapacidad" data-validation="egresoval" value="0"/>        
		 </fieldset>
		 <div align="center">
			<button type="submit" id="buttonGuardarEvento" class="myButton">Guardar</button>
		 	<font color="white">___</font>
		 	<!-- button type="submit" onclick="showMensaje('El evento se registró correctamente!')" class="myButton">Cancelar</button-->
			<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>									 	
		 </div>
		 <div id="divDefEgreso" style="display: none">
			 <p>	        	
				<label>FECHA DEF:
			    </label>
			    <input type="date" id="fechaDefEgreso" data-validation="valtipoevento"/>
			    <label>HORA EGRESO:
			    </label>
			    <input type="time" id="horaDefEgreso" data-validation="valtipoevento"/>
			    <label>CAUSA DIRECTA DEF:</label>	
	   			<textarea size="300" id="causaDirectaDef" name="causaDirectaDef" data-validation="valtipoevento"></textarea>
			 </p>		 
		 </div>		  
	</form>           	                  
</div>
</div>
<div id="divIcdsEgreso" title="Seleccione un Diagnóstico de Egreso" style="display: none" align="center">					
	<jsp:include page="../icd/grid_icdsEgreso.jsp" />
</div>

<div id="divCpts" title="Seleccione un Procedimiento" style="display: none" align="center">					
	<jsp:include page="../cpt/grid_cpts.jsp" />
</div>

