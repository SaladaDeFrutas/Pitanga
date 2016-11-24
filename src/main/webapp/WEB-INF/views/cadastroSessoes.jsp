<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="/css/rodapeFuncionarios.css" rel="stylesheet" />
<title>Cadastrar Sessões</title>
</head>
	<c:import url="cabecalhoFuncionarios.jsp"/>
<body class = pagina>
	
	 <h3>Cadastrar Sessão para Atrações</h3> <br>

	<form action= "cadastroSessoesFilmeFuncionarios" method="post">
 		<button type="submit" name = "submitCadastrarFilme" value ="" class="btn btn-default">Sessões para Filmes
 		</button>	
	</form>
	<br><br>
	<form action= "cadastroSessoesPecaFuncionarios" method="post">
 		<button type="submit" name = "submitCadastrarPeca" value ="" class="btn btn-default">Sessões para Pecas
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
</html>
