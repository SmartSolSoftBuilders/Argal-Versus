<table>
	<tr>
		<td>Id Evento:<c:out value="${datosCliente.pContrato}"/></td>
		<td>Titular:<c:out value="${datosCliente.pContrato}"/></td>
		<td>Paciente:<c:out value="${datosCliente.pContrato}"/></td>
	</tr>
	<tr>
		<td>TipoCliente:<c:out value="${datosCliente.pContrato}"/></td>
		<td>Cliente:<c:out value="${datosCliente.pContrato}"/></td>
		<td>No.Autorización:<c:out value="${datosCliente.pContrato}"/></td>
	</tr>
	<tr>
		<td>Total Estado de Cuenta:<c:out value="${datosCliente.pContrato}"/></td>
		<td>Total Cargos Observados:<c:out value="${datosCliente.pContrato}"/></td>
		<td>Total Gastos Relevantes:<c:out value="${datosCliente.pContrato}"/></td>
	</tr>
</table>
<div id="tabs">
	<ul>
	    <li><a href="#tabs-1">ESTADOS DE CUENTA</a></li>
	    <li><a href="#tabs-2">GASTOS OBSERVADOS</a></li>
	    <li><a href="#tabs-3">GASTOS RELEVANTES</a></li>
	  </ul>
	  <div id="tabs-1">
	    <table id="tablaEdosCuenta" class="display" cellspacing="0" width="100%">
		 <thead>
            <tr>
                <th>Id Edo Cuenta</th>
                <th>Tipo Estado de Cuenta</th>
                <th>Fecha</th>
                <th>Monto</th>				                				               
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
            </tr>
    		</thead>
    		<tbody>
    		</tbody>
		</table>
	  </div>
</div>					