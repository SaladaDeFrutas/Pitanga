<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Informações da compra</title>
</head>
<body>
	<c:import url="cabecalho.jsp"/>
	
	<form action= "gerarComprovante" method="post">
	
	ID da compra: ${transacaoCompra.reference}
			
	Data da compra: <fmt:formatDate	value = "${transacaoCompra.date.time}"
							pattern="dd/MM HH:mm" /> 
			
	Valor: ${transacaoCompra.netAmount} Em ${transacaoCompra.installmentCount} parcelas
	
	<br> 
		Status do pagamento: ${registroCompra.pagamentoAprovado}
	<br> 
	
	<h3>Ingressos Comprados:</h3>
	
	<table>
		<c:forEach items = "${ingressosCompra}" var = "ingresso">
		<tr>
			<td>Tipo: ${ingresso.umTipoIngresso}</td>
		</tr>
		</c:forEach>
	</table>
		 			<button type="submit" name = "gerarComprovante" value ="${registroCompra.idRegistroCompra}"
		 				class="btn btn-default">Gerar Comprovante Compra
		 			</button>
	</form>	
	
</body>
</html>