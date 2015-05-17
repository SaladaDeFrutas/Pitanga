<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<title>Atracoes</title>
</head>
<body class = pagina>
	<c:import url="cabecalho.jsp"/>
	<c:if test="${not empty filmes}">
	<c:forEach items="${filmes}" var = "filme">
	<h3>Filmes:</h3>
	<b>Título:</b> ${filme.titulo} <br>
	<b>Sinopse: </b>${filme.sinopse} <br>
	<b>Genero:</b>${filme.genero} <br>
	<b>Duracao:</b>${filme.duracao} min.<br>
	
	<form action= "mostrarSessoesFilme" method="post">
 			<button type="submit" name = "id" value ="${filme.id}" class="btn btn-default">Visualizar Sessões
 	</button>
 	<input type = "hidden" name=titulo value="${filme.titulo}">
	</form>
	</c:forEach>
	</c:if>
	
	<c:if test="${not empty pecas}">
	<c:forEach items="${pecas}" var = "peca">
	<h3>Peças:</h3>
	<b>Titulo:</b> ${peca.titulo}<br>
	<b>Sinopse: </b>${peca.sinopse}<br>
	<b>Genero:</b>${peca.genero}<br>
	<b>Duracao:</b>${peca.duracao} min.<br>
	<form action= "mostrarSessoesPeca" method="post">
 		<button type="submit" name = "id" value ="${peca.id}" class="btn btn-default">Visualizar Sessões
 		</button>
 		<input type = "hidden" name=titulo value="${peca.titulo}">
	</form>
	</c:forEach>
	</c:if>
</body>
</html>