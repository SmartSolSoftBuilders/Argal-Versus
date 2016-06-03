<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td bgcolor="#6688a6" width="1180" height="23" align="center" ><img src="static/img/implant.jpg" height="30" width="30"/> <span style="color:white"> AGREGAR IMPLANT</span>
	   </td>
	    </tr><tr><td>INGRESE LOS DATOS DEL NUEVO IMPLANT</td>
	</tr>
</table>
<div ng-controller="AltaImplantController">
	<form name="altaimplantform" class="css-form">
	<table class="table ng-table-rowselected">
		<tr>		
			<td>NOMBRE:</td>
			<td><input type="text" ng-model="implant.nombreImplant" required onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
			<td>APELLIDO PATERNO :</td>
			<td><input type="text" ng-model="implant.appImplant" size="30" required onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
		</tr>		  		  	  	  
		<tr>
			<td>APELLIDO MATERNO:</td> 
	  	  	<td><input type="text" ng-model="implant.apmImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
	  		<td>NEXTEL:</td> 
	  	  	<td><input type="text" ng-model="implant.nextelImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
	  	</tr>
	  	<tr>
	  	  	<td>ID:</td> 
	  	  	<td><input type="text" ng-model="implant.idNextelImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  	
	  		<td>CELULAR:</td> 
	  	  	<td><input type="text" ng-model="implant.celularImplant" size="30" numbers-only="numbers-only" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
	  	</tr>
	  	<tr>
	  	  	<td>TELÉFONO OFICINA:</td>
	  	  	<td><input type="text" ng-model="implant.telOfiImplant" size="30" numbers-only="numbers-only" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  	
	  		<td>PUESTO:</td> 
	  		<td><input type="text" ng-model="implant.puestoImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
	  	</tr>
	  	<tr>
	  		<td>CORREO ELECTRÓNICO INSTITUCIONAL:</td>
	  	  	<td><input type="email" ng-model="implant.emailInstImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  	
	  		<td>CORREO ELECTRONICO PERSONAL:</td>
	  	  	<td><input type="email" ng-model="implant.emailPersImplant" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
	  	</tr>
  		<tr>
  			<td>¿SUPER MÉDICO?</td>
  			<td><input type="checkbox" ng-model="implant.superMedico" /></td>
  			<td></td> 
  		</tr>  			  		  
		<tr>
			<td>
			</td>
			<td>  	  	
  	  			<!--  button ng-click="guardarImplant(implant)"  ng-disabled="altaimplantform.$invalid || isUnchanged(implant)">Guardar</button-->
  			</td>
  			<td></td>
	  		<td><span class="mensajeError" ng-show="altaimplantform.$invalid">{{mensajeError}}</span></td>
  		</tr>
  		</table>   
	</form>
  <alert ng-repeat="alert in alerts" type="alert.type" close="closeAlertEditarImplant($index)">{{alert.msg}}</alert>
  <table align="center">			
		<tr><td></td>
			<td>  	  	
	  	  		<button class="btn btn-primary" ng-click="guardarImplant(implant)"  ng-disabled="altaimplantform.$invalid || isUnchanged(implant)">Guardar</button>  	  		  	  		   
  			</td>
  			<td><button class="btn btn-warning" ng-click="cancelImplForm()">Cerrar</button></td>
	  		<td><span class="mensajeError" ng-show="editarimplantform.$invalid">{{mensajeError}}</span></td>	  		            
  		</tr>
  		</table>   
	<script type="text/ng-template" id="modalAltaImplant.html">
	  <div class="modal-header">
            <h3>Notificación</h3>
        </div>
        <div class="modal-body">
            {{mensajeDialogoAltaImplant}}
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="resetImplForm()">Agregar Otro</button>
            <button class="btn btn-warning" ng-click="cancelImplForm()">Cerrar</button>
        </div>
    </script>
	
</div>    
    