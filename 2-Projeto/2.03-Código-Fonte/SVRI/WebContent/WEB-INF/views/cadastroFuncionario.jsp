<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrando Funcionário</title>

</head>
<body>
<c:import url="cabecalho.jsp" />

	<h3 class = pagina>Cadastro Funcionário</h3>
	
	<form class = pagina action = "cadastrarFuncionarioslogin" method="post">
		<form:errors path="funcionario.nome" /><br>
		Nome: <br><input type = "text" name = "nome"><br>
		<form:errors path="funcionario.email" /><br>
		Email: <br><input type = "text" name = "email"><br>
		<form:errors path="funcionario.senha" /><br>
		Senha:
		<br><input type = "password" name = "senha"><br>
		<form:errors path="funcionario.dataDeNascimento" /><br>
		Data de Nascimento: <br><jsTag:campoData id="dataDeNascimento"/><br>
		Nível de acesso: <br>
		<select class="selectpicker" name = "nivelAcesso">
						 <option value = "2">Gerente</option>
						 <option value = "3">Admin</option>
					</select><br>
		Matrícula: <br><input type = "text" name = matricula><br>			
		Função: <br><input type = "text" name="funcao">			
		<br><br><button type="submit" class="btn btn-default">Enviar </button>
	</form>

</body>
</html>