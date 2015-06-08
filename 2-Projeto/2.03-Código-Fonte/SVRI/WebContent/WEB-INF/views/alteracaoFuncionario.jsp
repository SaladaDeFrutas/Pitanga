<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />
<link type="/text/css" href="resources/jquery/jquery.datetimepicker.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.datetimepicker.js"></script>
<title>Alterando Dados do Funcionário</title>

</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />

	<h3 class = pagina>Alteração de Dados do Funcionário</h3>
	
	<form class = pagina action = "alterarFuncionarios" method="post">
		<form:errors path="funcionario.nome" /><br>
		Nome: <br><input type = "text" name = "nome" value="${funcionario.nome }"><br>
		<form:errors path="funcionario.email" /><br>
		Email: <br><input type = "text" name = "email" value="${funcionario.email}"><br>
		<form:errors path="funcionario.senha" /><br>
		Senha:
		<br><input type = "password" name = "senha" value="${funcionario.senha}"><br>
		<form:errors path="funcionario.dataDeNascimento" /><br>
		Data de Nascimento: <br><input type="text" name="dataDeNascimento" id="dataDeNascimento" 
					                    value=" <fmt:formatDate	value = "${funcionario.dataDeNascimento.time}"
							pattern="dd/MM/yyyy" />"/>
					                    <br>
					                      <script type="text/javascript">
								            $(function () {  
								                $('#data').datetimepicker();
								            });
								        </script>
		Nível de acesso: <br>
		<select class="selectpicker" name = "nivelAcesso">
						<c:choose> 
							<c:when test="${funcionario.nivelAcesso == '2' }">
								<option value = "2" selected="selected">Gerente</option>
						 		<option value = "4">Atendente</option>
						  		<option value = "5">Auxiliar</option>	
							</c:when>
							<c:when test="${funcionario.nivelAcesso == '4' }">
								<option value = "2">Gerente</option>
						 		<option value = "4"selected="selected">Atendente</option>
						  		<option value = "5">Auxiliar</option>	
							</c:when>
							<c:when test="${funcionario.nivelAcesso == '5' }">
								<option value = "2">Gerente</option>
						 		<option value = "4">Atendente</option>
						  		<option value = "5" selected="selected">Auxiliar</option>	
							</c:when>
							<c:otherwise>
								<option value = "2">Gerente</option>
						 		<option value = "4">Atendente</option>
						 		<option value = "5">Auxiliar</option>
							</c:otherwise>
						</c:choose>

					</select><br>
		<form:errors path="funcionario.matricula" /><br>
		Matrícula: <br><input type = "text" name = matricula  value="${funcionario.matricula }"><br>
		<form:errors path="funcionario.funcao" /><br>		
		Função: <br><input type = "text" name="funcao" value="${funcionario.funcao }">			
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