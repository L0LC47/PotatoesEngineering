<%@ page import="pdtb.Database" %>
<%@page import="java.util.Vector"%> 
 <html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="stile.css" rel="stylesheet" type="text/css">	
	<title>Database Iceberg</title>
 </head>
 <body>
 <jsp:useBean id="connetti" scope="session" class="pdtb.Database" /> 
 <h3>Database Iceberg Proudly Made in Sparta
 <form name="esci" action="index.html">
	<input id="submit" type="submit" value="Disconnetti"/>
</form>
</h3>
 <div style="text-align:center">
	<%
	Database dbase = new Database("iceberg",request.getParameter("username"),request.getParameter("password"));
	dbase.connetti();
	request.setAttribute("dblog",dbase);
	if(dbase.isConnesso()==false) {
		String site = "errore_collegamento.html";
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
	else {
	%>
	
		<h2>Connessione al database riuscita.<br />
		Cosa si desidera fare? <br /></h2>
 </div>
		<div id="contenitore">
			<div id="div_log_sx">
				<div id="div_log"> 
				<h4>Ricerca utente e storico</h4>
					<form name="ricerut" action="ricerca_storico.jsp" method="post">
						Inserire il codice utente: <input name="cod_utente" type="text" /> <br />
						<input type="hidden" name="username" value="<%=request.getParameter("username")%>" />
						<input type="hidden" name="password" value="<%=request.getParameter("password")%>" />
						<br />
						<input id="submit" type="submit" value="Ricerca Utente"/>
					</form>
				</div>
				<br />
				<div id="div_log"> 
					<h4>Inserimento nuovo utente</h4>
					<form name="insUt" action="inserisci_utente.jsp" method="post">
						<div  id="testo_sx">
							<table>
							<tr>
								<td>
								Nome:
								</td>
								<td>
								<input name="nome_utente" type="text" />
								</td>
							</tr>
							<tr>
								<td>
								Cognome:
								</td>
								<td>
								<input name="cognome_utente" type="text" />
								</td>
							</tr>
							<tr>
								<td>
								Telefono: 
								</td>
								<td>
								<input name="tel_utente" type="text" />
								</td>
							</tr>
							<tr>
								<td>
								Indirizzo:
								</td>
								<td>
								 <input name="ind_utente" type="text" /> <br />
								</td>
							</tr>
							</table>
							<input type="hidden" name="username" value="<%=request.getParameter("username")%>" />
							<input type="hidden" name="password" value="<%=request.getParameter("password")%>" />
						</div>
						</br>
						<input id="submit" type="submit" value="Inserisci Utente"/>
					</form>
				</div>
			
			</div>
			<div id="div_log_dx">
				<div id="div_log"> 
				<h4>Ricerca problemi</h4>
					<form name="ricerpb" action="ricerca_problema.jsp" method="post">
						Inserire il codice problema: <input name="cod_problema" type="text" /> <br />
						<input type="hidden" name="username" value="<%=request.getParameter("username")%>" />
						<input type="hidden" name="password" value="<%=request.getParameter("password")%>" />
						<br />
						<input id="submit" type="submit" value="Ricerca Problema"/>
					</form>
				</div>
				<br />
				<div id="div_log"> 
				<h4>Inserimento Report Storico</h4> 
					<form name="insSt" action="inserisci_storico.jsp" method="post">
						<div  id="testo_sx">
							<table>
							<tr>
								<td>
								Inserire codice problema: 
								</td>
								<td>
								<input name="cod_problema" type="text" />
								</td>
							</tr>
							<tr>
								<td>
								Inserire codice utente:
								</td>
								<td>
								<input name="cod_problema" type="text" />
								</td>
							</tr>
							<tr>
								<td>
								Inserire eventuale soluzione data:
								</br>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<textarea name="soluz" rows="5" cols="60"> </textarea>
								</td>
							</tr>
							</table>
							<input type="hidden" name="username" value="<%=request.getParameter("username")%>" />
							<input type="hidden" name="password" value="<%=request.getParameter("password")%>" />
						</div>
						</br>
						<input id="submit" type="submit" value="Inserisci Storico"/>
					</form>
				</div>
			</div>
			<div id="div_fine">
			</div>
			
		</div>
		</br>
		<div id="div_log">
		CIAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
		</div>
		<%
	dbase.disconnetti();
	}
		%>
 </body>
 </html>
