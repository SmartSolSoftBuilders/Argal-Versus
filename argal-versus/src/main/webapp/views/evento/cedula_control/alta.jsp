
<center>
	<br><strong><span class="mensajeTitulo">{{message}}</span></strong><br>
	<br><span class="mensajeTitulo">Por favor llene el siguiente formulario:</span>
	
</center> 
<div ng-controller="EstadoCuentaController">
	<table border=0>
		<tr>		
			<td>ARGAL IMPLANT:</td> 
			<td>{{nombreLogin}}</td>
			<td>HOSPITAL: {{hospitalSeleccionado}}</td>
			<td>
			<div ng-controller="MainCtrl" ><button ng-click="launchSimpleModal('showgridhosp','Seleccione un Hospital','views/hospital/gridhosp.jsp')">Otro...</button>
			</div>
			</td>
		</tr>		  		  	  	  		
	</table>	   		  	   	    	  				  	   
</div>    
    