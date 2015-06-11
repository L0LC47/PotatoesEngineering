<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
	String currentUser = "";
int privileges = Integer.MAX_VALUE;
int pagePrivileges = 2;
String currentVeicolo = "";
Date currentDate = null;
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
    	else{
    		if(session.getAttribute("dataSelezionata") == null)
        		response.sendRedirect("userStoricoDate.jsp");
    		else{    		
    		   currentVeicolo = session.getAttribute("veicoloSelezionato").toString();
    		   currentDate = (Date)session.getAttribute("dataSelezionata");
    		}    	
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
	<h3>Percorso effettuato dal veicolo nella data selezionata</h3>
	<p>AGGIUNGERE MAPPA</p>

	<form>
		<table>
			<tr>
				<td>Posizione del veicolo nella data selezionata</td>
			</tr>
			<%List<String> storico = Storico.getStorico(currentVeicolo, currentDate);%>

			<%for(String s : storico){%>
			<tr>
				<td><%=s%></td>
			</tr>
			<% }%>
		</table>

	</form>
</body>
</html>