package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.interfaces.dao.InterfacePecaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PecaDao implements InterfacePecaDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void adicionarPeca(Peca umaPeca) {
        manager.persist(umaPeca);
    }

    @Override
    public void removerPeca(Peca umaPeca) {
        Peca pecaARemover = buscarPorId(umaPeca.getIdAtracao());
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
