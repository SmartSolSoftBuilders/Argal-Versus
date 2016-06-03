<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<center>
	<br><strong><span class="mensajeTitulo">{{message}}</span></strong><br>
	<br><span class="mensajeTitulo">Por favor llene el siguiente formulario:</span>
</center> 
<div ng-controller="EditarImplantController">
	<form  name="editarimplantform" class="css-form">
	<table>
		<tr>		
			<td>NOMBRE:</td>
			<td><input type="text" ng-model="implant.nombreImplant" /></td>
			<td>APELLIDO PATERNO :</td>
			<td><input type="text" ng-model="implant.appImplant" size="30" required/></td>
		</tr>		  		  	  	  
		<tr>
			<td>APELLIDO MATERNO:</td> 
	  	  	<td><input type="text" ng-model="implant.apmImplant" size="30"/></td>
	  		<td>NEXTEL:</td> 
	  	  	<td><input type="text" ng-model="implant.nextelImplant" size="30"/></td>
	  	<tr/>
	  	<tr>
	  	  	<td>ID:</td> 
	  	  	<td><input type="text" ng-model="implant.idNextelImplant" size="30"/></td>	  	
	  		<td>CELULAR: 
	  	  	<td><input type="text" ng-model="implant.celularImplant" size="30"/></td>
	  	<tr/>
	  	<tr>
	  	  	<td>TELÉFONO OFICINA:</td>
	  	  	<td><input type="text" ng-model="implant.telofiImplant" size="30"/></td>	  	
	  		<td>PUESTO:</td> 
	  		<td><input type="text" ng-model="implant.puestoImplant" size="30"/></td>
	  	<tr/>
	  	<tr>
	  		<td>CORREO ELECTRÓNICO INSTITUCIONAL:</td>
	  	  	<td><input type="email" ng-model="implant.emailInst" size="30"/></td>	  	
	  		<td>CORREO ELECTRÓNICO PERSONAL:</td>
	  	  	<td><input type="email" ng-model="implant.emailPers" size="30"/></td>
	  	<tr/>
  		<tr>
  			<td>¿SUPER MÉDICO?</td>
  			<td><input type="checkbox" ng-model="implant.superMedico" /></td>
  			<td></td> 
  		<tr/>  			  		  
		<tr>
			<td>
			</td>
			<td>  	  	
  	  		<button ng-click="guardarImplant(implant)"  ng-disabled="editarimplantform.$invalid || isUnchanged(implant)">Guardar</button>
  			</td>
  			<td></td>
	  		<td><span class="mensajeError" ng-show="altaimplantform.$invalid">{{mensajeError}}</span></td>
  		</tr>
  		</table>   
	</form>
</div>    
    