package br.ufg.inf.dao;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.Ingresso;
import br.ufg.inf.entidades.RegistroCompra;
import br.ufg.inf.interfaces.dao.InterfaceIngressoDao;
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
    public Ingresso buscarPorId(int id) {
        return manager.find(Ingresso.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ingresso> buscaPorCliente(Cliente umCliente) {
        return manager.createQuery("from Ingresso as i where umCliente.email='" + umCliente.getEmail() + "'").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ingresso> buscaPorRegistroCompra(RegistroCompra umRegistroCompra) {
        return manager.createQuery("from Ingresso as i where registroCompra.idRegistroCompra=" + umRegistroCompra.getIdRegistroCompra()).getResultList();
    }
}
