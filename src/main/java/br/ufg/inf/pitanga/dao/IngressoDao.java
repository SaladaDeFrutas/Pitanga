package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceIngressoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IngressoDao implements InterfaceIngressoDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void adicionarIngresso(Ingresso umIngresso) {
        manager.persist(umIngresso);
    }

    @Override
    public void removerIngresso(Ingresso umIngresso) {
        Ingresso ingressoARemover = buscarPorId(umIngresso.getId());
        manager.remove(ingressoARemover);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ingresso> listarIngressos() {

        return manager.createQuery("from Ingresso").getResultList();
    }

    @Override
    public void alterarIngresso(Ingresso umIngresso) {
        manager.merge(umIngresso);
    }

    @Override
    public Ingresso buscarPorId(Long id) {
        return manager.find(Ingresso.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ingresso> buscaPorCliente(Cliente umCliente) {
        return manager.createQuery("from Ingresso as i where umCliente.email='" + umCliente.getEmail() + "'").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ingresso> buscaPorRegistroCompra(Compra compra) {
        return manager.createQuery("from Ingresso as i where compra.id=" + compra.getId()).getResultList();
    }
}
