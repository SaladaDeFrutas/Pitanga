<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet"/>
    <link type="/text/css" href="resources/jquery/jquery.datetimepicker.css" rel="stylesheet"/>
    <script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
    <script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="resources/jquery/jquery.datetimepicker.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
    <script
        src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
    <link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet"/>
    <link type="/text/css" href="/css/rodapeFuncionarios.css" rel="stylesheet"/>
    <script type="text/javascript" src="resources/js/validaCadastroFilme.js"></script>
    <title>Cadastrando Filmes</title>
    <style>
        d {
            right: 0px;
        }
    </style>
</head>
<body class=pagina>
<c:import url="cabecalhoFuncionarios.jsp"/>
<br>
<h3 class=pagina>Cadastro de Filmes</h3>

<form class=pagina action="cadastrarFilmeFuncionarios" method="post">
    <form:errors path="filme.titulo"/><br>
    <label for="usr">T�tulo:</label><br>
    <input type="text" id="titulo" name="titulo"> <br>

    <form:errors path="filme.idioma"/><br>
    <label for="usr">Idioma:</label><br>
    <input type="text" id="idioma" name="idioma"> <br>

    <form:errors path="filme.duracao"/><br>
    Dura��o (em minutos):<br><input type="text" name="duracao" id="duracao"><br>

    <form:errors path="filme.sinopse"/><br>
    <br><label for="sinopse">Sinopse:</label>
    <textarea class="form-control" rows="4" id="sinopse" name="sinopse"></textarea><br>

    <form:errors path="filme.genero"/><br>
    G�nero:<br>
    <select class="selectpicker" name="genero">
        <option value="Acao">A��o</option>
        <option value="Comedia">Com�dia</option>
        <option value="Drama">Drama</option>
        <option value="Suspense">Suspense</option>
        <option value="Terror">Terror</option>
        <option value="Ficcao Cientifica">Fic��o Cient�fica</option>
    </select>
    <br>

    <form:errors path="filme.classificacaoIndicativa"/><br>
    Classifica��o Indicativa:<br>
    <select class="selectpicker" name="classificacaoIndicativa">
        <option value="0">Livre</option>
        <option value="10">10</option>
        <option value="12">12</option>
        <option value="14">14</option>
        <option value="16">16</option>
        <option value="18">18</option>
    </select>
    <br>

    <form:errors path="filme.dataEstreia"/><br>
    Data de Estreia: <br>
    <input type="text" name="dataEstreia" id="dataEstreia" value="11/11/1111"/>
    <br>

    <form:errors path="filme.legendado"/><br>
    Legendado/Dublado:<br>
    <select class="selectpicker" name="legendado">
        <option value="true">Legendado</option>
        <option value="false">Dublado</option>
    </select>
    <br>

    <form:errors path="filme.modoDeExibicao"/><br>
    Modo de Exibi��o:<br><input type="text" name="modoDeExibicao"
                                id="modoDeExibicao"><br>

    <form:errors path="filme.produtora"/><br>
    Produtora:<br><input type="text" name="produtora" id="produtora"><br>

    <br><br>
    <button type="submit" class="btn btn-default"
            onClick="return validaCadastroFilme()">Enviar
    </button>
</form>

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
