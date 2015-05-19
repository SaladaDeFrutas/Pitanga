package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import svri.entidades.Sala;
import svri.interfaces.dao.InterfaceSalaDao;

public class SalaDao implements InterfaceSalaDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarSala(Sala umaSala) {
		manager.persist(umaSala);
	}

	@Override
	public void removerSala(Sala umaSala) {
		Sala SalaARemover = buscarPorId(umaSala.getId());
		manager.remove(SalaARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> listarSalas() {
		
		return manager.createQuery("from Sala").getResultList();
	}

	@Override
	public void alterarSala(Sala umaSala) {
		manager.merge(umaSala);
	}

	@Override
	public Sala buscarPorId(int id) {
		return manager.find(Sala.class, id);
	}

}
