<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<style>
.modal2 { 	
		overflow: scroll; 
		height:650px;
	}
	</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<table height="50px" width=1200 >
		<tr>
			<td width=1150 align="center" bgcolor="#6688a6">
				<span style="color: #ffffff;"><img width=30 height=30 src="static/img/segmedico1.jpg"/>SEGUIMIENTO MÉDICO</span>
			</td>			
		    <td width=50 bgcolor="#6688a6">
				<img style="cursor: pointer; cursor: hand;" src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	   		</td>			 					
		</tr> 
</table>

<table border="0">
	<tr>
		<td>
			<div ng-show="eventoEditSelected.registroSeguroPersonal">
			<span style="color: #6688a6;">ID EVENTO:</span> {{eventoEditSelected.idEvento}} 
			<span style="color: #6688a6;">FECHA:</span>{{eventoEditSelected.fechaIngreso}} 
				<span style="color: #6688a6;">TITULAR:</span>
					{{eventoEditSelected.registroSeguroPersonal.nombreTitular}}&nbsp;
					{{eventoEditSelected.registroSeguroPersonal.appTitular}}&nbsp;
					{{eventoEditSelected.registroSeguroPersonal.apmTitular}}&nbsp;
				<span style="color: #6688a6;">PACIENTE:</span>
					{{eventoEditSelected.registroSeguroPersonal.nombrePaciente}}&nbsp;
					{{eventoEditSelected.registroSeguroPersonal.appPaciente}}&nbsp;
					{{eventoEditSelected.registroSeguroPersonal.apmPaciente}}&nbsp;
    		</div>
    		<div ng-show="eventoEditSelected.registroGastosMayores"> 
    		<span style="color: #6688a6;">ID EVENTO:</span> {{eventoEditSelected.idEvento}} 
			<span style="color: #6688a6;">FECHA:</span>{{eventoEditSelected.fechaIngreso}}
				<span style="color: #6688a6;">TITULAR:</span>
					{{eventoEditSelected.registroGastosMayores.nombreTitular}}&nbsp;
					{{eventoEditSelected.registroGastosMayores.appTitular}}&nbsp;
					{{eventoEditSelected.registroGastosMayores.apmTitular}}&nbsp;
				<span style="color: #6688a6;">PACIENTE:</span>
					{{eventoEditSelected.registroGastosMayores.nombrePaciente}}&nbsp;
					{{eventoEditSelected.registroGastosMayores.appPaciente}}&nbsp;
					{{eventoEditSelected.registroGastosMayores.apmPaciente}}&nbsp;
    		</div>			
		</td>
	</tr>
</table>

<form  name="altasegmedform" class="css-form">
	<div ng-controller="SeguimientoMedicoController" id="divAltaSeguimientoMedicoController" style="display:none;color: #ffffff;  background:#E2F2FA;" align="center"  >
		<div align="center" style="color: #ffffff; background:#6688a6;" ><span style="color: white">REGISTRAR NUEVA BITÁCORAS MÉDICA</span></div>
	<table width="900px" border=0 >	
	<tr><td>
	<table width="570px">																	
		<tr>
			<td width='200px'><span style="color: #6688a6;">REPORTE MÉDICO:</span></td>
			<td><select ng-model="regreportemedicoselected"  ng-options="reportemedico.descripcion for reportemedico in reportesmedicos" required> 	  	  			 
				<option value="">Seleccione...</option></select>	 					 				  	  	
			</td>
		</tr>
		<tr >
			<td width='200px'><span style="color: #6688a6;">OBSERVACIONES:</span></td>
			<td>
				<textarea ng-model="bitacora.observaciones" size=300 required onkeyup="javascript:this.value=this.value.toUpperCase();"></textarea>		   					 					 		
			</td>
		</tr>
		<tr>		  			
			<td><span style="color: #6688a6;">FECHA:</span></td>
			<td>
		        <input type="text" readonly class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" required/>				        				   
			</td>
		</tr>
		<tr>		
			<td><span style="color: #6688a6;">INTERCONSULTA:</span></td>
			<td>
				<select  required ng-model="interconsultaselected" ng-options="esp.descripcion for esp in especialidades">
					<option value="">Seleccione...</option>
				</select>
			</td>										  	
	  	</tr>
	  </table>
	  </td>
	  <td>
	 	<table width="330px">
		 	<tr><td>
		 		<img src="static/img/escribir-bitacora.jpg" height="100" width="100"/>
			 </td></tr>
		</table>
	  </td>
	 </tr>
	 
	 </table>
	  <div ng-controller="SeguimientoMedicoController" id="divDef" style="display:none">
	  	<table>
	  	<tr>	  	
			<td><span style="color: #6688a6;">FECHA DEF.:</span></td>
			<td><input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt2" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" /></td>		
			<td><span style="color: #6688a6;">HORA DEF.:</span></td>
			<td><input type="time" id="exampleInput" name="input" ng-model="valueTime" placeholder="HH:mm" min="08:00" max="17:00" style="width: 100px"/></td>
		</tr>
	  	</table>
	  </div>	  
	  <table>
	  	<tr>
	  		<td></td><td></td>
			<td>
				<button class="btn btn-primary" ng-click="guardarBitacora()">Guardar</button>
			</td>											
			<td>
				<button class="btn btn-primary" ng-click="hideAltaSeguimientoMedicoForm()">Cancelar</button>
			</td>
			<td></td><td></td>
		</tr>
	  </table>			
</div>
</form>

<form  name="editsegmedform" class="css-form">
<div ng-controller="SeguimientoMedicoController" id="divEditaSeguimientoMedicoController" style="display:none;display:none;color: #ffffff;  background:#E2F2FA;" align="center">
	<div align="center" style="color: #ffffff; background:#6688a6;" ><span style="color: white">EDITAR BITÁCORA MÉDICA</span></div>
	<table width="900px" border=0>	
	<tr><td>
	<table width="570px">																
		<tr>
			<td width='200px'><span style="color: #6688a6;">REPORTE MÉDICO:</span></td>
			<td><select  ng-model="editbitacora.editregreportemedicoselected" ng-selected="editbitacora.editregreportemedicoselected.idRegistroMedico" ng-options="reportemedico.descripcion for reportemedico in reportesmedicos" required > 	  	  			 
					<option value="">Seleccione...</option>
				</select>	 					 				  	  	
			</td>
		</tr>
		<tr >
			<td width='200px'><span style="color: #6688a6;">OBSERVACIONES:</span></td>
			<td>
				<textarea ng-model="editbitacora.observaciones" size=300 required></textarea>		   					 					 		
			</td>
		</tr>
		<tr>		  			
			<td><span style="color: #6688a6;">FECHA:</span></td>
			<td>
		        <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />				        				   
			</td>
		</tr>
		<tr>		
			<td><span style="color: #6688a6;">INTERCONSULTA:</span></td>
			<td><select ng-model="editbitacora.editinterconsultaselected" ng-options="reportemedico.descripcion for reportemedico in especialidades" required> 	  	  			 
				<option value="">Seleccione...</option></select>	 					 				  	  	
			</td>										  	
	  	</tr>
	  </table>
	  </td>
	  <td>
	 	<table width="330px">
		 	<tr><td>
		 		<img src="static/img/editar-bitacora.jpg" height="100" width="100"/>
			 </td></tr>
		</table>
	  </td>
	 </tr>
	 
	 </table>
	
	 <table>
	  	<tr>
	  		<td></td><td></td>
			<td>
				<button class="btn btn-primary" ng-click="guardarEditarBitacora()">Guardar</button>
			</td>											
			<td>
				<button class="btn btn-primary" ng-click="hideEditaSeguimientoMedicoForm()">Cancelar</button>
			</td>
			<td></td><td></td>
		</tr>
	  </table>			
</div>				
</form>			
<div id="divSeguimientoMedicoController" ng-controller="SeguimientoMedicoController" align="center">
	<div align="center" style="color: #ffffff; background:#6688a6" >BITÁCORAS MÉDICAS REGISTRADAS</div>
	<table>
	 <tr><td> Buscar: <input ng-model="searchText"></td><td ng-hide="tipoUsuarioLogin==2" align="right">Agregar<a ng-click="showAltaSeguimientoMedicoForm()"><img style="cursor: pointer; cursor: hand;" src="static/img/nueva-bitacora.jpg" height="40" width="40"/></a>
	 </td></tr>
	</table>
  	<table width="1180px" class="table ng-table-rowselected" border="3" ng-table="tableParams" show-filter="true">
            <tr ng-repeat="bitacora in eventoEditSelected.bitacoras | filter:searchText"
                ng-click="bitacora.$selected = !bitacora.$selected; changeSelection(bitacora)"
                ng-class="{'active': bitacora.$selected}" >
                <td data-title="'IdBitácora'" sortable="'idBitacora'">
                    {{bitacora.idBitacora}}
                </td>
                <td data-title="'Fecha'" sortable="'fechaBitacora'">
                    {{bitacora.fechaBitacora | date:'shortDate'}}
                </td>
                <td data-title="'Estado del Paciente'" sortable="'idRegistroMedico'">
                    {{reportesmedicos[bitacora.idRegistroMedico-1].descripcion}}
                </td>
                <td data-title="'Observaciones'" >
                    {{bitacora.observaciones.substr(0,45)}}
                </td>
                <td data-title="'Interconsulta'" sortable="'interconsulta'">
                    {{bitacora.interconsulta}}
                </td>
                <td data-title="" align="center">
                	<center><a ng-hide="tipoUsuarioLogin==2" ng-click="showEditaSeguimientoMedicoForm(bitacora.idBitacora)"><img  style="cursor: pointer; cursor: hand;" src="static/img/editar.jpg" height="20" width="20"/></a></center>
                </td>
                <td data-title="" align="center"><center>
                	<a  ng-hide="tipoUsuarioLogin==2" ng-click="eliminarBitacora(bitacora)"> 
                		<img style="cursor: pointer; cursor: hand;" height="20" width="20" src="static/img/borrar.jpg"/>
                	</a></center>
                </td>
                
            </tr>
    </table>
    
	<!--  table width="1180px" border=2>
   		<tr ng-show="tipoUsuarioLogin!=2">
			<td align="center">   
				<button class="btn btn-default" >Cerrar</button>
				<button class="btn btn-default" ng-click="showEditaSeguimientoMedicoForm()">Editar</button>	           			
				<button class="btn btn-default" ng-click="eliminarGastoImproForm()">Eliminar</button>
			</td>
		</tr>
	</table-->
</div>