package br.ufg.inf.dao;

import br.ufg.inf.entidades.Sala;
import br.ufg.inf.interfaces.dao.InterfaceSalaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SalaDao implements InterfaceSalaDao {

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
