<html>
<body>
	<%
		session.setAttribute("name","Divya");
		out.println(session.getAttribute("name"));
	%>
</body>
</html>