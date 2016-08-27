<html>
<center>
<br><br><br><br><br><br>
<div id="errordiv" style="color:red;">
	<% 
		if(session.getAttribute("error")!=null) 
	  		out.println(session.getAttribute("error"));
	%>
</div>
<br><br>
<form action="login" method="post">
	User Name: &nbsp; <input type="text" name="user_name"><br><br>
	Password: &nbsp; <input type="password" name="password"><br><br>
	Type: <select name="type">
		<option value="donor">Donor</option>
		<option value="recipient">Recipient</option>
		<option value="hospital">Hospital</option>
	</select><br><br>
	<input type="submit" value="Log In">
</form>
	<br><br>
	Don't have an account? 
	<br><br>
	<a href="register">Register here</a>

</html>
