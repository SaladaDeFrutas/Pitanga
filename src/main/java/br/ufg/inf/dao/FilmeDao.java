package br.ufg.inf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufg.inf.entidades.Filme;
import br.ufg.inf.interfaces.dao.InterfaceFilmeDao;

//Repositorio n�o est� funcionando corretamente, � necess�rio colocar o bean
//manualmente no spring-context.xml
@Repository
public class FilmeDao implements InterfaceFilmeDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarFilme(Filme umFilme) {
		manager.persist(umFilme);
	}

	@Override
	public void removerFilme(Filme umFilme) {
		Filme filmeARemover = buscarPorId(umFilme.getIdAtracao());
		manager.remove(filmeARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> listarFilmes() {
		
		return manager.createQuery("from Filme").getResultList();
	}

	@Override
	public void alterarFilme(Filme umFilme) {
		manager.merge(umFilme);
	}

	@Override
	public Filme buscarPorId(int id) {
		return manager.find(Filme.class, id);
	}

	
	
	
}
