<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<title>Sess�es</title>
</head>
<body class = pagina>	
		<c:import url="cabecalhoFuncionarios.jsp"/>
		<c:if test="${not empty sessoes}">
		<h3>Sess�es dispon�veis para ${peca.titulo}</h3>
		<c:forEach items="${sessoes}" var = "sessao">
			<form action= "alteracaoSessaoPecaFuncionarios" method="post">
			<b> Data e Hora: </b><fmt:formatDate	value = "${sessao.data.time}"
							pattern="dd/MM/yyyy HH:mm" /> 
			<b>Sala:</b> ${ sessao.sala.id}<br>
 			<button type="submit" name = "idSessao" value ="${sessao.idSessao}" class="btn btn-default"> Alterar Sess�o</button>
			</form>
			<form action= "exclusaoSessaoFuncionarios" method="post">
 			<button type="submit" name = "idSessao" value ="${sessao.idSessao}" class="btn btn-default"
 			onClick="return confirm('Deseja mesmo excluir?')"> Excluir Sess�o</button>
			</form><br>
		</c:forEach>
		</c:if>
		
		<c:if test="${empty sessoes}"><h3>N�o h� sess�es cadastradas para esta pe�a.</h3></c:if>														
</body>
</html>
