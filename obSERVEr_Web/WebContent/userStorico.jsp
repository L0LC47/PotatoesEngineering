<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
    String currentUser = "";
    int privileges = Integer.MAX_VALUE;
    int pagePrivileges = 2;
    if(session.getAttribute("currentSessionUser") == null)
        response.sendRedirect("Login.jsp");
    else {
        currentUser = session.getAttribute("currentSessionUser").toString();
        privileges = Integer.parseInt(session.getAttribute("currentUserPrivileges").toString());
        if (privileges > pagePrivileges)
               response.sendRedirect("accessoNegato.jsp");
    }
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza storico</title>
</head>
<body>
	<p>Elenco dei veicoli nello storico</p>
	<p>Selezionare un veicolo per visualizzare le date in cui è stato
		utilizzato</p>

	<form action="userStoricoDate.jsp" method="POST">
		<table>
			<tr>
				<td>Guidatore</td>
				<td>Targa</td>
				<td>Modello</td>
				<td>Marca</td>
			</tr>
			<%
				List<Veicolo> listaVeicoli = Storico.getUserVeicoliUtente(currentUser);
			%>
			<%
				for(Veicolo veicolo : listaVeicoli){
			%>
			<tr>
				<td><%=veicolo.getGuidatore()%></td>
				<td><input type="submit" name="targa"
					value="<%=veicolo.getTarga()%>"></td>
				<td><%=veicolo.getModello()%></td>
				<td><%=veicolo.getMarca()%></td>
			</tr>
			<%
				session.setAttribute("veicoloSelezionato", veicolo.getTarga());
			%>
			<%
				}
			%>
		</table>

	</form>
</body>
</html>