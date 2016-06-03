<div align="center">		
	<form name="altaclienteform" id="altaclienteform" class="register">
	<fieldset>
	<legend>Ingrese los datos del nuevo Cliente:</legend>	
		<p>	
		<label>RAZON SOCIAL:
	    </label>		                    
	    <input type="text" id="razonSocialCliente" name="razonSocialCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>DOMICILIO:
	    </label>		                    
	    <input type="text" id="domicilioCliente" name="domicilioCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>DIRECTOR MÉDICO:
	    </label>		                    
	    <input type="text" id="directorMedicoCliente" name="directorMedicoCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>	    
		<label>CONMUTADOR:
	    </label>		                    
	    <input type="text" id="conmutadorCliente" name="conmutadorCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		</p>
		<p>
		<label>NOMBRE CORTO:
	    </label>		                    
	    <input type="text" id="nombreCortoCliente" name="nombreCortoCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		</p>
	</fieldset>
	<fieldset>
	<legend>Datos de Contacto:</legend>
		<p>
		
		<label>NOMBRE:
	    </label>			                        
	    <input type="text" id="nombreCliente" name="nombreCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>	    
		<label>PUESTO:
	    </label>			                        
	    <input type="text" id="puestoCliente" name="puestoCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    <label>TELÉFONO OFICINA:
	    </label>			                        
	    <input type="text" id="telOficinaCliente" name="telOficinaCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
	    <label>TELÉFONO CELULAR:
	    </label>			                        
	    <input type="text" id="telCelularCliente" name="telCelularCliente" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CORREO ELECTRÓNICO:
	    </label>		                    
	    <input type="text" id="emailCliente" name="emailCliente" data-validation="required email" onkeyup="javascript:this.value=this.value.toUpperCase();"/>			    
	    </p>	    
	    <br><br><br>
	    </fieldset>
	    <div align="center"><button type="submit" id="buttonGuardarCliente" class="myButton">Guardar</button></div>
	</form>	
</div>	