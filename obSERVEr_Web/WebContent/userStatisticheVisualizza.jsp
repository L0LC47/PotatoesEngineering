<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="it.univr.is.observer.logica.*" import="java.util.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 2;
	String currentVeicolo = "";
	String dInizio;
	String dFine;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza statistiche</title>
</head>
<body>
	<h3>Statistiche del veicolo selezionato</h3>
	<p>Targa:</p><%=currentVeicolo%>
	<p>Dal:</p><%=session.getAttribute("dataInizio")%>
	<p>al:</p><%=session.getAttribute("dataFine")%>
	</br>
	<p>Velocità minima:</p><%=session.getAttribute("velMin")%>
	<p>Velocità massima:</p><%=session.getAttribute("velMax")%>
	<p>Velocità media:</p><%=session.getAttribute("velMed")%>
	<p>Velocità media in movimento:</p><%=session.getAttribute("velMedMov")%>
	</br>
	</br>
	<a href="userStatistiche.jsp">Torna alla pagina di selezione date e
		veicolo</a>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>