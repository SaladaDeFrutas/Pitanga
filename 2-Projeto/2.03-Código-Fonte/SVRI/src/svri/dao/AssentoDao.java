package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import svri.entidades.Assento;
import svri.interfaces.dao.InterfaceAssentoDao;

@Repository
public class AssentoDao implements InterfaceAssentoDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarAssento(Assento umAssento) {
		manager.persist(umAssento);
	}

	@Override
	public void removerAssento(Assento umAssento) {
		Assento assentoARemover = buscarPorId(umAssento.getId());
		manager.remove(assentoARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Assento> listarAssentos() {
		
		return manager.createQuery("from Assento").getResultList();
	}

	@Override
	public void alterarAssento(Assento umAssento) {
		manager.merge(umAssento);
	}

	@Override
	public Assento buscarPorId(int id) {
		return manager.find(Assento.class, id);
	}
}
