<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<div ng-controller="LayoutController" align="center">
<table width="1190px" border=0>
	<tr>
		<td width="1000" height="23" align="center" ><img src="static/img/reporte1.jpg" height="30" width="30"/> DESCARGA DE LAYOUT
	    </td>
	</tr>
</table>

<table width="890" border="1">
	<tr>
		<td width="890" align="center" bgcolor="#6688a6"><span style="color: #ffffff;">Generar Layout:</span></td>
	</tr>
</table>				
<table width="880" border=2>																	
		<tr>
			<td><span style="color: #6688a6;">TIPO DE EVENTO:</span></td>
			<td>
				<select ng-model="tipoEvento" id="tipoEvento" name="tipoEvento" required>
					<option value="">Seleccione...</option>
					<option value="1">BANCO</option>
					<option value="2">ASEGURADORA</option>
				</select>
			</td>
			<td><span style="color: #6688a6;">¿EVENTOS FINALIZADOS?:</span></td>
			<td><input type="checkbox" ng-model="finalizado" /></td>
			</tr>
			<tr>
			<td><span style="color: #6688a6;">FECHA DE INICIO:</span></td>
			<td>
		        <input type="text" ng-click="open($event,'opened1')" class="form-control" datepicker-popup="{{format}}" ng-model="dt" name="dt" id="dt" is-open="opened1" min="minDate" datepicker-options="dateOptions" date-disabled="disabled(date, mode)"  close-text="Close" />				        				   
			</td>
			<td><span style="color: #6688a6;">FECHA DE FIN:</span></td>
			<td>
		        <input type="text" ng-click="open($event,'opened2')" class="form-control" datepicker-popup="{{format}}" ng-model="dt2" name="dt2" id="dt2" is-open="opened2" min="minDate" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" close-text="Close" />				        				   
			</td>		  	
			
		</tr>		
	</table>
	<!-- button ng-show="sendFile!=1" ng-click="ajax_download()">Generar</button -->														  	
	<button class="btn btn-primary" ng-click="ajax_download()" >GENERAR</button>
</div>