<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
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
<title>Alterando Peca</title>
<style> 
	d{right: 0px;}
</style>
</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />
	<br>
	<h3 class = pagina>Alteração de Peca</h3>
	
	<form class = pagina action = "alterarPecaFuncionarios" method="post">
		<input type ="hidden" name="idAtracao" value="${peca.idAtracao}">
		
		<form:errors path="peca.titulo" /><br>
		<label for="usr">Título:</label>
      	<input type="text" class="form-control" id="titulo" name="titulo" value="${peca.titulo}">
		
		<form:errors path="peca.idioma" /><br>
		 <label for="usr">Idioma:</label>
      	<input type="text" class="form-control" id="idioma" name="idioma" value="${peca.idioma}">
		
		<form:errors path="peca.duracao" /><br>
		Duração (em minutos):<br><input type = "text" name = "duracao" value="${peca.duracao}"><br>
		
		<form:errors path="peca.sinopse" /><br>
		<br><label for="sinopse">Sinopse:</label>
      	<textarea class="form-control" rows="4" id="sinopse" name="sinopse">${peca.sinopse}</textarea><br>
		
		<form:errors path="peca.genero" /><br>
		 Gênero:<br>
		 	<select class="selectpicker" name = "genero">	 
		 		<c:choose>
		 			<c:when test="${peca.genero =='Acao'}">
		 				<option value = "Acao" selected="selected">Ação</option>
		 				<option value = "Comedia">Comédia</option>
						 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${peca.genero =='Comedia'}">
		 				<option value = "Acao">Ação</option>
		 				<option value = "Comedia" selected="selected">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${peca.genero =='Drama'}">
		 				 <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama" selected="selected">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${peca.genero =='Suspense'}">
		 				  <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense" selected="selected">Suspense</option>
						 <option value = "Terror">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${peca.genero =='Terror'}">
		 				 <option value = "Acao">Ação</option>
		 				<option value = "Comedia">Comédia</option>
		 				 <option value = "Drama">Drama</option>
						 <option value = "Suspense">Suspense</option>
						 <option value = "Terror" selected="selected">Terror</option>
						 <option value = "Ficcao Cientifica">Ficção Científica</option>
		 			</c:when>
		 			<c:when test="${peca.genero =='Ficcao Cientifica'}">
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
		
		<form:errors path="peca.classificacaoIndicativa" /><br>
		 Classificação Indicativa:<br>
		 	<select class="selectpicker" name = "classificacaoIndicativa">
				 <c:choose>
		 			<c:when test="${peca.classificacaoIndicativa == '0'}">
		 				 <option value = "0" selected="selected">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${peca.classificacaoIndicativa == '10'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10" selected="selected">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${peca.classificacaoIndicativa == '12'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12" selected="selected">12</option>
						 <option value = "14">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${peca.classificacaoIndicativa == '14'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14" selected="selected">14</option>
						 <option value = "16">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${peca.classificacaoIndicativa == '16'}">
		 				 <option value = "0">Livre</option>
						 <option value = "10">10</option>
						 <option value = "12">12</option>
						 <option value = "14">14</option>
						 <option value = "16" selected="selected">16</option>
						 <option value = "18">18</option>
		 			</c:when>
		 			<c:when test="${peca.classificacaoIndicativa == '18'}">
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
		
		<form:errors path="peca.dataEstreia" /><br>
		Data de Estreia: <br>
		            <div class="form-group">
		                <div class='input-group date' id='dataEstreia-div'>
		                    <input type='text' class="form-control" name="dataEstreia"
		                    	 value="<fmt:formatDate	value = "${peca.dataEstreia.time}"
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
			
		<form:errors path="peca.diretor" /><br>
		Diretor:<br><input type = "text" name = "diretor" value="${peca.diretor}"><br>
		
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