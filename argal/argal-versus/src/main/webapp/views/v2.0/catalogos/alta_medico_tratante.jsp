<div align="center">		
	<form name="altaMedTraform" id="altaMedTraform" class="register">
	<fieldset>
	<legend>Ingrese los datos del nuevo M�dico Tratante:</legend>	
		<p>	
			<label>NOMBRE:
		    </label>		                    
		    <input type="text" id="nombreMedTrat" name="nombreMedTrat" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
			<label>APELLIDO PATERNO:
		    </label>		                    
		    <input type="text" id="appMedTrat" name="appMedTrat" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
			<label>APELLIDO MATERNO:
		    </label>		                    
		    <input type="text" id="apmMedTrat" name="apmMedTrat" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
		<p>
			<label>TIPO M�DICO:
		    </label>		                    
		    <select id="tipoMedSel" name="tipoMedSel" style="width:180px;" data-validation="required">   									
				<option value="">Seleccione...</option>
				<option value="1">RED</option>
				<option value="2">STAFF</option>
				<option value="3">INTERINO</option>
			</select>				
	    </p>	    
	    <p>
			<br><br>
			ESPECIALIDADES:
		    <table>
		    	<tr>
					<td>
						<table> 
							<tr>
								<td>GASTROENTEROLOG�A QUIR�RGICA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="1"/></td>								
							</tr>
							<tr>
								<td>NEUROLOG�A M�DICA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="2"/></td>								
							</tr>
							<tr>
								<td>UROLOG�A  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="3"/></td>								
							</tr>
							<tr>
								<td>GINECOLOG�A Y OBSTETRICIA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="4"/></td>								
							</tr>
							<tr>
								<td>NEUMOLOG�A
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="5"/></td>								
							</tr>
							<tr>
								<td>GASTROENTEROLOG�A M�DICA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="6"/></td>								
							</tr>
							<tr>
								<td>CIRUG�A VASCULAR PERIF�RICA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="7"/></td>								
							</tr>
							<tr>
								<td>TRAUMATOLOG�A Y ORTOPEDIA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="8"/></td>								
							</tr>
							<tr>
								<td>CARDIOLOG�A
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="9"/></td>								
							</tr>
							<tr>
								<td>INFECTOLOG�A
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="10"/></td>		
							</tr>
							<tr>
								<td>MEDICINA CRITICA  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="11"/></td>								
							</tr>
							<tr>
								<td>ONCOLOG�A
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="12"/></td>								
							</tr>
						</table>
					</td>				
					<td>
						<table> 
							<tr>
								<td>OTORRINOLARINGOLOG�A  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="13"/></td>								
							</tr>
							<tr>
								<td>PEDIATR�A 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="14"/></td>  
							</tr>
							<tr>
								<td>NEUROLOG�A QUIR�RGICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="15"/></td>															
							</tr>
							<tr>
								<td>MEDICINA INTERNA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="16"/></td>
							</tr>
							<tr>
								<td>NEUROCIRUG�A  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="17"/>
								</td>													
							</tr>
							<tr>
								<td>CIRUG�A GENERAL  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="18"/></td>								
							</tr>
							<tr>
								<td>CIRUG�A ONCOLOGICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="19"/></td>								
							</tr>
							<tr>
								<td>GASTROCIRUG�A 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="20"/></td>								
							</tr>
							<tr>
								<td>GINECOLOGIA 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="21"/></td>								
							</tr>
							<tr>
								<td>HEMATOLOG�A 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="22"/></td>								
							</tr>
							<tr>
								<td>NEFROLOG�A 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="23"/></td>								
							</tr>
							<tr>
								<td>CIRUG�A PEDIATRICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="24"/></td>								
							</tr>
						</table>
					</td>				
					<td>
						<table> 
							<tr>
								<td>UROLOGIA   
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="26"/></td>								
							</tr>
							<tr>
								<td>GASTROENTEROLOGIA PEDIATRICA  								  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="27"/></td>								
							</tr>
							<tr>
								<td>INFECTOLOGIA PEDIATRICA  								  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="28"/></td>								
							</tr>
							<tr>
								<td>ONCOLOGIA PEDIATRICA  								  
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="29"/></td>								
							</tr>
							<tr>
								<td>CIRUG�A PL�STICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="30"/></td>														
							</tr>
							<tr>
								<td>CIRUG�A PLASTICA PEDIATRICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="32"/></td>								
							</tr>
							<tr>
								<td>ANGIOLOGIA 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="34"/></td>		
							</tr>
							<tr>
								<td>ORTOPEDIA PEDIATRICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="35"/></td>								
							</tr>
							<tr>
								<td>ALERGOLOGA 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="36"/></td>								
							</tr>
							<tr>
								<td>MAXILOFACIAL 
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="37"/></td>								
							</tr>
							<tr>
								<td>ODONTOLOGIA PEDIATRICA  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="33"/></td>								
							</tr>
							<tr>
								<td>ENDOCRINOLOG�A  								
								<input type="checkbox" id="especialidades_altamed" name="especialidades_altamed" value="25"/></td>								
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</p>
	    </fieldset>
	    <br>
	    <div align="center"><button type="submit" id="buttonGuardarEvento" class="myButton">Guardar</button></div>
	</form>	
</div>	