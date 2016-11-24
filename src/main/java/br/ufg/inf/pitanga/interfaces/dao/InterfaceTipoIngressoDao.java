package br.ufg.inf.pitanga.interfaces.dao;

import br.ufg.inf.pitanga.entidades.TipoIngresso;

import java.util.List;

public interface InterfaceTipoIngressoDao {
    public void adicionarTipoIngresso(TipoIngresso umTipoIngresso);

    public void removerTipoIngresso(TipoIngresso umTipoIngresso);

    public List<TipoIngresso> listarTipoIngresso();

    public void alterarTipoIngresso(TipoIngresso umTipoIngresso);

    public TipoIngresso buscarPorNome(String nome);
}
