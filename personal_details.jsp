<html>
<head>
<style>
input
{
	padding:5px;
	background-color:black;
	color:white;
	border-radius: 15px;
}
input:hover
{
	border-color: blue;
}
#submit
{
	padding:12px;
	font-style:bold;
	color:white;
	background-color:black;
	font-size:18px;
}
#submit:hover
{
	font-size:20px;
}
div#div1
{
	border-radius: 80px;
	background-color:grey;
	width:800px;
	padding:20px;
	color:white;
	font-size:18px;
}
</style>
</head>
<body>
	<center><br>
	<h1>Your personal details</h1>
	<hr><br>
	<div id="div1">
	<br><br>
	<form action="personal_details" method="post">
		Name : <input type="text" name="name" required><br><br>
		Age  : <input type="number" name="age" required><br><br>
		Gender : &nbsp; <input type="radio" name="gender" value="male" required>Male
		&nbsp; &nbsp; <input type="radio" name="gender" value="female" required>Female<br><br>
		Date of Birth : <input type="date" name="dob" required><br><br>
		Contact Number : <input type="number" name="contact_no" required><br><br>
		<!--Wanna be a <input type="radio" name="type" value="donor" required>Donor
		&nbsp; &nbsp; <input type="radio" name="type" value="recipient" required>Recipient
		<br><br>-->
		<input type="submit" value="SUBMIT" id="submit" >
	</form>
	<div>
</body>
</html>