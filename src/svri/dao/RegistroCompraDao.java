package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import svri.entidades.Cliente;
import svri.entidades.RegistroCompra;
import svri.interfaces.dao.InterfaceRegistroCompraDao;

public class RegistroCompraDao implements InterfaceRegistroCompraDao{


	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarRegistroCompra(RegistroCompra umRegistroCompra) {
		manager.persist(umRegistroCompra);
	}

	@Override
	public void removerRegistroCompra(RegistroCompra umRegistroCompra) {
		RegistroCompra registroCompraARemover = buscarPorId(umRegistroCompra.getIdRegistroCompra());
		manager.remove(registroCompraARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroCompra> listarRegistroCompra() {
		
		return manager.createQuery("from RegistroCompra").getResultList();
	}

	@Override
	public void alterarRegistroCompra(RegistroCompra umRegistroCompra) {
		manager.merge(umRegistroCompra);
	}

	@Override
	public RegistroCompra buscarPorId(int id) {
		return manager.find(RegistroCompra.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroCompra> buscaPorCliente(Cliente umCliente) {
		return manager.createQuery("from RegistroCompra as r where umCliente.email='"+umCliente.getEmail()+"'").getResultList();
	}

}
