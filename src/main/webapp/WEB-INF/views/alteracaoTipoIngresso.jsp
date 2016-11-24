<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="/css/rodapeFuncionarios.css" rel="stylesheet" />
<link rel="stylesheet"
 href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
<script
 src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
<title>Alterando Peca</title>
<style> 
	d{right: 0px;}
</style>
</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />
	<br>
	<h3 class = pagina>Altera��o de Tipo Ingresso</h3>
	
	<form class = pagina action = "alterarTipoIngressoFuncionarios" method="post">
		
		<form:errors path="peca.titulo" /><br>
		<label for="usr">Nome:</label>
      	<input type="text" class="form-control" id="nome" name="nome" value="${tipoIngresso.nome}">
		
		<form:errors path="peca.idioma" /><br>
		 <label for="usr">Preco:</label>
      	<input type="text" class="form-control" id="preco" name="preco" value="${tipoIngresso.preco}">

		<br><br><button type="submit" class="btn btn-default">Salvar </button>
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
