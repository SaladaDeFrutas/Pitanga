package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.TipoIngresso;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceTipoIngressoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TipoIngressoDao implements InterfaceTipoIngressoDao {
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
