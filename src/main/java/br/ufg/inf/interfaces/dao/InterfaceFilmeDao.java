package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Filme;
import java.util.List;

public interface InterfaceFilmeDao {

	 public void adicionarFilme(Filme umFilme);
	 public void removerFilme(Filme umFilme);
	 public List<Filme> listarFilmes();
	 public void alterarFilme(Filme umFilme);
	 public Filme buscarPorId(int id);
}
