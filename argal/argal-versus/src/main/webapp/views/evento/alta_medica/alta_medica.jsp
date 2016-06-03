<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td ng-show="idUsuarioLogin!=2" width="960px" bgcolor="#6688a6" height="23" align="center" ><img src="static/img/egresopaciente1.jpg" height="30" width="30"/><span style="color:#fff;"> EGRESO DE PACIENTE</span>   
		</td>	
		<td ng-show="idUsuarioLogin==2" width="960px" bgcolor="#6688a6" height="23" align="center" ><img src="static/img/egresopaciente1.jpg" height="30" width="30"/><span style="color:#fff;"> DATOS DEL EGRESO</span>   
		</td>
		<td bgcolor="#6688a6">
			<img style="cursor: pointer; cursor: hand;" src="static/img/cerrar.png" width="30" height="30" ng-click="$dismiss()"></img>	
	  	</td>	
	</tr>
</table>

<form  name="altamedform" class="css-form">
<div align="center" style="color: #ffffff; background:#6688a6;" ><span style="color: white">INGRESE LOS DATOS DEL EGRESO</span></div>
<div id="divEgresoFormulario" ng-controller="AltaMedicaController" style="color: #ffffff;  background:#E2F2FA;" align="center">		
	<table border=0 width="900px">			
		<tr>
			<td>
				<span style="color: #6688a6;">FECHA DE EGRESO:</span>		        
		        <input type="text" class="form-control" datepicker-popup="{{format}}" 
								            name="dt" ng-model="dt" 
								            is-open="datepickers.dt" datepicker-options="dateOptions" 
								            ng-required="true" close-text="Close" />
		        <br>
		        <span style="color: #6688a6;">HORA DE EGRESO:</span>
		        <input type="time" id="exampleInput" name="input" ng-model="valueTime" placeholder="HH:mm" min="00:00" max="24:60" style="width: 100px"/>				        				   
			</td>
			<td>
				<span style="color: #6688a6;float:left;" >DIAGNOSTICO DE EGRESO(ICD):</span>			
				<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
					<input type="text" ng-readonly="true" required ng-model="tipoIcd3" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',3)" style="width: 180px;cursor: pointer;"/>
				</div>		
				<div style="float:left;" ng-controller="Cat_ICD_Controller" >  
					<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openAddIcd2('','views/cat_icd/cat_icd.jsp',3)">			
				</div>
			</td>
		</tr>
		<tr>					
			<td><span style="color: #6688a6;float:left;">TIPO DE PROCEDIMIENTO 1 (CPT):</span>
				<div style="float:left;" ng-controller="Cat_Cpt_Controller" >  
					<input type="text" required ng-readonly="true" ng-model="tipoCpt1" ng-click="openAddCpt2('','views/cat_cpt/cat_cpt.jsp',1)" style="width: 180px;cursor: pointer;"/>
				</div>		
				<div style="float:left;" ng-controller="Cat_Cpt_Controller" >  
					<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openAddCpt2('','views/cat_cpt/cat_cpt.jsp',1)">			
				</div>
			</td>
			<td><span style="color: #6688a6;float:left;">TIPO DE PROCEDIMIENTO 2 (CPT):</span>
				<div style="float:left;" ng-controller="Cat_Cpt_Controller" >  
					<input type="text" ng-readonly="true" ng-model="tipoCpt2" ng-click="openAddCpt2('','views/cat_cpt/cat_cpt.jsp',2)" style="width: 180px;cursor: pointer;"/>
				</div>
				<div style="float:left;" ng-controller="Cat_Cpt_Controller" >  
					<img  style="cursor: pointer;" src="static/img/buscar.jpg" height="25" width="25" ng-click="openAddCpt2('','views/cat_cpt/cat_cpt.jsp',2)">			
				</div>
			</td>		
		</tr>
	</table>
	<table>
		<tr>	
			<td>
				<span style="color: #6688a6;">EVENTOS NO DESEABLES DEL ENTORNO HOSPITALARIO:</span>
				<textarea ng-model="evento.eventosNoDeseablesEntornoHosp" style="text-transform:uppercase"></textarea>		   					 					 		
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><span style="color: #6688a6;">MOTIVO DE EGRESO:</span>
				<select required ng-model="motivoEgresoSelected" ng-options="motivoegreso.descripcion for motivoegreso in motivosegresos" ng-change="showFormNac()">
							<option value="">Seleccione...</option>
				</select>		   					 					 		
			</td>
			<td><span style="color: #6688a6;">DIAS DE INCAPACIDAD:</span>
				<input ng-model="evento.diasIncapacidad"  numbers-only="numbers-only"/>		   					 					 		
			</td>		
		</tr>
	</table>
		
	<table>	
		<tr ng-show="tipoUsuarioLogin!=2">
			<td></td>
			<td></td>  	
			<td>
				<button class="btn btn-primary" ng-click="guardarAltaPaciente()">Guardar</button>
			</td>											
			<td>
				<button class="btn btn-warning" ng-click="$dismiss()">Cancelar</button>
			</td>	
			<td></td>														 
			<td></td>
	  	</tr>
	  </table>
	  <table ng-show="showFormNac0==1">
			  	<tr>	  	
					<td><span style="color: #6688a6;">FECHA DEF.:</span></td>
					<td>					 
					<td><input type="text" class="form-control" datepicker-popup="{{format}}" 
								            name="dt2" ng-model="dtSecond" 
								            is-open="datepickers.dtSecond" datepicker-options="dateOptions" 
								            ng-required="true" close-text="Close" />
								           </td>		
					<td><span style="color: #6688a6;">HORA DEF.:</span></td>
					<td><input  type="time" id="exampleInput" name="input" ng-model="valueTime3" placeholder="HH:mm" min="00:00" max="24:60" style="width: 100px"/></td>
					<td><span style="color: #6688a6;">CAUSA DIRECTA DEF.:</span></td>
					<td width="215">
						<textarea ng-model="evento.causaDirectaDef" ></textarea>		   					 					 		
					</td>
				</tr>
			  	</table>
</div>
			<div ng-show="showFormNac0==1" ng-controller="AltaMedicaController" id="divDef" style="display:none">
			  	
	  		</div>
	<div id="divShowDiagEgreso" style="display:none" ng-controller="AltaMedicaController">
		<center>		
			<br><span class="mensajeTitulo">Seleccione un Diagnóstico de Egreso:</span>
		</center>
		<div ng-controller="Cat_ICD_Controller_Egreso" >
		<form  name="selectcat_descform"  class="css-form">
		    	<smart-table class="table table-striped smart-table" table-title="Smart Table" config="globalConfig" columns="columnCollectionICD_Egreso" rows="rowCollectionICD_Egreso" ></smart-table>
			    <div ng-show="tipoUsuarioLogin!=2" >    
					<button ng-click="showMainAltaMedicaForm()">Aceptar</button>	           
				</div>
			</form>        
		</div>	  
	</div>
	<div id="divShowProcedimiento" style="display:none" ng-controller="AltaMedicaController">
		<center>		
			<br><span class="mensajeTitulo">Seleccione un Procedimiento:</span>
		</center>
		<div ng-controller="Cat_ICD_Controller_Egreso"  >
			<form  name="selectcat_descform"  class="css-form">
    			<smart-table class="table table-striped smart-table" table-title="Smart Table" config="globalConfig" columns="columnCollectionCPT" rows="rowCollectionCPT_Egreso" ></smart-table>
    			<div>    
					<button ng-click="showMainAltaMedicaForm()">Aceptar</button>	           
				</div>
			</form>     	   
		</div>	  
	</div>

</form>

