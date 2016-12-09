package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String CLIENTE = "cliente";

    /**
     * Validação do nome do usuário, se o nome e maior que 5 caracteres e menor que 255 caracteres.
     *
     * @param nome Nome do usuário.
     * @return True para nome válido e False para nome ínvalido.
     */
    private static Boolean validacaoNome(String nome) {
        if ((nome.length() <= 5) || (nome.length() > 255)) {
            return false;
        }
        return true;
    }

    /**
     * Validação da data de nascimento do usuário, se o ano é maior que 1900 e menor que o ano corrente, se o mês
     * está entre 1 e 12 e o dia está entre 1 e 31.
     *
     * @param dataNascimento Data de Nascimento do usuário.
     * @return True para data de nascimento válido e False para data de nascimento ínvalido.
     */
    private static Boolean validacaoDataDeNascimento(Calendar dataNascimento) {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        if ((dataNascimento.get(Calendar.YEAR) < 1900) ||
            (dataNascimento.get(Calendar.YEAR) >= calendar.get(Calendar.YEAR))) {
            return false;
        }
        return true;
    }

    /**
     * Validação da senha, se a mesma possui mais de 25 caracteres
     *
     * @param senha senha do Cliente.
     * @return true se a senha é valida, false se a senha não é válida.
     */
    private static Boolean validacaoSenha(String senha) {
        if (senha.length() > 25) {
            return false;
        }
        return true;
    }

    /**
     * Testa se as informações do cliente são consistentes, se verdadeiro, realiza o cadastro do cliente.
     *
     * @param cliente Cliente a ser armazenado na base de dados.
     * @return Cliente armazenado.
     */
    public Cliente cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new InvalidParameterException(CLIENTE);
        }

        if (!validacaoNome(cliente.getNome())) {
            return null;
        }

        if (!validacaoDataDeNascimento(cliente.getDataDeNascimento())) {
            return null;
        }

        if (!validacaoSenha(cliente.getSenha())) {
            return null;
        }

        clienteRepository.save(cliente);
        return cliente;
    }

    /**
     * Recuperar um cliente pelo identificador primário, que no caso, é o e-mail do cliente.
     *
     * @param email Identificador primário;
     * @return Cliente que possui o identificador primário;
     */
    public Cliente recuperarClientePorEmail(String email) {
        if ((email == null) || ("".equals(email))) {
            throw new InvalidParameterException("email");
        }
        return clienteRepository.findByEmail(email);
    }

    /**
     * Remove um cliente da base de dados.
     *
     * @param cliente cliente a ser removido.
     * @return true para remoção com sucesso e falso para falha na remoção.
     */
    public Boolean deletarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new InvalidParameterException(CLIENTE);
        }
        String email = cliente.getEmail();
        clienteRepository.delete(cliente);
        if (clienteRepository.findByEmail(email) == null) {
            return true;
        }
        return false;
    }

    /**
     * Altera um cliente.
     *
     * @param cliente Cliente a ser alterado, já com as novas informações.
     * @return cliente alterado e que já consiste na base de dados.
     */
    public Cliente alterarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new InvalidParameterException(CLIENTE);
        }
        clienteRepository.save(cliente);
        return clienteRepository.findByEmail(cliente.getEmail());
    }

}
