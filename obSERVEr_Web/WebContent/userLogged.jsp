<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaElenco" scope="session"
	class="java.util.ArrayList"></jsp:useBean>
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
<title>Area riservata - Utente</title>
</head>
<body>
	Benvenuto nell'area riservata agli utenti
	<%=currentUser%>.
	</br>
	</br> Selezionare una funzionalità:
	<ul>
		<%if(privileges == 0) {%>
		<li><a href="adminGestioneUtenti.jsp">Gestisci Utenti</a></li>
		<li><a href="adminGestioneVeicoli.jsp">Gestisci Veicoli</a></li>
		<li><hr></li>
		<%} if(privileges <= 1){ %>
		<li><a href="gestoreAssociaUtentiVeicoli.jsp">Associa Utente
				a Veicolo</a></li>
		<li><a href="gestoreImpostaAllarmi.jsp">Imposta Allarme
				Velocità</a></li>
		<li><a href="gestoreImpostaSMS.jsp">Imposta SMS e e-mail di posizione</a></li>
		<li><hr></li>
		<%} if(privileges <= 2) {%>
		<li><a href="userPosizione.jsp">Visualizza Posizione Veicoli</a></li>
		<li><a href="userAllarmi.jsp">Controlla Allarmi Velocità</a></li>
		<li><a href="userStorico.jsp">Visualizza Storico Viaggi
				Veicoli</a></li>
		<li><a href="userVideo.jsp">Visualizza Video</a></li>
		<li><a href="userStatistiche.jsp">Visualizza Statistiche</a></li>
		<li><hr></li>
		<li><a href="/files/obSERVEr_InformativaPrivacy.pdf" download><bold>Informativa sulla privacy</bold> </a></li>
		<%} else {%>
		There is no spoon
		<% } %>
	</ul>
	<hr>
	<form action="LogoutServlet" method="POST">
<input type="submit" name="Logout" value="Logout">
	</form>
	<hr>
</body>
</html>