package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Sala;

import java.util.List;


public interface InterfaceSalaDao {

	public void adicionarSala(Sala umaSala);
	public void removerSala(Sala umaSala);
	public List<Sala> listarSalas();
	public void alterarSala(Sala umaSala); 
	public Sala buscarPorId(int Id); 
}
