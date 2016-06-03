
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<style>
	.modal2 { 	
		overflow: scroll; 
		height:650px;
	}
</style>
<table heigth="50px" border="0">
	<tr>
		<td bgcolor="#6688a6" width="1180" height="23" align="center" ><img src="static/img/hospital.jpg" height="30" width="30"/> <span style="color:white"> Hospitales asignados al Implant</span>
	   </td>
	    </tr><tr><td></td>
	</tr>
</table>

<div ng-controller="HospitalController" align="center">
<table width="719px">
 <tr><td style="color:#6688a6;background: #fff;" align="center" width="1000"><p>Buscar: <input class="form-control" type="text" ng-model="filter.$" /></p></td><td align="right">
 </td></tr></table>
  
  	<table width="719px" class="table ng-table-rowselected" border="3" ng-table="tableParamsHosp" show-filter="true">
            <tr ng-repeat="hosp in $data"
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
                	<a style="cursor:pointer;" ng-click="closeShowHosp(hosp.idHospital,hosp.nombreHospital)">Seleccionar</a>
                </td>                
            </tr>
        </table>
    	
      

	<script type="text/ng-template" id="eliminardesachospdialog.html">
	  <div class="modal-header">
            <h3>Notificación</h3>
        </div>
        <div class="modal-body">
            {{mensajeDialogoAltaHosp}}
        </div>
        <div class="modal-footer">            
            <button class="btn btn-warning" ng-click="cancelHospForm()">Cerrar</button>
        </div>
    </script> 
    
</div>