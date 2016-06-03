<ul id="menu">
	<li>
		<img src="../../static/img/v2.0/img/logo_argal.png" width="80" height="50" />
	</li>	
	<li>Bienvenido:<%=request.getSession().getAttribute("userSession")%><br><a style="color: #ffffff;" class="logout" href="../../logout">Cerrar sesión</a></li>
	<li><a href="#" onclick="location.href='/argal-web?p=1';">
		<img src="../../static/img/v2.0/img/home.png" width="30" height="30" onclick="location.href='/argal-web?p=1';">Volver al Inicio</a>
		<ul>							
		</ul></li>
	<li>	
</ul>