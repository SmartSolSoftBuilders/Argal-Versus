<style>
    .ovh {overflow:hidden;}
    
  .modal2-open {
  	overflow: scroll; height:200px;
}
#veil {
	position: absolute;
	top: 0;
	left: 0;
	height:100%;
	width:100%;
	cursor: not-allowed;
	filter: alpha(opacity=60);
	opacity: 1.6;
	background: blue;
}
#feedLoading {
	position: absolute;
	top:200px;
	width:100%;
	text-align: center;
	font-size: 2em;
	color:black;
	text-shadow: 2px 2px 2px black;	
}
</style>
<br>

<div id=veil" ng-show="isLoading"></div>
<div id="feedLoading" ng-show="isLoading">CARGANDO, POR FAVOR ESPERE...</div>

<div ng-controller="EventoController" align="center" >
 		
	<!-- TABLA PARA ADMINISTRADORES E IMPLANTS-->
	<center>
		<table width="1000">
			<tr style="color: #fff;">
				<td style="color:#6688a6;background: #fff;" align="center" width="1000">EVENTOS REGISTRADOS</td>				
				<td style="color:#6688a6;background: #fff;" align="center" width="1000"><p>Buscar: <input class="form-control" type="text" ng-model="filter.$" onkeyup="javascript:this.value=this.value.toUpperCase();"/></p></td>
			</tr>
		</table>
	</center>	
  	<table width="1000" style="background: #D3E0EC;" class="table" ng-table="tableParamsEvento">
            <tr ng-repeat="evento in $data">
               	<td data-title="'IdEvento'" sortable="'idEvento'">
                    {{evento.idEvento}}
                </td>
                <td data-title="'Folio Hospital'" sortable="'folioHospital'">
                    {{evento.folioHospital}}
                </td>                
                <td ng-show="tipoUsuarioLogin!=2" data-title="'Folio Argal'" sortable="'Folio Argal'"  >
                    {{evento.folioArgal}}
                </td>                
                <td data-title="'Fecha Ingreso'" sortable="'fechaIngreso'">
                    {{evento.fechaIngreso | date:'shortDate'}}
                </td>
                <td ng-show="tipoUsuarioLogin!=2" data-title="'Cliente'" sortable="'cliente.nombreCorto'">
                    {{evento.cliente.nombreCorto}}
                </td>                
                <td data-title="'Hospital'" sortable="'hospital.nombreHospital'">
                    {{evento.hospital.nombreHospital}}
                </td>                                
                <td data-title="'Paciente'" >                    
                    {{evento.registroSeguroPersonal.nombrePaciente}} {{evento.registroSeguroPersonal.appPaciente}} {{evento.registroSeguroPersonal.apmPaciente}}	
                </td>
                <td  align ="center" ng-show="tipoUsuarioLogin==2" data-title="'Días Transcurridos'" sortable="'idEvento'">                
                    {{fecha(evento)}}
                </td>
                <td ng-show="tipoUsuarioLogin==2" data-title="'Estado Actual Paciente'" >
                    {{evento.bitacoras[(evento.bitacoras.length)-1].descripcion}}
                </td>
                <td data-title="'Estatus del Evento'" >
                    <div ng-show="evento.statusEvento==1">EN CURSO</div>
                    <div ng-show="evento.statusEvento==2">EGRESADO</div>
                    <div ng-show="evento.statusEvento==3">FINALIZADO</div>
                </td>
                <td ng-show="tipoUsuarioLogin==2" data-title="'Monto Gastos Actual'" >
                    {{evento.gastos[(evento.gastos.length)-1].montoUnitario | currency:"$"}}
                </td>                                                                                   
                <td ng-show="tipoUsuarioLogin!=2" width="50" data-title="'Editar Datos'" >
                    <center><img style=" cursor: pointer; cursor: hand;" src="static/img/editar.jpg" width="20" height="20" ng-click="openEventoById('modal2','EditarEventoController','views/evento/editar.jsp',evento.idEvento)"></img></center>
                </td>
                                
                <td ng-show="tipoUsuarioLogin!=2" width="50" data-title="'Control de Gastos'" >                
                    <center><img style=" cursor: pointer; cursor: hand;" src="static/img/gastos1.jpg" width="20" height="20" ng-click="openEventoById('modal2','CedulaControlController','views/evento/cedula_control/cedula_control.jsp',evento.idEvento)"></img></center>
                </td>
                <td ng-show="tipoUsuarioLogin==2" width="50" data-title="'Resumen de Gastos'" >                
                    <center><img style=" cursor: pointer; cursor: hand;" src="static/img/gastos1.jpg" width="20" height="20" ng-click="openEventoById('modal1','CedulaControlController','views/evento/cedula_control/cedula_control.jsp',evento.idEvento)"></img></center>
                </td>                                                               
                <td width="50" data-title="'Seguimiento Médico'" >
                    <center><img style=" cursor: pointer; cursor: hand;" src="static/img/segmedico1.jpg" width="20" height="20" ng-click="openEventoById('modal2','SeguimientoMedicoController','views/evento/seguimiento_medico/seguimiento_medico.jsp',evento.idEvento)"></img></center>
                </td>
                <td ng-show="tipoUsuarioLogin!=2" width="50" data-title="'Egresar Paciente'" >
                    <center><a style=" cursor: pointer; cursor: hand;" ng-click="openEventoById('modal2','AltaMedicaController','views/evento/alta_medica/alta_medica.jsp',evento.idEvento)"><img src="static/img/egresopaciente1.jpg" width="20" height="20"></img></a></center>
                </td>
                <td ng-show="tipoUsuarioLogin==2" width="50" data-title="'Datos de Egreso'" >
                    <center><a style=" cursor: pointer; cursor: hand;" ng-click="openEventoById('modal1','AltaMedicaController','views/evento/alta_medica/alta_medica.jsp',evento.idEvento)"><img src="static/img/egresopaciente1.jpg" width="20" height="20"></img></a></center>
                </td>
                
                <td ng-show="tipoUsuarioLogin!=2" width="50" data-title="'Finalizar Evento'"  >
                    <center><a style=" cursor: pointer; cursor: hand;" ng-click="openEventoById('modal2','AltaMedicaController','views/evento/finalizar_evento/finalizar_evento.jsp',evento.idEvento)"><img src="static/img/finalizarevento1.jpg" width="20" height="20"></img></a></center>
                </td>
                <td ng-show="tipoUsuarioLogin==2" width="50" data-title="'Balance Final'"  align="center">
                    <center><a style=" cursor: pointer; cursor: hand;" ng-click="openEventoById('modal2','AltaMedicaController','views/evento/finalizar_evento/finalizar_evento.jsp',evento.idEvento)"><img src="static/img/finalizarevento1.jpg" width="20" height="20"></img></a></center>
                </td>
            </tr>
        </table>
    
        <!-- TABLA PARA CLIENTES -->                                                                                                                     
   

	<!-- button class="buttonappstyle" ng-click="openViewEvento('modal1','CedulaControlController','views/evento/cedula_control/cedula_control.jsp')">CONTROL DE GASTOS</button> -->	    			
	<!--button class="buttonappstyle" ng-click="openViewEvento('modal1','SeguimientoMedicoController','views/evento/seguimiento_medico/seguimiento_medico.jsp')">SEGUIMIENTO MÉDICO</button> -->			
	<!--button class="buttonappstyle" ng-click="openViewEvento('modal1','PolizaController','views/evento/poliza/poliza.jsp')">DATOS DE LA POLIZA</button> -->
	<!--button class="buttonappstyle" ng-click="openViewEvento('modal1','AltaMedicaController','views/evento/alta_medica/alta_medica.jsp')">EGRESO DE PACIENTES</button> -->
	<!-- button class="buttonappstyle" ng-click="openViewEvento('modal2','AltaMedicaController','views/evento/finalizar_evento/finalizar_evento.jsp')">FINALIZAR EVENTO</button> --> 
    
</div>