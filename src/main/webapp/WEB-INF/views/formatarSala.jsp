<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <link type="/text/css" href="/css/escolherIngresso.css" rel="stylesheet"/>
    <link type="/text/css" href="/css/rodapeFuncionarios.css" rel="stylesheet"/>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
    <script type="text/javascript">
        function salvarFileira(element, i, j) {
            if ($(element).is(':checked')) {
                $("#assentos-coluna".concat(i, j)).html(
                    "<input type='hidden' name = 'assentos' value='" + i + "'/>"
                );
            }
            if (!$(element).is(':checked')) {
                $("#assentos-coluna".concat(i, j)).html("");
            }
        }

    </script>
    <title>Formatando Sala:</title>
</head>
<body class=pagina>
<c:import url="cabecalhoFuncionarios.jsp"/>

<h3 class=posicaoSala>Escolha o assentos que deseja remover: </h3>
<div class=posicaoSala>
    <form action="cadastrarSalaFuncionarios" method="post">
        <table>
            <c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"/>
            <c:set var="alphabetsplit" value="${fn:split(alphabet, ',')}"/>

            <c:forEach var="i" begin="0" end="${sala.qntFileiras - 1}">
                <tr>
                    <td>
                        <c:out value="${alphabetsplit[i]}"/>
                    </td>
                    <c:forEach var="j" begin="0" end="${sala.qntColunas - 1}">
                        <td class=posicaoCadeira>
                            <img name="cadeira" id="cadeira" src="resources/assento_disponivel.gif">
                            <br/>
                            <input id="assentos-coluna${i}${j}" type="checkbox" name="assentos"
                                   value="${j}"
                                   onClick="salvarFileira(this,${i},${j})"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" name="qntFileiras" value="${sala.qntFileiras}"/>
        <input type="hidden" name="qntColunas" value="${sala.qntColunas}"/> <br>
        <button type="submit" name="idBotao" value="" class="btn btn-default">Salvar
        </button>
    </form>
</div>

<div id="wrap">
    <div id="push"></div>
</div>
<div id="footer">
    <div class="container">
        <p class="func"> Acesso para <a href="./">Compra de Ingresso</a></p>
    </div>
</div>
</body>
</html>
