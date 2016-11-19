package br.ufg.inf.dao;

import br.ufg.inf.entidades.TipoAssento;
import br.ufg.inf.entidades.TipoAssento;

import br.ufg.inf.interfaces.dao.InterfaceAssentoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class AssentoDao implements InterfaceAssentoDao {

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
