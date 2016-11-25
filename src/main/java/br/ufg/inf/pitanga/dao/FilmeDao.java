package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceFilmeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Repositorio não está funcionando corretamente, é necessário colocar o bean
//manualmente no spring-context.xml
@Repository
public class FilmeDao implements InterfaceFilmeDao {

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
