package br.ufg.inf.servicos;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufg.inf.entidades.Sala;
import br.ufg.inf.entidades.TipoAssento;
import br.ufg.inf.entidades.Filme;
import br.ufg.inf.entidades.Peca;
import br.ufg.inf.entidades.Sessao;
import br.ufg.inf.entidades.TipoIngresso;

/**
 * 
 * Classe para geracao de registros no banco de dados
 * para realizar os testes
 *
 */
public class GeraTabelas {

	public static void main(String[] args) {
		Calendar dataEstreia = Calendar.getInstance();
		dataEstreia.set(Calendar.YEAR, 2015);
		dataEstreia.set(Calendar.MONTH, Calendar.APRIL);
		dataEstreia.set(Calendar.DAY_OF_MONTH, 30);
		dataEstreia.set(Calendar.HOUR,4);
		dataEstreia.set(Calendar.MINUTE,20);
		
		Calendar outraDataEstreia = Calendar.getInstance();
		outraDataEstreia.set(Calendar.YEAR, 2015);
		outraDataEstreia.set(Calendar.MONTH, Calendar.APRIL);
		outraDataEstreia.set(Calendar.DAY_OF_MONTH, 11);
		outraDataEstreia.set(Calendar.HOUR,12);
		outraDataEstreia.set(Calendar.MINUTE,5);
		
		Filme umFilme = new Filme();
		//umFilme.setId(1);
		umFilme.setTitulo("Velozes e Furiosos 7");
		umFilme.setIdioma("Pt-BR");
		umFilme.setDuracao(120);
		umFilme.setSinopse("Com Paul Walker, Vin Diesel.");
		umFilme.setGenero("Acao");
		umFilme.setLegendado(true);
		umFilme.setProdutora("Paramount");
		umFilme.setModoDeExibicao("3D");
		umFilme.setDataEstreia(dataEstreia);
		
		Peca umaPeca = new Peca();
		umaPeca.setTitulo("Fora do Normal");
		umaPeca.setIdioma("Pt-br");
		umaPeca.setDuracao(180);
		umaPeca.setSinopse("Muito engracado.");
		umaPeca.setGenero("Comedia");
		umaPeca.setDataEstreia(dataEstreia);
		umaPeca.setDiretor("N�o informado");
		
		TipoIngresso umTipoIngresso = new TipoIngresso();
		umTipoIngresso.setNome("Inteira");
		umTipoIngresso.setPreco(40.00);
		TipoIngresso outroTipoIngresso = new TipoIngresso();
		outroTipoIngresso.setNome("Meia");
		outroTipoIngresso.setPreco(20.00);
		TipoIngresso maisUmTipoIngresso = new TipoIngresso();
		maisUmTipoIngresso.setNome("Meia-Itau");
		maisUmTipoIngresso.setPreco(19);
		TipoAssento umAssento = new TipoAssento();
		umAssento.setDescricao("Assento especial do dia dos Namorados.");
		umAssento.setNome("Lovers");
		umAssento.setPreco(40.90);
		
		String assentosInvalidos= "";
	
		for(int i = 2; i < 4; i++){
			for (int j = 0; j<20;j++){
				assentosInvalidos += j+ "," +i + ";";
			}
		}
		
		
		for(int i = 17; i < 19; i++){
			for (int j = 0; j<20;j++){
				assentosInvalidos += j+ "," +i + ";";
			}
		}
		Sala umaSala = new Sala();
		umaSala.setQntColunas(20);
		umaSala.setQntFileiras(20);
		umaSala.setAssentosInvalidos(assentosInvalidos);
		
		Sessao umaSessao = new Sessao();
		umaSessao.setData(dataEstreia);
		umaSessao.setAtracao(umFilme);
		umaSessao.setSala(umaSala);
		umaSessao.setAssentosOcupados(" ");
		Sessao outraSessao = new Sessao();
		outraSessao.setData(outraDataEstreia);
		outraSessao.setAtracao(umFilme);
		outraSessao.setSala(umaSala);
		outraSessao.setAssentosOcupados(" ");
		
		
		EntityManagerFactory factory = Persistence.
				createEntityManagerFactory("SVRIUnit");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(umFilme);
		manager.persist(umaPeca);
		manager.persist(umaSessao);
		manager.persist(outraSessao);
		manager.persist(umTipoIngresso);
		manager.persist(outroTipoIngresso);
		manager.persist(maisUmTipoIngresso);
		manager.persist(umAssento);
		manager.persist(umaSala);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
		
		
	}
}
