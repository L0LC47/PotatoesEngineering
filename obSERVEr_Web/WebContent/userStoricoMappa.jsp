<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
	
<%
	String currentUser = session.getAttribute("currentSessionUser").toString();
	//CREARE FUNZIONE PER CONTROLLO UTENTE
	String currentVeicolo = session.getAttribute("veicoloSelezionato").toString();
	Date currentDate = (Date)session.getAttribute("dataSelezionata");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Percorso effettuato dal veicolo nella data selezionata</p>

	<form>
		<table>
			<tr>
				<td>Storico</td>
			</tr>
			<%
				List<String> storico = Storico.getStorico(currentVeicolo, currentDate);%>
				
					<% for(String s : storico){%>
			<tr>
				<td><%=s%></td>
			</tr>
			<%
				}
			%>
		</table>
		
	</form>
</body>
</html>