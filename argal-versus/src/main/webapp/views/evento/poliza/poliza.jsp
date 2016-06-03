<div ng-controller="PolizaController" id="divShowNoDataTipoEvento" ng-show="showNoDataTipoEvento"> 
{{alertaNoTipoEvento}}
  
	TIPO DE CLIENTE: <select  ng-model="listaTipoClienteSelected" ng-options="tiposeguro.descripcion for tiposeguro in tiposseguro"   
			  			ng-change="showTipoSeguroView()">
							<option value="">Seleccione...</option>
						</select>
	
</div>
<BR>
<div ng-controller="PolizaController" id="tipoSeguroPersonalDIV" ng-show="tipoEventoSeguroPersonalPoliza">
	<table border="1">
		<tr>
			<td bgcolor="#6688a6"><span style="color: #ffffff;">2) DATOS DEL PACIENTE (BANCO)</span></td>
		</tr>
		<tr>
			<td>
				<table border=0> 
					<tr>
						<td>NO. NOMINA:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.numeroNomina" size="30"/></td>	  	
						<td>INSTITUCION:</td> 
						<td><input type="text" ng-model="evento.registroSeguroPersonal.institucion" size="30"/></td>
						<td>NO. AUTORIZACIÓN:</td>
						<td><input type="email" ng-model="evento.registroSeguroPersonal.numeroAutorizacion" size="30"/></td>	  							
					</tr>
				</table>
				DATOS DEL TITULAR
				<table border=0>
					<tr>
						<td>NOMBRE:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.nombreTitular" /></td>
						<td>APELLIDO PATERNO:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.appTitular" /></td> 
						<td>APELLIDO MATERNO:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.apmTitular" /></td>
					</tr>
				</table>
				DATOS DEL PACIENTE
				<table border=0>
					<tr>
						<td>NOMBRE:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.nombrePaciente" /></td>
						<td>APELLIDO PATERNO:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.appPaciente" /></td> 
						<td>APELLIDO MATERNO:</td>
						<td><input type="text" ng-model="evento.registroSeguroPersonal.apmPaciente" /></td>
					</tr>						
					</table>
					<table width="787">
					<tr>
						<td width="88">EDAD:<input type="text" ng-model="evento.registroSeguroPersonal.edadPaciente" size="5"/>
						</td>
						<td width="235">UNIDAD:
							<select ng-model="unidadEdadPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in unidadesedades">
								<option value="">Seleccione...</option>
							</select>							  			
						</td>												
						<td width="182">SEXO:
						<select ng-model="sexoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo">
							<option value="">Seleccione...</option>
						</select></td>
						<td width="262">PARENTESCO
						<select ng-model="parentescoPacienteSegPersonalSelected" ng-options="unidad.descripcion for unidad in parentescos">
							<option value="">Seleccione...</option>
						</select>
						<!-- evento.registroSeguroPersonal.unidadEdadPaciente -->  				
						</td>
					</tr>  			
				</table>		
			</td>
		</tr>
	</table>	
</div>

<div ng-controller="PolizaController" id="tipoSeguroGastosMayoresDIV" ng-show="tipoEventoGastosMayoresPoliza">
	<table border="1">
		<tr>
			<td bgcolor="#6688a6"><span style="color: #ffffff;">2) DATOS DEL PACIENTE (ASEGURADORA)</span></td>
		</tr>
		<tr>
			<td>
				<table> 
					<tr>
						<td>NO.POLIZA:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.numeroPoliza" size="30"/></td>	  	
						<td>DEDUCIBLE:</td> 			
						<td><input type="text" ng-model="evento.registroGastosMayores.deducible" size="30"/></td>
					</tr>
					<tr>
						<td>COASEGURO MEDICO/HOSPITALARIO:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.coaseguroMedico" size="30"/></td>	  	
						<td>SUMA ASEGURADA:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.sumaAsegurada" size="30"/></td>
					</tr>
					<tr>
						<td>MONTO DE CARTA DE AUTORIZACIÓN INICIAL:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.montoCartaAutInicial" size="30"/></td>	  	
						<td>TABULACIÓN DE HONORARIOS MÉDICOS:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.tablaHonorariosMedicos" size="30"/></td>
					</tr>
				</table>
				DATOS DEL TITULAR
				<table>
					<tr>
						<td>NOMBRE:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.nombreTitular" /></td>
						<td>APELLIDO PATERNO:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.appTitular" /></td>   				
						<td>APELLIDO MATERNO:</td>
						<td><input type="text" ng-model="evento.registroGastosMayores.apmTitular" /></td>  				
					</tr>
				</table>
				DATOS DEL PACIENTE
				<table>				
					<tr>
						<td width="202">NOMBRE:
						<input type="text" ng-model="evento.registroGastosMayores.nombrePaciente" size="20"></td>
						<td width="146">APELLIDO PATERNO:</td>
						<td width="163"><input type="text" ng-model="evento.registroGastosMayores.appPaciente" /></td> 					
						<td width="111">APELLIDO MATERNO:</td>
						<td width="145"><input type="text" ng-model="evento.registroGastosMayores.apmPaciente" /></td>
					</tr>
				</table>
				<table width="787">
					<tr>
						<td width="88">EDAD:<input type="text" ng-model="evento.registroGastosMayores.edadPaciente" size="5"/>
						</td>
						<td width="235">UNIDAD:
							<select ng-model="unidadEdadPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in unidadesedades">
								<option value="">Seleccione...</option>
							</select>							  			
						</td>												
						<td width="182">SEXO:
						<select ng-model="sexoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in sexodatoscombo">
							<option value="">Seleccione...</option>
						</select></td>
						<td width="262">PARENTESCO
						<select ng-model="parentescoPacienteGastosMayoresSelected" ng-options="unidad.descripcion for unidad in parentescos">
							<option value="">Seleccione...</option>
						</select>
						<!-- evento.registroSeguroPersonal.unidadEdadPaciente -->  				
						</td>
					</tr>  			
				</table>	
			</td>
		</tr>
	</table>	
</div>