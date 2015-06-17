<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 2;
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
<title>Visualizza allarmi</title>
</head>
<body>
	<h3>Elenco degli allarmi</h3>
	<p>Selezionare un veicolo dall'elenco per visualizzarne gli allarmi</p>
	<form action="AllarmeServlet" method="POST">
		<table order="1" cellpadding="1" cellspacing="5">
			<thead>
				<tr>
					<td>Targa</td>
					<td>Guidatore</td>
					<td>Marca</td>
					<td>Modello</td>

				</tr>
			</thead>
			<tbody>
				<%
				List<Veicolo> listaVeicoli = Usr.getUserVeicoliUtente(currentUser);
			%>
				<%
				for (Veicolo veicolo : listaVeicoli) {
			%>
				<tr>
					<td><input type="submit" name="targa"
						value="<%=veicolo.getTarga()%>"></td>
					<td><%=veicolo.getGuidatore()%></td>
					<td><%=veicolo.getMarca()%></td>
					<td><%=veicolo.getModello()%></td>

				</tr>
				<%
				}
			%>
			</tbody>
		</table>
	</form>
	<hr>
	<a href="userLogged.jsp">Torna alla Home</a>
	<hr>
	<form action="LogoutServlet" method="POST">
<input type="submit" name="Logout" value="Logout">
	</form>
	<hr>
</body>
</html>