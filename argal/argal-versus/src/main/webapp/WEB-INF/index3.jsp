<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<!--link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/media/images/favicon.ico" /-->
		
		<title>FAM</title>
		<style type="text/css" title="currentStyle">
			@import "static/js/libs/css/demo_page.css";
			@import "static/js/libs/css/demo_table.css";
			@import "static/js/libs/css/jquery-ui-1.9.2.custom.css";
		</style>
		<script type="text/javascript" language="javascript" src="static/js/libs/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" language="javascript" src="static/js/libs/js/jquery.custom.js"></script>
		<script type="text/javascript" language="javascript" src="static/js/libs/js/jquery.dataTables.js"></script>
		
		<link rel="stylesheet" type="text/css" href="static/js/libs/css/jMenu.jquery.css" media="screen" />
 
		<!-- jQuery files -->
		<script type="text/javascript" src="static/js/libs/js/jMenu.jquery.js"></script>		

		<script type="text/javascript" charset="utf-8">
		 $(document).ready(function(){
			    // simple jMenu plugin called
			    $("#jMenu").jMenu();
			 
			    // more complex jMenu plugin called
			    $("#jMenu").jMenu({
			      ulWidth : 'auto',
			      effects : {
			        effectSpeedOpen : 300,
			        effectTypeClose : 'slide'
			      },
			      animatedText : false
			    });
			  });			
		</script>
	</head>
	<body id="dt_example">
	<!-- First, write your HTML -->
<ul id="jMenu">
  <li><a class="fNiv">Afiliado</a><!-- Do not forget the "fNiv" class for the first level links !! -->
    <ul>
      <li class="arrow"></li>
      <li><a>Registrar Afiliado</a></li>
      <li><a>Mantenimiento Afiliados</a></li>             
      <li><a>Carga Masiva Afiliados</a></li>
      <li><a>Aprobar Cambio Afiliados</a></li>
    </ul>    
  </li>
  <li><a class="fNiv">P&oacute;liza Colectiva</a><!-- Do not forget the "fNiv" class for the first level links !! -->
    <ul>
      <li class="arrow"></li>
      <li><a>Corregir Datos Certificado</a></li>
      <li><a>Mantenimiento de Certificados</a></li>             
      <li><a>Aprobar Cambios Certificados</a></li>      
    </ul>    
  </li>
  <li><a class="fNiv">Productos</a><!-- Do not forget the "fNiv" class for the first level links !! -->
    <ul>
      <li class="arrow"></li>
      <li><a>FAM Ahorro</a></li>                  
      <li><a>CrediFAM</a></li>      
    </ul>    
  </li>
  <li><a class="fNiv">Cat&aacute;logos</a><!-- Do not forget the "fNiv" class for the first level links !! -->
    <ul>
      <li class="arrow"></li>
      <li><a>Corregir Datos Certificado</a></li>
      <li><a>Mantenimiento de Certificados</a></li>             
      <li><a>Aprobar Cambios Certificados</a></li>      
    </ul>    
  </li>
</ul>
	</body>
</html>