<style>
.ng-table-rowselected tr {
    cursor: pointer;
}
</style>
<br>
<table width="1180" heigth="50px" border="0">
	<tr>
		<td width="1180" height="23" align="center" ><img src="static/img/implant.jpg" height="30" width="30"/> ADMINISTRACIÓN DE IMPLANTS
	    <hr color="#6688a6" /></td>
	</tr>	
</table>
<div ng-controller="ImplantController" align="center">
	<table width="919px">
	 <tr><td> Buscar: <input ng-model="searchText"></td><td align="right">Agregar<a style=" cursor: pointer; cursor: hand;" ng-click="openEditarImplant2('','views/implant/alta.jsp')"><img src="static/img/agregarimplant.jpg" height="80" width="80"/></a>
	 </td></tr>
	</table>
  	<table width="1019px" class="table ng-table-rowselected" border="3" ng-table="tableParams" show-filter="true">
            <tr ng-repeat="user in $data | filter:searchText"
                ng-click="user.$selected = !user.$selected; changeSelection(user)"
                ng-class="{'active': user.$selected}" >
                <td data-title="'Nombre'" sortable="'nombreImplant'">
                    {{user.nombreImplant}} {{user.appImplant}} {{user.apmImplant}}
                </td>
                <td data-title="'Nextel'" sortable="'nextelImplant'">
                    {{user.nextelImplant}}
                </td>
                <td data-title="'Id Nextel'" sortable="'idNextelImplant'">
                    {{user.idNextelImplant}}
                </td>
                <td data-title="'Celular'" sortable="'celularImplant'">
                    {{user.celularImplant}}
                </td>
 				<td data-title="'Email Inst'" sortable="'emailInstImplant'">
                    {{user.emailInstImplant}}
                </td>
                 <td data-title="'Nick Implant'" sortable="'emailInstImplant'">
                    {{user.nickImplant}}
                </td> 
                <td data-title="">
                	<a ng-click="openEditarImplant(user,'','views/implant/editar.jsp')"><img src="static/img/editar.jpg" height="20" width="20"/></a>
                </td>
                <td data-title="">
                	<a ng-click="eliminarDesactImplant(user)"> 
                		<img height="20" width="20" src="static/img/borrar.jpg"/>
                	</a>
                </td>
                
            </tr>
        </table>
        <!--  table>
		    <tr>   
				<td>   
					<button class="btn btn-default" ng-click="openEditarImplant('','views/implant/editar.jsp')">Editar</button>	           
				</td>
				<td>
					<button class="btn btn-default" ng-click="eliminarDesactImplant()"><img height="20" width="20" src="static/img/borrar.jpg"/></button>
				</td>
			</tr>
		</table>-->		
        
	<script type="text/ng-template" id="eliminardesacimpldialog.html">
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