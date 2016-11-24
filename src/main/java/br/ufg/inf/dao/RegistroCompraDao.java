package br.ufg.inf.dao;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.RegistroCompra;
import br.ufg.inf.interfaces.dao.InterfaceRegistroCompraDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegistroCompraDao implements InterfaceRegistroCompraDao {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public void adicionarRegistroCompra(RegistroCompra umRegistroCompra) {
        manager.persist(umRegistroCompra);
    }

    @Override
    public void removerRegistroCompra(RegistroCompra umRegistroCompra) {
        RegistroCompra registroCompraARemover = buscarPorId(umRegistroCompra.getIdRegistroCompra());
        manager.remove(registroCompraARemover);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RegistroCompra> listarRegistroCompra() {

        return manager.createQuery("from RegistroCompra").getResultList();
    }

    @Override
    public void alterarRegistroCompra(RegistroCompra umRegistroCompra) {
        manager.merge(umRegistroCompra);
    }

    @Override
    public RegistroCompra buscarPorId(int id) {
        return manager.find(RegistroCompra.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RegistroCompra> buscaPorCliente(Cliente umCliente) {
        return manager.createQuery("from RegistroCompra as r where umCliente.email='" + umCliente.getEmail() + "'").getResultList();
    }

}
