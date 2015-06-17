<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*" import="java.sql.*"%>
<%
	String currentUser = "", targa = "", marca = "", modello = "", gestore = "";
	String messaggio = "Sei sicuro di voler inserire il nuovo veicolo?", disabled = "";
	String benvenuto = "Inserisci i dati per il nuovo veicolo ...";
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
					.getAttribute("veicoloSelezionato") == null))
		response.sendRedirect("adminGestioneVeicoli.jsp");

	if (session.getAttribute("modalita").toString()
			.equalsIgnoreCase("Modifica")) {
		targa = session.getAttribute("veicoloSelezionato").toString();
		Veicolo veicoloUpdate = Veicolo.getVeicoloData(targa);
		marca = veicoloUpdate.getMarca();
		modello = veicoloUpdate.getModello();
		gestore = veicoloUpdate.getGestore();
		messaggio = "Sei sicuro di voler confermare le modifiche?";
		disabled = "disabled";
		benvenuto = "Edita i dati che intendi modificare.";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione veicoli</title>
</head>
<body>
	<%=benvenuto%>
	</br>
	<form action="ModificaVeicoliServlet" method="POST" name="formFiltro">
		<table order="1" cellpadding="1" cellspacing="5">
			<td>Targa</td>
			<td><input type="text" name="txtTarga" value="<%=targa%>"
				<%=disabled%> required></td>
			</tr>
			<tr>
				<td>Marca</td>
				<td><input type="text" name="txtMarca" value="<%=marca%>"
					required></td>
			</tr>
			<tr>
				<td>Modello</td>
				<td><input type="text" name="txtModello" value="<%=modello%>"
					required></td>
			</tr>
		</table>

		<hr>
		<input type="submit" name="conferma" value="Conferma"
			onclick="return confirm('<%=messaggio%>')">
		<hr>
	</form>
	<a href="userLogged.jsp">Torna alla Home</a>
	<hr>
	<form action="LogoutServlet" method="POST">
 <input type="submit" name="Logout" value="Logout">
	</form>
	<hr>
</body>
</html>