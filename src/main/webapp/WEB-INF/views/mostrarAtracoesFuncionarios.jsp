<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <title>Atra��es</title>
</head>
<body class=pagina>
<c:import url="cabecalhoFuncionarios.jsp"/>


<c:if test="${not empty filmes}">
    <h3>Filmes:</h3>
    <c:forEach items="${filmes}" var="filme">

        <b>T�tulo:</b> ${filme.titulo} <br>
        <b>Sinopse: </b>${filme.sinopse} <br>
        <b>Genero:</b>${filme.genero} <br>
        <b>Duracao:</b>${filme.duracao} min.<br>

        <form action="alteracaoFilmeFuncionarios" method="post">
            <button type="submit" name="idAtracao" value="${filme.idAtracao}"
                    class="btn btn-default">Alterar Filme
            </button>
        </form>

        <form action="exclusaoFilmeFuncionarios" method="post">
            <button type="submit" name="idAtracao" value="${filme.idAtracao}"
                    class="btn btn-default"
                    onClick="return confirm('Deseja mesmo excluir?')">Excluir Filme
            </button>
        </form>
        <br>
    </c:forEach>
</c:if>


<c:if test="${not empty pecas}">
    <h3>Pe�as:</h3>
    <c:forEach items="${pecas}" var="peca">

        <b>Titulo:</b> ${peca.titulo}<br>
        <b>Sinopse: </b>${peca.sinopse}<br>
        <b>Genero:</b>${peca.genero}<br>
        <b>Duracao:</b>${peca.duracao} min.<br>

        <form action="alteracaoPecaFuncionarios" method="post">
            <button type="submit" name="idAtracao" value="${peca.idAtracao}"
                    class="btn btn-default">Alterar Pe�a
            </button>
        </form>

        <form action="exclusaoPecaFuncionarios" method="post">
            <button type="submit" name="idAtracao" value="${peca.idAtracao}" class="btn btn-default"
                    onClick="return confirm('Deseja mesmo excluir?')">Excluir Pe�a
            </button>
        </form>
        <br>
    </c:forEach>
</c:if>

<c:if test="${empty pecas and empty filmes}"><h3>N�o h� atra��es cadastradas</h3></c:if>
</body>
</html>
