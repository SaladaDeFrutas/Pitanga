<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<title>Salas</title>
</head>
<body class = pagina>
	<c:import url="cabecalhoFuncionarios.jsp"/>
	
	<h3>Salas:</h3>
	<c:if test="${not empty salas}">
	<c:forEach items="${salas}" var = "sala">
	
		<b>Sala </b> ${sala.id}
		<b>Número de Fileiras: </b>${sala.qntFileiras} <br>
		<b>Número de Colunas: </b>${sala.qntColunas} <br>
	
	
		<form action= "alteracaoSalaFuncionarios" method="post">
	 			<button type="submit" name = "id" value ="${sala.id}" class="btn btn-default">Alterar Sala
	 			</button>
		</form>
	
		<form action= "exclusaoSalaFuncionarios" method="post">
	 			<button type="submit" name = "id" value ="${sala.id}" class="btn btn-default" 
	 				onClick="return confirm('Deseja mesmo excluir?')">Excluir Sala
	 			</button>
		</form>
	<br>
	</c:forEach>
	</c:if>
</body>
</html>