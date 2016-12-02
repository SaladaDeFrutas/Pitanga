package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Classe relacionada a operaçãos de cadastro de usuários.
 */
public class CadastroUsuario {

    /**
     * Interface de comunicação do cliente com o banco de dados
     */
    @Autowired
    private InterfaceClienteDao clienteDao;


    /**
     * Função que retorna o nome da página de cadastro
     *
     * @return nome da página de cadastro.
     */
    @RequestMapping("cadastro")
    public String retornaPaginaCadastro() {
        return "cadastro";
    }

    /**
     * Função para cadastrar clientes
     *
     * @param cliente Cliente a ser cadastrado
     * @param result Resultado
     * @return
     */
    @RequestMapping("cadastrarCliente")
    public String cadastrarCliente(@Valid Cliente cliente,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        }
        clienteDao.adicionarCliente(cliente);
        return "cadastroSucesso";
    }


}
