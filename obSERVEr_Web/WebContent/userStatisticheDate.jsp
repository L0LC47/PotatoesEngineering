<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="it.univr.is.observer.logica.*" import="java.util.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 2;
	String currentVeicolo = "";
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
				response.sendRedirect("userStatistiche.jsp");
			else
				currentVeicolo = session.getAttribute(
						"veicoloSelezionato").toString();
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="text/html; charset=ISO-8859-1;1">
<title>Visualizza date</title>
</head>
<body>
	<h3>Date per le statistiche del veicolo selezionato</h3>
	<p>Targa:</p><%=currentVeicolo%>

	<p>Inserire le date per cui si desidera calcolare le statistiche</p>
	<table>
		<tr>
			<td><input type="date" name="dataInizio" value="2015-01-01"></td>
			<td><input type="date" name="dataFine" value="2015-12-31"></td>
		</tr>
	</table>
	
<%-- 	<table>
		<%
			Statistica s = new StatisticaMax();
		%>
		<%
			int statistica = Veicolo.getStatistica(Veicolo
					.getStatisticheVeicolo(currentVeicolo, request
							.getParameter("dataInizio").toString(), request
							.getParameter("dataFine").toString()), s);
		%>

	</table> --%>
	
</body>
</html>