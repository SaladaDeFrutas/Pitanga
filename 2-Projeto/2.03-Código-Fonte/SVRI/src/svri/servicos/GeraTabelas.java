package svri.servicos;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import svri.entidades.Sala;
import svri.entidades.TipoAssento;
import svri.entidades.Filme;
import svri.entidades.Peca;
import svri.entidades.Sessao;
import svri.entidades.TipoIngresso;

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
		umFilme.setTitulo("Projeto integrador.");
		umFilme.setIdioma("Pt-BR");
		umFilme.setDuracao(120);
		umFilme.setSinopse("Por via das duvidas responda 42.");
		umFilme.setGenero("Conhecimento.");
		umFilme.setLegendado(true);
		umFilme.setProdutora("UFG PRODUCTIONS");
		umFilme.setModoDeExibicao("3D");
		umFilme.setDataEstreia(dataEstreia);
		
		Peca umaPeca = new Peca();
		//umaPeca.setId(1);
		umaPeca.setTitulo("Minha mae e uma pe�a.");
		umaPeca.setIdioma("Pt-br");
		umaPeca.setDuracao(180);
		umaPeca.setSinopse("Muito engracado.");
		umaPeca.setGenero("Comedia");
		umaPeca.setDataEstreia(dataEstreia);
		
		
		TipoIngresso umTipoIngresso = new TipoIngresso();
		//umTipoIngresso.setId(1);
		umTipoIngresso.setNome("Inteira");
		umTipoIngresso.setPreco(40);
		TipoIngresso outroTipoIngresso = new TipoIngresso();
		//outroTipoIngresso.setId(2);
		outroTipoIngresso.setNome("Meia");
		outroTipoIngresso.setPreco(20);
		TipoIngresso maisUmTipoIngresso = new TipoIngresso();
		//maisUmTipoIngresso.setId(3);
		maisUmTipoIngresso.setNome("Meia-Itau");
		maisUmTipoIngresso.setPreco(19.5);
		TipoAssento umAssento = new TipoAssento();
		umAssento.setDescricao("Assento especial do dia dos Namorados.");
		//umAssento.setId(1);
		umAssento.setNome("Lovers");
		umAssento.setPreco(40.9);
		
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
		
		Sessao outraSessao = new Sessao();
		outraSessao.setData(outraDataEstreia);
		outraSessao.setAtracao(umFilme);
		outraSessao.setSala(umaSala);
		
		
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
