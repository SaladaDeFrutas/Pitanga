<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.datetimepicker.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />
<link rel="stylesheet"
 href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
<script
 src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
<title>Alterando Filme</title>
<style> 
	d{right: 0px;}
</style>
</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />
	<br>
	<h3 class = pagina>Alteração de Filme</h3>
	
	<form class = pagina action = "alterarFilmeFuncionarios" method="post">
		<input type ="hidden" name="idAtracao" value="${filme.idAtracao}">
		
		<form:errors path="filme.titulo" /><br>
		<label for="usr">Título:</label>
      	<input type="text" class="form-control" id="titulo" name="titulo" value="${filme.titulo}">
		
		<form:errors path="filme.idioma" /><br>
		<label for="usr">Idioma:</label>
      	<input type="text" class="form-control" id="idioma" name="idioma" value="${filme.idioma}">
		
		<form:errors path="filme.duracao" /><br>
		Duração (em minutos):<br><input type = "text" name = "duracao" value="${filme.duracao}"><br>
		
		<form:errors path="filme.sinopse" /><br>
		<br><label for="sinopse">Sinopse:</label>
      	<textarea class="form-control" rows="4" id="sinopse" name="sinopse">${filme.sinopse}</textarea><br>
		
		<form:errors path="filme.genero" /><br>
		 Gênero:<br>
		 	<select class="selectpicker" name = "genero">	 
		 		<c:choose>
		 			<c:when test="${filme.genero =='Acao'}">
		 				<option value = "Acao" selected="selected">Ação</option>
		 				<option value = "Comedia">Comédia</option>
						 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${filme.genero =='Comedia'}">
		 				<option value = "Acao">Ação</option>
		 				<option value = "Comedia" selected="selected">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${filme.genero =='Drama'}">
		 				 <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama" selected="selected">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${filme.genero =='Suspense'}">
		 				  <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense" selected="selected">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${filme.genero =='Terror'}">
		 				 <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror" selected="selected">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${filme.genero =='Ficcao Cientifica'}">
		 				  <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica" selected="selected">Ficção Científica</option>
		 			</c:when>
		 			<c:otherwise>
		 				  <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:otherwise>
		 		</c:choose>
				 
			</select>
		<br>
		
		<form:errors path="filme.classificacaoIndicativa" /><br>
		 Classificação Indicativa:<br>
		 	<select class="selectpicker" name = "classificacaoIndicativa">
				 <c:choose>
		 			<c:when test="${filme.classificacaoIndicativa == '0'}">
		 				 <option value = "0" selected="selected">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${filme.classificacaoIndicativa == '10'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10" selected="selected">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${filme.classificacaoIndicativa == '12'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12" selected="selected">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${filme.classificacaoIndicativa == '14'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14" selected="selected">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${filme.classificacaoIndicativa == '16'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16" selected="selected">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${filme.classificacaoIndicativa == '18'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18" selected="selected">18</option>
		 			</c:when>
		 		</c:choose>
			</select>
		<br>
		
		<form:errors path="filme.dataEstreia" /><br>
		Data de Estreia: <br>
		            <div class="form-group">
		                <div class='input-group date' id='dataEstreia-div'>
		                    <input type='text' class="form-control" name="dataEstreia"
		                    id="dataEstreia" value="<fmt:formatDate	value = "${filme.dataEstreia.time}"
							pattern="dd/MM/yyyy" />"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
		           
		        <script type="text/javascript">
		            $(function () {
		                $('#dataEstreia').datetimepicker();
		            });
		        </script>
		    </div>
		<br>
		
		<form:errors path="filme.legendado" /><br>
		 Legendado/Dublado:<br>
		 	<select class="selectpicker" name = "legendado">
		 		<c:choose>
			 		<c:when test="${filme.legendado == 'true'}">
			 			<option value = "true" selected="selected">Legendado</option>
			 			<option value = "false">Dublado</option>
			 		</c:when>
					 <c:otherwise>
					 	 <option value = "true">Legendado</option>
					 	 <option value = "false" selected="selected">Dublado</option>
					 </c:otherwise>
				</c:choose>
			</select>
		<br>
		
		<form:errors path="filme.modoDeExibicao" /><br>
		Modo de Exibição:<br><input type = "text" name = "modoDeExibicao" value="${filme.modoDeExibicao }"><br>
		
		<form:errors path="filme.produtora" /><br>
		Produtora:<br><input type = "text" name = "produtora" value="${filme.produtora }"><br>
		
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