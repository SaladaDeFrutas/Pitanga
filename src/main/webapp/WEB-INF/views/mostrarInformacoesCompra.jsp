<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <title>Informações da compra</title>
</head>
<body class=pagina>
<c:import url="cabecalho.jsp"/>

<form action="gerarComprovante" method="post">
    <h3>Dados da Compra:</h3>
    <b>ID da compra:</b> ${transacaoCompra.reference} <br>

    <b>Data de recebimento da transação:</b> <fmt:formatDate
    value="${transacaoCompra.date}"
    pattern="dd/MM HH:mm"/> <br>

    <b>Valor: ${transacaoCompra.grossAmount}</b> <b> Dividido
    Em </b>${transacaoCompra.installmentCount}x

    <br>
    <b>Status do pagamento:</b> <c:choose>
    <c:when test="${compra.pagamentoAprovado}">
        <b>Aprovado</b>
    </c:when>
    <c:otherwise>
        <b>Pendente</b>
    </c:otherwise>

</c:choose>
    <br>

    <h3>Ingressos Comprados:</h3>

    <table>
        <c:forEach items="${ingressosCompra}" var="ingresso">
            <tr>
                <td><b>Tipo:</b> ${ingresso.umTipoIngresso.nome} </td>
                <td><b></b></td>
                <td><b>Preço:</b> ${ingresso.umTipoIngresso.preco} </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:choose>
        <c:when test="${compra.pagamentoAprovado}">
            <button type="submit" name="idRegistroCompra" value="${compra.id}"
                    class="btn btn-default">Imprimir ingressos
            </button>

        </c:when>
        <c:otherwise>
            A compra ainda não foi concluída, o comprovante com status pendente não garante seus ingressos.<br>
            <button type="submit" name="idRegistroCompra" value="${compra.id}"
                    class="btn btn-default">Gerar Comprovante
            </button>
        </c:otherwise>
    </c:choose>


</form>

</body>
</html>
