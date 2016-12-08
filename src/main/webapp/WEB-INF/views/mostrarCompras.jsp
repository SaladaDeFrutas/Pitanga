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
<c:choose>
    <c:when test="${not empty compras}">
        <h3>Compras de ${compras.nome}:</h3>

        <c:forEach items="${compras}" var="compra">
            <b>Código da Compra:</b> ${compra.id} <br>
            <b>Data da Compra: </b>${compra.dataCompra}<br>

            <b>Valor:</b>${compra.valor} <br>

            <form action="mostrarInformacoesCompra" method="post">
                <button type="submit" name="idRegistroCompra"
                        value="${compra.id}"
                        class="btn btn-default">Visualizar Compra
                </button>
            </form>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h3>Não há registros de compras suas, ${cliente.nome}.</h3>
    </c:otherwise>

</c:choose>
</body>
</html>
