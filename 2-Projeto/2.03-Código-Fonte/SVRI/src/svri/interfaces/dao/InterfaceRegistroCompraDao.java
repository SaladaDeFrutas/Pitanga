package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Cliente;
import svri.entidades.RegistroCompra;

public interface InterfaceRegistroCompraDao {

	public void adicionarRegistroCompra(RegistroCompra umRegistroCompra);
	public void removerRegistroCompra(RegistroCompra umRegistroCompra);
	public List<RegistroCompra> listarRegistroCompra();
	public void alterarRegistroCompra(RegistroCompra umRegistroCompra);
	public RegistroCompra buscarPorId(int Id);
	public List<RegistroCompra> buscaPorCliente(Cliente umCliente);
}
