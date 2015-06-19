<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*" import="java.sql.*"%>
<jsp:useBean id="listaElenco" scope="session"
	class="java.util.ArrayList"></jsp:useBean>
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
<title>Gestione veicoli</title>
</head>
<body>
	Benvenuto nell'area di gestione veicoli
	<%=currentUser%>.
	<form action="GestioneVeicoliServlet" method="POST" name="formFiltro">
		<table order="1" cellpadding="1" cellspacing="5">
			<thead>
				<tr>
				    <td>Seriale</td>
					<td>Targa</td>
					<td>Marca</td>
					<td>Modello</td>
					<td>Gestore</td>
					<td></td>

				</tr>
			</thead>
			<tbody>
				<%
				List<Veicolo> listaVeicoli = Veicolo.getVeicoli();
			%>
				<%
				for (Veicolo v : listaVeicoli) {
			%>

				<tr>
				    <td><%=Veicolo_observer.getAssociazioneCorrente(v.getTarga()).getSerial()%></td>
					<td><%=v.getTarga()%></td>
					<td><%=v.getMarca()%></td>
					<td><%=v.getModello()%></td>
					<td><%=v.getGestore()%></td>
					<td><input type="radio" name="rdbSelezione"
						value="<%=v.getTarga()%>"></td>
				</tr>
				<%
				}
			%>
			</tbody>
		</table>

		<hr>
		<input type="submit" name="btnMode" value="Inserisci"> <input
			type="submit" name="btnMode" value="Modifica"> <input
			type="submit" name="btnMode" value="Elimina"
			onclick="return confirm('Sei sicuro di voler eliminare il veicolo selezionato?')">
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