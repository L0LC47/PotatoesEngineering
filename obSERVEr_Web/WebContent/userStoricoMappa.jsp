<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
    String currentUser = "";
int privileges = Integer.MAX_VALUE;
int pagePrivileges = 2;
String currentVeicolo = "";
Date currentDate = null;
int count = 0;
String head = "", tail = "";
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
               System.out.println(currentDate);
            }       
        }
    }
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posizione del veicolo nella data selezionata</title>
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
    var directionDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;

    function initialize() {
        directionsDisplay = new google.maps.DirectionsRenderer({
            suppressMarkers : true
        });

        var myOptions = {
            zoom : 3,
            mapTypeId : google.maps.MapTypeId.ROADMAP,
        }

        map = new google.maps.Map(document.getElementById("map-canvas"),
                myOptions);
        directionsDisplay.setMap(map);

        var waypts = [];
<%List<String> storico = Storico.getStorico(currentVeicolo,
                currentDate);%>
    
<%for (String s : storico) {
            if(count == 0)
                head = s;
            if(count == storico.size()-1){
                tail = s;%>
<%}%>
    stop = new google.maps.LatLng(
<%=s%>
    )
        waypts.push({
            location : stop,
            stopover : true
        });
<%count++;}%>
    start = new google.maps.LatLng(
<%=head%>
    );
        end = new google.maps.LatLng(
<%=tail%>
    );

        createMarker(start);

        var request = {
            origin : start,
            destination : end,
            waypoints : waypts,
            optimizeWaypoints : true,
            travelMode : google.maps.DirectionsTravelMode.DRIVING
        };

        directionsService.route(request, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                var route = response.routes[0];
            }
        });
    }

    function createMarker(latlng) {

        var marker = new google.maps.Marker({
            position : latlng,
            map : map
        });
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    google.maps.event.trigger(map, 'resize');
</script>
</head>
<body>
	<h3>Percorso effettuato dal veicolo nella data selezionata</h3>
	<div id="map-canvas"></div>

	<form>
		<table>
			<tr>
				<td>Posizioni rilevate del veicolo nella data selezionata</td>
			</tr>

			<%
                for (String s : storico) {
            %>
			<tr>
				<td><%=s%></td>
			</tr>
			<%
                }
            %>
		</table>
	</form>
	</br>
	</br>
	</br>
	<a href="userLogged.jsp">Torna alla Home</a>
	<form action="LogoutServlet" method="POST">
		</br> </br> </br> <input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>