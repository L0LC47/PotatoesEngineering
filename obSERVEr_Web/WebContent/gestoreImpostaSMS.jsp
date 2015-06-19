<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"%>
<%
	String currentUser = "";
	int privileges = Integer.MAX_VALUE;
	int pagePrivileges = 1;
	if (session.getAttribute("currentSessionUser") == null)
		response.sendRedirect("Login.jsp");
	else {
		currentUser = session.getAttribute("currentSessionUser")
				.toString();
		privileges = Integer.parseInt(session.getAttribute(
				"currentUserPrivileges").toString());
		if (privileges > pagePrivileges)
			response.sendRedirect("accessoNegato.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Impostazione invio SMS e e-mail di posizione</title>
</head>
<body>
	Benvenuto nell'area riservata a gestori flotta e amministratori
	<%=currentUser%>.


	<form action="SMSServlet" method="POST">
		<table order="1" cellpadding="1" cellspacing="5">
			<thead>
				<tr>
					<th scope="col">obSERVEr</th>
					<th scope="col">Targa</th>
					<th scope="col">Marca</th>
					<th scope="col">Modello</th>
					<th scope="col">Intervallo SMS/e-mail</th>
					<th scope="col">Modifica</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Veicolo_observer vo : Veicolo_observer
							.getAssociazioniCorrenti()) {
				%>
				<%
					Veicolo v = Veicolo.getVeicoloData(vo.getTarga());
						int SMS = Observer.getSMS(vo.getSerial());
						String smsString = "there is no spoon!";
						if (SMS == 0)
							smsString = "Disattivato";
						if (SMS == 30)
							smsString = "30 minuti";
						if (SMS == 1)
							smsString = "1 Ora";
						if (SMS == 3)
							smsString = "3 Ore";
						if (SMS == 6)
							smsString = "6 Ore";
						if (SMS == 12)
							smsString = "12 Ore";
						if (SMS == 24)
							smsString = "24 Ore";
				%>
				<tr>
					<td><%=vo.getSerial()%></td>
					<td><%=v.getTarga()%></td>
					<td><%=v.getMarca()%></td>
					<td><%=v.getModello()%></td>
					<td><%=smsString%></td>
					<td style="text-align: center;"><input name="rdbSelezione"
						type="radio" value="<%=vo.getSerial()%>" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<hr>
		<select name='intervallo' required>
			<option value='0'>Disattivato
			<option value='30'>30 minuti
			<option value='1'>1 ora
			<option value='3'>3 ore
			<option value='6'>6 ore
			<option value='12'>12 ore
			<option value='24'>24 ore
		</select> <input type="submit" name="btnMode" value="Modifica">
		</f
			orm>

		<%=(request.getAttribute("messaggio") == null) ? ""
					: request.getAttribute("messaggio").toString()%>
		<hr>
		<a href="userLogged.jsp">Torna alla Home</a>
		<hr>
		<form action="LogoutServlet" method="POST">
			<input type="submit" name="Logout" value="Logout">
		</form>
		<hr>
</body>
</html>