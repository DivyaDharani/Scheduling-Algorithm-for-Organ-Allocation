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
	<div><b>Note:</b>&nbsp;&nbsp;If you have already given your personal details, filling this form will update your details!</div><br><br>
	<div id="div1">
	<br><br>
	<form action="personal_details" method="post">
		Name : <input type="text" name="name" required><br><br>
		Gender : &nbsp; <input type="radio" name="gender" value="male" required>Male
		&nbsp; &nbsp; <input type="radio" name="gender" value="female" required>Female<br><br>
		Date of Birth : <input type="date" name="dob" required><br><br>
		Contact Number : <input type="number" name="contact_no" required><br><br>
		Emergency Contact Number : <input type="number" name="emergency_contact" required><br><br>
		<%@ page import="java.sql.*" %>
		<%
			String type = (String)session.getAttribute("type");
			if(type.equals("recipient"))
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");

				String sql = "select * from hospital_details";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet result = pstmt.executeQuery();
				String code = "Hospital : <select id='hospital_selection'>";
				String name;
				while(result.next())
				{
					name = result.getString("HospitalName");	
					code += "<option value='"+name+"'>"+name+"</option>";
				}
				code += "</select><br><br>";

				code+="City : <input type='text' name='city' required><br><br>";
				code+="Patient ID (provided by hospital) : <input type='number' name='patient_id' required><br><br>";
				out.println(code);
			}
		%>
		<input type="submit" value="SUBMIT" id="submit" >
	</form>
	<div>
</body>
</html>