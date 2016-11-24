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
    <c:when test="${not empty registrosCompras}">
        <h3>Compras de ${cliente.nome}:</h3>

        <c:forEach items="${registrosCompras}" var="registroCompra">
            <b>Código da Compra:</b> ${registroCompra.idRegistroCompra} <br>
            <b>Data da Compra: </b><fmt:formatDate value="${registroCompra.dataCompra.time}"
                                                   pattern="dd/MM HH:mm"/> <br>

            <b>Valor:</b>${registroCompra.valor} <br>

            <form action="mostrarInformacoesCompra" method="post">
                <button type="submit" name="idRegistroCompra"
                        value="${registroCompra.idRegistroCompra}"
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
