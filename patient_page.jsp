<html>
<center>
<head>
	<style>
		table
		{
			border:2px solid grey;
			border-collapse:collapse;
		}
		button
		{
			font-size:15px;
			padding-top:10px;
			padding-bottom:10px;
			width:200px;
			height:50px;
			text-align:center;
		}
		button:hover
		{
				background-color: grey;
				color:white;
		}
	</style>
	<script>
		function redirect_to(loc)
		{
				window.location=loc;
		}
	</script>
</head>
<body>
	<br><br>
	<h1>PROFILE PAGE</h1>
	
	<br><br><br>
	<table>
		<tr>
			<td><button onclick="redirect_to('personal_details.jsp')"><b>Submit your personal details</b></button></td>
			<td><button onclick="redirect_to('medical_report.html')"><b>Submit medical details</b></button></td>
			<td><button onclick="redirect_to('find_recipient')"><b>Search for a Recipient</b></button></td>
		</tr>
	</table>	
</body>
</html>