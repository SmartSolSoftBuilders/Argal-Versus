<style>
	.modal2 { 	
		overflow: scroll; 
		height:650px;
	}

.ng-table-rowselected tr {
    cursor: pointer;
}
</style>
<br>
<table width="1180" heigth="50px" border="0">
	<tr>
		<td width="1180" height="23" align="center" ><img src="static/img/seguridad2.jpg" height="30" width="30"/> PERMISOS DE IMPLANTS PARA HOSPITALES
	    <hr color="#6688a6" /></td>
	</tr>
</table>
<div ng-controller="PermisosController">
	<form  name="selectpermisosform"  class="css-form">
	 	SELECCIONE UN IMPLANT:
	 	<select id="implantSel" ng-model="implantSel" ng-options="implant.nombreImplant +' '+ implant.appImplant for implant in implants"  ng-change="updateHospImplants()">
		 	<option value="">Seleccione un implant...</option>
	 	</select>
	<br/> 	
   	<h5>HOSPITALES DONDE EL IMPLANT TIENE ACCESO:</h5>   
	<table ng-show="implantSel.idImplant>0" width="719px">
		 <tr><td> Buscar: <input ng-model="searchText"></td><td align="right"><a style=" cursor: pointer; cursor: hand;" ng-click="openEditarPermisosImplant('','views/permisos/hospitales_gen.jsp')">Agregar Permisos<img src="static/img/permiso.jpg" height="35" width="35"/></a>
 	</td></tr></table>
  	<table width="719px" class="table ng-table-rowselected" border="3" ng-table="tableParams" show-filter="true">    
            <tr ng-repeat="hosp in $data | filter:searchText"
                ng-click="hosp.$selected = !hosp.$selected; changeSelection(hosp)"
                ng-class="{'active': hosp.$selected}" >
                <td data-title="'IdHospital'" sortable="'idHospital'">
                    {{hosp.idHospital}}
                </td>
                <td data-title="'Nombre'" sortable="'nombreHospital'">
                    {{hosp.nombreHospital}}
                </td>
                <td data-title="'Estado'" sortable="'estadoHospital'">
                    {{hosp.estadoHospital}}
                </td>
                <td data-title="'Municipio'" sortable="'municipioDelHospital'">
                    {{hosp.municipioDelHospital}}
                </td>
 				<td data-title="'Teléfono Módulo'" sortable="'telModuloHospital'">
                    {{hosp.telModuloHospital}}
                </td>
                <td data-title="">
                	<a ng-click="eliminarPermisoHospImplant()"> 
                		<img height="20" width="20" src="static/img/borrar.jpg"/>
                	</a>
                </td>
                
            </tr>
        </table>
	 	
	 	
	<!--  smart-table class="table table-striped" table-title="Smart Table" config="globalConfig" columns="columnCollection" rows="rowCollection" ></smart-table-->
    	<!-- div ng-controller="MainCtrl" >    
			<button class="btn btn-default" ng-click="openEditarPermisosImplant('','views/permisos/hospitales_gen.jsp')">Agregar Permiso</button>
		    <button ng-click="eliminarPermisoHospImplant()">Eliminar</button>        
		</div -->        
	</form>
	
	<script type="text/ng-template" id="dialogaltapermiso.html">
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