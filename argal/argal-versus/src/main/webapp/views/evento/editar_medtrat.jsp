<style>
.ng-table-rowselected tr {
    cursor: pointer;
}
</style>
<br>
<table width="1180" heigth="50px" border="0">
	<tr>
		<td width="1180" height="23" align="center" ><img src="static/img/medtratante.jpg" height="30" width="30"/>ADMINISTRACIÓN DE MÉDICOS TRATANTES
	    <hr color="#6688a6" /></td>
	</tr>
</table>

<div ng-controller="MedicoTratanteController" align="center">
	
<table width="719px">
 <tr><td> Buscar: <input ng-model="searchText"></td><td align="right"><a ng-click="openEditarMedTrat('','views/evento/altamedtrat.jsp')" style=" cursor: pointer; cursor: hand; ">Agregar<img src="static/img/agregarimplant.jpg" height="80" width="80"/></a>
 </td></tr></table>
  
  	<table width="719px" class="table ng-table-rowselected" border="3" ng-table="tableParams" show-filter="true">
            <tr ng-repeat="medtrat in $data | filter:searchText"
                ng-click="medtrat.$selected = !cliente.$selected; changeSelection(medtrat)"
                ng-class="{'active': medtrat.$selected}" >
                <td data-title="'IdMedicoTratante'" sortable="'idMedicoTratante'">
                    {{medtrat.idMedicoTratante}} 
                </td>
                <td data-title="'Nombre'" sortable="'nombre'">
                    {{medtrat.nombre}}
                </td>
                <td data-title="'Tipo'" sortable="'tipo'">
                	<span ng-show="medtrat.tipo==1">RED</span>
                	<span ng-show="medtrat.tipo==2">NO RED</span>
                	<span ng-show="medtrat.tipo==3">INTERINO RED</span>
                </td>
                <td data-title="'Especialidades'" >
                <div ng-repeat="esp in medtrat.especialidades">
                	{{esp.descripcion}}, 
                </div>    
                </td>
                <td data-title="">
                	<a ng-click="eliminarMedicoTratante(medtrat.idMedicoTratante)"> 
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