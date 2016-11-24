<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="cabecalhoFuncionarios.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="/css/rodapeFuncionarios.css" rel="stylesheet" />
<title>Gerenciar Tipos de Ingresso</title>
</head>

<body class = pagina>
	
	<h3>Gerenciar Tipos de Ingresso</h3> <br>
	<form action= "cadastroTipoIngressoFuncionarios" method="post">
 		<button type="submit" name = "submitCadastrar" value ="" class="btn btn-default">Cadastrar tipo de ingresso
 		</button>	
	</form>
	<br><br>
	<form action= "mostrarTipoIngressoFuncionarios" method="post">
 		<button type="submit" name = "submitMostrar" value ="" class="btn btn-default">Mostrar tipos de ingresso
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
