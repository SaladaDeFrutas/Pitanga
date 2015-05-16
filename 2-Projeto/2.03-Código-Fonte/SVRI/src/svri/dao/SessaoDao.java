package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import svri.entidades.Atracao;
import svri.entidades.Sessao;
import svri.interfaces.dao.InterfaceSessaoDao;

public class SessaoDao implements InterfaceSessaoDao{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarSessao(Sessao umaSessao) {
		manager.persist(umaSessao);
	}

	@Override
	public void removerSessao(Sessao umaSessao) {
		Sessao sessaoARemover = buscarPorId(umaSessao.getId());
		manager.remove(sessaoARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sessao> listarSessoes() {
		
		return manager.createQuery("from Sessao").getResultList();
	}

	@Override
	public void alterarSessao(Sessao umaSessao) {
		manager.merge(umaSessao);
	}

	@Override
	public Sessao buscarPorId(int id) {
		return manager.find(Sessao.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sessao> buscarPorAtracao(Atracao umaAtracao) {
		return manager.createQuery("from Sessao as s where s.atracao="+umaAtracao.getId()).getResultList();

	}

	
}
