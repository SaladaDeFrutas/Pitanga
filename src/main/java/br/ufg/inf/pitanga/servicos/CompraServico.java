package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.dao.ClienteDao;
import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.CompraDTO;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceCompraDao;
import br.ufg.inf.pitanga.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CompraServico {

    @Autowired
    PagamentoPagseguroServico pagamentoPagseguroServico;

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    InterfaceCompraDao compraDao;

    @Autowired
    private CompraRepository compraRepository;

    InterfacePagamento tipoPagamento;

    public Compra buscarPorId(Long id){
        return compraRepository.findOne(id);
    }

    public String efetuarPagamento(ArrayList<Ingresso> ingressos, Compra novaCompra, Cliente cliente) {
        tipoPagamento = new PagamentoPagseguroServico();
        return tipoPagamento.realizaPagamento(ingressos, novaCompra, cliente);
    }

    public List<CompraDTO> obtenhaComprasDoCliente(String email) {
        Cliente cliente = clienteDao.buscarPorId(email);
        String nomeCliente = cliente.getNome();
        List<Compra> compras = compraRepository.findByCliente(cliente);

        List<CompraDTO> compraDTOS = new ArrayList<>();

        for (Compra compra : compras) {
            String dataCompra = formatarDataDaCompra(compra.getDataCompra());
            compraDTOS.add(new CompraDTO(nomeCliente, compra.getId(), dataCompra, compra.getValorTotal()));
        }

        return compraDTOS;
    }

    private String formatarDataDaCompra(Calendar dataCompra) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataCompra.getTime());
    }
}
