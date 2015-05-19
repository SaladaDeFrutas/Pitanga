package svri.interfaces.dao;

import java.util.List;

import svri.entidades.TipoIngresso;


public interface InterfaceTipoIngressoDao {
	public void adicionarTipoIngresso(TipoIngresso umTipoIngresso);
	 public void removerTipoIngresso(TipoIngresso umTipoIngresso);
	 public List<TipoIngresso> listarTipoIngresso();
	 public void alterarTipoIngresso(TipoIngresso umTipoIngresso);
	 public TipoIngresso buscarPorNome(String nome);
}
