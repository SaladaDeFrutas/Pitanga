package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Peca;

public interface InterfacePecaDao {

	 public void adicionarPeca(Peca umaPeca);
	 public void removerPeca(Peca umaPeca);
	 public List<Peca> listarPecas();
	 public void alterarPeca(Peca umaPeca);
	 public Peca buscarPorId(int id);
}
