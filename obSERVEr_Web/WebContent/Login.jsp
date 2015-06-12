<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>obSERVEr - Home</title>
</head>

<body>
	<h1>obSERVEr - Sistema per la localizzazionE in tempo Reale del
		VEicolo</h1>
	La principale preoccupazione di un automobilista è subire il furto del
	proprio mezzo. Nonostante la vasta gamma di antifurti offerti dalle
	case automobilistiche si può avvertire la necessità di un ulteriore
	controllo sulla posizione dei propri veicoli. Inoltre la gestione e il
	controllo di grandi quantità di veicoli (flotte aziendali, veicoli a
	noleggio, ... ) risulta spesso problematico e oneroso in termini di
	tempo e risorse. Il sistema obSERVEr fornisce uno strumento semplice e
	versatile per il controllo in tempo reale della posizione del veicolo
	ed il monitoraggio della velocità dello stesso.
	<br>
	<h2>LOGIN</h2>
	<form action="LoginServlet" method="POST">
		<br />Email:
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
			type="email" name="user" required
			placeholder="Inserisci un indirizzo email"> <br> <br />Password:
		&nbsp;&nbsp;&nbsp;&nbsp; <input type="password" name="pw" required
			placeholder="Inserisci la tua password"> <br> <br /> <input
			type="submit" value="Invia">
	</form>
</body>
</html>