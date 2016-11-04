package br.com.ufg.inf.es.SeleniumSVRI;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SVRITest {
	private static WebDriver driverFirefox = new FirefoxDriver();
	private static final String textoMsgCadastroSucesso = "Cadastro realizado com sucesso.";
	
	public static void main(String[] args) {
		cadastroSucesso();
	}
	
	public static void cadastroSucesso() {
		driverFirefox.get("http://svrideploy-svri.rhcloud.com/SVRI/cadastrarCliente");
		String telaCadastro = driverFirefox.getWindowHandle();
		
		driverFirefox.findElement(By.name("nome")).sendKeys("Teste Selenium");
		driverFirefox.findElement(By.name("email")).sendKeys("testeselenium@svri.com");
		driverFirefox.findElement(By.name("senha")).sendKeys("selenium");
		driverFirefox.findElement(By.name("dataDeNascimento")).sendKeys("01/01/2001");
		
		driverFirefox.findElement(By.cssSelector("form.pagina > button")).click();
		
		String telaAtual = driverFirefox.getWindowHandle();
		
		//String textoMensagem = driverFirefox.findElement(By.id("msgSucesso")).getText();
		
		assertEquals(telaCadastro, telaAtual);
		
		driverFirefox.quit();
	}
	
	public static void loginSucesso() {
		driverFirefox.findElement(By.cssSelector("form.pagina > button")).click();
		
		driverFirefox.findElement(By.name("email")).sendKeys("testeselenium@svri.com");
		driverFirefox.findElement(By.name("senha")).sendKeys("selenium");
		
		driverFirefox.findElement(By.cssSelector("form > button")).click();
	}
}
