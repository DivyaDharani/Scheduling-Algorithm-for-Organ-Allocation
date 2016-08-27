<html>
<center>
<head>
	<style>
		table
		{
			border:2px solid grey;
			border-collapse:collapse;
		}
		div
		{
			font-size:20px;
			padding:15px;
			width:200px;
			height:30px;
		}
		div:hover
		{
				background-color: grey;
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
	<h1>DONOR PAGE</h1>
	<!--<%= session.getAttribute("name") %>-->
	<br><br><br>
	<table>
		<tr>
			<td><div onclick="redirect_to('donor_registration_page.html')"><b>Edit your profile</b></div></td>
			<td><div onclick="redirect_to('medical_report.html')"><b>Submit medical details</b></div></td>
			<td><div onclick="redirect_to('find_recipient')"><b>Search for a Recipient</b></div></td>
		</tr>
	</table>	
</body>
</html>