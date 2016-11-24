<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<title>Tipos de ingresso</title>
</head>
<body class = pagina>
	<c:import url="cabecalhoFuncionarios.jsp"/>
	
	<h3>Tipos de ingresso:</h3>
	<c:if test="${not empty tiposIngresso}">
	<c:forEach items="${tiposIngresso}" var = "tipoIngresso">
	
		<b>Nome:</b> ${tipoIngresso.nome}
		<b>Preco: </b>${tipoIngresso.preco} <br>
	
	
		<form action= "alteracaoTipoIngressoFuncionarios" method="post">
	 			<button type="submit" name = "nome" value ="${tipoIngresso.nome}" class="btn btn-default">Alterar Tipo de ingresso
	 			</button>
		</form>
	
		<form action= "exclusaoTipoIngressoFuncionarios" method="post">
	 			<button type="submit" name = "nome" value ="${tipoIngresso.nome}" class="btn btn-default" 
	 				onClick="return confirm('Deseja mesmo excluir?')">Excluir Tipo de ingresso
	 			</button>
		</form>
	<br>
	</c:forEach>
	</c:if>
</body>
</html>
