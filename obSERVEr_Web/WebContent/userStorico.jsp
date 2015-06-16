<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.univr.is.observer.persistenza.*"
	import="java.util.*"%>
<%
    String currentUser = "";
    int privileges = Integer.MAX_VALUE;
    int pagePrivileges = 2;
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
<title>Visualizza storico</title>
</head>
<body>
    <h3>Elenco dei veicoli nello storico</h3>
    <p>Selezionare un veicolo per visualizzare le date in cui è stato
        utilizzato</p>

    <form action="StoricoServlet" method="POST">
        <table>
            <tr>
                <td>Targa</td>
                <td>Guidatore</td>
                <td>Marca</td>
                <td>Modello</td>

            </tr>
            <%--
            creare altra query per restituire anche guidatore 
            --%>
            <%
                List<Veicolo> listaVeicoli = Usr.getUserVeicoliUtente(currentUser);
            %>
            <%
                for (Veicolo veicolo : listaVeicoli) {
            %>
            <tr>
                <td><input type="submit" name="targa"
                    value="<%=veicolo.getTarga()%>"></td>
                <td><%=veicolo.getGuidatore()%></td>
                <td><%=veicolo.getMarca()%></td>
                <td><%=veicolo.getModello()%></td>

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
        </br>
        </br>
        </br> <input type="submit" name="Logout" value="Logout">
    </form>
</body>
</html>