<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <title>Funcion�rios</title>
</head>
<body class=pagina>
<c:import url="cabecalhoFuncionarios.jsp"/>

<h3>Funcion�rios:</h3>
<c:if test="${not empty funcionarios}">
    <c:forEach items="${funcionarios}" var="funcionario">

        <b>Nome:</b> ${funcionario.nome} <br>
        <b>Email: </b>${funcionario.email} <br>
        <b>N�vel de Acesso: </b>${funcionario.nivelAcesso} <br>
        <b>Fun��o:</b> ${funcionario.funcao}


        <form action="alteracaoFuncionarios" method="post">
            <button type="submit" name="email" value="${funcionario.email}" class="btn btn-default">
                Alterar Dados do Funcion�rio
            </button>
        </form>

        <form action="exclusaoFuncionarios" method="post">
            <button type="submit" name="email" value="${funcionario.email}" class="btn btn-default"
                    onClick="return confirm('Deseja mesmo excluir?')">
                Excluir Funcion�rio
            </button>
        </form>
        <br>
    </c:forEach>
</c:if>
</body>
</html>
