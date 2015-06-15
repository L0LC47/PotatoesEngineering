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


<%!String veicolo = "";
	String utente = "";%>

<%
	if (request.getParameter("veicolo") != null)
		veicolo = request.getParameter("veicolo");

	if (request.getParameter("utente") != null)
		utente = request.getParameter("utente");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Associazione utenti veicoli</title>
</head>
<body>
	Benvenuto nell'area riservata aa gestori flotta e amministratori
	<%=currentUser%>.
	</br>

	<form action="AssociazioneServlet" method="POST">
		<table>
			</br>
			<tr>
				<td>Utente</td>
				<td><select id="utente" name="utente"
					onChange="this.form.submit()">
						<%
							for (Usr u : Usr.getUsers()) {
						%>
						<%
							if (u.getGestore() == 2) {
						%>
						<option value="<%=u.getEmail()%>"
							<%=utente.equals(u.getEmail()) ? " selected" : ""%>>
							<%=u.getEmail()%>
						</option>
						<%
							}
						%>
						<%
							}
						%>
				</select></td>

				<td>Veicolo</td>
				<td><select id="veicolo" name="veicolo"
					onChange="this.form.submit()">
						<%
							for (Veicolo v : Usr_veicolo.getVeicoli()) {
						%>
						<%
							if (v.getGuidatore() == null) {
						%>
						<option value="<%=v.getTarga()%>"
							<%=veicolo.equals(v.getTarga()) ? " selected" : ""%>>
							<%=v.getTarga()%>
						</option>
						<%
							}
						%>
						<%
							}
						%>
				</select></td>
			</tr>
		</table>
		<input type="submit" name="button" value="Visualizza dati"> <input
			type="submit" name="button" value="Associa">



		<%
			if (!veicolo.isEmpty()) {
		%>
		<p>
			marca:
			<%=Veicolo.getVeicoloData(veicolo).getMarca()%>
			</br> modello:
			<%=Veicolo.getVeicoloData(veicolo).getModello()%>
		</p>
		<%
			}
		%>

		<%
			if (!utente.isEmpty()) {
		%>
		<p>
			nome:
			<%=Usr.getUserData(utente).getNome()%>
			</br> cognome:
			<%=Usr.getUserData(utente).getCognome()%>
		</p>
		<%
			}
		%>



	</form>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br> </br> </br> <input type="submit" name="Logout" value="Logout">
	</form>


</body>
</html>