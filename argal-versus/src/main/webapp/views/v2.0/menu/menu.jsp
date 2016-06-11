<ul id="menu">
	<li>
		<img src="static/img/v2.0/img/logo_argal.png" width="80" height="50" />		
	</li>	
	<li>Bienvenido:<%=request.getSession().getAttribute("userSession")%><br><a style="color: #ffffff;" class="logout" href="logout">Cerrar sesión</a></li>
		<li><a href="#"><img src="static/img/v2.0/img/versusicon1.png" width="30" height="30" />Versus</a>
		<ul>
		<li><a href="#" onclick="loadPage(1);">Realizar Versus</a>
		</li>				
		<li><a href="#" onclick="loadPage(1);">Historial</a>
		</li>
	</ul></li>
	<%if (Integer.parseInt(""+request.getSession().getAttribute("tipoUsuarioLogin"))==3){%>
	<li>
		<a href="#"><img src="static/img/v2.0/img/catalogo.png" width="30" height="30" />Catálogos</a>
		<ul>
			<li><a href="#" onclick="loadPage(2);">Implants</a></li>
			<li><a href="#" onclick="loadPage(3);">Hospitales</a></li>
			<li><a href="#" onclick="loadPage(4);">Permisos</a></li>
			<li><a href="#" onclick="loadPage(5);">Médicos Trantantes</a></li>
			<li><a href="#" onclick="loadPage(6);">Cliente</a></li>
			<li><a href="#" onclick="loadPage(7);">Lista de Precios</a></li>
		</ul>
	</li>
	<%}%>
	<%if (Integer.parseInt(""+request.getSession().getAttribute("tipoUsuarioLogin"))==3 || Integer.parseInt(""+request.getSession().getAttribute("tipoUsuarioLogin"))==2){%>
	<li>
		<a href="#"><img src="static/img/v2.0/img/report-icon.png" width="30" height="30" />Reportes</a>
		<ul>
			<li><a href="#" onclick="loadPage(8);">Layout</a></li>					
		</ul>
	</li>
	<%}%>
	<li><a href="#"><img src="static/img/v2.0/img/password.jpg" width="30" height="30" />Cambiar Password</a>				
	</li>
</ul>