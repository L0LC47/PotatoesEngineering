<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
    String currentUser = "";
    int privileges = Integer.MAX_VALUE;
    int pagePrivileges = 2;
    String pos = "";
    String currentVeicolo = "";
    java.sql.Date currentDate = null;
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
                response.sendRedirect("userPosizione.jsp");
            else {
                currentVeicolo = session.getAttribute(
                        "veicoloSelezionato").toString();
                currentDate = new java.sql.Date(
                        new java.util.Date().getTime());
                pos = Storico.getLastPos(currentVeicolo);
                System.out.println(pos);
            }
        }
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posizione attuale del veicolo</title>
<style>
html, body, #map-canvas {
	height: 400px;
	margin: 0px;
	padding: 0px
}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script>
    function initialize() {
        var myLatlng = new google.maps.LatLng(<%=pos%>);
        var mapOptions = {
            zoom : 4,
            center : myLatlng
        }
        var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);

        var marker = new google.maps.Marker({
            position : myLatlng,
            map : map,
            title : 'MyMap'
        });
    }

    google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<h3>
		Posizione attuale del veicolo selezionato:
		<%=pos %></h3>
	<div id="map-canvas"></div>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br> </br> </br> <input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>