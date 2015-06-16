<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 1;
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
<title>Associazione utenti veicoli</title>
</head>
<body>
	Benvenuto nell'area riservata a gestori flotta e amministratori
	<%=currentUser%>.
	</br>

	<form action="AssociazioneServlet" method="POST">
		<table order="1" cellpadding="1" cellspacing="5">
			<thead>
				<tr>
					<th scope="col">Email</th>
					<th scope="col">Nome</th>
					<th scope="col">Cognome</th>
					<th scope="col">Targa</th>
					<th scope="col">Marca</th>
					<th scope="col">Modello</th>
					<th scope="col">Inizio</th>
					<th scope="col">Elimina</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Usr_veicolo uv : Usr_veicolo.getAssociazioniCorrenti()) {
				%>
				<%
					Veicolo v = Veicolo.getVeicoloData(uv.getTarga());
						Usr u = Usr.getUserData(uv.getEmail());
				%>
				<tr>
					<td><%=uv.getEmail()%></td>
					<td><%=u.getNome()%></td>
					<td><%=u.getCognome()%></td>
					<td><%=uv.getTarga()%></td>
					<td><%=v.getMarca()%></td>
					<td><%=v.getModello()%></td>
					<td><%=uv.getInizio()%></td>
					<td style="text-align: center;"><input name="rdbSelezione"
						type="radio" value="<%=uv.getTarga()%>" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	<hr>
	<input type="submit" name="btnMode" value="Inserisci">
	<input type="submit" name="btnMode" value="Elimina"
		onclick="return confirm('Sei sicuro di voler eliminare l\'associazione selezionata?')">
	<hr>

    <%= (request.getAttribute("messaggio") == null) ? "" : request.getAttribute("messaggio").toString() %>

	</form>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br> </br> </br> <input type="submit" name="Logout" value="Logout">
	</form>

</body>
</html>
