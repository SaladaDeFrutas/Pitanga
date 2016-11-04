package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Atracao;
import svri.entidades.Sessao;

public interface InterfaceSessaoDao {
	
	public void adicionarSessao(Sessao umaSessao);
	public void removerSessao(Sessao umaSessao);
	public List<Sessao> listarSessoes();
	public void alterarSessao(Sessao umaSessao);
	public Sessao buscarPorId(int Id);
	public List<Sessao> buscarPorAtracao(Atracao umaAtracao);
	
	
}
