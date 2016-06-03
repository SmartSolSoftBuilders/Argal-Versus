<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- General meta information -->
	<title>ARGAL-HSCS</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="robots" content="index, follow" />
	<meta charset="utf-8" />	
	<script src="static/js/v2.0/js/jquery-2.2.0.js"></script>	
	<script type="text/javascript" src="static/css_login/js/css_script.js"></script>		
	<link type="text/css" rel="stylesheet" href="static/css_login/style.css" media="screen" />	
	<link type="text/css" rel="stylesheet" href="static/css/v2.0/argal_style.css" media="screen" />
<script>
	$(document).ready(function(){ 
	$("#submit1").hover(
	function() {
	$(this).animate({"opacity": "0"}, "slow");
	},
	function() {
	$(this).animate({"opacity": "1"}, "slow");
	});
 	});
	
	function sendForm(){
		document.getElementById("j_username").value=document.getElementById("j_username").value.toUpperCase();
		document.getElementById("j_password").value=document.getElementById("j_password").value.toUpperCase();
		document.forms.loginform.submit();
	}	
</script>
	
</head>
<body>

	<div id="wrapper">
		<div id="wrappertop"></div>
		<div id="wrappermiddle">
			<center>
				<h4>HOSPITAL SYSTEM CONTROL SPENDING</h4>
				<img src="static/img/v2.0/img/logo_argal.png" width="100" height="60" />
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</center>
			<form name="loginform" action="j_spring_security_check" method="post">			
				<div id="username_input">
					<div id="username_inputleft"></div>
					<div id="username_inputmiddle">				
						<!--  input type="text" name="link" id="url" value="E-mail Address" onclick="this.value = ''"-->
						<input type="text" id="j_username" name="j_username" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
						<img id="j_username_user" src="static/css_login/images/mailicon.png" alt=""/>				
					</div>
					<div id="username_inputright"></div>
				</div>
				<div id="password_input">	
					<div id="password_inputleft"></div>
					<div id="password_inputmiddle">				
						<!--  input type="password" name="link" id="url" value="Password" onclick="this.value = ''"-->
						<input type="password" name="j_password" id="j_password" onkeyup="javascript:this.value=this.value.toUpperCase();"/>
						<img id="j_password_user" src="static/css_login/images/passicon.png" alt=""/>
						<br>										
					</div>
					<center><button type="button" id="buttonGuardarEvento" onclick="sendForm();" class="myButton">Login</button></center>
				</div>
			</form>			
			<div id="links_left">
			</div>
			<div id="links_right"></div>
		</div>
		<div id="wrapperbottom"></div>		
		<div id="powered">		
		</div>
	</div>

</body>
</html>