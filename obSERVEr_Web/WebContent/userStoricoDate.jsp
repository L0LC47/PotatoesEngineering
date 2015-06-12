<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
String currentUser = "";
int privileges = Integer.MAX_VALUE;
int pagePrivileges = 2;
String currentVeicolo = "";
if(session.getAttribute("currentSessionUser") == null)
    response.sendRedirect("Login.jsp");
else {
    currentUser = session.getAttribute("currentSessionUser").toString();
    privileges = Integer.parseInt(session.getAttribute("currentUserPrivileges").toString());
    if (privileges > pagePrivileges)
           response.sendRedirect("accessoNegato.jsp");
    else {
    	if(session.getAttribute("veicoloSelezionato") == null)
    		response.sendRedirect("userStorico.jsp");
    	else
    		   currentVeicolo = session.getAttribute("veicoloSelezionato").toString();
    }
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza date</title>
</head>
<body>
	<h3>Elenco date in cui il veicolo è stato utilizzato</h3>
	<p>Selezionare una data per visualizzare il percorso effettuato</p>

	<form action="userStoricoMappa.jsp" method="POST">
		<table>
			<tr>
				<td>Date in cui il mezzo è stato utilizzato</td>
			</tr>
			<%
				List<Date> listaDate = Storico.getUserVeicoloDate(currentVeicolo);%>


			<% for(Date data : listaDate){%>
			<tr>
				<td><input type="submit" name="data" value="<%=data%>"></td>
			</tr>
			<%  session.setAttribute("dataSelezionata", data);%>
			<%
				}
			%>
		</table>
	</form>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>