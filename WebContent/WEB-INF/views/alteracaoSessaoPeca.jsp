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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>Atrações e Sessões</title>
</head>
<body class = pagina>
	<c:import url="cabecalhoFuncionarios.jsp"/>
	<h3>Alteração Sessão</h3> <br>
	<form action= "alterarSessaoPecaFuncionarios" method="post">
		
		<c:if test="${not empty pecas}">
			<h4>Peça:</h4>
			<select class="selectpicker" name = "idAtracao">
					<c:forEach items="${pecas}" var = "peca">
					<c:choose>
							<c:when test="${peca.idAtracao == sessao.atracao.idAtracao}">
								<option value = "${peca.idAtracao}" selected="selected"> ${peca.titulo}</option>
							</c:when>
							<c:otherwise>
								<option value = "${peca.idAtracao}"> ${peca.titulo}</option>
							</c:otherwise>
					</c:choose>
					<br>
					</c:forEach>
			</select>
				<c:if test="${not empty salas}">
					<h4>Na sala:</h4>
					<select class="selectpicker" name = "sala.id">
					<c:forEach items="${salas}" var = "sala">
						<c:choose>
							<c:when test="${sala.id == sessao.sala.id}">
								<option value = "${sala.id}" selected="selected"> Sala ${sala.id}</option>
							</c:when>
							<c:otherwise>
								<option value = "${sala.id}" > Sala ${sala.id}</option>
							</c:otherwise>
							
						</c:choose>
						
					<br>
					
					</c:forEach>
					
					</select>
					<br> <br>
					<h4>Na data e hora:</h4> <br>
					<div class="container">
					                <div class='input-group date' id='data'>
					                	<form:errors path="sessao.data" />
					                    <input type="text" name="data" id="data" 
					                    value=" <fmt:formatDate	value = "${sessao.data.time}"
							pattern="dd/MM/yyyy HH:mm" />"/>
					                    
					                </div>
					        <script type="text/javascript">
					            $(function () {  
					                $('#data').datetimepicker();
					            });
					        </script>
					    </div>
					<br>
					<input type ="hidden" name="idSessao" value="${sessao.idSessao}">
					<button type="submit" class="btn btn-default"> Salvar Sessão </button>
				</c:if>
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