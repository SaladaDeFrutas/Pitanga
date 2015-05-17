<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />

<title>Cadastrando conta</title>
<style> 
	d{right: 0px;}
</style>
</head>
<body>
<c:import url="cabecalho.jsp" />

	<h3 class = pagina>Cadastro</h3>
	
	<form class = pagina action = "cadastrarCliente" method="post">
		<form:errors path="cliente.nome" /><br>
		Nome: <br><input type = "text" name = "nome"><br>
		<form:errors path="cliente.email" /><br>
		Email: <br><input type = "text" name = "email"><br>
		<form:errors path="cliente.senha" /><br>
		Senha:
		<br><input type = "password" name = "senha"><br>
		<form:errors path="cliente.dataDeNascimento" /><br>
		Data de Nascimento: <br><jsTag:campoData id="dataDeNascimento"/>
		<br><br><button type="submit" class="btn btn-default">Enviar </button>
	</form>

</body>
</html>