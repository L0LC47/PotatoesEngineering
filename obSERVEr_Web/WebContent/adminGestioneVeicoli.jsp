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
<title>Gestione veicoli</title>
</head>
<body>
	Benvenuto nell'area di gestione veicoli
	<%=currentUser%>.
	</br>
	</br> Selezionare una funzionalità:
	<ul>
		<li><a href="nuovoVeicolo.jsp">Nuovo Veicolo</a></li>
		<li><a href="modificaVeicolo.jsp">Modifica Veicolo</a></li>
		<li><a href="eliminaVeicolo.jsp">Elimina Veicolo</a></li>
	</ul>

</body>
</html>