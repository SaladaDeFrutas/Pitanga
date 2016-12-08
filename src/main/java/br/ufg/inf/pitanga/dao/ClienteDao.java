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

    /**
     * Serviço central para todas as ações de persistência.
     */
    @PersistenceContext
    private EntityManager manager;

    /**
     * Adiciona um cliente a base de dados.
     *
     * @param umCliente Cliente a ser adicionado.
     */
    @Override
    public void adicionarCliente(Cliente umCliente) {
        umCliente.setSenha(new FuncaoHash().gerarHash(umCliente.getSenha()));
        manager.persist(umCliente);
    }

    /**
     * Remove um cliente da base de dados.
     *
     * @param umCliente Cliente a ser removido.
     */
    @Override
    public void removerCliente(Cliente umCliente) {
        manager.remove(umCliente);
    }

    /**
     * Lista todos os cliente.
     *
     * @return Lista de todos os clientes que existem na base de dados.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> listarCliente() {
        return manager.createQuery("from Cliente").getResultList();
    }

    /**
     * Altera um cliente na base de dados.
     *
     * @param umCliente Cliente, já com as devidas alterações.
     */
    @Override
    public void alterarCliente(Cliente umCliente) {
        manager.merge(umCliente);
    }

    /**
     * Busca um cliente pelo identificador do cliente, que no caso, é o email do cliente.
     *
     * @param email Identificador do Cliente.
     * @return Cliente compatível com o identificador.
     */
    @Override
    public Cliente buscarPorId(String email) {
        return manager.find(Cliente.class, email);
    }

    /**
     * Checa cliente, recebendo o objeto cliente que deve possuir o ID do cliente para buscar no banco, e testa
     * se as informações do cliente passado como parâmetro coincidem com as informações armazenadas
     * na base de dados.
     *
     * @param cliente Cliente a ser checado.
     * @return true para Cliente existente na base de dados e igual ao passado como parâmetro e
     * false para cliente inexistente na base dedas ou informações inconsistentes com o cliente
     * passado como parâmetro.
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
