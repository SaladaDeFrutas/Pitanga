package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Atracao;
import br.ufg.inf.entidades.Sessao;

import java.util.List;


public interface InterfaceSessaoDao {
	
	public void adicionarSessao(Sessao umaSessao);
	public void removerSessao(Sessao umaSessao);
	public List<Sessao> listarSessoes();
	public void alterarSessao(Sessao umaSessao);
	public Sessao buscarPorId(int Id);
	public List<Sessao> buscarPorAtracao(Atracao umaAtracao);
	
	
}
