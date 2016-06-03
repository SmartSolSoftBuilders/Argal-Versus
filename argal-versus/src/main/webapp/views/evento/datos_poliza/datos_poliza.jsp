<tabset>
    <tab heading="GASTOS IMPROCEDENTES">
	<div id="divTableGastosImpro" ng-controller="EstadoCuentaController">	   
		<table border=2>
	    <tr>
	    	<td>
		    	<smart-table class="table table-striped" table-title="Smart Table" config="globalConfigImpro" columns="columnCollectionEstadoCuentaImpro" rows="rowCollectionEstadoCuentaImpro" ></smart-table>
		   	</td>
		</tr>
		<tr>
			<td>   
				<button class="btn btn-default" ng-click="showAltaGastoImproForm()">Registrar</button>
				<button class="btn btn-default" ng-click="showEditGastoImproForm()">Editar</button>	           			
				<button class="btn btn-default" ng-click="eliminarGastoImproForm()">Eliminar</button>
			</td>
		</tr>
		</table>
	</div>
	<div ng-controller="AltaCargoImproController" id="divFormTableGastosImpro" style="display:none">		
		<form  name="altaclienteform" class="css-form">
		<center><h4>Registrar un gasto Improcedente</h4>
			<h5>Por favor llene el siguiente formulario:</h5>
		</center>
		<table >
			<tr>
				<td>TIPO DE CARGO:</td>				
				<td><select ng-model="tipoCargoSel" ng-options="tipoCargo.descripcion for tipoCargo in tiposCargo" ng-change="update()"> 	  	  			 
					 		<option value="">Seleccione...</option></select>	 					 				  	  	
				</td>
				<td>RAZÓN:</td>
				<td><select ng-model="razonesAltaGastoImproSelected" ng-options="razonesGastoImpro.descripcion for razonesGastoImpro in razonesGastoImpros" ng-change="update()"> 	  	  			 
					 		<option value="">Seleccione...</option></select>	 					 		
		  	  	</td>
				<td>FECHA:</td>
				<td>
					<div class="row">
				        <div class="col-md-6">
				            <p class="input-group">
				              <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate" max="'2015-06-22'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
				              <span class="input-group-btn">
				                <button class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
				              </span>
				            </p>
				        </div>
				    </div>
				</td>
			</tr>		  		  	  	  
			<tr>
				<td>MONTO:</td>
	  	  		<td><input type="text" ng-model="cliente.conmutadorCliente" size="30" /></td>
	  			<td>EVIDENCIA</td> 
	  	  		<td><input type="file" ng-model="cliente.conmutadorCliente" size="30" numbers-only="numbers-only"/></td>
	  	  		<td></td>
	  		<tr/>
	  		<tr>
			<td>
				<button class="btn btn-default" ng-click="showAltaGastoImproForm()">Guardar</button>
			</td>
			<td>
				<button class="btn btn-default" ng-click="hideAltaGastoImproForm()">Cancelar</button>
			</td>
			<td></td>
		</tr>
	  	</table>
	  	</form>
	</div>
	<div ng-controller="EditarCargoImproController" id="divEditFormTableGastosImpro" style="display:none">		
		<form  name="editarclienteform" class="css-form">
		<center><h4>Editar un gasto Improcedente</h4>
			<h5>Por favor llene el siguiente formulario:</h5>
		</center>
		<table >
			<tr>
				<td>TIPO DE CARGO:</td>
				<td><input type="text" ng-model="tipoCargoEdit" /></td>
				<td>RAZÓN:</td>
				<td><select ng-model="razonesAltaGastoImproSelected" ng-options="razonesGastoImpro.descripcion for razonesGastoImpro in razonesGastoImpros" ng-change="update()"> 	  	  			 
					 		<option value="">Seleccione...</option></select>	 					 		
		  	  	</td>
				<td>FECHA:</td>
				<td>
					<div class="row">
				        <div class="col-md-6">
				            <p class="input-group">
				              <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate" max="'2015-06-22'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
				              <span class="input-group-btn">
				                <button class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
				              </span>
				            </p>
				        </div>
				    </div>
				</td>
			</tr>		  		  	  	  
			<tr>
				<td>MONTO:</td>
	  	  		<td><input type="text" ng-model="montoEdit" size="30" numbers-only="numbers-only"/></td>
	  			<td>EVIDENCIA</td> 
	  	  		<td><input type="file" ng-model="evidenciaEdit" size="30" numbers-only="numbers-only"/></td>
	  	  		<td></td>
	  		<tr/>
	  		<tr>
			<td>
				<button class="btn btn-default" ng-click="showEditAltaGastoImproForm()">Guardar</button>
			</td>
			<td>
				<button class="btn btn-default" ng-click="hideEditGastoImproForm()">Cancelar</button>
			</td>
			<td></td>
		</tr>
	  	</table>
	  	</form>
	</div>
    </tab>
    <tab heading="GASTOS PROCEDENTES">
    	<div id="divTableGastosPro" ng-controller="EstadoCuentaController">    	  
		<table>
	    <tr>
	    	<td>
		    	<smart-table class="table table-striped" table-title="Smart Table" config="globalConfig" columns="columnCollectionEstadoCuentaPro" rows="rowCollectionEstadoCuentaPro" ></smart-table>
		   	</td>  
		</tr>
		<tr>
			<td>   
				<button class="btn btn-default" ng-click="showAltaGastoImproForm()">Registrar</button>
				<button class="btn btn-default" ng-click="showEditGastoImproForm()">Editar</button>	           			
				<button class="btn btn-default" ng-click="eliminarGastoImproForm()">Eliminar</button>
			</td>
		</tr>
		</table>
		</div>
    </tab>
</tabset>