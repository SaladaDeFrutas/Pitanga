package svri.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * Classe cliente foi passada para um a subclasse de usuario, para que seja possivel
 *  fatorar os atributos comuns entre cliente e funcionario
 *
 */
@Entity
@Table(name="clientes")
public class Cliente extends Usuario {
}
