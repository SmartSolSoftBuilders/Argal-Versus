<div id="loadFinalizarGif">
		<img src="static/img/v2.0/img/load2.gif" height="100" width="100" id="imgEgreso" name="imgEgreso"/>
		<h3>Por favor Espere...</h3>
</div>
<div id="divFinalizarMain">
<table>
	<tr>
		<td><div id="idEventoBarEgrBal"></div></td>
		<td><div id="titularEventoBarBal"></div></td>
		<td><div id="pacienteEventoBarBal"></div></td>	
		<!--  td><div id="tipoClienteEventoBar"></div></td-->
		<td><div id="clienteEventoBarBal"></div></td>
	</tr>
	<tr>
		<td></td>		
		<td><div id="numAutorizacionEventoBarBal"></div></td>				
		<td></td>
		<td></td>
	</tr>
</table>
<div id="dialog-eliminar-factura" title="¿Eliminar el evento?"  style="display: none" align="center">
  	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Se eliminará la factura, Continuar?</p>
</div>
<div id="tabsFinalizar">
	<input name="idEventoFinalizarHid" id="idEventoFinalizarHid" type="hidden" value=""/>
	<ul>
		<li><a href="#tabs-0">REGISTRAR FACTURA</a></li>
	    <li><a href="#tabs-1">FACTURAS DEL EVENTO</a></li>
	    <li><a href="#tabs-2">FINALIZAR MONTOS</a></li>	    
	  </ul>
	  <div id="tabs-0">
	  	<fieldset>		
			<legend>
				<img src="static/img/v2.0/img/factura-reg.png" width="40" height="30"/>INGRESE LOS DATOS PARA SUBIR UNA NUEVA FACTURA
			</legend>	
			<br>		
			<form name="form4" id="form4" method="post" action="mvc/cont/uploadFile" enctype="multipart/form-data">
				<div id="waitUpload" style="display:none">
				<div><h4>Subiendo Factura....</h4></div>
				<div><h4><img src="static/img/v2.0/img/loadingEvent.gif" /></h4></div>
				</div>
				<span style="color: #46C2BC;">SELECCIONE EL ARCHIVO PARA ADJUNTAR LA FACTURA:</span><BR>
				<input name="file4" id="file4" type="file" /><br/>
				<input name="idEventoHidden4" id="idEventoHidden4" type="hidden" value=""/>																											
			</form>
			<br>
			<form id="altaFacturaform" name="altaFacturaform" class="register" >
				<label>¿APROBADA?</label>
				<select id="aprobadaFacturaSubir" onchange="showTipoFacturaDiv()">
							<option value="SI">SI</option>
							<option value="NO">NO</option>
				</select>
				<div id="facturaAprobadaDiv" >
				<p>					
					<label>NÚMERO DE FACTURA:</label><input type="text" id="numeroFacturaAprob" name="numeroFacturaAprob" data-validation="facturaval"/>		    
					<label>MONTO:</label><input type="text" id="montoFacturaAprob" name="montoFacturaAprob" data-validation="facturaval"/>
					<label>OBSERVACIONES:</label><textarea size="300" id="obsFacturaAprob" name="obsFacturaAprob" >
					</textarea>
				</p>
				</div>									
				<div id="facturaNoAprobadaDiv" style="display:none">
				<p>					
					<label>NÚMERO DE FACTURA:</label><input type="text" id="numeroFacturaNoAprob" name="numeroFacturaNoAprob" data-validation="facturaval2"/>		    
					<label>MONTO:</label><input type="text" id="montoFacturaNoAprob" name="montoFacturaNoAprob" data-validation="facturaval2"/>
					<label>OBSERVACIONES:</label><textarea size="300" id="obsFacturaNoAprob" name="obsFacturaNoAprob" data-validation="facturaval2">
					</textarea>
				</p>					
				<p>
					<label>AJUSTE FACTURA:</label><input type="text" id="ajusteFacturaNoAprob" name="ajusteFacturaNoAprob" data-validation="facturaval2"/>
					<label>TIPO DE COMPROBANTE FISCAL CORREGIDO:</label><input type="text" id="tipoCompFacturaNoAprob" name="tipoCompFacturaNoAprob" data-validation="facturaval2"/>
				</p>
				<p>
					<label>FOLIO COMPROBANTE FISCAL CORREGIDO:</label><input type="text" id="folioCompFacturaNoAprob" name="folioCompFacturaNoAprob" data-validation="facturaval2"/>
					<label>MONTO COMPROBANTE FISCAL CORREGIDO:</label><input type="text" id="montoCompFacturaNoAprob" name="montoCompFacturaNoAprob" data-validation="facturaval2"/>
				</p>
				</div>
				<button type="submit" id="buttonGuardarFact" class="myButton">Guardar</button>
				<font color="white">___</font>
				<button type="submit"  class="myButton">Cancelar</button>									 				
			</form>					
		</fieldset>
	  </div>
	  <div id="tabs-1">	  	
	  <div>
	  	<fieldset>
	  	<legend>
	  		<img src="static/img/v2.0/img/factura-aprob.png" width="60" height="40"/>FACTURAS APROBADAS
	  	</legend>	  	
		    <table id="facturasAprobadasTabla" class="display" cellspacing="0" width="50%">
				<thead>
		            <tr>
		                <th>No. FACTURA</th>
		                <th>MONTO</th>	                				                				              
		                <th></th>
		                <th>ELIMINAR</th>
		            </tr>
		    	</thead>
		    	<tbody>
		    	</tbody>
			</table>
		</fieldset>
		</div>		
		<div>
		<fieldset>
	  	<legend><img src="static/img/v2.0/img/factura-rech.png" width="60" height="40"/>FACTURAS RECHAZADAS	  	
	  	</legend>
		<table id="facturasRechazadasTabla" class="display" cellspacing="0" width="50%">
			<thead>
	            <tr>
	                <th>No. FACTURA</th>
	                <th>MONTO</th>	    
	                <th></th>
		            <th>ELIMINAR</th>            				                				              
	            </tr>
	    	</thead>
	    	<tbody>
	    	</tbody>
		</table>
		</fieldset>
		</div>		
	  </div>
	  <div id="tabs-2">
	  	<div id="jubiladoCenso">Jubilado con Censo
	  		<br>Capita:<select id="capitaFinalizarEvento" name="capitaFinalizarEvento">
	  					<option value="">Seleccione...</option>
	  					<option>CH</option>
	  					<option>NA</option>
	  					<option>CH/NA</option>
	  					<option>CH/NC</option>
	  					<option>CH/NA/NC</option>
	  					<option>NC/NA</option>			  			 
	  			</select>
	  		Cantidad Cubierta por Convenio:<input type="text" id="capitaCantidadFinalizarEvento" name="capitaCantidadFinalizarEvento">
	  	</div>
	  	<fieldset>	  		  		
	  		<form id="finalizarEventoform" name="finalizarEventoform" class="register">	  			  				
	  		<table width="900px" border="1" align="center">
				<tr>
					<td align="center" bgcolor="#46C2BC">
						<img src="static/img/v2.0/img/finalizarevento1.jpg" width="40" height="30"/>
						<span style="color: #ffffff;">FINALIZAR MONTOS DEL EVENTO</span>
					</td>    						
				</tr>
			</table>
			<table width="900px" border="1" align="center">
				<tr>								
					<td width="100px"><span style="color: black;">MONTO ANTES DE DESVÍOS:</span></td>
					<td width="150"><div id="divMontoAntesDesvios"></div></td>
					<td width="100px"><span style="color: black;"># FACTURAS APROBADAS:</span></td>
					<td width="150"><div id="divFacturasAprobadas"></div></td> 				        				  
				</tr>
				<tr>			
					<td><span style="color: black;">TOTAL CARGOS OBSERVADOS:</span></td>			
					<td><div id="divTotalCargosObservados"></div></td>
					<td width="100px"><span style="color: black;"># FACTURAS RECHAZADAS:</span></td>
					<td width="150"><div id="divFacturasRechazadas"></div></td>			
				</tr>	
				<tr>				
					<td><span style="color: black;">DESVÍOS COMPROBADOS:</span></td>			
					<td><input type="text" id="desviosComprobados" name="desviosComprobados" value="0" data-validation="finalizareventoval"></td>
					<td><span style="color: black;">MONTO TOTAL DESVÍOS FACTURACIÓN:</span></td>			
					<td><div id="divMontoTotalDesviosFacturacion"></div></td>		   					 					 												
				</tr>
				<tr>								
					<td width="100px"><span style="color: black;">MONTO DESPUÉS DE DESVÍOS:</span></td>
					<td width="150"><div id="divMontoDespuesDesvios"></div></td>
					<td><span style="color: black;">MONTO AJUSTE FACTURACIÓN: $</span></td>
					<td><input type="text" id="montoAjusteFacturacion" name="montoAjusteFacturacion" data-validation="finalizareventoval"></td>			
				</tr>	
				<tr>				
					<td><span style="color: black;">IVA: $</span></td>
					<td><input type="text" id="ivaFinalizarEvento" name="ivaFinalizarEvento" value="0" data-validation="finalizareventoval"></td>
					<td><span style="color: black;">MONTO FACTURACIÓN CORREGIDO: $</span></td>
					<td><input type="text" id="montoFacturacionCorregido" name="montoFacturacionCorregido" data-validation="finalizareventoval"></td>
				</tr>
				<tr>				
					<td><span style="color: black;">COASEGURO: (%)</span></td>	 		
					<td><input type="text" id="coaseguroFinalizarEvento" name="coaseguroFinalizarEvento" value="0" data-validation="finalizareventoval"></td>
					<td><span style="color: black;">MONTO FINAL FACTURACIÓN:</span></td>
					<td><div id="divMontoFinalFacturacion"></div></td>
				</tr>
				<tr>				
					<td><span style="color: black;">DEDUCIBLE: $</span></td>			
					<td><input type="text" id="deducibleFinalizarEvento" name="deducibleFinalizarEvento" data-validation="finalizareventoval" value="0"></td>
					<td></td>
					<td><button type="submit" id="buttonGuardarFact" class="myButton">FINALIZAR</button></td>					
							   					 					 									
				</tr>
				<tr>
					<td><span style="color: black;">DESCUENTOS HOSPITAL: $</span></td>
					<td><input type="text" id="descuentoHospFinalizarEvento" name="descuentoHospFinalizarEvento" data-validation="finalizareventoval"></td>
					<td></td>
					<td><button type="button"  class="myButton" onclick="cerrarVentana();">Cancelar</button></td>						
				</tr>										
				</table>	  
	  	</form>	  
	  </fieldset>
	  </div>
</div>					

</div>