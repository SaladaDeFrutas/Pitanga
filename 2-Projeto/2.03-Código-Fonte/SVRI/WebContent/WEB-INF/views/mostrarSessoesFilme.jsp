<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<title>Sessões</title>
</head>
<body class = pagina>	
		<c:import url="cabecalho.jsp"/>
		<h3>Sessões disponíveis para ${filme.titulo}</h3>
		<c:forEach items="${sessoes}" var = "sessao">
			<form action= "escolherIngressos" method="post">
 			<button type="submit" name = "id" value ="${sessao.id}" class="btn btn-default"><fmt:formatDate	value = "${sessao.data.time}"
							pattern="dd/MM HH:mm" /></button>
			</form><br>
		</c:forEach>															
</body>
</html>