<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<title>Sessões de ${peca.titulo}</title>
</head>
<body class = pagina>
		<c:import url="cabecalho.jsp"/>
		<c:choose>
		<c:when test="${not empty sessoes}">
		<h3>Sessões disponíveis para ${peca.titulo}</h3>
		<c:forEach items="${sessoes}" var = "sessao">
			<form action= "escolherIngressos" method="post">
 			<button type="submit" name = "id" value ="${peca.id}" class="btn btn-default"><fmt:formatDate	value = "${sessao.data.time}"
							pattern="dd/MM HH:mm" /></button>
			</form><br>
		</c:forEach>
		</c:when>
		<c:otherwise>
		<h3>Não há sessões disponíveis para ${peca.titulo}</h3>
		</c:otherwise>	
		</c:choose>															
</body>
</html>