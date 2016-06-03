<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js" type="text/javascript"></script>
<script src="http://m-e-conroy.github.io/angular-dialog-service/javascripts/dialogs.min.js" type="text/javascript"></script>

<table heigth="50px" border="0">
	<tr>
	<td width="95%" ng-show="idUsuarioLogin!=2" width="1180" bgcolor="#6688a6" height="23" align="center" ><img src="static/img/finalizarevento1.jpg" height="30" width="30"/><span style="color:#fff;"> FINALIZAR EVENTO</span>   
	</td>	
	<td ng-show="idUsuarioLogin==2" width="1180" bgcolor="#6688a6" height="23" align="center" ><img src="static/img/finalizarevento1.jpg" height="30" width="30"/><span style="color:#fff;"> BALANCE FINANCIERO DEL EVENTO</span>   
	</td>
	<td bgcolor="#6688a6">
		<img style="cursor: pointer; cursor: hand;" src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	</td>		
	</tr>
</table>
<div ng-controller="AccordionDemoCtrl"> 		 
	<tabset>
		<tab heading="REGISTRAR FACTURA">
			<div id="subirFacturaForm" ng-hide="tipoUsuarioLogin==2">			
				<span  style="color: #6688a6;">INGRESE LOS DATOS DE LA NUEVA FACTURA:</span>
				<table width="900px" border="0" align="center">
					<tr>
						<td><span style="color: #6688a6;">¿APROBADA?:</span>			
							<select required style="width: 120px" ng-model="factaprobSelected" ng-options="factaprob.nombre for factaprob in facturaAprobadaCombo" class="css-form">
								<option value="">Seleccione...</option>
							</select>		        				        				  
						</td>																	
						<td style="width: 130px">
							<span style="color: #6688a6;">NUMERO DE FACTURA:</span>
						</td>
						<td>
							<input class="form-control" onkeyup="javascript:this.value=this.value.toUpperCase();" required type="text" ng-model="eventoFacturaForm.numeroFactura" />
						</td>				 					 				  	  							
						<td style="width: 65px">
							<span style="color: #6688a6;">MONTO:$</span>
						</td>
						<td>			
							<input type="text" field="currencyVal" currency-input size="30" placeholder="insert currency value" ng-model="eventoFacturaForm.montoFactura" numbers-only="numbers-only"/>
						</td>
					</tr>
				</table>
				<table width="900px" border="0">
					<tr>		   					 					 					
						<td style="width: 340px;">
							<span style="color: #6688a6;">OBSERVACIONES:</span>			
							<textarea style="width: 320px; height:103px" onkeyup="javascript:this.value=this.value.toUpperCase();" ng-model="eventoFacturaForm.observaciones" ></textarea>		   					 					 		
						</td>
						<td align="center">
							<form name="form2" id="form2" method="post" action="/argal-web/mvc/cont/uploadFile" enctype="multipart/form-data">
								<!-- File input -->   
								<div id="waitUpload" style="display:none">
										Por favor espere mientras el archivo es enviado al servidor!
										<img src="static/img/waiting2.gif" width="50" height="50"></img>
								</div> 
										<span style="color: #6688a6;">SELECCIONE EL ARCHIVO PARA ADJUNTAR FACTURA:</span><BR><input name="file2" id="file2" type="file" /><br/>
										<input name="idEventoHidden" id="idEventoHidden" type="hidden" value=""/>
										<!--  input name="idEventoHidden" id="idEventoHidden" type="hidden" value="2"/-->  					
										<!-- button value="Submit" onclick="uploadJqueryForm()" >Subir Archivo</button><i>Using JQuery Form Plugin</i><br/> -->					
										<div id="result"></div>
							</form>	
						</td>
					</tr>		
				</table>	
				
				<div ng-show="factaprobSelected.idFacturaAprobada==2">
					Llene los siguientes datos para Facturas rechazadas:</h5></td>
				</div>
				<table ng-show="factaprobSelected.idFacturaAprobada==2" border=0 >
					<tr>
						<td>
							<span style="color: #6688a6;">AJUSTE FACTURA:</span>
							<input type="text" ng-model="eventoFacturaForm.ajusteFactura" numbers-only="numbers-only" field="currencyVal2" currency-input size="30" placeholder="insert currency value"/>
						</td>			
						<td><span style="color: #6688a6;">TIPO DE COMPROBANTE FISCAL CORREGIDO:</span>			
							<input type="text" ng-model="eventoFacturaForm.tipoComprobanteFiscalCorregido" onkeyup="javascript:this.value=this.value.toUpperCase();"/>				        				   
						</td>
						<td><span style="color: #6688a6;">FOLIO COMPROBANTE FISCAL CORREGIDO:</span>
							<input type="text" ng-model="eventoFacturaForm.folioComprobanteFiscalCorregido" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
						</td>		  	
						<td><span style="color: #6688a6;">MONTO COMPROBANTE FISCAL CORREGIDO:</span>
							<input type="text" ng-model="eventoFacturaForm.montoComprobanteFiscalCorregido" numbers-only="numbers-only"/>	 					 				  	  	
						</td>			
					</tr>
				</table>
				<br>
				<br>
				<center>
					<button class="btn btn-primary"  ng-disabled="isSaving" id="botonGuardarFactura" ng-click="subirFactura()">GUARDAR</button>
					<button class="btn btn-warning"  ng-disabled="isSaving" id="botonCancelarFactura" ng-click="nuevaFacturaShow()">CANCELAR</button>
				</center>
				
			</div>			
		</tab>		
		<tab heading="VER FACTURAS">
			<div id="showFacturasForm">
				<table style="width: 1000px" border=1 ng-show="!subirFacturaVar">	
					<tr>
						<td style="width: 500px" align="center">
							<h5>Facturas Aprobadas:</h5>
							<table style="width: 500px"  class="table ng-table-rowselected" ng-table="tableParams" show-filter="false" border=0>	  	
								<tr ng-repeat="factura in dataFacturas | filter:{aprobada: 'SI'}"
								ng-click="factura.$selected = !factura.$selected; changeSelection(factura)"
								ng-class="{'active': factura.$selected}" >
									<td  data-title="'NoFactura'">
										{{factura.numeroFactura}}
									</td>			                
									<td data-title="'Monto'" sortable="'monto'">
										{{factura.monto | currency:"$"}}
									</td>
									<td>
										<div align="center" ng-show="tipoUsuarioLogin!=2" >										
											<a href="#" ng-click="ajax_download(eventoEditSelected.idEvento,factura.idFactura)">ver</a>
										</div>			                    
										</td>
									</div>									                               
									<td data-title="'borrar'">
										<div align="center" ng-show="tipoUsuarioLogin!=2" >
											<a href="#" ng-click="eliminarFactura(factura)"><img height="20" width="20" src="static/img/borrar.jpg"/></a>
										</div>
									</td>
								</tr>
							</table>
						</td>			
						<td style="width: 500px" valign="top">
							<h5>Facturas Rechazadas:</h5>                
							<table style="width: 500px"  class="table ng-table-rowselected" ng-table="tableParams" show-filter="false" border=0>	  	
								<tr ng-repeat="factura in dataFacturas | filter:{aprobada: 'NO'}"
									ng-click="factura.$selected = !factura.$selected; changeSelection(factura)"
									ng-class="{'active': factura.$selected}" >
									<td data-title="'NoFactura'">
										{{factura.numeroFactura}}
									</td>
									<td data-title="'Monto'" sortable="'aprobada'">
										{{factura.monto | currency:"$"}}
									</td>
								    <td data-title="'Ajuste Factura'">
										{{factura.ajusteFactura | currency:"$"}}
							    	</td>
								    <!--  td data-title="'Tipo Comp. Fiscal'" sortable="'aprobada'">
										{{factura.tipoComprobanteFiscalCorregido}}
								    </td-->
								    <td data-title="'Folio Comp. Fiscal'" sortable="'aprobada'">
										{{factura.folioComprobanteFiscalCorregido}}
								    </td>
								    <td data-title="'Monto Comp. Fiscal'" sortable="'aprobada'">
										{{factura.montoComprobanteFiscalCorregido | currency:"$"}}
								    </td>			                                               
								    <td data-title="''">
									 	<div ng-show="!editarEvento" >
											<a href="#" ng-click="ajax_download(eventoEditSelected.idEvento,factura.idFactura)">ver</a>			                    
										</div>			                    
								    </td>
								    <td data-title="'borrar'">
										<div ng-show="!editarEvento && tipoUsuarioLogin!=2">
										<a href="#" ng-click="eliminarFactura(factura)"><img height="20" width="20" src="static/img/borrar.jpg"/></a>
										</div>
									</td>
								</tr>			
							</table>          	 
						</td>       								
					</tr>
				</table>
			</div>
			<!-- button ng-show="tipoUsuarioLogin!=2" ng-click="nuevaFacturaShow()" ng-hide="subirFacturaVar"><h5>Subir Nueva Factura</h5></button> -->	
		</tab>    
					
		<tab heading="FINALIZAR MONTOS">      
		{{eventoEditSelected.registroSeguroPersonal.condicionPaciente}}
		con censo?{{eventoEditSelected.registroSeguroPersonal.censo}}
		
			<div ng-show="eventoEditSelected.registroSeguroPersonal.censo=='SI' && eventoEditSelected.registroSeguroPersonal.condicionPaciente=='JUBILADO'  && tipoUsuarioLogin!=2">
				<table width="700px" border="0" align="left">
					<tr>    	
						<td width="50px"><span style="color: #6688a6;"><br>CAPITA:</span></td>
						<td width=""><select required ng-model="capitaSelected" ng-options="capita.nombre for capita in capitas">
							<option value="">Seleccione...</option>
							</select>					
						</td>
						<td width="400px"><span style="color: #6688a6;">CANTIDAD CUBIERTA POR CONVENIO:</span></td>
						<td width="100px"><input required type="text" ng-model="evento.registroSeguroPersonal.cantidadCubiertaConvenio" /></td>				
					</tr>
				</table>
			</div>    
	      	
			<table width="900px" border="1" align="center">
				<tr>
					<td align="center" bgcolor="#6688a6"><span style="color: #ffffff;">FINALIZAR MONTOS DEL EVENTO</span>
					</td>    						
				</tr>
			</table>
			<table width="900px" border="1" align="center">
				<tr>								
					<td width="100px"><span style="color: #6688a6;">MONTO ANTES DE DESVÍOS:</span></td>
					<td width="150">{{eventoFinalizarEventoForm.montoAntesDesvios | currency:"$"}}</td>
					<td width="100px"><span style="color: #6688a6;"># FACTURAS APROBADAS:</span></td>
					<td width="150">{{eventoFinalizarEventoForm.numFacturasAprobadas}}</td> 				        				  
				</tr>
				<tr>			
					<td><span style="color: #6688a6;">TOTAL CARGOS OBSERVADOS:</span></td>			
					<td>{{eventoFinalizarEventoForm.totalDesvios | currency:"$"}}</td>
					<td width="100px"><span style="color: #6688a6;"># FACTURAS RECHAZADAS:</span></td>
					<td width="150">{{eventoFinalizarEventoForm.numFacturasRechazadas}}</td>			
				</tr>	
				<tr>				
					<td><span style="color: #6688a6;">DESVÍOS COMPROBADOS:</span></td>			
					<td><input type="text" ng-model="eventoFinalizarEventoForm.totalDesviosComprobados" value=0/></td>
					<td><span style="color: #6688a6;">MONTO TOTAL DESVÍOS FACTURACIÓN:</span></td>			
					<td>{{eventoFinalizarEventoForm.montoDesviosFacturacion | currency:"$"}}</td>		   					 					 												
				</tr>
				<tr>								
					<td width="100px"><span style="color: #6688a6;">MONTO DESPUÉS DE DESVÍOS:</span></td>
					<td width="150">{{(eventoFinalizarEventoForm.montoAntesDesvios-eventoFinalizarEventoForm.totalDesvios) | currency:"$"}}</td>
					<td><span style="color: #6688a6;">MONTO AJUSTE FACTURACIÓN: $</span></td>
					<td><input type="text" ng-model="eventoFinalizarEventoForm.montoAjusteFacturacion" /></td>			
				</tr>	
				<tr>				
					<td><span style="color: #6688a6;">IVA: $</span></td>
					<td><input type="text" ng-model="eventoFinalizarEventoForm.ivaFinalizarEvento" ng-change="updateMontoFinal()" value=0/></td>
					<td><span style="color: #6688a6;">MONTO FACTURACIÓN CORREGIDO: $</span></td>
					<td><input type="text" ng-model="eventoFinalizarEventoForm.montoFacturacionCorregido" /></td>
				</tr>
				<tr>				
					<td><span style="color: #6688a6;">COASEGURO: (%)</span></td>	 		
					<td><input type="text" ng-model="eventoFinalizarEventoForm.coaseguroFinalizarEvento" ng-change="updateMontoFinal()" value=0/></td>
					<td><span style="color: #6688a6;">MONTO FINAL FACTURACIÓN:</td>
					<td></span>{{eventoFinalizarEventoForm.montoFinalFacturacion | currency:"$"}}</td>
				</tr>
				<tr>				
					<td><span style="color: #6688a6;">DEDUCIBLE: $</span></td>			
					<td><input type="text" ng-model="eventoFinalizarEventoForm.deducibleFinalizarEvento" ng-change="updateMontoFinal()"/></td>		   					 					 									
				</tr>
				<tr>
					<td><span style="color: #6688a6;">DESCUENTOS HOSPITAL: $</span></td>
					<td><input type="text" ng-model="eventoFinalizarEventoForm.descuentoHospFinalizarEvento" ng-change="updateMontoFinal()"/></td>
					<td><center><button ng-show="tipoUsuarioLogin!=2" class="btn btn-primary" ng-click="finalizarMontosEvento()" ng-show="subirFacturaVar">FINALIZAR EVENTO</button></center></td>		
				</tr>						
				
				</table>											     				        				   	
			</tab>    
		</tabset>  
	</div>
		
