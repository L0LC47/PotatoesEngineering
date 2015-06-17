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
<title>Impostazione allarmi di velocità</title>
</head>
<body>
	Benvenuto nell'area riservata a gestori flotta e amministratori
	<%=currentUser%>.


	<form action="ImpostaAllarmiServlet" method="POST">
		<table order="1" cellpadding="1" cellspacing="5">
			<thead>
				<tr>
					<th scope="col">obSERVEr</th>
					<th scope="col">Targa</th>
					<th scope="col">Marca</th>
					<th scope="col">Modello</th>
					<th scope="col">Allarme</th>
					<th scope="col">Modifica</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Veicolo_observer vo : Veicolo_observer
							.getAssociazioniCorrenti()) {
				%>
				<%
					Veicolo v = Veicolo.getVeicoloData(vo.getTarga());
						int allarme = Allarme.getAllarmeDB(vo.getSerial());
				%>
				<tr>
					<td><%=vo.getSerial()%></td>
					<td><%=v.getTarga()%></td>
					<td><%=v.getMarca()%></td>
					<td><%=v.getModello()%></td>
					<td><%=allarme%></td>
					<td style="text-align: center;"><input name="rdbSelezione"
						type="radio" value="<%=vo.getSerial()%>" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<hr>
		<input type="text" name="velocita" required>
		<input type="submit" name="btnMode" value="Modifica">
	</form>



	<%=(request.getAttribute("messaggio") == null) ? ""
					: request.getAttribute("messaggio").toString()%>
	<hr>
	<a href="userLogged.jsp">Torna alla Home</a>
	<hr>
	<form action="LogoutServlet" method="POST">
		<input type="submit" name="Logout" value="Logout">
	</form>
	<hr>
</body>
</html>