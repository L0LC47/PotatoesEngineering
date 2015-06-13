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
<form action="GestioneUtentiServlet" method="POST" name="formFiltro" >
	<table>
		<tr>
			<td>Targa</td>
			<td>Guidatore</td>
			<td>Marca</td>
			<td>Modello</td>

		</tr>
		<%
			List<Usr> listaUtenti = Usr.getUsers();
		%><%-- 
		<%
			for (Usr u : listaUtenti) {
		%>
		
		<tr>
			<td></td>
			<td><%=veicolo.getGuidatore()%></td>
			<td><%=veicolo.getMarca()%></td>
			<td><input type="radio" name="rdbSelezione"
				value="<%=((it.univr.is.entity.Persona) listaElenco.get(i))
						.getID()%>"></td>

		</tr>
		<%
			session.setAttribute("veicoloSelezionato", veicolo.getTarga());
		%>
		<%
			}
		%>
--%>
	</table>

	<hr>
	<input type="submit" name="btnMode" value="Insert">
	<input type="submit" name="btnMode" value="Update">
	<input type="submit" name="btnMode" value="Delete">

	<hr>
	</form>

	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>