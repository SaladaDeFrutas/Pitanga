package svri.dao;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import svri.entidades.Filme;
import svri.entidades.Peca;
import svri.entidades.Sessao;


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
	
		umFilme.setId(1);
		umFilme.setTitulo("As aventuras do projeto integrador.");
		umFilme.setIdioma("Portugues");
		umFilme.setDuracao(120);
		umFilme.setSinopse("Por via das duvidas responda 42.");
		umFilme.setGenero("Comedia com drama, terror, insonia e conhecimento.");
		umFilme.setLegendado(true);
		umFilme.setProdutora("UFG PRODUCTIONS");
		umFilme.setModoDeExibicao("Realidade Virtual");
		umFilme.setDataEstreia(dataEstreia);
		
		Peca umaPeca = new Peca();
		umaPeca.setId(1);
		umaPeca.setTitulo("Minha mae e uma peça.");
		umaPeca.setIdioma("Portugues");
		umaPeca.setDuracao(180);
		umaPeca.setSinopse("Com Paulo Gustavo, muito engracado.");
		umaPeca.setGenero("Comedia, muita comedia.");
		umaPeca.setDataEstreia(dataEstreia);
		
		Sessao umaSessao = new Sessao();
		umaSessao.setData(dataEstreia);
		umaSessao.setId(1);
		umaSessao.setUmaAtracao(umFilme);
		
		Sessao outraSessao = new Sessao();
		outraSessao.setData(dataEstreia);
		outraSessao.setId(1);
		outraSessao.setUmaAtracao(umFilme);

		EntityManagerFactory factory = Persistence.
				createEntityManagerFactory("SVRIUnit");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(umFilme);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
		
		
	}
}
