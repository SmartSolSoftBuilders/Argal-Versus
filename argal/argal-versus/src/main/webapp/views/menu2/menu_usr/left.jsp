<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<!--link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/media/images/favicon.ico" /-->		
<title>FAM</title>

<script type="text/javascript" language="javascript" src="../../../static/js/libs/js/jquery-1.9.1.js"></script>
<script type="text/javascript" language="javascript" src="../../../static/js/libs/js/jquery.custom.js"></script>

	<link rel="stylesheet" href="../../../static/js/libs/css/jquery.treeview.css" />
	<link rel="stylesheet" href="../../../static/js/libs/css/screen.css" />	
	
	<script src="../../../static/js/libs/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="../../../static/js/libs/js/jquery.treeview.js" type="text/javascript"></script>	
	<script type="text/javascript" src="../../../static/js/libs/js/demo.js"></script>
	
	<script language="javascript">
		jQuery(document).ready(function(){
				
		});
		
		function openFrame(url){
			top.frames['mainFrame'].location="../"+url+".jsp"; 
		}
		function openFrameParams(url,param){
			top.frames['mainFrame'].location="../../"+url+".jsp?"+"id="+param; 
		}
		function cursor(a) { 
		    if (navigator.appName=="Netscape") { 
		        a.style.cursor='pointer'; 
		    } else { 
		        a.style.cursor='hand'; 
		    } 
		} 
	</script>
		

<body>
	<p>&nbsp;</p>
	<div align="center">
		<p><img src="../../../static/img/1.png" width="115" height="72"/>    </p>
	</div>
	<h2>Usuario:<%=request.getSession().getAttribute("usuarioAfiliado")%></h2>
	<h4>Men&uacute; Principal</h4>
	<ul id="browser" class="filetree">	
		<li><span class="folder">Mis datos de Afiliado</span>
			<ul>				
				<li><span class="file" onMouseOver="cursor(this)" onclick="openFrameParams('afiliados/modificaafiliado',<%=request.getSession().getAttribute("idAfiliado")%>)">Mantenimiento de datos</span></li>								
			</ul>
		</li>
		<li><span class="folder">P&oacute;liza Colectiva</span>
			<ul>
				<li><span class="file" onMouseOver="cursor(this)" onclick="openFrameParams('certificados/editarcertificado',<%=request.getSession().getAttribute("idCertificadoAfiliado")%>)">Corregir datos del Certificado</span></li>
				<li><span class="file" onMouseOver="cursor(this)" onclick="openFrameParams('certificados/contenidocertificado',<%=request.getSession().getAttribute("idCertificadoAfiliado")%>)">Mantenimiento del Certificado</span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder">Productos</span>
			<ul>
				<li class="closed"><span class="folder">FAMAhorro</span>					
					<ul><span class="file" onMouseOver="cursor(this)" onclick="openFrameParams('famahorro/movimientos',<%=request.getSession().getAttribute("idAfiliado")%>)">Movimientos FAMAhorro</span></ul>
				</li>
				<li class="closed"><span class="folder">CrediFAM</span>
				</li>
			</ul>
		</li>		
	</ul>		
	<br>
	<br>
	<center><a href="#" onclick="openFrame('../catalogos/cambio_cont')">Cambiar Contraseña</a></center>
</body>
</html>