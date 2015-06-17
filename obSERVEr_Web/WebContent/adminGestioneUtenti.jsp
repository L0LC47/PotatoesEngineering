<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*" import="java.sql.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 0;
	if (session.getAttribute("currentSessionUser") == null)
		response.sendRedirect("Login.jsp");
	else {
		currentUser = session.getAttribute("currentSessionUser")
				.toString();
		privileges = Integer.parseInt(session.getAttribute(
				"currentUserPrivileges").toString());
		if (privileges > pagePrivileges)
			response.sendRedirect("accessoNegato.jsp");
	}
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
	<form action="GestioneUtentiServlet" method="POST" name="formFiltro">
		<table>
			<tr>
				<td>Email</td>
				<td>Nome</td>
				<td>Cognome</td>
				<td>Liv.Privilegi</td>
				<td></td>

			</tr>
			<%
				List<Usr> listaUtenti = Usr.getUsers();
			%>
			<%
				for (Usr u : listaUtenti) {
			%>

			<tr>
				<td><%=u.getEmail()%></td>
				<td><%=u.getNome()%></td>
				<td><%=u.getCognome()%></td>
				<td><%=u.getGestore()%></td>
				<td><input type="radio" name="rdbSelezione"
					value="<%=u.getEmail()%>"></td>

			</tr>
			<%
				}
			%>
		</table>

		<hr>
		<input type="submit" name="btnMode" value="Inserisci"> <input
			type="submit" name="btnMode" value="Modifica"> <input
			type="submit" name="btnMode" value="Elimina"
			onclick="return confirm('Sei sicuro di voler eliminare l\'utente selezionato?')">
		<hr>
	</form>
	</br>
	</br>
	</br>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br> </br> </br> <input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>