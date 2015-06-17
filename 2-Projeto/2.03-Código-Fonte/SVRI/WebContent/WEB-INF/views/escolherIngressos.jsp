<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="/text/css" href="resources/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="resources/css/escolherIngresso.css" rel="stylesheet" />
<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/validaEscolherIngressos.js"></script>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet"
 href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/css/bootstrap-select.min.css">
<script
 src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.5/js/bootstrap-select.min.js"></script>
<title>Ingressos</title>
</head>
<body class = pagina>	
<c:import url="cabecalho.jsp"/>
<h3 >Ingressos:</h3>
<form action="lugares" method = "get" >
<table>
	<tr>
		<td>
			Tipo de Ingresso:
		</td>
		<td class = tipoIngressos>
			Quantidade
		</td>
	</tr>

	<c:forEach items = "${tiposIngressos}" var="tipoIngresso" varStatus="status">
			
		
			<tr>
				<td>
					${tipoIngresso.nome}
				</td>				
				<td class = tipoIngressos>
				
				 	<select class="form-control" name = "quantidadeIngresso" id="quantidadeIngresso${status.index }">
						 <option value = "0">0</option>
						 <option value = "1">1</option>
						 <option value = "2">2</option>
						 <option value = "3">3</option>
						 <option value = "4">4</option>
						 <option value = "5">5</option>
					</select>
				<input type="hidden" name = "nomeTipoIngresso" value = "${tipoIngresso.nome}">
				<!-- <input type = "text" name = "quantidadeIngresso"> -->
				</td>  
			</tr>
	</c:forEach>
</table>
	<br><button type="submit" class="btn btn-default" onClick="return validaEscolherIngressos(${fn:length(tiposIngressos)})">Enviar</button>
	<input type="hidden" name = "idSessao" value = "${sessao.idSessao}">
	
</form>

</body>
</html>