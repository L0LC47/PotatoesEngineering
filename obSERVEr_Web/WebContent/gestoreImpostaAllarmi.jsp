<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 1;
	if(session.getAttribute("currentSessionUser") == null)
	    response.sendRedirect("Login.jsp");
	else {
	    currentUser = session.getAttribute("currentSessionUser").toString();
	    privileges = Integer.parseInt(session.getAttribute("currentUserPrivileges").toString());
	    if (privileges > pagePrivileges)
	           response.sendRedirect("accessoNegato.jsp");
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Impostazione allarmi di velocità</title>
</head>
<body>
	Benvenuto nell'area riservata aa gestori flotta e amministratori
	<%=currentUser%>.
	</br>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>