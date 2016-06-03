<style>
.modal1 { 	
		overflow: scroll; 
		height:650px;
	}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<table heigth="50px" border="0">
	<tr>
		<td bgcolor="#6688a6" width="1180" height="23" align="center" ><img src="static/img/cptlist.png" height="30" width="30"/> <span style="color:white"> Seleccione un CPT</span>
	   </td>
	    </tr><tr><td></td>
	</tr>
</table>

<div ng-controller="Cat_Cpt_Controller">
<table> <tr><td style="color:#6688a6;background: #fff;" align="center" width="1000"><p>Buscar: <input class="form-control" type="text" ng-model="filter.$" /></p></td>
</tr></table>
<table width="719px" class="table ng-table-rowselected" border="3" ng-table="tableParamsCPT" show-filter="true">    
            <tr ng-repeat="cpt in $data"
                ng-click="cpt.$selected = !cpt.$selected; changeSelection(cpt)"
                ng-class="{'active': cpt.$selected}" >
                <td data-title="'Idcpt'" sortable="'idcpt'">
                    {{cpt.idCpt}}
                </td>
                <td data-title="'Nombre'" sortable="'descripcion'">
                    {{cpt.descripcion}}
                </td>
                <td data-title="'Clave'" sortable="'clave'">
                    {{cpt.cveCpt}}
                </td>
                <td data-title="">
                	<a style="cursor: pointer;" ng-click="selectCpt1(cpt.idCpt,cpt.descripcion)"> 
                		Seleccionar
                	</a>
                </td>
                
            </tr>
        </table>
</div>
   