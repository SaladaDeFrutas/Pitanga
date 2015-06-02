<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />
<title>Cadastrar Atrações</title>
</head>
	<c:import url="cabecalhoFuncionarios.jsp"/>
<body class = pagina>
	
	 <h3>Cadastrar Atrações</h3> <br>

	<form action= "cadastroFilmeFuncionarios" method="post">
 		<button type="submit" name = "submitCadastrarFilme" value ="" class="btn btn-default">Cadastrar Filmes
 		</button>	
	</form>
	<br><br>
	<form action= "cadastroPecaFuncionarios" method="post">
 		<button type="submit" name = "submitCadastrarPeca" value ="" class="btn btn-default">Cadastrar Pecas
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