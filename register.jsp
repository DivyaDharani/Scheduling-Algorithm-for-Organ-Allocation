<html>
	<head>
		<script src="jquery-3.0.0.js"></script>
		<script>
			$(function(){
				$("#header").load("header.html");
			});
			function check_password() 
			{
			    var x = document.getElementsByName("password")[0].value;
			    var y = document.getElementsByName("re_password")[0].value;
			    document.getElementById("div1").innerHTML = x.value;
			    if(x!=y)
			    {
			    	document.getElementById("div1").innerHTML = "Passwords didn't match";
			    	return false;
			    }
			    else
			   	{
			    	document.getElementById("div1").innerHTML = "Passwords matched";
			   		return true;
			   	}
			}
		</script>
		
	</head>
<body>
	<center>
	<h1>Registration</h1><br><br>
	<div>
	For your convenience, we don't ask all your personal details now. Take your own time to update later!!
	</div><br><br>
	<div id="errordiv" style="color:red;">
	<% if(session.getAttribute("reg_error")!=null)
			out.println(session.getAttribute("reg_error"));
	%>
	</div><br><br>
	<form action="register" method="post" onsubmit="return check_password()">
		Registration Type : 
		<select name="registration_option">
			<option value="donor">Donor</option>
			<option value="recipient">Recipient</option>
		</select> <br><br>
		User Name : <input type="text" name="user_name"><br><br>
		Password : <input type="password" name="password"><br><br>
		Retype password : <input type="password" name="re_password" onchange="check_password()"><br><br>
		<div id="div1">
			<br>
		</div>
		<br><br>
		<input type="submit" value="SUBMIT">
	</form>
	</body>
	</html>