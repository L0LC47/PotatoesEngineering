<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
String currentUser = "";
int privileges = Integer.MAX_VALUE;
int pagePrivileges = 2;
String currentVeicolo = "";
java.sql.Date currentDate = null;
if(session.getAttribute("currentSessionUser") == null)
response.sendRedirect("Login.jsp");
else {
currentUser = session.getAttribute("currentSessionUser").toString();
privileges = Integer.parseInt(session.getAttribute("currentUserPrivileges").toString());
if (privileges > pagePrivileges)
       response.sendRedirect("accessoNegato.jsp");
else {
	if(session.getAttribute("veicoloSelezionato") == null)
		response.sendRedirect("userPosizione.jsp");
	else{
		   currentVeicolo = session.getAttribute("veicoloSelezionato").toString();
		   currentDate = new java.sql.Date(new java.util.Date().getTime());
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
	<h3>Posizione attuale del veicolo selezionato</h3>
	<p>Visualizza posizione del veicolo selezionato</p>

	<form>
		<table>
			<tr>
				<td>Posizione</td>
			</tr>
			<%List<String> posizione = Storico.getStorico(currentVeicolo, currentDate);%>

			<% for(String p : posizione){%>
			<tr>
				<td><%=p%></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
	<a href="userLogged.jsp">Torna alla Home</a>
</body>
</html>