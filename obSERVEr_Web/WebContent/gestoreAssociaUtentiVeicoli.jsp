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
<title>Associazione utenti veicoli</title>
</head>
<body>
	Benvenuto nell'area riservata aa gestori flotta e amministratori
	<%=currentUser%>.
	</br>

</body>
</html>