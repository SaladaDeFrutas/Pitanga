package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceCompraDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompraDao implements InterfaceCompraDao {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public void adicionarCompra(Compra compra) {
        manager.persist(compra);
    }

    @Override
    public void removerCompra(Compra umCompra) {
        Compra compraARemover = buscarPorId(umCompra.getId());
        manager.remove(compraARemover);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Compra> listarCompras() {

        return manager.createQuery("from Compra").getResultList();
    }

    @Override
    public void alterarCompra(Compra umCompra) {
        manager.merge(umCompra);
    }

    @Override
    public Compra buscarPorId(Long id) {
        return manager.find(Compra.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Compra> buscaPorCliente(Cliente umCliente) {
        return manager.createQuery("from Compra as r where umCliente.email='" + umCliente.getEmail() + "'").getResultList();
    }

}
