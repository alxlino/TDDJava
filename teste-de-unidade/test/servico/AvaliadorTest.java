package servico;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import builder.CriadorDeLeilao;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario ragnar;
	private Usuario alfred;
	private Usuario maria;
	private Usuario odda;
	private Usuario uhtred;

	@Before
	public void setUp() {
		
		this.leiloeiro =  new Avaliador();
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
		this.uhtred = new Usuario("Uhtred");
		this.ragnar = new Usuario("Ragnar");
		this.alfred = new Usuario("Alfred");
		this.odda = new Usuario("Odda");
		//System.out.println("teste before...");
		
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
			
			Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
			
			leiloeiro.avalia(leilao);
		
	}
	
	@Test
    public void deveEntenderLancesEmOrdemCrescente() {
        
		// cenario: 3 lances em ordem crescente

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));
        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,301.0));
        leilao.propoe(new Lance(jose,302.0));
        
        // executando a acao
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;
        double mediaEsperada = 300.5;
        
        assertThat(leiloeiro.getMaiorLance(), equalTo(maiorEsperado));
        assertThat(leiloeiro.getMenorLance(), equalTo(menorEsperado));
        assertThat(leiloeiro.getMediaLance(), equalTo(mediaEsperada));
        
        //assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        //assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        //assertEquals(mediaEsperada, leiloeiro.getMediaLance(), 0.0001);
    }
	
	 @Test
	 public void deveEncontrarOsTresMaioresLances() {
		
		// Leilao leilao = new Leilao("Playstation 3 Novo");
		//
		// leilao.propoe(new Lance(joao, 100.0));
		// leilao.propoe(new Lance(maria, 200.0));
		// leilao.propoe(new Lance(joao, 300.0));
		// leilao.propoe(new Lance(maria, 400.0));
		// leilao.propoe(new Lance(joao, 150.0));
		// leilao.propoe(new Lance(joao, 180.0));
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.lance(joao, 150.0)
				.lance(joao, 180.0)
				.constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
//		assertThat(maiores.size(), equalTo(3));
		assertThat(maiores.get(0).getValor(), equalTo(400.0));
		assertThat(maiores.get(1).getValor(), is(equalTo(300.0)));
		assertThat(maiores.get(2).getValor(), is(200.0));
		
		 assertThat(maiores, hasItems(
			 new Lance(maria, 200.0),
			 new Lance(joao, 300.0),
			 new Lance(maria, 400.0)
		 ));
		
		//assertEquals(3, maiores.size());
		//assertEquals(400, maiores.get(0).getValor(), 0.00001);
		//assertEquals(300, maiores.get(1).getValor(), 0.00001);
		//assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}
	
	 @Test
	 public void deveEncontrarApenasUmLances() {
		

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(uhtred, 200.0));

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 200;
		double menorEsperado = 200;
		int apenasUmlance = 1;
		
		assertThat(leilao.getLances().size(), equalTo(apenasUmlance));
		assertThat(leiloeiro.getMaiorLance(), is(maiorEsperado));
		assertThat(leiloeiro.getMenorLance(), is(menorEsperado));
		
		//assertEquals(apenasUmlance, leilao.getLances().size());
		//assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		//assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}
	 
	@Test
	public void deveEncontrarMaiorMenorEmValoresAleatorios() {

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(uhtred, 200.0));
		leilao.propoe(new Lance(ragnar, 450.0));
		leilao.propoe(new Lance(alfred, 120.0));
		leilao.propoe(new Lance(odda, 700.0));
		leilao.propoe(new Lance(uhtred, 630.0));
		leilao.propoe(new Lance(alfred, 230.0));

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 700.0;
		double menorEsperado = 120.0;

		
		assertThat(leiloeiro.getMaiorLance(), is(maiorEsperado));
		assertThat(leiloeiro.getMenorLance(), is(menorEsperado));
		
		//assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		//assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}
	
	
	@Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        
		// cenario: 3 lances em ordem crescente

        Leilao leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(maria,400.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,200.0));
        leilao.propoe(new Lance(maria,100.0));
        
        // executando a acao
        leiloeiro.avalia(leilao);
        
        List<Lance> todoLances = leiloeiro.getTodosLances();

        assertThat(todoLances.size(), equalTo(4));
        assertThat(todoLances.get(0).getValor(), equalTo(400.0));
        assertThat(todoLances.get(1).getValor(), equalTo(300.0));
        assertThat(todoLances.get(2).getValor(), equalTo(200.0));
        assertThat(todoLances.get(3).getValor(), equalTo(100.0));
        
		//assertEquals(4, todoLances.size());
		//assertEquals(400.0, todoLances.get(0).getValor(), 0.00001);
		//assertEquals(300.0, todoLances.get(1).getValor(), 0.00001);
		//assertEquals(200.0, todoLances.get(2).getValor(), 0.00001);
		//assertEquals(100.0, todoLances.get(3).getValor(), 0.00001);

    }
	
	@Test
	 public void deveEncontrarApenasDoisLances() {

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(uhtred, 200.0));
		leilao.propoe(new Lance(maria, 300.0));

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		int apenasDoisLances = 2;
		
		assertThat(leilao.getLances().size(), equalTo(apenasDoisLances));
		
		//assertEquals(apenasDoisLances, leilao.getLances().size());
		
	}	
	
//	@Test
//	 public void deveEncontrarSemNenhumLance() {
//		
//		Leilao leilao = new Leilao("Playstation 4 Novo");
//
//		// executando a acao
//		leiloeiro.avalia(leilao);
//
//		// comparando a saida com o esperado
//		int nenhumLance = 0;
//		
//		assertEquals(nenhumLance, leilao.getLances().size());
//		
//	}
	
	@After
	public void finaliza() {
	  //System.out.println("fim");
	}
}
