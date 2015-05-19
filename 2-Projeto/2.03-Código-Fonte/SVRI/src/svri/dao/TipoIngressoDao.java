package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import svri.entidades.TipoIngresso;
import svri.interfaces.dao.InterfaceTipoIngressoDao;

public class TipoIngressoDao implements InterfaceTipoIngressoDao{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarTipoIngresso(TipoIngresso umTipoIngresso) {
		manager.persist(umTipoIngresso);
	}

	@Override
	public void removerTipoIngresso(TipoIngresso umTipoIngresso) {
		TipoIngresso TipoIngressoARemover = buscarPorNome(umTipoIngresso.getNome());
		manager.remove(TipoIngressoARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoIngresso> listarTipoIngresso() {
		
		return manager.createQuery("from TipoIngresso").getResultList();
	}

	@Override
	public void alterarTipoIngresso(TipoIngresso umTipoIngresso) {
		manager.merge(umTipoIngresso);
	}

	@Override
	public TipoIngresso buscarPorNome(String nome) {
		return manager.find(TipoIngresso.class, nome);
	}
}
