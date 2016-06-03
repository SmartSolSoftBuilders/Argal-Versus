<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<div ng-controller="PasswordController" align="center">
<table width="1190px" border=0>
	<tr>
		<td width="1000" height="23" align="center" ><img src="static/img/reporte1.jpg" height="30" width="30"/> Cambiar Password
	    </td>
	</tr>
</table>

<table width="890" border="1">
	<tr>
		<td width="890" align="center" bgcolor="#6688a6"><span style="color: #ffffff;">Ingresa el nuevo password:</span></td>
	</tr>
</table>
<table width="880" border=2>																	
		<tr>			
			<td>
		        Nuevo Password:<input type="password" required ng-model="password"/>				        				   
			</td>		  				
		</tr>		
	</table>
	<!-- button ng-show="sendFile!=1" ng-click="ajax_download()">Generar</button -->														  	
	<button class="btn btn-primary" ng-click="enviarPassword()" >Enviar</button>
</div>
