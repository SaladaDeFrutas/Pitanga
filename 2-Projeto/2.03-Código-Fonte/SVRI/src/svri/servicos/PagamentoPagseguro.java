package svri.servicos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import svri.entidades.Cliente;
import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;

public class PagamentoPagseguro implements InterfacePagamento{

	@Override
	public String realizaPagamento(ArrayList<Ingresso> ingressos,
			RegistroCompra novoRegistroCompra, Cliente cliente) {
		Checkout checkout = new Checkout();
		SimpleDateFormat sDformat = new SimpleDateFormat("dd/MM/yyyy");
		//adicionar aqui cada ingresso usando o while
		for (Ingresso ingresso : ingressos) {
			checkout.addItem(
					Integer.toString(ingresso.getId()), // id
					ingresso.getUmaSessao().getAtracao().getTitulo() + ", " +// descricao
					sDformat.format(ingresso.getUmaSessao().getData().getTime()), // descricao
					Integer.valueOf(1), // quantidade
					new BigDecimal(ingresso.getUmTipoIngresso().getPreco()), // valor unitario
					null,
					null
			);
		}
		
		// colocando dados do comprador
		checkout.setSender(
			cliente.getNome(), // nome
			cliente.getEmail() //email
		);
		
		// selecionando moeda
		checkout.setCurrency(Currency.BRL);
		
		// colocando referencia para a transação
		checkout.setReference(String.valueOf(novoRegistroCompra.getIdCompra()));
		
		return null;
	}

}
