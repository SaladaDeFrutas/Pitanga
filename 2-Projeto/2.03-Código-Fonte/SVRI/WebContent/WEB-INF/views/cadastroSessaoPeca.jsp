<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
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
<script type="text/javascript" src="resources/js/validaDataHora.js"></script>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>Atrações e Sessões</title>
</head>
<body class = pagina>
	<c:import url="cabecalhoFuncionarios.jsp"/>
	<h3>Criar Sessão</h3> <br>
	<form action= "cadastrarSessaoPecaFuncionarios" method="post">
	
		<c:if test="${not empty pecas}">
			<h4>Peça:</h4>
			<select class="selectpicker" name = "idAtracao">
					<c:forEach items="${pecas}" var = "peca">
			
					<option value = "${peca.idAtracao}"> ${peca.titulo}</option>
			
				
					</c:forEach>
			</select>
				<c:if test="${not empty salas}">
					<h4>Na sala:</h4>
					<select class="selectpicker" name = "sala.id">
					<c:forEach items="${salas}" var = "sala">
			
					<option value = "${sala.id}"> Sala ${sala.id}</option>
					
					
					</c:forEach>
					
					</select>
					<br> <br>
					<h4>Na data e hora:</h4>
					<form:errors path="sessao.data" />
	                    <input type="text" name="data" id="data" value="11/11/1111 22:22"/>
					<br><br>
			
					<button type="submit" class="btn btn-default" id="botaoCadastrarSessaoPeca"
					onClick="return validaDataHora()"> Cadastrar Sessão </button>
				</c:if>
		</c:if>
			

		<br><br>
		<c:choose>
			<c:when test="${empty pecas}">
			<h4>Não há Peças cadastradas.</h4>
			</c:when>
		</c:choose>
		<br><br>
		<c:if test="${empty salas}">
			<h4>Nao há Salas cadastradas.</h4>
		</c:if>	
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