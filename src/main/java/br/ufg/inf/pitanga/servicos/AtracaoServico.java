package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.interfaces.dao.InterfaceFilmeDao;
import br.ufg.inf.pitanga.interfaces.dao.InterfacePecaDao;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceSessaoDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AtracaoServico {

    @Autowired
    private InterfaceFilmeDao filmeDao;

    @Autowired
    private InterfacePecaDao pecaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;


}
