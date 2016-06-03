<style>
	.modal2 { 	
		overflow: scroll; 
		height:650px;
	}
</style>
<table heigth="50px" border="0">
	<tr>
		<td width="1000" height="23" align="center" ><img src="static/img/editar.jpg" height="30" width="30"/> EDITAR EVENTO
	    	<hr color="#6688a6" />
	    </td>	   
	    <td bgcolor="#fff">
			<img src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	   </td>			 	    
	</tr>
</table>

<div ng-controller="EditarEventoController" align="center">
	<form name="editareventoform" class="css-form">
		<table border="1">
			<tr>
				<td bgcolor="#6688a6"><span style="color: #ffffff;">1) DATOS GENERALES DEL INGRESO</span></td>			
			</tr>
			<tr>
				<td>
  					<table border=0>
						<tr>
							<td width="120">HOSPITAL: </td>	
							<td width="170">
								<div ng-controller="HospitalController" style="float:left;" > 
									<input type="text" ng-model="hospitalSeleccionadoTxt" ng-click="openShowHosp('','views/hospital/gridhosp.jsp')" required style="width: 120px;cursor: pointer;" required ng-readonly="true"/>
								</div>
								<div ng-controller="HospitalController" style="float:left;">
									<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openShowHosp('','views/hospital/gridhosp.jsp')">
								</div>		
							</td>
							<td width="131">ID HOSPITALARIO:</td> 
							<td width="187"><input type="text" ng-model="eventoEditSelected.folioHospital" required /></td>
							<td>FOLIO ARGAL:</td>
							<td><input type="text" ng-model="eventoEditSelected.folioArgal" required/></td>
							<td width="87">CLIENTE:</td> 
			  				<td width="145">
			  					<select ng-model="listaClienteSelected" ng-options="cliente.razonSocialCliente for cliente in clientes" required> 	  	  			 
									<option value="">Seleccione...</option>
								</select>	 					 		
			  				</td>
						</tr>
						<tr>			
							<td>TIPO DE CLIENTE:</td> 			
		  					<td>
								<div ng-show="eventoEditSelected.tipoSeguro.idTipoSeguro==1"> BANCO</div>
								<div ng-show="eventoEditSelected.tipoSeguro.idTipoSeguro==2"> ASEGURADORA</div>
							</td>	  	
		  					<td>TIPO DE EVENTO:</td>  
				  			<td><select ng-model="listaTipoEventoSelected" ng-options="tipoevento.descripcion for tipoevento in tiposevento" required>
								<option value="">Seleccione...</option></select>
							</td>
							<td>FECHA INGRESO:</td> 
							<td>
				   				<input style="float:left;" type="text" class="form-control" size=15 datepicker-popup="{{format}}" ng-model="dt" is-open="opened"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />			
				   				<button style="float:left;" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>				            				    		
		  					</td>		  	
		  					<td width="112">HORA INGRESO:</td> 
							<td width>
								<input type="time" id="exampleInput" name="input" required ng-model="valueTime" placeholder="HH:mm" style="width: 100px"/>
        					</td>					
		  				</tr>		  
					</table>
				</td>
			</tr>		
		</table>		                             	
		<div  id="tipoSeguroPersonalDIV" style="display:none">
			<table border="0">
				<tr>
					<td bgcolor="#6688a6"><span style="color: #ffffff;">2) DATOS DEL PACIENTE (BANCO)</span></td>
				</tr>
				<tr>
					<td>
						<span style="color: #6688a6;">DATOS DEL TITULAR</span>		
						<table border=1>
							<tr>
								<td>				
									<table border=0> 
										<tr>
											<td>NO. NOMINA:</td>
											<td><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.numeroNomina" size="30"/></td>	  	
											<td></td> 
											<td><input type="hidden" ng-model="eventoEditSelected.registroSeguroPersonal.institucion" size="30" value="1"/></td>
											<td>NO. AUTORIZACIÓN:</td>
											<td><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.numAutorizacion" size="30"/></td>	  							
										</tr>
										<tr>
											<td>CONDICIÓN DEL ASEGURADO:</td>
											<td><select  ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 120px" ng-model="condicionesaseguradosSelected" ng-options="condicionesasegurado.nombre for condicionesasegurado in condicionesasegurados">
													<option value="">Seleccione...</option>
												</select>
											</td>
											<td>CENSO:</td>
											<td><select  ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 120px" ng-model="censoSelected" ng-options="censo.nombre for censo in censos">
													<option value="">Seleccione...</option>
												</select>
											</td>
											<td></td>
										</tr>
									</table>
									<table border=0>
										<tr>
											<td>NOMBRE:</td>
											<td width="250px"><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nombreTitular" /></td>
											<td>APELLIDO PATERNO:</td>
											<td width="250px"><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.appTitular" /></td> 
											<td>APELLIDO MATERNO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.apmTitular" /></td>
										</tr>			
									</table>
								</td>
							</tr>
						</table>
				
						<span style="color: #6688a6;">DATOS DEL PACIENTE</span>		
						<table border=1>
							<tr>
								<td>				
									<table ng-show="listaTipoEventoSelected.idTipoEvento!=4"  border=0>
										<tr>
											<td>NOMBRE:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nombrePaciente" /></td>
											<td >APELLIDO PATERNO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.appPaciente" /></td> 
											<td>APELLIDO MATERNO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.apmPaciente" /></td>
										</tr>						
									</table>
									<table border=0>
										<tr>
											<td ng-show="listaTipoEventoSelected.idTipoEvento!=4" >EDAD:</td><td ng-show="listaTipoEventoSelected.idTipoEvento!=4" ><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.edadPaciente" style="width: 80px" />
											</td>
											<td ng-show="listaTipoEventoSelected.idTipoEvento!=4" >UNIDAD:
												<select  ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 100px" ng-model="unidadEdadPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in unidadesedades">
													<option value="">Seleccione...</option>
												</select>							  			
											</td>												
											<td>SEXO:
												<select  ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 150px" ng-model="sexoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo">
													<option value="">Seleccione...</option>
												</select>
											</td>
											<td>PARENTESCO:
												<select  ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 200px" ng-model="parentescoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in parentescos">
													<option value="">Seleccione...</option>
												</select>								  				
											</td>
										</tr>  			
									</table>
									<table ng-show="listaTipoEventoSelected.idTipoEvento==4"  border=0>
										<tr> 
											<td>NOMBRE RECIEN NACIDO:</td>
											<td width="200px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nombrePaciente" /></td>
											<td>APELLIDO PATERNO:</td>
											<td width="200px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.appPaciente" /></td> 
											<td>APELLIDO MATERNO:</td>
											<td width="200px"><input type="text" ng-model="eventoEditSelected.registroSeguroPersonal.apmPaciente" /></td>
											<td></td>
										</tr>						
									</table>
									<table ng-show="listaTipoEventoSelected.idTipoEvento==4" border=1>
										<tr>
											<td>FECHA NACIMIENTO:</td>
											<td>
												<input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" style="float:left;" type="text" class="form-control" size=15 datepicker-popup="{{format}}" ng-model="dt2" is-open="opened" min="minDate" max="'2035-06-22'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />			
										   		<button style="float:left;" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
											</td>			
											<td>HORA NACIMIENTO:</td>
											<td>
												<input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="time" id="exampleInput" name="input" ng-model="valueTime2" placeholder="HH:mm" style="width: 100px"/>									  			
											</td>							
											<td>TIPO PARTO:</td>
											<td>
												<select ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" style="width: 150px" ng-model="tipoPartoSelected" ng-options="tipoparto.descripcion for tipoparto in tiposparto">
													<option value="">Seleccione...</option>
												</select>
											</td>
											<td>TALLA:<input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nacimientoTalla" /></td>						
										</tr>
										<tr ng-show="listaTipoEventoSelected.idTipoEvento==4">
											<td>PESO:</td>
											<td><input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nacimientoPeso" /></td>
											<td>APGAR:</td>
											<td><input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="eventoEditSelected.registroSeguroPersonal.nacimientoApgar" /></td>								
											<td>
												<div style="float:left;">
												MÉDICO:
													<select ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4"  ng-model="listaMedTrat2" ng-options="medtrat.nombre for medtrat in medictratantes">	 
							 							<option value="">Seleccione...</option>
							 						</select>
						 						</div>			
											</td>
										</tr>  			
									</table>								
								</td>
							</tr>
						</table>					
					</td>
				</tr>
			</table>	
		</div>

	<div id="tipoSeguroGastosMayoresDIV" style="display:none">
		<table border="1">
			<tr>
				<td bgcolor="#6688a6"><span style="color: #ffffff;">2) DATOS DEL PACIENTE (ASEGURADORA)</span></td>
			</tr>
			<tr>
				<td>
					<span style="color: #6688a6;">DATOS DEL TITULAR</span>		
					<table border=1>
						<tr>
							<td>							
							<table  border=0> 
									<tr>
										<td width="100px">NO.POLIZA:</td>
										<td width="150px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.numeroPoliza"/></td>	  	
										<td width="100px">DEDUCIBLE:</td> 			
										<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.deduciblePoliza" /></td>
										<td>COASEGURO MEDICO/HOSPITALARIO:</td>
										<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.coaseguroMedico" /></td>	  						
									</tr>
									<tr>						
										<td>SUMA ASEGURADA:</td>
										<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.sumaAsegurada"/></td>
										<td>MONTO DE CARTA DE AUTORIZACIÓN INICIAL:</td>
										<td><input type="text"  ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.montoCartaAutInicial" /></td>	  	
										<td>TABULACIÓN DE HONORARIOS MÉDICOS:</td>
										<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.tablaHonorariosMedicos" size="30"/></td>
									</tr>
								</table>				
								<table>
									<tr>
										<td>NOMBRE:</td>
										<td width="250px"><input  ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.nombreTitular" /></td>
										<td >APELLIDO PATERNO:</td>
										<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.appTitular" /></td>   				
										<td>APELLIDO MATERNO:</td>
										<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.apmTitular" /></td>  				
									</tr>					
								</table>
				 			</td>
				 		</tr>
					</table>		
					<span style="color: #6688a6;">DATOS DEL PACIENTE</span>																
						<table border=1>
							<tr>
								<td>							
									<table ng-show="listaTipoEventoSelected.idTipoEvento!=4" width="1180" border=0>				
										<tr>
											<td>NOMBRE:</td>
											<td width="250px"><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.nombrePaciente" size="20"></td>
											<td>APELLIDO PATERNO:</td>
											<td width="250px"><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.appPaciente" /></td> 					
											<td>APELLIDO MATERNO:</td>
											<td width="250px"><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.apmPaciente" /></td>
										</tr>
									</table>
									<table width="1180" border=0>
										<tr>
											<td ng-show="listaTipoEventoSelected.idTipoEvento!=4">EDAD:</td><td ng-show="listaTipoEventoSelected.idTipoEvento!=4">
												<input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="eventoEditSelected.registroGastosMayores.edadPaciente" style="width: 80px"/>
												</td>
											<td ng-show="listaTipoEventoSelected.idTipoEvento!=4">UNIDAD:
												<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="unidadEdadPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in unidadesedades" style="width: 100px">
													<option value="">Seleccione...</option>
												</select>							  			
											</td>												
											<td>SEXO:
											<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="sexoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo" style="width: 150px">
												<option value="">Seleccione...</option>
											</select></td>
											<td>PARENTESCO
											<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="parentescoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in parentescos" style="width: 200px">
												<option value="">Seleccione...</option>
											</select>
											<!-- eventoEditSelected.registroSeguroPersonal.unidadEdadPaciente -->  				
											</td>
										</tr>  			
									</table>
									<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="1180" border=0>
										<tr>
											<td>NOMBRE RECIEN NACIDO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroGastosMayores.nombrePaciente" /></td>
											<td>APELLIDO PATERNO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroGastosMayores.appPaciente" /></td> 
											<td>APELLIDO MATERNO:</td>
											<td width="250px"><input type="text" ng-model="eventoEditSelected.registroGastosMayores.apmPaciente" /></td>
											<td></td>
										</tr>						
									</table>
									<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="1180">
										<tr>
											<td>FECHA NACIMIENTO:</td>
											<td>
												<input ng-required="listaTipoClienteSelected.idTipoSeguro==2" style="float:left;" type="text" class="form-control" size=15 datepicker-popup="{{format}}" ng-model="dt2" is-open="opened" min="minDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />			
												<button style="float:left;" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
											</td>			
											<td>HORA NACIMIENTO:</td>
											<td>
												<input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="time" id="exampleInput" name="input" ng-model="valueTime2" placeholder="HH:mm" min="08:00" max="17:00" style="width: 100px"/>									  			
											</td>							
											<td>TIPO PARTO:</td>
											<td>
												<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" style="width: 150px" ng-model="tipoPartoSelected2" ng-options="tipoparto.descripcion for tipoparto in tiposparto">
													<option value="">Seleccione...</option>
												</select>
											</td>
											<td>TALLA:<input  ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.nacimientoTalla" /></td>																		
										</tr>
										<tr ng-show="listaTipoEventoSelected.idTipoEvento==4">
											<td>PESO:</td>
											<td><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.nacimientoPeso" /></td>
											<td>APGAR:</td>
											<td><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="eventoEditSelected.registroGastosMayores.nacimientoApgar" /></td>												
											<td>MÉDICO:
												<div style="float:left;">
													<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="listaMedTrat3" ng-options="medtrat.nombre for medtrat in medictratantes">	 
														<option value="">Seleccione...</option>
													</select>
									 			</div>			
											</td>
										</tr>  			
									</table>		
								</td>
							</tr>						
						</table>			
					</td>
				</tr>
			</table>	
		</div>	 	
		
		<table border="1">
			<tr>
				<td bgcolor="#6688a6"><span style="color: #ffffff;">3) DATOS DE INGRESO HOSPITALARIO </span></td>				
			</tr> 			
			<tr>
				<td>
					<table border=0>						  		  	  	
					<tr>		
						<td width="131">NÚMERO HABITACION:</td> 
						<td width="187"><input required type="text" ng-model="eventoEditSelected.numHabitacion" /></td>
						<td>ANTECEDENTES PERSONALES PATOLÓGICOS:</td>
						<td><select ng-model="antecedenteSelected" ng-options="antecedente.nombre for antecedente in antecedentes"> 	  	  			 
						 		<option value="">Seleccione...</option>
						 	</select>				 		
						</td>
						<td ng-show="listaTipoClienteSelected.idTipoSeguro==2">
							MÉDICO DICTAMINADOR:
						</td>
						<td ng-show="listaTipoClienteSelected.idTipoSeguro==2">
							<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" style="width: 200px" ng-model="medDictaminadorSelected" ng-options="meddictaminador.nombreImplant + ' ' + meddictaminador.appImplant + ' ' + meddictaminador.apmImplant for meddictaminador in medicosdictaminadores">
								<option value="">Seleccione...</option>
								</select>
						</td>
					</tr>		  		  	  	  
					<tr>															
				  		<td>DIAGNOSTICO DE INGRESO1:</td> 
						<td>
							<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
								<input required ng-readonly="true" type="text" ng-model="tipoIcd1" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',1)" style="width: 180px;cursor: pointer;"/>
							</div>
					  	</td>
						<td>
							<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
								<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',1)">			
							</div>
						</td>
						<td>DIAGNOSTICO DE INGRESO2:</td>		  	  
					  	<td>
					  		<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
								<input type="text" ng-model="tipoIcd2" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',2)" style="width: 180px;cursor: pointer;"/>
							</div>
						</td>
						<td>
					  		<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
								<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',2)">			
							</div>
						</td>		  	
						
					</tr>
				  	<tr>			
						<td>MEDICO TRATANTE:</td>  
						<td>
							<div style="float:left;"> 
								<select required ng-model="listaMedTrat" ng-options="medtrat.nombre for medtrat in medictratantes" ng-change="updateMedTrat()" > 	  	  			 
						 			<option value="">Seleccione...</option>
						 		</select>
					 		</div>
					 	</td><td>
					 		<div style="float:left;">
					 		<img  style="cursor: pointer" src="static/img/nuevomedico.jpg" height="25" width="23" ng-click="openAltaMedicoTratante('','views/evento/altamedtrat.jsp')">								
					 		</div>
					 	</td>		  	  				  		
						<td>ESPECIALIDAD:    
						<li ng-repeat="lt in listaMedTrat.especialidades">
							{{lt.descripcion}}
						</lk>
						</td>  								
																										
				  		<td>TIPO MÉDICO:
				  			<div  ng-show="listaMedTrat.tipo==0">RED</div>
				  			<div ng-show="listaMedTrat.tipo==1">NO RED</div>
				  			<div  ng-show="listaMedTrat.tipo==2">STAFF</div>
				  			<div  ng-show="listaMedTrat.tipo==3">NINGUNA</div>					
							<!-- input type="checkbox" ng-model="eventoEditSelected.red" / -->
						</td>
				  	</tr>
				</table>			
			</td>
		</tr>
	</table>	   		
	
	<button class="btn btn-default" ng-click="guardarEventoEditar(evento)" >Guardar Evento</button>  		
	<span class="mensajeError" ng-show="editareventoform.$invalid">{{mensajeError}}</span>  	 	  	    	  				  	   
	</form>
</div>    
