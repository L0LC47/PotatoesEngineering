<%@ page isErrorPage = "true" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errore</title>
</head>
<body>
<h1>Ops, si è verificato un errore.</h1>
</br>
Se l'errore persiste contatta il supporto tecnico specificando questo messaggio:
<%=exception.getMessage() %>
</body>
</html>