<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <title>Atracoes</title>
</head>
<body class=pagina>
<c:import url="cabecalho.jsp"/>
<c:if test="${not empty filmes}">
    <h3>Filmes:</h3>
    <c:forEach items="${filmes}" var="filme">
        <b>Título:</b> ${filme.titulo} <br>
        <b>Sinopse: </b>${filme.sinopse} <br>
        <b>Genero:</b>${filme.genero} <br>
        <b>Duracao:</b>${filme.duracao} min.<br>
        <b>Idioma:</b>${filme.idioma} <br>
        <b>Data de Estreia:</b> <fmt:formatDate value="${filme.dataEstreia.time}"
                                                pattern="dd/MM/yyyy"/> <br>
        <b>Classificação Indicativa:</b>
        <c:choose>
            <c:when test="${filme.classificacaoIndicativa == '0'}">
                Livre
            </c:when>
            <c:otherwise>
                ${filme.classificacaoIndicativa} Anos
            </c:otherwise>
        </c:choose>
        <br>
        <b>Modo de Exibição:</b>
        <c:choose>
            <c:when test="${filme.legendado}">
                Legendado
            </c:when>
            <c:otherwise>
                Dublado
            </c:otherwise>
        </c:choose>

        ${filme.modoDeExibicao} <br>

        <form action="mostrarSessoesFilme" method="post">
            <button type="submit" name="idAtracao" value="${filme.idAtracao}"
                    class="btn btn-default">Visualizar Sessões
            </button>
            <input type="hidden" name=titulo value="${filme.titulo}">
        </form>
    </c:forEach>
</c:if>

<c:if test="${not empty pecas}">
    <h3>Peças:</h3>
    <c:forEach items="${pecas}" var="peca">
        <b>Titulo:</b> ${peca.titulo}<br>
        <b>Sinopse: </b>${peca.sinopse}<br>
        <b>Genero:</b>${peca.genero}<br>
        <b>Duracao:</b>${peca.duracao} min.<br>
        <b>Idioma:</b>${peca.idioma} <br>
        <b>Data de Estreia:</b> <fmt:formatDate value="${peca.dataEstreia.time}"
                                                pattern="dd/MM/yyyy"/> <br>
        <b>Classificação Indicativa:</b>
        <c:choose>
            <c:when test="${peca.classificacaoIndicativa == '0'}">
                Livre
            </c:when>
            <c:otherwise>
                ${peca.classificacaoIndicativa} Anos
            </c:otherwise>
        </c:choose>
        <br>

        <form action="mostrarSessoesPeca" method="post">
            <button type="submit" name="idAtracao" value="${peca.idAtracao}"
                    class="btn btn-default">Visualizar Sessões
            </button>
            <input type="hidden" name=titulo value="${peca.titulo}">
        </form>
    </c:forEach>
</c:if>

<c:if test="${empty pecas and empty filmes}"><h3>Não há atrações cadastradas.</h3></c:if>
</body>
</html>
