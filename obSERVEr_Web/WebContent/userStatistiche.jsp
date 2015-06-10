<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String currentUser = session.getAttribute("currentSessionUser")
			.toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza stitistiche</title>
</head>
<body>
	Benvenuto nell'area riservata agli utenti registrati
	<%=currentUser%>.
	</br>

</body>
</html>