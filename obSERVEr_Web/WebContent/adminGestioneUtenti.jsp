<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaElenco" scope="session"
	class="java.util.ArrayList"></jsp:useBean>
<%
	String currentUser = session.getAttribute("currentSessionUser")
			.toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione utenti</title>
</head>
<body>
	Benvenuto nell'area di gestione utenti
	<%=currentUser%>.
	</br>
	</br> Selezionare una funzionalità:
	<ul>
		<li><a href="nuovoUtente.jsp">Nuovo Utente</a></li>
		<li><a href="modificaUtente.jsp">Modifica Utente</a></li>
		<li><a href="eliminaUtente.jsp">Elimina Utente</a></li>
	</ul>

</body>
</html>