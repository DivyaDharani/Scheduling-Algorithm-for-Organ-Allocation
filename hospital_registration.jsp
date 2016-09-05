<html>
<head>
<style>
input,select
{
	padding: 5px;
	background-color: #404040;
	color: white;
	border-radius: 15px;
	font-family: lucida;
	font-size: 18px;
	height: 50px;
}
input:hover,select:hover
{
	border-color: #dfdf1f;
	border-width: 2px;
}
input:focus,select:focus
{
	outline: none;
}
#submit
{
	border-color: white;
	border-width:3px;
	padding:12px;
	font-style:bold;
	color:white;
	background-color:seagreen;
	font-size:18px;
	width:150px;
}
#submit:hover
{
	font-size:20px;
}
div#div1
{
	border-radius: 70px;
	background-color:seagreen;
	width:800px;
	padding:20px;
	color:white;
	font-size:18px;
}
</style>
</head>
<body bgcolor="black">
	<center>
	<h1 style="color:white;">HOSPITAL REGISTRATION</h1>
	<hr>
	<div id="errordiv" style="color:red;">
	<% if(session.getAttribute("reg_error")!=null)
			out.println(session.getAttribute("reg_error"));
	%>
	</div><br>
	<div id="div1">
	<form action="hospital_reg" method="post">
		<table cellpadding="10" cellspacing="20" style="border-collapse:collapse;color:white;font-size:20px;">
			<tr><th>Name of hospital</th>
				<td><input type="text" name="hosp_name" required></td>
			</tr>
			<tr><th>Location(City)</th>
			<td>
				<select name="city" style="width:230px;" required>
		  			<option value="Chennai">Chennai</option>
		  			<option value="Madurai">Madurai</option>
		  			<option value="Trichy">Trichy</option>
		  			<option value="Coimbatore">Coimbatore</option>
		  			<option value="Vellore">Vellore</option>
		  			<option value="Tanjore">Tanjore</option>
		  			<option value="Salem">Salem</option>
				</select>
			</td></tr>
			<tr><th>Type of Hospital</th>
			<td>
				<select name="hosp_type" style="width:230px;" required>
					<option value="private">Private</option>
		  			<option value="government">Government</option>
				</select>
			</td></tr>
			<tr><th>Contact Number</th>
				<td>
					<input type="number" name="phone" maxlength="10" required>
				</td>
			</tr>
			<tr><th>User Name</th><td><input type="user_name" name="user_name" required></td></tr>
			<tr><th>Password</th><td><input type="password" name="password" required></td></tr>

		</table> <br>
		<input id="submit" type="submit" value="SUBMIT">
	</form>
	<div>
</body>
</html>