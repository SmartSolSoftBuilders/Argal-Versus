<style>
    .ovh {overflow:hidden;}
</style>
<br>

<div ng-controller="EventoController" align="center">
	<center>
	<table width="1000">
		<tr>
			<td align="center" width="1000">EVENTOS</td>
		</tr>
	</table>
	</center>
	<table width="1000">
		<tr>
			<td width="1000">					
    			<smart-table class="table table-striped" table-title="Smart Table" config="globalConfigEventos" columns="columnCollectionEventos" rows="rowCollectionEventos" ></smart-table>
   		 		<!-- div ng-controller="MainCtrl" >    
					<button ng-click="launchSimpleModal('editarevento','Editar Evento','views/evento/editar.jsp')">Editar</button>
	    			<button ng-click="eliminarEvento(evento)">Eliminar</button>        
				</div> -->				 	      
    		</td>
		</tr>
	</table>    

	<button class="buttonappstyle" ng-click="openViewEvento('modal1','CedulaControlController','views/evento/cedula_control/cedula_control.jsp')">CONTROL DE GASTOS</button>	    			
	<button class="buttonappstyle" ng-click="openViewEvento('modal1','SeguimientoMedicoController','views/evento/seguimiento_medico/seguimiento_medico.jsp')">SEGUIMIENTO MÉDICO</button>			
	<button class="buttonappstyle" ng-click="openViewEvento('modal1','PolizaController','views/evento/poliza/poliza.jsp')">DATOS DE LA POLIZA</button>
	<button class="buttonappstyle" ng-click="openViewEvento('modal1','AltaMedicaController','views/evento/alta_medica/alta_medica.jsp')">EGRESO DE PACIENTES</button>
	<button class="buttonappstyle" ng-click="openViewEvento('modal2','AltaMedicaController','views/evento/finalizar_evento/finalizar_evento.jsp')">FINALIZAR EVENTO</button> 
    
</div>