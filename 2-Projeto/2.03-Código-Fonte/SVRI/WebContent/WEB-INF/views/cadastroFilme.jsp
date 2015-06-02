<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<link type="/text/css" href="resources/jquery/jquery.datetimepicker.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.datetimepicker.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet"
 href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
<script
 src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />
<title>Cadastrando Filmes</title>
<style> 
	d{right: 0px;}
</style>
</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />
	<br>
	<h3 class = pagina>Cadastro de Filmes</h3>
	
	<form class = pagina action = "cadastrarFilmeFuncionarios" method="post">
		<form:errors path="filme.titulo" /><br>
		<label for="usr">Título:</label>
      	<input type="text" id="titulo" name="titulo">
		
		<form:errors path="filme.idioma" /><br>
		 <label for="usr">Idioma:</label>
      	<input type="text" id="idioma" name="idioma">
		
		<form:errors path="filme.duracao" /><br>
		Duração (em minutos):<br><input type = "text" name = "duracao"><br>
		
		<form:errors path="filme.sinopse" /><br>
		<br><label for="sinopse">Sinopse:</label>
      	<textarea class="form-control" rows="4" id="sinopse" name="sinopse"></textarea><br>
		
		<form:errors path="filme.genero" /><br>
		 Gênero:<br>
		 	<select class="selectpicker" name = "genero">
				 <option value = "Acao">Ação</option>
				 <option value = "Comedia">Comédia</option>
				 <option value = "Drama">Drama</option>
				 <option value = "Suspense">Suspense</option>
				 <option value = "Terror">Terror</option>
				 <option value = "Ficcao Cientifica">Ficção Científica</option>
			</select>
		<br>
		
		<form:errors path="filme.classificacaoIndicativa" /><br>
		 Classificação Indicativa:<br>
		 	<select class="selectpicker" name = "classificacaoIndicativa">
				 <option value = "0">Livre</option>
				 <option value = "10">10</option>
				 <option value = "12">12</option>
				 <option value = "14">14</option>
				 <option value = "16">16</option>
				 <option value = "18">18</option>
			</select>
		<br>
		
		<form:errors path="filme.dataEstreia" /><br>
		Data de Estreia: <br>
		<div class="container">
		    <div class="row">
		        <div class='col-sm-6'>
		            <div class="form-group">
		                <div class='input-group date' id='dataEstreia'>
		                    <input type='text' class="form-control" name="dataEstreia" value="11/11/1111" />
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
		            </div>
		        </div>
		        <script type="text/javascript">
		            $(function () {  
		                $('#dataEstreia').datetimepicker();
		            });
		        </script>
		    </div>
		</div>
		<br>
		
		<form:errors path="filme.legendado" /><br>
		 Legendado/Dublado:<br>
		 	<select class="selectpicker" name = "legendado">
				 <option value = "true">Legendado</option>
				 <option value = "false">Dublado</option>
			</select>
		<br>
		
		<form:errors path="filme.modoDeExibicao" /><br>
		Modo de Exibição:<br><input type = "text" name = "modoDeExibicao"><br>
		
		<form:errors path="filme.produtora" /><br>
		Produtora:<br><input type = "text" name = "produtora"><br>
		
		<br><br><button type="submit" class="btn btn-default">Enviar </button>
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