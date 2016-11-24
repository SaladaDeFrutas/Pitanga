<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="/text/css" href="/css/formatacaoPagina.css" rel="stylesheet" />
<link type="/text/css" href="/css/escolherIngresso.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="resources/jquery/external/jquery/jquery.js"></script>

<script type="text/javascript">
		var quantidadeRestante = ${qntIngressos};
		var maxAllowed;
		var cnt;
		$(document).ready(function () {
	    $("input[name='assentos']").change(function () {
	    	maxAllowed = ${qntIngressos};
	    	cnt = $("input[name='assentos']:checked").length;
	      if (cnt > maxAllowed)
	      {
	         $(this).prop("checked", "");
	     }
	     /*  if($("input[name='assentos.coluna']").is(':checked')){
	    	  $("#assentos.fileira").html("<input type="hidden" name = "assentos.fileira" value="${i}"/>");
	      } */
	  });
	});

	
</script>

<script type="text/javascript">
		function mudarContador(element, i, j){
			if(cnt <= maxAllowed){
				if($(element).is(':checked')){
					$("#quantidadeRestanteId").html(quantidadeRestante - 1);
					quantidadeRestante--;
					
					$("#assentos-coluna".concat(i,j)).html(
							"<input type='hidden' name = 'assentos' value='" + i + "'/>"
					);
				} 					
				if(!$(element).is(':checked')){
					$("#quantidadeRestanteId").html(quantidadeRestante + 1);
					quantidadeRestante++;
				}
			}
		}

</script>

<script type="text/javascript">
function checarQuantidadeIngresso() {
	if(quantidadeRestante != 0 ) {
    	alert("Por favor, selecione a quantidade exata de ingressos disponiveis.");
		return false;
	}
	else{
		return confirm("Tem certeza que deseja finalizar a compra?");
	}
}
</script>
<!-- <script type="text/javascript">
		function checarCheckbox() {
			return $('#).is(':checked'))
		}
</script> -->

<title>Escolhendo lugares:</title>
</head>
<body class = pagina>
<c:import url="cabecalho.jsp"/>

<h3 class = posicaoSala>Escolha o assento: </h3>
<h4 class = quantidadeRestante>
							<c:set var="quantidadeRestante" scope="page" value="${qntIngressos}"/>
							
							Ingressos restantes: <span id="quantidadeRestanteId"><c:out value="${quantidadeRestante}"/> </span>
							
 </h4>
<div class = posicaoSala>
<form action="finalizarCompra" method="post">
	<table>
		<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
		<c:set var="alphabetsplit" value="${fn:split(alphabet, ',')}"/>
		
		<c:forEach var = "i" begin = "0" end = "${sala.qntFileiras}">
			<tr>
				<td>
					<c:out value="${alphabetsplit[i]}" />
				</td>		
			<c:forEach var = "j" begin = "0" end = "${sala.qntColunas}">
				<td class = posicaoCadeira>
					<c:set var="entrou" scope="page" value="${0}"/>
					<c:forEach items = "${assentosInvalidos}" var = "assentoInvalido">
						<c:if test= "${assentoInvalido.fileira == i && assentoInvalido.coluna == j}" >	
							<img name = "cadeira" id="cadeira" src = "resources/assento_invalido.gif" >	
							<c:set var="entrou" scope="page" value="${1}"/>
							<br/>
						</c:if>
					</c:forEach>
					<c:forEach items = "${assentosOcupados}" var = "assentoOcupado">
						<c:if test= "${assentoOcupado.fileira == i && assentoOcupado.coluna == j}" >	
							<img name = "cadeira" id="cadeira" src = "resources/assento_indisponivel.gif" >	
							<c:set var="entrou" scope="page" value="${1}"/>
							<br/><input type="checkbox" name = "assentos" disabled = "disabled"
								/>	
						</c:if>
					</c:forEach>
					<c:if test="${entrou == 0}">
						<img name = "cadeira" id="cadeira" src = "resources/assento_disponivel.gif" >
						<br/>
							<input  id="assentos-coluna${i}${j}" type="checkbox" name = "assentos"  value="${j}"
							onClick="mudarContador(this,${i},${j})"/>
							
							
					</c:if>
				</td>
			</c:forEach>	
			</tr>
	</c:forEach>
	</table>
		<input type="hidden" name = "quantidadeIngresso" value="${quantidadeIngresso}"/>
		<input type="hidden" name = "nomeTipoIngresso" value="${nomeTipoIngresso}"/>
		<input type="hidden" name = "idSessao" value="${umaSessao.idSessao}"/> <br/>
		<button type="submit" name = "idBotao" value ="" class="btn btn-default" onClick="return checarQuantidadeIngresso()">Finalizar Compra
 		</button>
</form>
</div>
</body>
</html>
