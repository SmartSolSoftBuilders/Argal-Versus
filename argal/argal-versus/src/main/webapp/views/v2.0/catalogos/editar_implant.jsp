<div align="center">		
	<form name="editarimplantform" id="editarimplantform" class="register">
	<fieldset>
	<legend>Modifique los datos que desee del Implant:</legend>	
		<p>	
		<label>NOMBRE:
	    </label>		                    
	    <input type="hidden" id="idImp" name="idImp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    <input type="text" id="nombreImpEditar" name="nombreImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>APELLIDO PATERNO:
	    </label>		                    
	    <input type="text" id="appImpEditar" name="appImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>APELLIDO MATERNO:
	    </label>		                    
	    <input type="text" id="apmImpEditar" name="apmImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
		<p>
		<label>NEXTEL:
	    </label>		                    
	    <input type="text" id="nextelImpEditar" name="nextelImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>ID:
	    </label>		                    
	    <input type="text" id="idNextImpEditar" name="idNextImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CELULAR:
	    </label>			                        
	    <input type="text" id="celularImpEditar" name="celularImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
		<label>TELÉFONO OFICINA:
	    </label>		                    
	    <input type="text" id="telOfImpEditar" name="telOfImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>PUESTO:
	    </label>			                        
	    <input type="text" id="puestoImpEditar" name="puestoImpEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
		<label>CORREO ELECTRÓNICO INSTITUCIONAL:
	    </label>		                    
	    <input type="text" id="emailInstImpEditar" name="emailInstImpEditar" data-validation="required email" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CORREO ELECTRÓNICO PERSONAL:
	    </label>			                        
	    <input type="text" id="emailPersImpEditar" name="emailPersImpEditar" data-validation="required email" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
		<label>¿SUPER MÉDICO?:
	    </label>		                    
	    <input type="checkbox" id="supermedicoImpEditar" name="supermedicoImpEditar"  onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>	    
	    <br><br><br>
	    </fieldset>
	    <div align="center"><button type="submit" id="buttonGuardarEvento" class="myButton">Guardar</button></div>
	</form>	
</div>	