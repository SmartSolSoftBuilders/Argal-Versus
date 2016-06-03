<div id="loadBitacorasGif">
	<img src="static/img/v2.0/img/load2.gif" height="100" width="100" id="imgEgreso" name="imgEgreso"/>
	<h3>Por favor Espere...</h3>
</div>
<div id="dialog-confirm-eliminarbitac" title="¿Eliminar el gasto?"  style="display: none" align="center">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Se eliminará el registro de la bitácora!
	<div id="eventoEliminar"></div>, Continuar?</p>
</div>
<div id="divBitacorasContenido">
<fieldset>
<table>
	<tr>
		<td><div id="idEventoBarBit"></div></td>
		<td><div id="titularEventoBarBit"></div></td>
		<td><div id="pacienteEventoBarBit"></div></td>	
		<!--  td><div id="tipoClienteEventoBar"></div></td-->
		<td><div id="clienteEventoBarBit"></div></td>
	</tr>
	<tr>		
		<td><div id="numAutorizacionEventoBarBit"></div></td>			
	</tr>
</table>
</fieldset>
<input type="hidden" id="idEventoHidBit" name="idEventoHidBit"/>
<div id="divTablaBitacoras">
	<div align="right">
	<legend>
	  <a href="#" onclick="showDivBitacora(2)">NUEVA BITÁCORA<img src="static/img/v2.0/img/nueva-bitacora.jpg" width="30" height="30"/></a>
    </legend>
    </div>
    <legend>BITÁCORAS REGISTRADAS</legend>
	<table id="tablaBitacoras" class="display" cellspacing="0" >
		<thead>
			<tr>
				<th>Id Bitácora</th>
				<th>Fecha</th>
				<th>Estado del Paciente</th>
				<th>Observaciones</th>				                				               
				<th>Interconsultas</th>
				<th>Editar</th>
				<th>Eliminar</th>
			</tr>
	    </thead>
	    <tbody>
	   	</tbody>
	</table>
</div>
<div id="divRegistrarBitacora" style="display:none" >
	<fieldset class="row1">
	<legend>INGRESE LOS DATOS DE LA BITÁCORA
	</legend>
	<img src="static/img/v2.0/img/nueva-bitacora.jpg" width="40" height="30" align="middle"/>          	
	<form id="altabitacora" name="altabitacora" class="register" >	  						
		<table>
			<tr>
			<td>
				<label>REPORTE MÉDICO:</label>
			</td>
			<td>
				<select id="reporteMedico" name="reporteMedico" data-validation="altabitacoraval"> 
		    		<option value="">seleccione...</option>
		    		<option value="1">PACIENTE ESTABLE</option>
		    		<option value="2">EN ESPERA DE RESULTADOS</option>
		    		<option value="3">PACIENTE EN ESPERA DE ALTA</option>
		    		<option value="4">PACIENTE GRAVE</option>
		    		<option value="5">PACIENTE TERMINAL</option>
		    		<option value="6">PACIENTE EN ESPERA DE DIAGNOSTICO</option>
		    	</select>			    	
		    </td>
		    <td>		   		
	   			<label>OBSERVACIONES:</label>
	   		</td>
	   		<td>
	   			<textarea size="300" id="observacionesReporteMedico" name="observacionesReporteMedico" data-validation="altabitacoraval">
	   			</textarea>
	   		</td>
	   		</tr>
	   		<tr>
		    <td>			   			   
	   			<label>FECHA:</label>
	   		</td>
	   		<td>
	   			<input type="date" name="fechaReporteMedico" id="fechaReporteMedico" data-validation="altabitacoraval"/>
	   		</td>	   		
	   		<td>
				<label>INTERCONSULTA:</label>
			</td>
			<td>
				<select id="interconsulta" name="interconsulta" data-validation="altabitacoraval"> 
		    		<option value="">seleccione...</option>
		    		<option >NO</option>
		    		<option >CARDIOLOGIA</option>
		    		<option >CIRUGIA VASCULAR PERIFERICA</option>
		    		<option >ENDOCRINOLOGIA</option>
		    		<option >GASTROENTEROLOGIA MEDICA</option>
		    		<option >GASTROENTEROLOGIA QUIRURGICA</option>
		    		<option >GENETICA</option>
		    		<option >GINECOLOGIA Y OBSTETRICIA</option>
		    		<option >HEMATOLOGIA</option>
		    		<option >INMUNOLOGIA</option>
		    		<option >MAXILOFACIAL</option>
		    		<option >NEFROLOGIA</option>
		    		<option >NEUMOLOGIA</option>
		    		<option >NEUROLOGIA MEDICA</option>
		    		<option >NEUROLOGIA QUIRURGICA</option>
	    			<option >ONCOLOGIA</option>
		    		<option >OTORRINOLARINGOLOTGIA</option>
		    		<option >PEDIATRIA</option>
		    		<option >PSIQUIATRIA</option>
		    		<option >REUMATOLOGIA</option>
		    		<option >TOXICOLOGIA</option>
		    		<option >TRAUMATOLOGIA Y ORTOPEDIA	</option>
		    		<option >UROLOGIA</option>
		    		<option >VARIOS</option>			    		
		    	</select>			    	
		    </td>
	   		</tr> 
	   	</table>		    		   	
	    <p>
		  	<button type="submit" id="buttonGuardarEventoBit" class="myButton">Guardar</button>
		  	<font color="white">___</font>
			<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>													
		</p>
	</form>	  				
	</fieldset>
</div>
<div id="divEditarBitacora" style="display:none" >		
	<fieldset class="row1">
	<legend>MODIFIQUE LOS DATOS QUE DESEE DE LA BITÁCORA  
	</legend>
	<img src="static/img/v2.0/img/nueva-bitacora.jpg" width="40" height="30" align="middle"/>          	
	<form id="editabitacora" name="editabitacora" class="register" >	  						
		<table>
			<tr>
			<td>
				<label>REPORTE MÉDICO:</label>
			</td>
			<td>
				<input type="hidden" name="idBitacoraHid" id="idBitacoraHid" data-validation="altabitacoraval" readonly/>				
				<select id="reporteMedicoEdit" name="reporteMedicoEdit" data-validation="altabitacoraval"> 
		    		<option value="">seleccione...</option>
		    		<option value="1">PACIENTE ESTABLE</option>
		    		<option value="2">EN ESPERA DE RESULTADOS</option>
		    		<option value="3">PACIENTE EN ESPERA DE ALTA</option>
		    		<option value="4">PACIENTE GRAVE</option>
		    		<option value="5">PACIENTE TERMINAL</option>
		    		<option value="6">PACIENTE EN ESPERA DE DIAGNOSTICO</option>
		    	</select>			    	
		    </td>
		    <td>		   		
	   			<label>OBSERVACIONES:</label>
	   		</td>
	   		<td>
	   			<textarea size="300" id="observacionesReporteMedicoEdit" name="observacionesReporteMedicoEdit" data-validation="altabitacoraval">
	   			</textarea>
	   		</td>
	   		</tr>
	   		<tr>
		    <td>			   			   
	   			<label>FECHA:</label>
	   		</td>
	   		<td>
	   			<input type="date" name="fechaReporteMedicoEdit" id="fechaReporteMedicoEdit" data-validation="altabitacoraval"/>
	   		</td>	   		
	   		<td>
				<label>INTERCONSULTA:</label>
			</td>
			<td>
				<select id="interconsultaEdit" name="interconsultaEdit" data-validation="altabitacoraval"> 
		    		<option value="">seleccione...</option>		    	
		    		<option >NO</option>
		    		<option >CARDIOLOGIA</option>
		    		<option >CIRUGIA VASCULAR PERIFERICA</option>
		    		<option >ENDOCRINOLOGIA</option>
		    		<option >GASTROENTEROLOGIA MEDICA</option>
		    		<option >GASTROENTEROLOGIA QUIRURGICA</option>
		    		<option >GENETICA</option>
		    		<option >GINECOLOGIA Y OBSTETRICIA</option>
		    		<option >HEMATOLOGIA</option>
		    		<option >INMUNOLOGIA</option>
		    		<option >MAXILOFACIAL</option>
		    		<option >NEFROLOGIA</option>
		    		<option >NEUMOLOGIA</option>
		    		<option >NEUROLOGIA MEDICA</option>
		    		<option >NEUROLOGIA QUIRURGICA</option>
	    			<option >ONCOLOGIA</option>
		    		<option >OTORRINOLARINGOLOTGIA</option>
		    		<option >PEDIATRIA</option>
		    		<option >PSIQUIATRIA</option>
		    		<option >REUMATOLOGIA</option>
		    		<option >TOXICOLOGIA</option>
		    		<option >TRAUMATOLOGIA Y ORTOPEDIA	</option>
		    		<option >UROLOGIA</option>
		    		<option >VARIOS</option>			    		
		    	</select>			    	
		    </td>
	   		</tr> 
	   	</table>			    		   	
	    <p>
		  	<button type="submit" id="buttonGuardarEvento" class="myButton">Guardar</button>
		  	<font color="white">___</font>
		  	<button type="button" onclick="showDivBitacora(1)"  class="myButton">Cancelar</button>							
		</p>
	</form>	  				
	</fieldset>	  				
</div>
</div>
