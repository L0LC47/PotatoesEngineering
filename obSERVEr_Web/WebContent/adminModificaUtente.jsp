<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*" import="java.sql.*"%>
<%
	String currentUser = "", email = "", nome = "", cognome = "", livPrivilegi = "";
	String messaggio = "Sei sicuro di voler inserire il nuovo utente?", disabled = "";
	String benvenuto = "Inserisci i dati per il nuovo utente ...";
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
	if (session.getAttribute("modalita") == null
			|| (session.getAttribute("modalita").toString()
					.equalsIgnoreCase("Modifica") && session
					.getAttribute("utenteSelezionato") == null))
		response.sendRedirect("adminGestioneUtenti.jsp");

	if (session.getAttribute("modalita").toString()
			.equalsIgnoreCase("Modifica")) {
		email = session.getAttribute("utenteSelezionato").toString();
		Usr utenteUpdate = Usr.getUserData(email);
		nome = utenteUpdate.getNome();
		cognome = utenteUpdate.getCognome();
		livPrivilegi += utenteUpdate.getGestore();
		messaggio = "Sei sicuro di voler confermare le modifiche?";
		disabled = "disabled";
		benvenuto = "Edita i dati che intendi modificare.";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione utenti</title>
</head>
<body>
	<%=benvenuto %>
	</br>
	<form action="ModificaUtentiServlet" method="POST" name="formFiltro">
		<table>
			<tr>
				<td>Email</td>
				<td><input type="email" name="txtEmail" value="<%=email%>"
					<%=disabled%> required></td>
			</tr>
			<tr>
				<td>Nome</td>
				<td><input type="text" name="txtNome" value="<%=nome%>"
					required></td>
			</tr>
			<tr>
				<td>Cognome</td>
				<td><input type="text" name="txtCognome" value="<%=cognome%>"
					required></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="txtPassword" required></td>
			</tr>
			<tr>
				<td>Conferma Password</td>
				<td><input type="password" name="txtCPassword" required></td>
			</tr>
			<tr>
				<td>Liv.Privilegi (0 - Admin, 1 - Gestore, 2 - Utente)</td>
				<td><input type="text" name="txtGestore"
					value="<%=livPrivilegi%>" required></td>
				<td></td>
			</tr>

		</table>

		<hr>
		<input type="submit" name="conferma" value="Conferma"
			onclick="return confirm('<%=messaggio%>')">
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