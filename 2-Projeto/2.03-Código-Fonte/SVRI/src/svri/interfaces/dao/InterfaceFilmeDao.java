package svri.interfaces.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import svri.entidades.Filme;

@Repository
public interface InterfaceFilmeDao {

	 public void adicionarFilme(Filme umFilme);
	 public void removerFilme(Filme umFilme);
	 public List<Filme> listarFilmes();
	 public void alterarFilme(Filme umFilme);
	 public Filme buscarPorId(int id);
}
