<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td width="1200" height="23" align="center" ><img src="static/img/regevento.jpg" height="30" width="30"/> REGISTRAR EVENTO (registrado por: {{nombreLogin}})
	    <hr color="#6688a6" /></td>
	</tr>
</table>
<div ng-controller="AltaEventoController">
<form name="altaeventoform" class="css-form">
	<table border="0" width="100%">	
		<tr>
			<td bgcolor="#6688a6"><span style="color: #ffffff;">1) DATOS GENERALES DEL INGRESO</span></td>			
		</tr>
		<tr>
			<td>
	  			<table border=0 width="100%">
					<tr>		 
						<td width="120">HOSPITAL: </td>
						<td width="170">
							<div ng-controller="HospitalController" style="float:left;" > 
								<input type="text" ng-model="hospitalSeleccionadoTxt" ng-click="openShowHosp('','views/hospital/gridhosp.jsp')" style="width: 120px;cursor: pointer;" required ng-readonly="true"/>
							</div>
							<div ng-controller="HospitalController" style="float:left;"><img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openShowHosp('','views/hospital/gridhosp.jsp')"></div>
						</td>
						<td>TIPO DE CLIENTE:</td> 
			  			<td width="131px"><select style="width:140px;"  ng-model="listaTipoClienteSelected" ng-options="tiposeguro.descripcion for tiposeguro in tiposseguro"  
				  			ng-change="showTipoSeguroView()" required>
								<option value="">Seleccione...</option>
							</select>
						</td>
						<td width="131">ID HOSPITALARIO:</td>
						<td width="187"><input  type="text" ng-model="evento.folioHospitalario" required/></td>
						<td>FOLIO ARGAL:</td>
						<td><input type="text" ng-model="evento.folioArgal" required/></td>
					</tr>
					<tr>
						<td width="87">CLIENTE:</td>
				  		<td width="145">
				  			<select ng-model="listaClienteSelected" ng-options="cliente.razonSocialCliente for cliente in clientes" required> 	  	  			 
								<option value="">Seleccione...</option>
							</select>
				  		</td>				 	
			  			<td>TIPO DE EVENTO:</td>
			  			<td><select ng-model="listaTipoEventoSelected" required ng-options="tipoevento.descripcion for tipoevento in tiposevento">
							<option value="">Seleccione...</option></select>
						</td>
						<td>FECHA INGRESO:</td>
						<td>
					   		<input type="text" class="form-control" datepicker-popup="{{format}}" 
					            name="dt" ng-model="dt" is-open="datepickers.dt" 	
					            datepicker-options="dateOptions" ng-required="true" 
					            close-text="Close" />
					      <span class="input-group-btn">
					        <button class="btn btn-default" ng-click="open($event,'dt')">
					            <i class="glyphicon glyphicon-calendar"></i></button>
					      </span>		    		
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
				<table border=0>
					<tr>
						<td>				
							<table border=0> 
								<tr>
									<td>NO. NOMINA:</td>
									<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==1" ng-model="evento.registroSeguroPersonal.numeroNomina" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  	
									<td></td> 
									<td><input type="hidden" ng-model="evento.registroSeguroPersonal.institucion" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();" value="1"/></td>
									<td>NO. AUTORIZACIÓN:</td>
									<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==1" ng-model="evento.registroSeguroPersonal.numAutorizacion" size="30" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  							
								</tr>
								<tr>
									<td>CONDICIÓN DEL ASEGURADO:</td>
									<td><select ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 120px" ng-model="condicionesaseguradosSelected" ng-options="condicionesasegurado.nombre for condicionesasegurado in condicionesasegurados">
											<option value="">Seleccione...</option>
										</select>
									</td>
									<td>CENSO:</td>
									<td><select ng-required="listaTipoClienteSelected.idTipoSeguro==1" style="width: 120px" ng-model="censoSelected" ng-options="censo.nombre for censo in censos">
											<option value="">Seleccione...</option>
										</select>
									</td>
									<td></td>
								</tr>
							</table>
							<table border=0>
								<tr>
									<td>NOMBRE:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="evento.registroSeguroPersonal.nombreTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
									<td>APELLIDO PATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="evento.registroSeguroPersonal.appTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td> 
									<td>APELLIDO MATERNO:</td>
									<td width="250px"><input type="text" ng-model="evento.registroSeguroPersonal.apmTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
								</tr>			
							</table>
						</td>
					</tr>
				</table>
				
				<span style="color: #6688a6;">DATOS DEL PACIENTE</span>		
				<table border=0>
					<tr>
						<td>				
							<table ng-show="listaTipoEventoSelected.idTipoEvento!=4" width="800px" border=0>
								<tr>
									<td>NOMBRE:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="evento.registroSeguroPersonal.nombrePaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
									<td >APELLIDO PATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==1" type="text" ng-model="evento.registroSeguroPersonal.appPaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td> 
									<td>APELLIDO MATERNO:</td>
									<td width="250px"><input type="text" ng-model="evento.registroSeguroPersonal.apmPaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
								</tr>						
							</table>
							<table  width="800px" border=0>
								<tr>
									<td ng-show="listaTipoEventoSelected.idTipoEvento!=4">
										EDAD:
									</td>
									<td ng-show="listaTipoEventoSelected.idTipoEvento!=4">											
										<input ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento<4" type="text" ng-model="evento.registroSeguroPersonal.edadPaciente" style="width: 80px"/>
									</td>									
									<td ng-show="listaTipoEventoSelected.idTipoEvento!=4">
										UNIDAD:									
										<select style="width: 100px" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento<4" ng-model="unidadEdadPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in unidadesedades">
											<option value="">Seleccione...</option>
										</select>							  			
									</td>												
									<td>SEXO:
										<select style="width: 150px" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento<4" ng-model="sexoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo">
											<option value="">Seleccione...</option>
										</select>
									</td>
									<td>PARENTESCO
										<select style="width: 200px" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento<4" ng-model="parentescoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in parentescos">
											<option value="">Seleccione...</option>
										</select>								  				
									</td>
								</tr>  			
							</table>
							<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="800px" border=0>
								<tr>
									<td>NOMBRE RECIEN NACIDO:</td>
									<td width="250px"><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" ng-model="evento.registroSeguroPersonal.nombrePaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
									<td>APELLIDO PATERNO:</td>
									<td width="250px"><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" ng-model="evento.registroSeguroPersonal.appPaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td> 
									<td>APELLIDO MATERNO:</td>
									<td width="250px"><input type="text" ng-model="evento.registroSeguroPersonal.apmPaciente"  ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
									<td></td>
								</tr>						
							</table>
							<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="800px" border=1>
								<tr>
									<td>FECHA NACIMIENTO:</td>
									<td>
										 <input type="text" class="form-control" datepicker-popup="{{format}}" 
								            name="dtSecond" ng-model="dtSecond" 
								            is-open="datepickers.dtSecond" datepicker-options="dateOptions" 
								            ng-required="true" close-text="Close" />
									      <span class="input-group-btn">
									        <button class="btn btn-default" ng-click="open($event,'dtSecond')">
								    	        <i class="glyphicon glyphicon-calendar"></i></button>
								    	  </span>						
									</td>			
									<td>HORA NACIMIENTO:</td>
									<td>
										<input type="time" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" id="exampleInput" name="input" ng-model="valueTime2" placeholder="HH:mm" style="width: 100px"/>									  			
									</td>							
									<td>TIPO PARTO:</td>
									<td>
										<select ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" style="width: 150px" ng-model="tipoPartoSelected" ng-options="tipoparto.descripcion for tipoparto in tiposparto">
											<option value="">Seleccione...</option>
										</select>
									</td>
									<td>TALLA:<input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" ng-model="evento.registroSeguroPersonal.nacimientoTalla" /></td>																		
								</tr>
								<tr ng-show="listaTipoEventoSelected.idTipoEvento==4">
									<td>PESO:</td>
									<td><input  numbers-only="numbers-only" ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="evento.registroSeguroPersonal.nacimientoPeso" /></td>
									<td>APGAR:</td>
									<td><input  ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="evento.registroSeguroPersonal.nacimientoApgar" /></td>												
									<td>
										<div style="float:left;">
										MÉDICO:
											<select ng-required="listaTipoClienteSelected.idTipoSeguro==1 && listaTipoEventoSelected.idTipoEvento==4" ng-model="listaMedTrat2" ng-options="medtrat.nombre for medtrat in medictratantes" style="width: 150px">	 
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
	<table border="3">
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
									<td width="150px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.numeroPoliza" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>	  	
									<td width="100px">DEDUCIBLE:</td> 			
									<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.deduciblePoliza" /></td>
									<td>COASEGURO MEDICO/HOSPITALARIO:</td>
									<td><input type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.coaseguroMedico" /></td>	  						
								</tr>
								<tr>						
									<td>SUMA ASEGURADA:$</td>
									<td><input numbers-only="numbers-only" type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.sumaAsegurada"/></td>
									<td>MONTO DE CARTA DE AUTORIZACIÓN INICIAL:$</td>
									<td><input  numbers-only="numbers-only" type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.montoCartaAutInicial" /></td>	  	
									<td>TABULACIÓN DE HONORARIOS MÉDICOS:$</td>
									<td><input numbers-only="numbers-only" type="text" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.tablaHonorariosMedicos" size="30"/></td>
								</tr>
							</table>
				
							<table>
								<tr>
									<td>NOMBRE:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.nombreTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
									<td >APELLIDO PATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.appTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>   				
									<td>APELLIDO MATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.apmTitular" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>  				
								</tr>					
							</table>
				 		</td>
				 	</tr>
				</table>
				<span style="color: #6688a6;">DATOS DEL PACIENTE</span>	
														
				<table border=1>
					<tr>
						<td>							
							<table ng-show="listaTipoEventoSelected.idTipoEvento!=4" width="800px" border=0>				
								<tr>
									<td>NOMBRE:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.nombrePaciente" size="20" onkeyup="javascript:this.value=this.value.toUpperCase();"></td>
									<td>APELLIDO PATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.appPaciente" onkeyup="javascript:this.value=this.value.toUpperCase();" /></td> 					
									<td>APELLIDO MATERNO:</td>
									<td width="250px"><input ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.apmPaciente" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
								</tr>
							</table>
							<table ng-show="listaTipoEventoSelected.idTipoEvento!=4" width="800px" border=0>
								<tr>
									<td>EDAD:</td><td>
										<input numbers-only="numbers-only" type="text"  ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento<4" ng-model="evento.registroGastosMayores.edadPaciente" style="width: 80px"/>
										</td>
									<td>UNIDAD:
										<select ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento<4" ng-model="unidadEdadPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in unidadesedades" style="width: 100px">
											<option value="">Seleccione...</option>
										</select>							  			
									</td>												
									<td>SEXO:
									<select ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento<4" ng-model="sexoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo" style="width: 150px">
										<option value="">Seleccione...</option>
									</select></td>
									<td>PARENTESCO
									<select ng-model="parentescoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in parentescos" style="width: 200px">
										<option value="">Seleccione...</option>
									</select>
									<!-- evento.registroSeguroPersonal.unidadEdadPaciente -->  				
									</td>
								</tr>  			
					</table>
					<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="800px" border=0>
							<tr>
								<td>NOMBRE RECIEN NACIDO:</td>
								<td width="250px"><input type="text" onkeyup="javascript:this.value=this.value.toUpperCase();" ng-required="listaTipoClienteSelected.idTipoSeguro==2" ng-model="evento.registroGastosMayores.nombrePaciente" /></td>
								<td>APELLIDO PATERNO:</td>
								<td width="250px"><input onkeyup="javascript:this.value=this.value.toUpperCase();" ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.appPaciente" /></td> 
								<td>APELLIDO MATERNO:</td>
								<td width="250px"><input onkeyup="javascript:this.value=this.value.toUpperCase();"  ng-required="listaTipoClienteSelected.idTipoSeguro==2" type="text" ng-model="evento.registroGastosMayores.apmPaciente" /></td>
								<td></td>
							</tr>						
					</table>
					<table ng-show="listaTipoEventoSelected.idTipoEvento==4" width="800px">
						<tr>
							<td>FECHA NACIMIENTO:</td>
							<td>
							 <input type="text" class="form-control" datepicker-popup="{{format}}" 
						            name="dtSecond" ng-model="dtSecond" 
						            is-open="datepickers.dtSecond" datepicker-options="dateOptions" 
						            ng-required="true" close-text="Close" />
						      <span class="input-group-btn">
						        <button class="btn btn-default" ng-click="open($event,'dtSecond')">
						            <i class="glyphicon glyphicon-calendar"></i></button>
						    	</span>
				       		</td>			
							<td>HORA NACIMIENTO:</td>
							<td>
								<input type="time" ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" id="exampleInput" name="input" ng-model="valueTime2" placeholder="HH:mm" min="08:00" max="17:00" style="width: 100px"/>									  			
							</td>							
							<td>TIPO PARTO:</td>
							<td>
							<select style="width: 150px" ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" ng-model="tipoPartoSelected2" ng-options="tipoparto.descripcion for tipoparto in tiposparto">
								<option value="">Seleccione...</option>
							</select>
							</td>
							<td>TALLA:<input ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="evento.registroGastosMayores.nacimientoTalla" /></td>																		
						</tr>
						<tr ng-show="listaTipoEventoSelected.idTipoEvento==4">
							<td>PESO:</td>
							<td><input numbers-only="numbers-only" ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="evento.registroGastosMayores.nacimientoPeso" /></td>
							<td>APGAR:</td>
							<td><input ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" type="text" ng-model="evento.registroGastosMayores.nacimientoApgar" /></td>												
							<td>MÉDICO:</td>
							<td>
								<div style="float:left;">
									<select  ng-required="listaTipoClienteSelected.idTipoSeguro==2 && listaTipoEventoSelected.idTipoEvento==4" ng-model="listaMedTrat3" ng-options="medtrat.nombre for medtrat in medictratantes">	 
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
	<div id="seccion3Div">
	<table border="0">
		<tr>
			<td bgcolor="#6688a6"><span style="color: #ffffff;">3) DATOS DE INGRESO HOSPITALARIO </span></td>				
		</tr>
		<tr>
			<td>
				<table border=0>						  		  	  	
				<tr>
					<td width="131">NÚMERO HABITACION:</td>
					<td width="187"><input type="text" ng-model="evento.numHabitacion" required onkeyup="javascript:this.value=this.value.toUpperCase();"/></td>
					<td>ANTECEDENTES PERSONALES PATOLÓGICOS:</td>
					<td><select ng-required="listaTipoClienteSelected.idTipoSeguro==2" style="width: 150px"  ng-model="antecedenteSelected" ng-options="antecedente.descripcion for antecedente in antecedentes"> 	  	  			 
					 		<option value="">Seleccione...</option>
					 	</select>				 		
					</td>
			  		<td ng-show="idtipoSeguroSel!=1">
						MÉDICO DICTAMINADOR:
					</td>
					<td ng-show="idtipoSeguroSel!=1">
						<select ng-required="listaTipoClienteSelected.idTipoSeguro==2" style="width: 200px" required  ng-model="medDictaminadorSelected" ng-options="meddictaminador.nombreImplant + ' ' + meddictaminador.appImplant + ' ' + meddictaminador.apmImplant for meddictaminador in medicosdictaminadores">
							<option value="">Seleccione...</option>
						</select>
					</td>
				</tr>
				<tr>												
			  		<td>DIAGNOSTICO DE INGRESO1:</td>
					<td>
						<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
							<input type="text" required ng-readonly="true" ng-model="tipoIcd1" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',1)" style="width: 180px;cursor: pointer;"/>
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
							<input type="text" ng-readonly="true" ng-model="tipoIcd2" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',2)" style="width: 180px;cursor: pointer;"/>
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
							<select required  ng-model="listaMedTrat" ng-options="medtrat.nombre for medtrat in medictratantes" ng-change="updateMedTrat()" > 	  	  			 
					 			<option value="">Seleccione...</option>
					 		</select>
				 		</div>
				 	</td>
				 	<td>
				 		<div style="float:left;">
				 		<img  style="cursor: pointer" src="static/img/nuevomedico.jpg" height="25" width="23" ng-click="openAltaMedicoTratante('','views/evento/altamedtrat.jsp')">								
				 		</div>
				 	</td>	  	  				  		
					<td>ESPECIALIDAD:    
					<li ng-repeat="lt in listaMedTrat.especialidades">
						{{lt.descripcion}}
					</li>
					</td>  								
																
			  		<td>TIPO MÉDICO:{{tipoMedTratSel}}						
						<!-- input type="checkbox" ng-model="evento.red" / -->
					</td>
			  	</tr>
			</table>
			
			</td>
		</tr>
	</table>
	</div>
	<center>
		<button class="btn btn-primary" ng-disabled="altaeventoform.$invalid" ng-click="guardarEvento(evento)" >Guardar Evento</button>
		<button class="btn btn-warning" ng-click="cancEvento(evento)" >Cancelar</button>  		
		<span class="mensajeError" ng-show="altaeventoform.$invalid">{{mensajeError}}</span>
	</center>
</form> 	 	 	  	    	  				  	   
</div>    
    