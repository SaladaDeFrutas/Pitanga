package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import svri.entidades.TipoAssento;
import svri.interfaces.dao.InterfaceAssentoDao;

@Repository
public class AssentoDao implements InterfaceAssentoDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarAssento(TipoAssento umAssento) {
		manager.persist(umAssento);
	}

	@Override
	public void removerAssento(TipoAssento umAssento) {
		TipoAssento assentoARemover = buscarPorId(umAssento.getId());
		manager.remove(assentoARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoAssento> listarAssentos() {
		
		return manager.createQuery("from Assento").getResultList();
	}

	@Override
	public void alterarAssento(TipoAssento umAssento) {
		manager.merge(umAssento);
	}

	@Override
	public TipoAssento buscarPorId(int id) {
		return manager.find(TipoAssento.class, id);
	}
}
