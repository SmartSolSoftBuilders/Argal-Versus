<link rel="stylesheet" href="static/css/bootstrap.min.css" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td bgcolor="#6688a6" width="960" height="23" align="center" ><img src="static/img/hospital.jpg" height="30" width="30"/> <span style="color:white"> Editar Hospital</span>
	   </td>
	   <td bgcolor="#6688a6">
			<img src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
		</td>			 
	    </tr>
	    <tr><td>Por favor modifique los datos deseados para el Hospital:</td>
	    <td></td>
	</tr>
</table>
<div ng-controller="EditarHospitalController">
	<form  name="editarhospitalform" class="css-form">
	<table>
		<tr>		
			<td>NOMBRE:</td>
			<td><input type="text" ng-model="hospital.nombreHospital" /></td>
			<td>CALLE Y NÚMERO:</td>
			<td><input type="text" ng-model="hospital.direccionHospital" size="30" required/></td>
		</tr>		  		  	  	  		
	  	<tr>
	  	  	<td>COLONIA:</td>
	  	  	<td><input type="text" ng-model="hospital.coloniaHospital" size="30"/></td>	  	
	  		<td>MUNICIPIO/DELEGACIÓN:</td> 
	  		<td><input type="text" ng-model="hospital.municipioDelHospital" size="30"/></td>
	  	<tr/>
	  	<tr>
	  		<td>C.P.:</td>
	  	  	<td><input type="text" ng-model="hospital.cpHospital" size="30"/></td>	  	
	  		<td>PAIS:</td>
	  	  	<!--td><input type="text" ng-model="hospital.paisHospital" size="30"/></td-->
	  	  	<td><select ng-model="paisSel" ng-options="pais.pais for pais in paises" ng-change="updateCiudadHospEditarForm()">
		 		<option value="">Seleccione un país...</option>
	 			</select>
	 		</td>
	  	</tr>
	  	<tr>
  			<td>ESTADO:</td>
  			<td>
  				<div class="animate-switch-container" ng-switch on="paisSel.id" ng-show="showciudadinput">
  				<input type="text" ng-model="hospital.estadoHospital" ng-switch-default size="30"/>  				
  				<select ng-controller="EditarHospitalController" ng-model="estadoSel1" ng-switch-when="1" ng-options="estado.estado for estado in estados" ng-change="updateEstadoHospForm()">
		 			<option value="">Seleccione un estado...</option>
	 			</select>
  				</div>
  			</td>
  			<td>CIUDAD:</td> 
  			<td><input type="text" ng-model="hospital.ciudadHospital" size="30" /></td>
  		</tr>
  		<tr>
  			<td>CONMUTADOR:</td>
  			<td><input type="text" ng-model="hospital.conmutadorHospital" size="30"/></td>
  			<td>TELÉFONO DIRECTO DE MÓDULO:</td> 
  			<td><input type="text" ng-model="hospital.telModuloHospital" size="30" numbers-only="numbers-only"/></td>
  		</tr>
  		</table>
  		<alert ng-repeat="alert in alerts" type="alert.type" close="closeAlertEditarHosp($index)">{{alert.msg}}</alert>
  		<table align="center">  			  		  
		<tr>
			<td>
			</td>
			<td>  	  	
	  	  		<button class="btn btn-primary" ng-click="actualizarHospital(hospital)">Guardar</button>  	  		  	  		   
  			</td>
  			<td><button class="btn btn-warning" ng-click="cancelHospForm()">Cerrar</button></td>
	  		<td><span class="mensajeError" ng-show="editarhospitalform.$invalid">{{mensajeError}}</span></td>
  		</tr>
  		</table>   
	</form>
</div>    
    