
<table id="tablaGridMedicos" class="display" cellspacing="0" width="100%">
<thead>
     <tr>   
         <th>Nombre</th>
		 <th>Tipo</th>
		 <th>Especialidades</th>                  				                				              
         <th>Seleccionar</th>
     </tr>
</thead>
<tbody>
</tbody>
</table>

<br><a href="#" onclick="agregarMedTratShowFromEvento()"><img src="../../static/img/v2.0/img/agregarimplant.jpg" height="30" width="30" id="imgImplants2" name="imgImplants2"/>Nuevo Médico Tratante</a>
<div id="divAgregarMedicoTratanteEvento" title="Agregar Médico Tratante" style="display: none" align="center">					
	<jsp:include page="..\catalogos\alta_medico_tratante.jsp" />
</div>
			