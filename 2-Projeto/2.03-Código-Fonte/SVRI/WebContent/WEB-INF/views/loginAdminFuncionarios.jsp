<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<title>Login Administrador</title>
</head>
<body class = pagina>
<c:import url="cabecalho.jsp" />
	<h3>Login Administrador</h3>
	<form action = "efetuaLoginAdminFuncionarios" method="post">
		Email:
		<br><input type = "text" name = "email"><br />
		Senha:
		<br><input type = "password" name = "senha"><br />
		
		<br><button type="submit" class="btn btn-default">Fazer Login
 		</button>
	</form>
</body>
</html>