package br.ufg.inf.pitanga.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceClienteDao;
import br.ufg.inf.pitanga.servicos.FuncaoHash;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDao implements InterfaceClienteDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void adicionarCliente(Cliente umCliente) {
        umCliente.setSenha(new FuncaoHash().gerarHash(umCliente.getSenha()));
        manager.persist(umCliente);
    }

    @Override
    public void removerCliente(Cliente umCliente) {
        manager.remove(umCliente);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> listarCliente() {
        return manager.createQuery("from Cliente").getResultList();
    }

    @Override
    public void alterarCliente(Cliente umCliente) {
        manager.merge(umCliente);
    }

    @Override
    public Cliente buscarPorId(String email) {
        return manager.find(Cliente.class, email);
    }

    /**
     * recebe o objeto cliente que deve possuir o ID
     * do cliente para buscar no banco
     */
    @Override
    public boolean checarCliente(Cliente cliente) {

        Cliente clienteBuscado = buscarPorId(cliente.getEmail());
        if (null != clienteBuscado) {
            if (clienteBuscado.getEmail().equals(cliente.getEmail())) {
                if (clienteBuscado.getSenha().equals(new FuncaoHash().gerarHash(cliente.getSenha()))) {
                    return true;
                }
            }

        }

        return false;
    }


}
