<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html >
<html>
<head>
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<link type="/text/css" href="resources/jquery/jquery.datetimepicker.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet"
 href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
<script
 src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />
<script type="text/javascript" src="resources/js/validaSenha.js"></script>
<script type="text/javascript" src="resources/js/validaDataDeNascimento.js"></script>
<script type="text/javascript" src="resources/js/validaTexto.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrando Funcionário</title>

</head>
<body class = pagina>
<c:import url="cabecalhoFuncionarios.jsp" />

	<h3 class = pagina>Cadastro Funcionário</h3>
	
	<form class = pagina action = "cadastrarFuncionarios" method="post">
		<form:errors path="funcionario.nome" /><br>
		Nome: <br><input type = "text" name = "nome" id="nome"><br>
		<form:errors path="funcionario.email" /><br>
		Email: <br><input type = "text" name = "email"><br>
		<form:errors path="funcionario.senha" /><br>
		Senha:
		<br><input type = "password" name = "senha" id="senha"><br>
		<form:errors path="funcionario.dataDeNascimento" /><br>
		Data de Nascimento: <br><jsTag:campoData id="dataDeNascimento"/><br>
		Nível de acesso: <br>
		<select class="selectpicker" name = "nivelAcesso">
						 <option value = "2">Gerente</option>
						 <option value = "4">Atendente</option>
						  <option value = "5">Auxiliar</option>
					</select><br>
		<form:errors path="funcionario.matricula" /><br>
		Matrícula: <br><input type = "text" name = "matricula" id="matricula"><br>
		<form:errors path="funcionario.funcao" /><br>		
		Função: <br><input type = "text" name="funcao" id="funcao">			
		<br><br><button type="submit" class="btn btn-default" id="botaoCadastroFuncionario"
		onClick="return ((validaSenha() && validaTexto()) && validaDataDeNascimento())">Enviar </button>
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