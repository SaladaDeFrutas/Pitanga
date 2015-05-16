package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import svri.entidades.Peca;
import svri.interfaces.dao.InterfacePecaDao;

@Repository
public class PecaDao implements InterfacePecaDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarPeca(Peca umaPeca) {
		manager.persist(umaPeca);
	}

	@Override
	public void removerPeca(Peca umaPeca) {
		Peca pecaARemover = buscarPorId(umaPeca.getId());
		manager.remove(pecaARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peca> listarPecas() {
		
		return manager.createQuery("from Peca").getResultList();
	}

	@Override
	public void alterarPeca(Peca umaPeca) {
		manager.merge(umaPeca);
	}

	@Override
	public Peca buscarPorId(int id) {
		return manager.find(Peca.class, id);
	}

}
