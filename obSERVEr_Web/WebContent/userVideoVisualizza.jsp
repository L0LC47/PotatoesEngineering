<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Visualizza video</title>
</head>
<body>
	<h3>Video del ladro</h3>
	<iframe width="960" height="720"
		src="https://www.youtube-nocookie.com/embed/tIvBjXDOld0?autoplay=1&rel=0&controls=0&shoinfo=0&start=10"
		frameborder="0"></iframe>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>