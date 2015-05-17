package svri.auxiliares;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Classe para gerar uma hash para criptografar as senhas antes
 * de envia-las para o banco de dados
 *
 */
public class FuncaoHash {

	public static String gerarHash(String senha) {
		try {
			MessageDigest hashing = MessageDigest.getInstance("MD5");
			hashing.update(senha.getBytes());
			senha = new String(hashing.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senha;
	}
}
