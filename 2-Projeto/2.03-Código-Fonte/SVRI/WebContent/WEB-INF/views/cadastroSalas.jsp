<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jsTag" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/jquery/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/rodapeFuncionarios.css" rel="stylesheet" />

<title>Cadastro de Salas</title>

</head>
<body>
<c:import url="cabecalhoFuncionarios.jsp" />

	<h3 class = pagina>Cadastro de Salas</h3>
	
	<form class = pagina action = "dimensoesSalaFuncionarios" method="post">
		<form:errors path="sala.qntFileiras" /><br>
		<b>Quantidade de Fileiras:</b> <br><input type = "text" name = "qntFileiras"><br>
		<form:errors path="sala.qntColunas" /><br>
		<b>Quantidade de Colunas:</b> <br><input type = "text" name = "qntColunas"><br>
		<br><br><button type="submit" class="btn btn-default"> Formatar Sala </button>
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