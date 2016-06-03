
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td bgcolor="#6688a6" width="970" height="23" align="center" ><img src="static/img/icdlist.png" height="30" width="30"/> <span style="color:white"> NUEVO MEDICO TRATANTE</span>
		</td>
	    <td bgcolor="#6688a6">
			<img src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	    </td>			 
	    </tr>
</table>
<div ng-controller="AltaMedicoTratanteController">
	<form  name="altamedtratform" class="css-form">
	<table>
			<tr>						
				<td>APELLIDO PATERNO:</td>
				<td><input type="text" ng-model="appMT" onkeyup="javascript:this.value=this.value.toUpperCase();" required/> 
				<td>APELLIDO MATERNO:</td>
				<td><input type="text" ng-model="apmMT" onkeyup="javascript:this.value=this.value.toUpperCase();" required/>
				<td>NOMBRE:</td> 
				<td><input type="text" ng-model="nombreMT" onkeyup="javascript:this.value=this.value.toUpperCase();" required/></td>
			<tr/>
			<tr>	
				<td>TIPO MÉDICO:</td>
				<td><select ng-model="tipoMTSelected" ng-options="tipoMT.descripcion for tipoMT in tiposMT" required> 	  	  			 
							<option value="">Seleccione...</option>
						</select>	 	
				</td>		
			
				<td>  	  	
						<button class="btn btn-primary" ng-click="guardarMedicoTratante()" >Guardar</button>  	  
  				</td>
  				<td></td>
	  			<td><span class="mensajeError" ng-show="altamedtratform.$invalid">{{mensajeError}}</span>
	  			</td>
  			</tr>
  		</table>   
	</form>
<table width="719px">
<tr>
	<td>
  		<table width="300px"  border="3" ng-table="tableParams" show-filter="false">
            <tr ng-repeat="esp in especialidades | filter:filterEvenStartFrom(0)">
			<div ng-init="myVar = myVar+1">  </div>
                <td>
                    {{esp.descripcion}} 
                    <input type="checkbox" checklist-model="user.especialidadesMedTrat" checklist-value="esp.idEspecialidad"/>
                </td>
            </tr>
        </table>
	</td>
	<td>
		<table width="300px" border="3" ng-table="tableParams" show-filter="false">
            <tr ng-repeat="esp in especialidades | filter:filterEvenStartFrom2(0)">
                <td>
                    {{esp.descripcion}} 
                    <input type="checkbox" checklist-model="user.especialidadesMedTrat" checklist-value="esp.idEspecialidad"/>
                </td>
            </tr>
        </table>
	</td>
		<td>
		<table width="300px" border="3" ng-table="tableParams" show-filter="false">
            <tr ng-repeat="esp in especialidades | filter:filterEvenStartFrom3(0)"
               >
                <td>
                    {{esp.descripcion}} 
                    <input type="checkbox" checklist-model="user.especialidadesMedTrat" checklist-value="esp.idEspecialidad"/>
                </td>
            </tr>
        </table>
	</td>	
	</tr>
</table>


</div>