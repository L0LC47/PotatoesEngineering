<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
	
<%
	String currentUser = session.getAttribute("currentSessionUser").toString();
	//CREARE FUNZIONE PER CONTROLLO UTENTE
	String currentVeicolo = session.getAttribute("veicoloSelezionato").toString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza date</title>
</head>
<body>
<p>Elenco date in cui il veicolo è stato utilizzato</p>
	<p>Selezionare una data per visualizzare il percorso effettuato</p>

	<form action="userStoricoMappa.jsp" method="POST">
		<table>
			<tr>
				<td></td>
				<td>Date utilizzo</td>
			</tr>
			<%
				List<Date> listaDate = Storico.getUserVeicoloDate(currentVeicolo);%>

				
					<% for(Date data : listaDate){%>
			<tr>
				<td><input type="submit" name="data"
					value="<%=data%>"></td>
			</tr>
			<%  session.setAttribute("dataSelezionata", data);%>
			<%
				}
			%>
		</table>
		
	</form>
</body>
</html>