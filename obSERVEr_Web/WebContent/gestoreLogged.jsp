<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaElenco" scope="session"
	class="java.util.ArrayList"></jsp:useBean>
<%
	String currentUser = session.getAttribute("currentSessionUser")
			.toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area riservata - Gestore flotta</title>
</head>
<body>
	Benvenuto nell'area riservata ai gestori flotta
	<%=currentUser%>.
	</br>
	</br> Selezionare una funzionalità:
	<ul>
		<li><a href="userPosizione.jsp">Visualizza Posizione Veicoli</a></li>
		<li><a href="userAllarmi.jsp">Controlla Allarmi Velocità</a></li>
		<li><a href="userStorico.jsp">Visualizza Storico Viaggi
				Veicoli</a></li>
		<li><a href="userVideo.jsp">Visualizza Video</a></li>
		<li><a href="userStatistiche.jsp">Visualizza Statistiche</a></li>
		<li><a href="gestoreAssociaUtentiVeicoli.jsp">Associa Utente
				a Veicolo</a></li>
		<li><a href="gestoreImpostaAllarmi.jsp">Imposta Allarme
				Velocità</a></li>
	</ul>

</body>
</html>