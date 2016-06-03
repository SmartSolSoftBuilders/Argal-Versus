<div align="center">		
	<form name="altaHospform" id="altaHospform" class="register">
	<fieldset>
	<legend>Ingrese los datos del nuevo Hospital:</legend>	
		<p>	
		<label>NOMBRE:
	    </label>		                    
	    <input type="text" id="nombreHosp" name="nombreHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CALLE Y NÚMERO:
	    </label>		                    
	    <input type="text" id="calleHosp" name="calleHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>COLONIA:
	    </label>		                    
	    <input type="text" id="colHosp" name="colHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
		<p>
		<label>MUNICIPIO/DELEGACIÓN:
	    </label>		                    
	    <input type="text" id="mundelHosp" name="mundelHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>		
		<label>C.P.:
	    </label>		                    
	    <input type="text" id="cpHosp" name="cpHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>PAÍS:
	    </label>			                        
	    <select id="paisHosp" name="paisHosp" data-validation="required">
			<option value="">Seleccione un país...</option>
			<option >MEXICO</option>
			<option >ESTADOS UNIDOS</option>
			<option >BRASIL</option>
			<option >CANADA</option>
			<option >ARGENTINA</option>
			<option >PERU</option>
			<option >COLOMBIA</option>
			<option >BOLIVIA</option>
			<option >VENEZUELA</option>
			<option >CHILE</option>
			<option >ECUADOR</option>
			<option >CHILE</option>
			<option >GUYANA</option>
			<option >URUGUAY</option>
			<option >CHILE</option>
			<option >SURINAM</option>
			<option >NICARAGUA</option>
			<option >HONDURAS</option>
			<option >CUBA</option>
			<option >GUATEMALA</option>
			<option >PANAMA</option>
			<option >COSTA RICA</option>
			<option >REPUBLICA DOMINICANA</option>
			<option >HAITI</option>
			<option >BELICE</option>
			<option >EL SALVADOR</option>
			<option >BAHAMAS</option>
			<option >JAMAICA</option>
			<option >TRINIDAD Y TOBAGO</option>
			<option >DOMINICA</option>
			<option >SANTA LUCIA</option>
			<option >ANTIGUA Y BARBUDA</option>
			<option >BARBADOS</option>
			<option >SAN VICENTE Y LAS GRANADINAS</option>
			<option >GRANADA</option>
			<option >SAN CRISTÓBAL Y NIEVES</option>	    
		</select>	    
	    </p>
	    <p>
		<label>ESTADO:
	    </label>		                    
	    <input type="text" id="estadoHosp" name="estadoHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>CIUDAD:
	    </label>			                        
	    <input type="text" id="ciudadHosp" name="ciudadHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>
	    <p>
		<label>CONMUTADOR:
	    </label>		                    
	    <input type="text" id="conmutadorHosp" name="conmutadorHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
		<label>TELÉFONO DIRECTO MÓDULO:
	    </label>			                        
	    <input type="text" id="telmoduloHosp" name="telmoduloHosp" data-validation="required" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
	    </p>	    	   
	    <br><br><br>
	    </fieldset>
	    <div align="center"><button type="submit" id="buttonGuardarEvento" class="myButton">Guardar</button></div>
	</form>	
</div>	