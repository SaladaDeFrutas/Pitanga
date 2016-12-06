package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.dao.ClienteDao;
import br.ufg.inf.pitanga.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public Cliente cadastrarCliente(Cliente cliente){

        if (!validacaoNome(cliente.getNome())){
            return null;
        }

        if (!validacaoEmail(cliente.getEmail())){
            return null;
        }

        if (!validacaoDataDeNascimento(cliente.getDataDeNascimento())){
            return null;
        }

        clienteDao.adicionarCliente(cliente);
        return cliente;
    }

    /**
     * Recuperar um cliente pelo identificador primário, que no caso, é o e-mail do cliente.
     *
     * @param email Identificador primário;
     * @return Cliente que possui o identificador primário;
     */
    public Cliente recuperarClientePorEmail(String email){
        return clienteDao.buscarPorId(email);
    }

    /**
     * Remove um cliente da base de dados.
     *
     * @param cliente cliente a ser removido.
     * @return true para remoção com sucesso e falso para falha na remoção.
     */
    public Boolean deletarCliente(Cliente cliente){
        String email = cliente.getEmail();
        clienteDao.removerCliente(cliente);
        if (clienteDao.buscarPorId(email) == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera um cliente.
     *
     * @param cliente Cliente a ser alterado, já com as novas informações.
     * @return cliente alterado e que já consiste na base de dados.
     */
    public Cliente alterarCliente(Cliente cliente){
        clienteDao.alterarCliente(cliente);
        return clienteDao.buscarPorId(cliente.getEmail());
    }

    /**
     * Validação do nome do usuário, se o nome e maior que 5 caracteres e menor que 255 caracteres.
     *
     * @param nome Nome do usuário.
     * @return True para nome válido e False para nome ínvalido.
     */
    private Boolean validacaoNome(String nome){
        if (nome.length() > 255) {
            return false;
        }

        if (nome.length() <= 5) {
            return false;
        }

        return true;
    }

    /**
     * Validação do email do usuário, se possui '@' no email.
     *
     * @param email Email para validação.
     * @return True para email válido e False para email ínvalido.
     */
    private Boolean validacaoEmail(String email){
        if (email.indexOf('@') > 0) {
            if (email.indexOf(".com") > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Validação da data de nascimento do usuário, se o ano é maior que 1900 e menor que o ano corrente, se o mês
     * está entre 1 e 12 e o dia está entre 1 e 31.
     *
     * @param dataNascimento Data de Nascimento do usuário.
     * @return True para data de nascimento válido e False para data de nascimento ínvalido.
     */
    private Boolean validacaoDataDeNascimento(Calendar dataNascimento){
        Calendar calendar = new GregorianCalendar().getInstance();

        if ((dataNascimento.YEAR < 1900 || dataNascimento.YEAR >= calendar.YEAR)){
            return false;
        }

        if ((dataNascimento.MONTH <= 0) || (dataNascimento.MONTH > 12)){
            return false;
        }

        if ((dataNascimento.DAY_OF_MONTH <= 0) || (dataNascimento.DAY_OF_MONTH > 31)){
            return false;
        }

        return true;
    }
}
