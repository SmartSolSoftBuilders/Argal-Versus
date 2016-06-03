<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js" type="text/javascript"></script>
<script src="http://m-e-conroy.github.io/angular-dialog-service/javascripts/dialogs.min.js" type="text/javascript"></script>
<style>
	.modal2 { 	
		overflow: scroll; 
		height:650px;
	}
</style>
<div ng-controller="CedulaControlController" id="divAltaCedulaControlController">
	<table width="1082px" height="50px">
		<tr>
			<td style="width: auto" align="center" bgcolor="#6688a6">
				<span style="color: #ffffff;"><img width=30 height=30 src="static/img/gastos1.jpg"/>CONTROL DE GASTOS</span>
			</td>			
		    <td bgcolor="#6688a6">
				<img style="cursor: pointer; cursor: hand;" src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	   		</td>			 					
		</tr>
		<tr>
			<td>
				<div ng-show="eventoEditSelected.registroSeguroPersonal">
					<span style="color: #6688a6;">ID EVENTO:</span> {{eventoEditSelected.idEvento}} 
					<span style="color: #6688a6;">FECHA INGRESO:</span>{{eventoEditSelected.fechaIngreso | date:'shortDate'}} 
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
					<span style="color: #6688a6;">FECHA INGRESO:</span>{{eventoEditSelected.fechaIngreso | date:dateFormat}}
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
	<table width="1000"  border=0>
		<tr>
			<td ng-show="eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">TIPO DE CLIENTE:</span>BANCO			    										   
			</td>
			<td ng-show="!eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">TIPO DE CLIENTE:</span>ASEGURADORA			    										   
			</td>
			<td ng-show="eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">CLIENTE:</span>{{eventoEditSelected.cliente.nombreCorto}}			    										   
			</td>
			<td ng-show="!eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">SUMA ASEGURADA:</span>{{eventoEditSelected.registroGastosMayores.sumaAsegurada}}			    										   
			</td>
			<td ng-show="eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">NO. AUTORIZACIÓN:</span>{{eventoEditSelected.registroSeguroPersonal.numAutorizacion}}			    										   
			</td>
			<td ng-show="!eventoEditSelected.registroSeguroPersonal">
				<span style="color: #6688a6;">MONTO DE CARTA DE AUTORIZACIÓN INICIAL:</span>{{eventoEditSelected.registroGastosMayores.montoCartaAutInicial}}			    										   
			</td>			
		</tr>
		<tr>
			<td>
				<span style="color: #6688a6;">TOTAL ESTADO DE CUENTA: $</span>{{subTotalTipoGastoResumen1}}			    					    										  
			</td>
			<td>
				<span style="color: #6688a6;">TOTAL CARGOS OBSERVADOS: $</span>{{subTotalTipoGastoResumen2}}		    										   
			</td>
			<td>
				<span style="color: #6688a6;">TOTAL GASTOS RELEVANTES: $</span>{{subTotalTipoGastoResumen3}}			    										   
			</td>			  	
		</tr>	
	</table>	
	<tabset style="color: #ffffff; background:#E2F2FA">			
		<tab heading="REGISTRAR GASTO"  style="color: #ffffff; background:#E2F2FA; border:1px solid " active=workspace.active>				
			<div ng-hide="tipoUsuarioLogin==2" align="center" style="color: #ffffff; background:#6688a6" >INGRESE LOS SIGUIENTES DATOS PARA EL REGISTRO DE UN NUEVO GASTO</div>									
			<table ng-hide="tipoUsuarioLogin==2" width="1082" style="color: #ffffff; background:#E2F2FA" >				
				<tr>															
					<td><span style="color: #6688a6;">TIPO DE CARGO:</span></td>
					<td><select ng-model="regtipocargoselected" ng-options="tipoCargo.descripcion for tipoCargo in tiposcargos" required> 	  	  			 
							<option value="">Seleccione...</option>
						</select>	 					 				  	  	
					</td>
					<td ng-show="regtipocargoselected.idTipoCargo==1"><span style="color: #6688a6;">TIPO ESTADO DE CUENTA:</span></td>					
					<td ng-show="regtipocargoselected.idTipoCargo==1"><select ng-model="tiposcargoedocuentaselected" ng-options="tipocargoedocuenta.descripcion for tipocargoedocuenta in tiposcargoedocuenta" > 	  	  			 
							<option value="">Seleccione...</option>
							</select>
					</td>	
					<td ng-show="regtipocargoselected.idTipoCargo==2 || regtipocargoselected.idTipoCargo==3"><span style="color: #6688a6;">AREA:</span></td>
					<td ng-show="regtipocargoselected.idTipoCargo==2 || regtipocargoselected.idTipoCargo==3" width="215">
						<select ng-model="regtipoareaselected" ng-options="tipoarea.descripcion for tipoarea in areascargos" > 	  	  			 
			 					<option value="">Seleccione...</option>
			 			</select>	 					 		
			 		</td>
			 		<td ng-show="regtipocargoselected.idTipoCargo==2"><span style="color: #6688a6;">RUBRO:</span></td>
			  		<td width="215" ng-show="regtipocargoselected.idTipoCargo==2">
			  			<select ng-model="regtiporubroselected" ng-options="tiporubro.descripcion for tiporubro in rubroscargos" > 	  	  			 
			 				<option value="">Seleccione...</option>
						</select>	 					 		
					</td>	
				</tr>
			  	<tr>		  		
					<td style="background:#E2F2FA;" ng-show="regtipocargoselected.idTipoCargo==2"><span style="color: #6688a6;">CAUSA:</span></td>
					<td style="background:#E2F2FA;" ng-show="regtipocargoselected.idTipoCargo==2">
						<select ng-model="regrazonesAltaGastoImproSelected" ng-options="razonesGastoImpro.descripcion for razonesGastoImpro in causascargos"> 	  	  			 
				 			<option value="">Seleccione...</option>
				 		</select>	 					 		
			  		</td>			  	  	 
					<td style="background:#E2F2FA;"><span style="color: #6688a6;">FECHA:</span></td>
					<td style="background:#E2F2FA;">
				  		<input type="text"  datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
				   		<span class="input-group-btn">
					   		<button class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
				       	</span>				    
					</td>				
					<td style="background:#E2F2FA;" ng-show="regtipocargoselected.idTipoCargo==2"><span style="color: #6688a6;">PRODUCTO:</span></td>
			  		<td  style="background:#E2F2FA;"  ng-show="regtipocargoselected.idTipoCargo==2"><input type="text" ng-model="cargo.nombre" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();" /></td>
			  	</tr>
			  	<tr>
					<td ng-show="regtipocargoselected.idTipoCargo==2"><span style="color: #6688a6;">MONTO UNITARIO:</span></td>
					<td ng-show="regtipocargoselected.idTipoCargo==1 || regtipocargoselected.idTipoCargo==3"><span style="color: #6688a6;">MONTO:$</span>
					</td>					
			  		<td ng-show="regtipocargoselected.idTipoCargo>=1"><input type="text" field="currencyVal" currency-input size="30" placeholder="insert currency value" ng-model="cargo.montoUnitario"/></td>	  				
			  		<td ng-show="regtipocargoselected.idTipoCargo==2"><span style="color: #6688a6;">CANTIDAD:</span></td>
			  		<td ng-show="regtipocargoselected.idTipoCargo==2"><input type="text" ng-model="cargo.cantidad" size="30" numbers-only="numbers-only" /></td>
			  		<td><span style="color: #6688a6;">EVIDENCIA:</span></td>
			  		<td>
			  			<form name="form2" id="form2" method="post" action="/argal-web/mvc/cont/uploadevidenciaFile" enctype="multipart/form-data">
							<div id="waitUpload" style="display:none">
								Por favor espere mientras el archivo es enviado al servidor!
								<img src="static/img/waiting2.gif" width="50" height="50"></img>
							</div> 			  	  							  	  			
			  				<span style="color: #6688a6;">SELECCIONE EL ARCHIVO PARA ADJUNTAR LA EVIDENCIA:</span><BR>
			  				<input name="file2" id="file2" type="file" /><br/>
							<input name="idEventoHidden" id="idEventoHidden" type="hidden" value=""/>												
							<div id="result"></div>
						</form>
			  	  	</td>
			  	</tr>
				<tr>
			  		<td></td>
			  		<td></td>
					<td><!-- {{savingFile}} -->
						<button  ng-show="savingFile==0" class="btn btn-primary" ng-click="guardarGasto()">Guardar</button>
					</td>				
					<td></td>
					<td>							
						<button  ng-show="savingFile==0" class="btn btn-warning" ng-click="$dismiss()">Cancelar</button>
						<!-- button  class="btn btn-warning" ng-click="hideAltaCedulaControlForm()">Cancelar</button -->
					</td>
					<td>
					</td>
				</tr>
		  	</table>		  				  					  		
		</tab>				
		<tab heading="VER ESTADOS DE CUENTA">
			<table style="width: 800px"  class="table ng-table-rowselected" ng-table="tableParamsEdoCuenta" show-filter="false" border=0>	  	
				<tr ng-repeat="gasto in coleccionEdosCuenta"
						ng-click="gasto.$selected = !gasto.$selected; changeSelection(gasto)"
						ng-class="{'active': gasto.$selected}" >								
					<td  data-title="'Id Edo de Cuenta'">			
							{{gasto.idGasto}}				
					</td>			                
					<td data-title="'Tipo Edo de Cuenta'" sortable="'tipoGasto'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">
							<div ng-show="gasto.idArea==1">ESTADO DE CUENTA PARCIAL</div>		
							<div ng-show="gasto.idArea==2">ESTADO DE CUENTA DE EGRESO</div>
							<div ng-show="gasto.idArea==3">ESTADO DE CUENTA FINAL</div>
						</div>
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
							<select ng-model="editarGastoForm.editTipoCargo" ng-options="tipocargoedocuenta.descripcion for tipocargoedocuenta in tiposcargoedocuenta" > 	  	  			 
									<option value="">Seleccione...</option>
							</select>							
						</div>
					</td>				
					<td data-title="'FECHA'" sortable="'fechaIngreso'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.fechaIngreso | date:'shortDate'}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
	              	    </div>
	                </td>
					<td data-title="'Monto Edo de Cuenta'" sortable="'montoUnitario'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.montoUnitario | currency:"$"}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" ng-model="editarGastoForm.montoUnitario"/>
	              	    </div>					
					</td>						
					<td data-title="''">
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							<a href="#" ng-click="ajax_download2(gasto.idGasto,gasto.rutaEvidencia)">ver archivo</a>
						</div>			                    
					</td>   
					<td data-title="''">
						<div align="center" ng-show="!editarEvento && tipoUsuarioLogin!=2" >
							<a href="#" ng-click="showEditarGastoForm(gasto)"><img height="20" width="20" src="static/img/editar.jpg"/></a>
						</div>
					</td>        
					<td data-title="''">
						<div align="center" ng-show="!editarEvento && tipoUsuarioLogin!=2" >
							 <a href="#" ng-click="eliminarGastoForm(gasto)"><img height="20" width="20" src="static/img/borrar.jpg"/></a>     	
						</div>
					</td>
					<td data-title="''">
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<button  class="btn btn-primary" ng-click="guardarGastoForm(1)">Guardar</button>
	              	    	<button class="btn btn-warning" ng-click="cancelarGastoForm()">Cancelar</button>
	              	    </div>
					</td>
				</tr>
			</table>											     				        				   	
		</tab>		
		<tab heading="VER CARGOS OBSERVADOS">
		a{{editarEdoCuenta}}
			b{{gasto.idGasto}}
			c{{idGastoAEditar}}
			<table style="width: 1000px"  class="table ng-table-rowselected" ng-table="tableParamsCargosObservados" show-filter="false" border=0>	  	
				<tr ng-repeat="gasto in coleccionCargosObservados"
						ng-click="gasto.$selected = !gasto.$selected; changeSelection(gasto)"
						ng-class="{'active': gasto.$selected}" >								
					<td  data-title="'Id GASTO'">
						{{gasto.idGasto}}
					</td>			                
					<td data-title="'FECHA'" sortable="'idEvento'">
	                    <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.fechaIngreso | date:'shortDate'}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" style="width: 100px" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
	              	    </div>
	                </td>
					<td data-title="'PRODUCTO'" sortable="'nombre'">
	                    <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.nombre}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" ng-model="editarGastoForm.nombre"/>
	              	    </div>
	                </td>
	                <td data-title="'MONTO UNITARIO'" sortable="'nombre'">
	                    <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.montoUnitario | currency:"$"}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" style="width: 100px" ng-model="editarGastoForm.montoUnitario"/>
	              	    </div>                  
	                </td>
	                <td data-title="'CANTIDAD'" sortable="'cantidad'">
	                   <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.cantidad}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" style="width: 100px" ng-model="editarGastoForm.cantidad"/>
	              	    </div>
	                </td>                								
					<td data-title="'AREA'" sortable="'idArea'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">			
							<div ng-show="gasto.idArea==1">PISO</div>
							<div ng-show="gasto.idArea==2">QUIROFANO</div>
							<div ng-show="gasto.idArea==3">TERAPIA INTENSIVA, INTERMEDIA Ó NEONATAL</div>
							<div ng-show="gasto.idArea==4">URGENCIAS</div>
							<div ng-show="gasto.idArea==5">GASTOS PERSONALES</div>
						</div>
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
							<select ng-model="editarGastoForm.editArea" ng-options="areascargo.descripcion for areascargo in areascargos" > 	  	  			 
									<option value="">Seleccione...</option>
							</select>							
						</div>
					</td>
					<td data-title="'RUBRO'" sortable="'idRubro'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">				
							<div ng-show="gasto.idRubro==1">MEDICAMENTOS</div>
							<div ng-show="gasto.idRubro==2">MATERIAL</div>
							<div ng-show="gasto.idRubro==3">LABORATORIO Y RX</div>
							<div ng-show="gasto.idRubro==4">TERAPIA RESPIRATORIA</div>
							<div ng-show="gasto.idRubro==5">HABITACIONES</div>
							<div ng-show="gasto.idRubro==6">ANESTESIS (MAQ Y GAS)</div>
							<div ng-show="gasto.idRubro==7">RENTA DE EQUIPOS</div>
							<div ng-show="gasto.idRubro==8">INSUMOS PROVEEDOR EXTERNO</div>
							<div ng-show="gasto.idRubro==9">CUBÍCULOS</div>
							<div ng-show="gasto.idRubro==10">TERAPIA INTENSIVA</div>
							<div ng-show="gasto.idRubro==12">BANCO DE SANGRE</div>
							<div ng-show="gasto.idRubro==13">QUIRÓFANO</div>
							<div ng-show="gasto.idRubro==14">TIEMPOS DE SALA</div>
						</div>
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
							<select ng-model="editarGastoForm.editRubro" ng-options="rubroscargo.descripcion for rubroscargo in rubroscargos" > 	  	  			 
									<option value="">Seleccione...</option>
							</select>							
						</div>				
					</td>
					<td data-title="'RAZÓN'" sortable="'idRazon'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">					
							<div ng-show="gasto.idRazon==1">NO UTILIZADO</div>
							<div ng-show="gasto.idRazon==2">DUPLICADO</div>
							<div ng-show="gasto.idRazon==3">NO INDICADO POR DIAGNÓSTICO</div>
							<div ng-show="gasto.idRazon==4">NO APARECE EN INDICACIONES MÉDICAS</div>
							<div ng-show="gasto.idRazon==5">COSTO INDEBIDO</div>
						</div>
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
							<select ng-model="editarGastoForm.editCausa" ng-options="causascargo.descripcion for causascargo in causascargos" > 	  	  			 
									<option value="">Seleccione...</option>
							</select>							
						</div>							
					</td>		
					<td data-title="''">
						<!-- a href="#" ng-click="ajax_download(gasto.idGasto,factura.rutaFactura)">ver</a -->
						<div align="center" ng-show="tipoUsuarioLogin!=2" >			                    
							<a href="#" ng-click="ajax_download2(gasto.idGasto,gasto.rutaEvidencia)">ver archivo</a>
						</div>
					</td>
					<td data-title="''">
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							<a href="#" ng-click="showEditarGastoForm(gasto)"><img height="20" width="20" src="static/img/editar.jpg"/></a>
						</div>
					</td>        
					<td data-title="''">
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							 <a href="#" ng-click="eliminarGastoForm(gasto)"><img height="20" width="20" src="static/img/borrar.jpg"/></a>     	
						</div>
					</td>
				</tr>				
			</table>					
			<!--div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar"-->
			<div ng-show="editarEdoCuenta" align="center">
	        	<button class="btn btn-primary" ng-click="guardarGastoForm(2)">Guardar</button>
	        	<button class="btn btn-warning" ng-click="cancelarGastoForm()">Cancelar</button>
	        </div>							     				        				   	
		</tab>
		<tab heading="VER DESGLOSE DE GASTOS RELEVANTES">
			<table style="width: 1000px"  class="table ng-table-rowselected" ng-table="tableParamsDesgloceGastosRelevantes" show-filter="false" border=0>	  	
				<tr ng-repeat="gasto in coleccionDesgloces"
						ng-click="gasto.$selected = !gasto.$selected; changeSelection(gasto)"
						ng-class="{'active': gasto.$selected}" >								
					<td  data-title="'Id GASTO'">
						{{gasto.idGasto}}
					</td>			                
					<td data-title="'FECHA'" sortable="'idEvento'">
	                    <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.fechaIngreso | date:'shortDate'}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
	              	    </div>
	                </td>
					<td data-title="'TIPO'" sortable="'nombre'">	                    
	              	    	{{gasto.nombre}}	              	    	              	   
	                </td>
	                <td data-title="'MONTO'" sortable="'nombre'">
	                    <div ng-show="gasto.idGasto!=idGastoAEditar">
	              	    	{{gasto.montoUnitario | currency:"$"}}
	              	    </div>
	              	    <div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<input type="text" ng-model="editarGastoForm.montoUnitario"/>
	              	    </div>                  
	                </td>
					<td data-title="'AREA'" sortable="'idArea'">
						<div ng-show="gasto.idGasto!=idGastoAEditar">			
							<div ng-show="gasto.idArea==1">PISO</div>
							<div ng-show="gasto.idArea==2">QUIROFANO</div>
							<div ng-show="gasto.idArea==3">TERAPIA INTENSIVA, INTERMEDIA Ó NEONATAL</div>
							<div ng-show="gasto.idArea==4">URGENCIAS</div>
							<div ng-show="gasto.idArea==5">GASTOS PERSONALES</div>
						</div>
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
							<select ng-model="editarGastoForm.editArea" ng-options="areascargo.descripcion for areascargo in areascargos" > 	  	  			 
									<option value="">Seleccione...</option>
							</select>							
						</div>
					</td>
					<td data-title="''">
						<!-- a href="#" ng-click="ajax_download(eventoEditSelected.idEvento,factura.rutaFactura)">ver</a -->
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							<a href="#" ng-click="ajax_download2(gasto.idGasto,gasto.rutaEvidencia)">ver archivo</a>
						</div>			                    
					</td>
					<td data-title="''">
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							<a href="#" ng-click="showEditarGastoForm(gasto)"><img height="20" width="20" src="static/img/editar.jpg"/></a>
						</div>
					</td>        
					<td data-title="''">
						<div align="center" ng-show="tipoUsuarioLogin!=2" >
							 <a href="#" ng-click="eliminarGastoForm(gasto)"><img height="20" width="20" src="static/img/borrar.jpg"/></a>     	
						</div>
					</td>
					<td data-title="''">
						<div ng-show="editarEdoCuenta && gasto.idGasto==idGastoAEditar">
	              	    	<button  class="btn btn-primary" ng-click="guardarGastoForm(3)">Guardar</button>
	              	    	<button class="btn btn-warning" ng-click="cancelarGastoForm()">Cancelar</button>
	              	    </div>
					</td>
				</tr>
			</table>											     				        				   	
		</tab>    
	</tabset>
</div>