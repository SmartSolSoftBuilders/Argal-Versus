<div id="loadGastosGif">
	<img src="static/img/v2.0/img/load2.gif" height="100" width="100" id="imgEgreso" name="imgEgreso"/>
	<h3>Por favor Espere...</h3>
</div>
<div id="dialog-confirm2" title="¿Eliminar el gasto?"  style="display: none" align="center">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Se eliminará el registro del gasto!<div id="eventoEliminar"></div>, Continuar?</p>
</div>
<div id="tituloGastos">
<fieldset>
<table>
	<tr>
		<td><div id="idEventoBar"></div></td>
		<td><div id="titularEventoBar"></div></td>
		<td><div id="pacienteEventoBar"></div></td>	
		<!--  td><div id="tipoClienteEventoBar"></div></td-->
		<td><div id="clienteEventoBar"></div></td>		
	</tr>
	<tr>				
		<td><div id="numAutorizacionEventoBar"></div></td>	
		<td><div id="totalEdoCtaEventoBar"></div></td>
		<td><div id="totalCargObsEventoBar"></div></td>
		<td><div id="totalGastosRelEventoBar"></div></td>
		<td><div id="totalGastosEvitadosEventoBar"></div></td>
	</tr>
</table>
</fieldset>
</div>
<input type="hidden" id="idEventoHid" name="idEventoHid"/>
<input type="hidden" id="idClienteHid" name="idClienteHid"/>
<input type="hidden" id="idHospitalHid" name="idHospitalHid"/>
<input type="hidden" id="nameClienteHid" name="nameClienteHid"/>						  
<input type="hidden" id="nameHospitalHid" name="nameHospitalHid"/>
<div id="tabs">
	  <ul>
		<li><a href="#tabs-0">REGISTRAR GASTO</a></li>
	    <li><a href="#tabs-1">ESTADOS DE CUENTA</a></li>
	    <li><a href="#tabs-2">GASTOS OBSERVADOS</a></li>
	    <li><a href="#tabs-3">GASTOS RELEVANTES</a></li>
	    <li><a href="#tabs-4">GASTOS EVITADOS</a></li>
	  </ul>
	  <div id="tabs-0">
	  <div id="divRegistroGasto">
	  	<fieldset class="row1">
	  		<legend>
	  			<img src="static/img/v2.0/img/gastos.png" width="40" height="30"/>REGISTRO DE NUEVO GASTO
            </legend>
	    	<p>
			    <label>TIPO DE CARGO A REGISTRAR:</label>
			    	<select id="tipoRegistroGasto" name="tipoRegistroGasto" onchange="showDivGasto();">
			    		<option value="">seleccione...</option>
			    		<option value="1">ESTADO DE CUENTA</option>
			    		<option value="2">CARGOS OBSERVADOS</option>
			    		<option value="3">DESGLOSE DE GASTOS RELEVANTES</option>
			    		<option value="4">GASTOS EVITADOS</option>
			    	</select>		    
			  </p>			  
		</fieldset>
		</div>
		<div id="loadingEdoCta" style="display:none">
				<div><h4>....Guardando Datos....</h4></div>
				<div><img src="static/img/v2.0/img/loadingEvent.gif" /></div>
		</div>
		<div id="divRegEdoCta" style="display:none" >
			<form name="form1" id="form1" method="post" action="mvc/cont/uploadevidenciaFile" enctype="multipart/form-data">
				<div id="waitUpload" style="display:none">
				<div><h4>Subiendo Evidencia....</h4></div>
				<div><img src="static/img/v2.0/img/loadingEvent.gif" /></h4></div>
				</div> 			  	  							  	  			
				<span style="color: #6688a6;">SELECCIONE EL ARCHIVO PARA ADJUNTAR LA EVIDENCIA:</span><BR>
					<input name="file1" id="file1" type="file" /><br/>
					<input name="idEventoHidden" id="idEventoHidden" type="hidden" value=""/>												
					<div id="result"></div>
		
	  		</form>
			<form id="altaedocta" name="altaedocta" class="register" >
			<input name="usarVersus" id="usarVersus" type="hidden" value=""/>				  					
			<input name="esJubilado" id="esJubilado" type="hidden" value=""/>
				<table>
					<tr>
					<td>
						<label>TIPO ESTADO DE CUENTA:</label>
					</td>
					<td>
						<select id="tipoEdoCuenta" name="tipoEdoCuenta" data-validation="edoctaval" onchange="showButtonVersus()";> 
				    		<option value="">seleccione...</option>
				    		<option value="1">ESTADO DE CUENTA PARCIAL</option>
				    		<option value="2">ESTADO DE CUENTA FINAL</option>
				    		<option value="3">ESTADO DE CUENTA DE EGRESO</option>
				    	</select>			    	
				    </td>
				    <td>			   			   
			   			<label>FECHA:</label>
			   		</td>
			   		<td>
			   			<input type="text" name="fechaGastoEdoCta" id="fechaGastoEdoCta" data-validation="edoctaval" readonly/>
			   		</td>
			   		<td>		   		
			   			<label>MONTO:</label>
			   		</td>
			   		<td>
			   			<input type="text" id="montoGasto" name="montoGasto" data-validation="edoctaval number" data-validation-allowing="float" 
			 			data-validation-decimal-separator=".">
			   		</td>
			   		</tr> 
			   	</table>		    		   	
		    	<p>
				  	<button type="submit" id="buttonGuardarEvento" class="myButton" onclick="$('#usarVersus').val(0);">Guardar</button>
			  		<font color="white">___</font>
			  		<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>
			  		<font color="white">___</font>
			  		<button type="submit" id="divButtonVersus" style="display:none;" class="myButton" onclick="$('#usarVersus').val(1);">Versus</button>							
				</p>
			</form>	  				
	  	</div>
	  	<div id="divRegGastoObs" style="display:none">
	  	<form name="form2" id="form2" method="post" action="mvc/cont/uploadevidenciaFile" enctype="multipart/form-data">
			<div id="waitUpload" style="display:none">
			<div><h4>Subiendo Evidencia....</h4></div>
			<div><h4><img src="static/img/v2.0/img/loadingEvent.gif" /></h4></div>
			</div> 			  	  							  	  			
			<span style="color: #6688a6;">SELECCIONE EL ARCHIVO PARA ADJUNTAR LA EVIDENCIA:</span><BR>
				<input name="file2" id="file2" type="file" /><br/>
				<input name="idEventoHidden2" id="idEventoHidden2" type="hidden" value=""/>												
				<div id="result"></div>		
	  	</form>
		<form id="altacargoobs" name="altacargoobs" class="register" >				  							  
			<fieldset class="row1">		
			<p>
			   	<label>ÁREA:</label><select id="areaGastoObs" name="areaGastoObs" data-validation="gastobsval">
			    		<option value="" class="">Seleccione...</option>
			    		<option value="1">PISO</option>
			    		<option value="2">QUIROFANO</option>
			    		<option value="3">TERAPIA INTENSIVA, INTERMEDIA, NEONATAL</option>
			    		<option value="4">URGENCIAS</option>
			    		<option value="5">GASTOS PERSONALES</option>		    		
			    	</select>
			    <label>RUBRO:</label><select id="rubroGastoObs" name="rubroGasto" data-validation="gastobsval">
			    		<option value="">Seleccione...</option>
			    		<option value="1">MEDICAMENTOS</option>
			    		<option value="2">MATERIAL</option>
			    		<option value="3">LABORATORIO Y RX</option>
			    		<option value="4">TERAPIA RESPIRATORIA</option>
			    		<option value="5">HABITACIONES</option>
			    		<option value="6">ANESTESIA (MAQ Y GAS)</option>
			    		<option value="7">RENTAS DE EQUIPOS</option>
			    		<option value="8">INSUMOS PROVEEDOR EXTERNO</option>
			    		<option value="9">CUBICULOS</option>
			    		<option value="10">TERAPIA INTENSIVA</option>
			    		<option value="11">BANCO DE SANGRE</option>			    		
			    		<option value="12">QUIROFANO</option>
			    		<option value="13">TIEMPOS DE SALA</option>			    		
			    	</select>		    
		    </p>
		    <p>
		    <label>CAUSA:</label><select id="causaGastoObs" name="causaGastoObs" data-validation="gastobsval">
		    		<option value="">seleccione...</option>
		    		<option value="1">NO UTILIZADO</option>
		    		<option value="2">DUPLICADO</option>
		    		<option value="3">NO INDICADO POR DIAGNÓSTICO</option>		    		
		    		<option value="4">NO APARECE EN INDICACIONES MÉDICAS</option>
		    		<option value="5">COSTO INDEBIDO</option>
		    	</select>		    
		   	<label>FECHA:</label><input type="text" id="fechaGastoObs" name="fechaGastoObs" readonly data-validation="gastobsval"/>
		    <label>PRODUCTO:</label><input type="text" id="productoGastoObs" name="productoGastoObs" data-validation="gastobsval"/>		    
		    </p>
		    <p>
		    <label>MONTO UNITARIO:</label><input type="text" id="montoGastoObs" name="montoGastoObs"
		    		data-validation="gastobsval number" data-validation-allowing="float" />		    
		   	<label>CANTIDAD:</label><input type="text" id="cantidadGastoObs" name="cantidadGastoObs" data-validation="gastobsval number"/>		   			   
		    </p>		    		   	
	  		</fieldset>
	  		<p>
			  	<button type="submit" id="buttonGuardarGastosObs" class="myButton">Guardar</button>
			  	<font color="white">___</font>
			  	<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>			  							
			</p>
	  	</form>
	  	</div>	  	
	  	<div id="divGastosRelev" style="display:none">
		  	<form name="form3" id="form3" method="post" action="mvc/cont/uploadevidenciaFile" enctype="multipart/form-data">
				<div id="waitUpload" style="display:none">
				<div><h4>Subiendo Evidencia....</h4></div>
				<div><h4><img src="static/img/v2.0/img/loadingEvent.gif" /></h4></div>
				</div> 			  	  							  	  			
				<span style="color: #6688a6;">SELECCIONE EL ARCHIVO PARA ADJUNTAR LA EVIDENCIA:</span><BR>
					<input name="file3" id="file3" type="file" /><br/>
					<input name="idEventoHidden3" id="idEventoHidden3" type="hidden" value=""/>																											
		  	</form>	  	
			<form id="altagastosrelev" name="altagastosrelev" class="register" >	  							  	
				<fieldset class="row1">		
					<p>
				   	<label>ÁREA:</label><select id="areaGastoRel" name="areaGastoRel" data-validation="gastrelval">
				    		<option value="" class="">Seleccione...</option>
				    		<option value="1">PISO</option>
				    		<option value="2">QUIROFANO</option>
				    		<option value="3">TERAPIA INTENSIVA, INTERMEDIA, NEONATAL</option>
				    		<option value="4">URGENCIAS</option>
				    		<option value="5">GASTOS PERSONALES</option>		    		
				    	</select>			    		   
			   		<label>FECHA:</label><input type="text" id="fechaGastoRel" name="fechaGastoRel" readonly data-validation="gastrelval"/>		   
			    	</p>
			    	<p>
			    	<label>MONTO UNITARIO:</label><input type="text" id="montoGastoRel" name="montoGastoRel"  
			    		data-validation="gastrelval number" data-validation-allowing="float" />		    
			    	</p>		    		   	
		  		</fieldset>
		  		<p>
				  	<button type="submit" id="buttonGuardarGastosRel" class="myButton">Guardar</button>
				  	<font color="white">___</font>
				  	<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>							
				</p>
	  		</form>
	  	</div>	  	
	  	<div id="divGastosEvitados" style="display:none">		  	
			<form id="altagastosevitados" name="altagastosevitados" class="register" >	  							  	
				<fieldset class="row1">		
					<p>				   	
			   		<label>FECHA:</label><input type="text" id="fechaGastoEvitado" name="fechaGastoEvitado" readonly data-validation="gastevitadoval"/>		   
			    	</p>
			    	<p>
			    	<label>MONTO:</label><input type="text" id="montoGastoEvitado" name="montoGastoEvitado"  
			    		data-validation="gastevitadoval number" data-validation-allowing="float" />		    
			    	</p>
			    	<p>		    		   	
			    	<label>JUSTIFICACIÓN:</label>
			    	<textarea style="width: 408px;height: 126px;" id="justificacionGastoEvitado" name="justificacionGastoEvitado" onkeyup="javascript:this.value=this.value.toUpperCase();" data-validation="gastevitadoval"></textarea>       			    				    
			    	</p>
		  		</fieldset>
		  		<p>
				  	<button type="submit" id="buttonGuardarGastosEvitados" class="myButton">Guardar</button>
				  	<font color="white">___</font>
				  	<button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button>							
				</p>
	  		</form>
	  </div>
	  	<div id="test" style="display:none;">
	  	</div>
	  </div>
	  <div id="tabs-1">
	    <table id="tablaEdosCuenta" class="display" cellspacing="0" width="100%">
		 <thead>
            <tr>
                <th>Id Edo Cuenta</th>
                <th>Tipo Estado de Cuenta</th>
                <th>Fecha</th>
                <th>Monto</th>
                <th>Ver Archivo</th>
                <th>Eliminar</th>				                				               
            </tr>
    				</thead>
    				<tbody>
    				</tbody>
		</table>	
	  </div>
	  <div id="tabs-2">
	    <table id="tablaCargosObservados" class="display" cellspacing="0" width="100%">
		 <thead>
            <tr>
                <th>Id Gasto</th>
                <th>Fecha</th>
                <th>Producto</th>
                <th>Monto Unitario</th>
                <th>Cantidad</th>
                <th>Área</th>
                <th>Rubro</th>
                <th>Razón</th>				                				               
                <th>Ver Archivo</th>
                <th>Eliminar</th>
            </tr>
    		</thead>
    		<tbody>
    		</tbody>
		</table>
	  </div>
	  <div id="tabs-3">
	    <table id="tablaGastosRelevantes" class="display" cellspacing="0" width="100%">
		 <thead>
            <tr>
                <th>Id Gasto</th>
                <th>Fecha</th>
                <th>Tipo</th>
                <th>Monto</th>
                <th>Área</th>
                <th>Ver Archivo</th>
                <th>Eliminar</th>				                				               
            </tr>
    		</thead>
    		<tbody>
    		</tbody>
		</table>
	  </div>
	  <div id="tabs-4">
	    <table id="tablaGastosEvitados" class="display" cellspacing="0" width="100%">
		 <thead>
            <tr>
                <th>Id Gasto</th>
                <th>Fecha</th>                
                <th>Monto</th>
                <th>Justificación</th>                				                				              
                <th>Eliminar</th>
            </tr>
    		</thead>
    		<tbody>
    		</tbody>
		</table>
	  </div>
</div>	

<div id="divVersus" style="display:none" >
<label>RESULTADOS DEL COMPARATIVO (VERSUS):</label>
	<table id="tablaResultadoVersus" class="display" cellspacing="0" width="100%">
		<thead>
           <tr>
              <th># Renglón</th>
              <th>Código</th>
              <th>Fecha</th>
              <th>Producto</th>
              <th>Costo Lista de Precios</th>
              <th>Costo Estado de Cuenta</th>
              <th>Diferencia</th>
              <th>¿Gasto Observado?</th>
              <th>Área</th>
              <th>Rubro</th>				                				               
              <!--th style="display:none">Rubro</th-->
           </tr>
    	</thead>
    	<tbody>
    	</tbody>
	</table>
	Subtotales de productos encontrados en la lista de precios:
	<table>
		<tr>
			<td><div id="subTotalVersus"></div></td>
			<td><div id="desviosVersus"></div></td>
			<td><div id="DifTotalDesviosVersus"></div></td>
		</tr>
	</table>
	<button type="button" onclick="guardarVersus();" id="finalizarVersusButton" class="myButton">Finalizar Versus</button>
</div>				