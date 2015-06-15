<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 2;
	String currentVeicolo = "";
	Date currentDate = null;
	if (session.getAttribute("currentSessionUser") == null)
		response.sendRedirect("Login.jsp");
	else {
		currentUser = session.getAttribute("currentSessionUser")
				.toString();
		privileges = Integer.parseInt(session.getAttribute(
				"currentUserPrivileges").toString());
		if (privileges > pagePrivileges)
			response.sendRedirect("accessoNegato.jsp");
		else {
			if (session.getAttribute("veicoloSelezionato") == null)
				response.sendRedirect("userPosizione.jsp");
			else {
				currentVeicolo = session.getAttribute(
						"veicoloSelezionato").toString();
				currentDate = (Date) session
						.getAttribute("dataOdierna");
			}
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Allarmi attivati del veicolo selezionato</h3>
	<p>Visualizza gli allarmi attivati dal veicolo selezionato</p>

	<form>
		<table>
			<tr>
				<td>Posizione</td>
			</tr>
			<%
				List<Storico> allarmi = Storico.getAllarmi(currentVeicolo);
			%>

			<%
				for (Storico a : allarmi) {
			%>
			<tr>
				<td><%="Guidatore"%></td>
				<td><%="Data e Ora"%></td>
				<td><%="Posizione"%></td>
				<td><%="Velocità"%></td>
			</tr>
			<tr>
				<td><%=currentUser%></td>
				<td><%=a.getIstante()%></td>
				<td><%=a.getPosizione()%></td>
				<td><%=a.getVelocita()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
	</br>
	</br>
	</br>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br>
		</br>
		</br> <input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>