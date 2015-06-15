<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"%>
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
<title>Associazione utenti veicoli</title>
</head>
<body>
	Benvenuto nell'area riservata aa gestori flotta e amministratori
	<%=currentUser%>.
	</br>

	</br>
	</br> Utenti
	</br>
	<select name="utenti">
		<%for (Usr u:Usr.getUsers()) { %>
		<option value="<%=u.getEmail() %>"><%=u.getEmail() %></option>
		<%}%>
	</select>
	</br>
	</br>
	</br>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br>
		</br>
		</br> <input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>