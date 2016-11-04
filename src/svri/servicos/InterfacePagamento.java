package svri.servicos;

import java.util.ArrayList;

import svri.entidades.Cliente;
import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;

public interface InterfacePagamento {
	String realizaPagamento(ArrayList<Ingresso> ingressos,
			RegistroCompra novoRegistroCompra, Cliente cliente);
}
