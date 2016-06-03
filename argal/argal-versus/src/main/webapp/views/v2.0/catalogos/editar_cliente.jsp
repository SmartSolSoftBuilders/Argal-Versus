<div align="center">		
	<form name="editarclienteform" id="editarclienteform" class="register">
	<fieldset>
	<legend>Modifique los datos que desee del Cliente:</legend>	
		<p>	
		<label>RAZON SOCIAL:
	    </label>		                    	    
	    <input type="hidden" id="idClienteClienteEditar" name="idClienteClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    <input type="text" id="razonSocialClienteEditar" name="razonSocialClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>DOMICILIO:
	    </label>		                    
	    <input type="text" id="domicilioClienteEditar" name="domicilioClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>DIRECTOR MÉDICO:
	    </label>		                    
	    <input type="text" id="directorMedicoClienteEditar" name="directorMedicoClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>	    
		<label>CONMUTADOR:
	    </label>		                    
	    <input type="text" id="conmutadorClienteEditar" name="conmutadorClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		</p>
		<p>
		<label>NOMBRE CORTO:
	    </label>		                    
	    <input type="text" id="nombreCortoClienteEditar" name="nombreCortoClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		</p>
	</fieldset>
	<fieldset>
	<legend>Datos de Contacto:</legend>
		<p>
		
		<label>NOMBRE:
	    </label>			                        
	    <input type="text" id="nombreClienteEditar" name="nombreClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>	    
		<label>PUESTO:
	    </label>			                        
	    <input type="text" id="puestoClienteEditar" name="puestoClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    <label>TELÉFONO OFICINA:
	    </label>			                        
	    <input type="text" id="telOficinaClienteEditar" name="telOficinaClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
	    <label>TELÉFONO CELULAR:
	    </label>			                        
	    <input type="text" id="telCelularClienteEditar" name="telCelularClienteEditar" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CORREO ELECTRÓNICO:
	    </label>		                    
	    <input type="text" id="emailClienteEditar" name="emailClienteEditar" data-validation="required email" onkeyup="javascript:this.value=this.value.toUpperCase();"/>			    
	    </p>	    
	    <br><br><br>
	    </fieldset>
	    <div align="center"><button type="submit" id="buttonGuardarClienteEditar" class="myButton">Guardar</button></div>
	</form>	
</div>	