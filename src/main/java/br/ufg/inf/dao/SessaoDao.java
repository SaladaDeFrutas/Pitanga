package br.ufg.inf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufg.inf.entidades.Atracao;
import br.ufg.inf.entidades.Sessao;
import br.ufg.inf.interfaces.dao.InterfaceSessaoDao;

public class SessaoDao implements InterfaceSessaoDao{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarSessao(Sessao umaSessao) {
		manager.persist(umaSessao);
	}

	@Override
	public void removerSessao(Sessao umaSessao) {
		Sessao sessaoARemover = buscarPorId(umaSessao.getIdSessao());
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
		return manager.createQuery("from Sessao as s where atracao.id="+umaAtracao.getIdAtracao()).getResultList();

	}

	
}
