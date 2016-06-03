<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Argal-Login</title>
<style>
	html{
		height:100%;
	}
	body {
		background-color:#3E6a84;	
		height:100%;
		font-family: Helvetica,Arial;
    	font-size: 10pt;
	}	
	#MainContainer {
		margin:auto;
		background-color:#FFFFFF;
		width:910px;
		height:100%;
		position:relative;
		top:0px;
		bottom:0px;
	}
.style1 {color: #FFFFFF}

.buttonstyle{
	-moz-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	-webkit-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	box-shadow:inset 0px 1px 0px 0px #97c4fe;
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #3d94f6), color-stop(1, #1e62d0) );
	background:-moz-linear-gradient( center top, #3d94f6 5%, #1e62d0 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3d94f6', endColorstr='#1e62d0');
	background-color:#623DF6;
	-webkit-border-top-left-radius:20px;
	-moz-border-radius-topleft:20px;
	border-top-left-radius:20px;
	-webkit-border-top-right-radius:20px;
	-moz-border-radius-topright:20px;
	border-top-right-radius:20px;
	-webkit-border-bottom-right-radius:20px;
	-moz-border-radius-bottomright:20px;
	border-bottom-right-radius:20px;
	-webkit-border-bottom-left-radius:20px;
	-moz-border-radius-bottomleft:20px;
	border-bottom-left-radius:20px;
	text-indent:0;
	border:1px solid #337fed;
	display:inline-block;
	color:#ffffff;
	font-family:Arial;
	font-size:12px;
	font-weight:bold;
	font-style:normal;
	height:36px;
	line-height:36px;
	width:136px;
	text-decoration:none;
	text-align:center;
	text-shadow:1px 1px 0px #1570cd;
}
.buttonstyle:hover {
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #1e62d0), color-stop(1, #3d94f6) );
	background:-moz-linear-gradient( center top, #1e62d0 5%, #3d94f6 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0', endColorstr='#3d94f6');
	background-color:#1e62d0;
}.buttonstyle:active {
	position:relative;
	top:1px;
}
</style>
<script language="javascript">
function sendForm(){
	document.getElementById("j_username").value=document.getElementById("j_username").value.toUpperCase();
	document.getElementById("j_password").value=document.getElementById("j_password").value.toUpperCase();
	document.forms.loginform.submit();
}
</script>
</head>
<body>
   <div id="MainContainer">   
   <center>   
	 	<p>&nbsp; </p>
	 	<br></br><br></br><br></br><br></br>
	 	<table width="300" border="0">
			<tr>
				<td><p><img src="static/img/argallogo.jpg" width="100" height="81"></img></p></td>
				<td width="250" bgcolor="#3E6a84">
					<form name="loginform" action="j_spring_security_check" method="post">
						<p align="center" class="style1">
							HOSPITAL SYSTEM CONTROL SPENDING
						</p>		
						<p align="center" class="style1">
						<strong>Por favor teclee sus datos de acceso<br></br></strong>
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </p>
	  					<table width="481">
							<tr><td width="194" class="style1" align="right">User:</td>
							<td width="333" class="style1"><input  type="text" id="j_username" name="j_username" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td></tr>
							<tr><td class="style1" align="right">Password:</td><td class="style1"><input style="text-transform:uppercase" type="password" name="j_password" id="j_password" onkeyup="javascript:this.value=this.value.toUpperCase();"/></td></tr>		
							<tr><td class="style1" align="right"></td><td class="style1"><br></br><input class="buttonstyle" type="button" onclick="sendForm()" value="Aceptar"/></td></tr>
	  					</table>
					</form>
				</td>
			</tr>		
  		</table>
	</center>				
	</div>
</body>
</html>