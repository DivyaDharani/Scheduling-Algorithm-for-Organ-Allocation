<html>
<body>
	<center>
	<h1>FINDING RECIPIENT</h1>
	<br><br>
	<form action="find_recipient" method="get">
		<table>
			<tr>
				<th>Donor ID : </th>
				<td><input type="number" name="id"></td>
			</tr>
			<tr><td/><td><button type="submit" name="search_type" value="within_hospital">Search within hospital</button></td></tr>
			<tr><td/><td><button type="submit" name="search_type" value="within_OPO">Search within OPO</button></td></tr>
			<tr><td/><td><button type="submit" name="search_type" value="other_OPOs">Search in other OPOs</button></td></tr>
		</table>
	</form>
</body>
</html>
