<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaElenco" scope="session"
	class="java.util.ArrayList"></jsp:useBean>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 0;
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
<title>Gestione veicoli</title>
</head>
<body>
	Benvenuto nell'area di gestione veicoli
	<%=currentUser%>.
	</br>
	</br> Selezionare una funzionalitą:
	<ul>
		<li><a href="nuovoVeicolo.jsp">Nuovo Veicolo</a></li>
		<li><a href="modificaVeicolo.jsp">Modifica Veicolo</a></li>
		<li><a href="eliminaVeicolo.jsp">Elimina Veicolo</a></li>
	</ul>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>