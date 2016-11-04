package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Sala;

public interface InterfaceSalaDao {

	public void adicionarSala(Sala umaSala);
	public void removerSala(Sala umaSala);
	public List<Sala> listarSalas();
	public void alterarSala(Sala umaSala); 
	public Sala buscarPorId(int Id); 
}
