<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cadastro bem sucedido !</title>
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>

</head>
<body class=pagina>
<c:import url="cabecalho.jsp"/>

Cadastro realizado com sucesso.<br><br>

<form action="login" method="post">
    <button type="submit" class="btn btn-default">Fazer Login</button>

</form>
</body>
</html>
