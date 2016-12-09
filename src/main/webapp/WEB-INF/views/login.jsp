<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript" src="jquery/external/jquery/jquery.js"></script>
    <link type="text/css" href="<c:url value="/css/formatacaoPagina.css"/>" rel="stylesheet"/>
    <link type="text/css" href="<c:url value="css/rodapeFuncionarios.css"/>" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value="/js/validaLogin.js"/>"></script>
    <title>Login</title>
</head>
<body class=pagina>
<c:import url="cabecalho.jsp"/>

<div id="wrap">

    <h3>Login</h3>

    <c:url value="/login" var="loginUrl"/>
    <form:form name="form" role="form" action="${loginUrl}" method="post">
        <c:if test="${param.error != null}">
            <div class="alert alert-error">
                <b>Usuário ou senha inválidos!</b>
                ${param.error}
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <b>Você realizou logout com sucesso!</b>
            </div>
        </c:if>
        <div>
            <label for="email">Email</label>
            <input type="text" name="email" id="email">
        </div>
        <div>
            <label for="senha">Senha</label>
            <input type="password" name="senha" id="senha">
        </div>
        <div>
            <input type="checkbox" id="remember-me"/>
            <label for="remember-me">Lembrar de mim</label>
        </div>
        <div>
            <button type="submit" class="btn btn-default" id="botaoLogin">
                Fazer Login
            </button>
        </div>
    </form:form>

    <div id="push"></div>
</div>

</body>
</html>
