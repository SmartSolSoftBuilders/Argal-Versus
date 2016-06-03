<style>
.ng-table-rowselected tr {
    cursor: pointer;
}
</style>
<br>
<table width="1180" heigth="50px" border="0">
	<tr>
		<td width="1180" height="23" align="center" ><img src="static/img/cliente.png" height="30" width="30"/>Administración de Clientes
	    <hr color="#6688a6" /></td>
	</tr>
</table>

<div ng-controller="ClienteController" align="center">
	
	<table width="719px">
 <tr><td> Buscar: <input ng-model="searchText"></td><td align="right">Agregar<a style=" cursor: pointer; cursor: hand;" ng-click="openEditarCliente2('','views/cliente/alta.jsp')"><img src="static/img/agregarimplant.jpg" height="80" width="80"/></a>
 </td></tr></table>
  
  	<table width="919px" class="table ng-table-rowselected" border="3" ng-table="tableParams" show-filter="true">
            <tr ng-repeat="cliente in $data | filter:searchText"
                ng-click="cliente.$selected = !cliente.$selected; changeSelection(cliente)"
                ng-class="{'active': cliente.$selected}" >
                <td data-title="'IdCliente'" sortable="'idCliente'">
                    {{cliente.idCliente}} 
                </td>
                <td data-title="'Nombre Corto'" sortable="'nombreCorto'">
                    {{cliente.nombreCorto}}
                </td>
                <td data-title="'Contacto'" sortable="'nombreCliente'">
                    {{cliente.nombreCliente}}
                </td>
                <td data-title="'Teléfono Oficina'" sortable="'telOfiCliente'">
                    {{cliente.telOfiCliente}}
                </td>
                <td data-title="'Email'" sortable="'emailCliente'">
                    {{cliente.emailCliente}}
                </td>
                	
                <td data-title="">
                	<a ng-click="openEditarCliente(cliente,'','views/cliente/editar.jsp')"><img src="static/img/editar.jpg" height="20" width="20"/></a>
                </td>
                <td data-title="">
                	<a ng-click="eliminarDesactCliente()"> 
                		<img height="20" width="20" src="static/img/borrar.jpg"/>
                	</a>
                </td>
                
            </tr>
        </table>
	
	
	<!-- form  name="selectclientesform"  class="css-form">
    <smart-table class="table table-striped smart-table" table-title="Smart Table" config="globalConfig" columns="columnCollection" rows="rowCollection" ></smart-table>
    <table>
	    <tr>   
			<td>    				
				<button class="btn btn-default" ng-click="openEditarCliente('','views/cliente/editar.jsp')">Editar</button>	           
			</td>
			<td>
				<button class="btn btn-default" ng-click="eliminarDesactCliente()">Eliminar</button>
			</td>
		</tr>
	</table>
	</form -->        
	
	<script type="text/ng-template" id="eliminardesacclientedialog.html">
	  <div class="modal-header">
            <h3>Notificación</h3>
        </div>
        <div class="modal-body">
            {{mensajeDialogoAltaImplant}}
        </div>
        <div class="modal-footer">            
            <button class="btn btn-warning" ng-click="cancelImplForm()">Cerrar</button>
        </div>
    </script> 
    
</div>