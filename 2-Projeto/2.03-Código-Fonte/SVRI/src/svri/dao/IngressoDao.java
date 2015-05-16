package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import svri.entidades.Cliente;
import svri.entidades.Ingresso;
import svri.interfaces.dao.InterfaceIngressoDao;

@Repository
public class IngressoDao implements InterfaceIngressoDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarIngresso(Ingresso umIngresso) {
		manager.persist(umIngresso);
	}

	@Override
	public void removerIngresso(Ingresso umIngresso) {
		Ingresso ingressoARemover = buscarPorId(umIngresso.getId());
		manager.remove(ingressoARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingresso> listarIngressos() {
		
		return manager.createQuery("from Ingresso").getResultList();
	}

	@Override
	public void alterarIngresso(Ingresso umIngresso) {
		manager.merge(umIngresso);
	}

	@Override
	public Ingresso buscarPorId(int id) {
		return manager.find(Ingresso.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscaPorCliente(Cliente umCliente) {
		return manager.createQuery("select i from ingressos i where i.cliente="+umCliente.getEmail()).getResultList();
	}
}
