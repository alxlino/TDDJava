package dominio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import builder.CriadorDeLeilao;
import static br.com.caelum.matcher.LeilaoMatcher.temUmLance;

public class LeilaoTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario ragnar;
	private Usuario maria;
	private Usuario odda;
	private Usuario uhtred;
	private Usuario steveJobs;


	@Before
	public void setUp() {
		this.steveJobs = new Usuario("Steve Jobs");
		this.uhtred = new Usuario("Uhtred");
		this.ragnar = new Usuario("Ragnar");
		//System.out.println("before");
	}

	
	@BeforeClass
	public static void testandoBeforeClass() {
	  //System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  //System.out.println("after class");
	}
	
	@After
	public void finaliza() {
	  //System.out.println("after");
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.lance(uhtred, 200.0)
				.lance(uhtred, 250.0)
				.lance(uhtred, 280.0)
				.lance(ragnar, 300.0)
				.lance(ragnar, 400.0)
				.lance(ragnar, 450.0)
				.lance(uhtred, 500.0)
				.lance(uhtred, 550.0)
				.constroi();
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(200.0, leilao.getLances().get(0).getValor(),0.00001);
		assertEquals(300.0, leilao.getLances().get(1).getValor(),0.00001);
		assertEquals(500.0, leilao.getLances().get(2).getValor(),0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.lance(uhtred, 200.0)
				.lance(ragnar, 250.0)
				.lance(uhtred, 300.0)
				.lance(ragnar, 450.0)
				.lance(uhtred, 500.0)
				.lance(ragnar, 650.0)
				.lance(uhtred, 700.0)
				.lance(ragnar, 850.0)
				.lance(uhtred, 900.0)
				.lance(ragnar, 1050.0)
				.lance(uhtred, 1100.0)
				.lance(ragnar, 1250.0)
				.lance(uhtred, 1350.0)
				.lance(ragnar, 1450.0)
				.constroi();
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(900.0, leilao.getLances().get(8).getValor(),0.00001);
		assertEquals(1050.0, leilao.getLances().get(9).getValor(),0.00001);
		
	}
	
	@Test
	public void deveCriarLanceComDobroDoValorDoUltimoLanceDoUsuario() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.lance(uhtred, 200.0) //1
				.lance(ragnar, 250.0) //2
				.lance(uhtred, 300.0) //3
				.lance(ragnar, 400.0) //4
				.lance(uhtred, 500.0) //5
				.lance(ragnar, 600.0) //6
				.dobralance(uhtred)   //7
				.dobralance(ragnar)   //8
				.constroi();
		
		assertEquals(8, leilao.getLances().size());
		assertEquals(200.0, leilao.getLances().get(0).getValor(),0.00001);
		assertEquals(250.0, leilao.getLances().get(1).getValor(),0.00001);
		assertEquals(1000.0, leilao.getLances().get(6).getValor(),0.00001);
		assertEquals(1200.0, leilao.getLances().get(7).getValor(),0.00001);

	}
	
	@Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
        		.dobralance(steveJobs)
        		.constroi();

        assertEquals(0, leilao.getLances().size());
    }
	
	
	@Test
	public void naoDeveDobrarSegundoLanceSeguido() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.lance(uhtred, 200.0) //1
				.lance(ragnar, 250.0) //2
				.dobralance(ragnar)   //3
				.dobralance(uhtred)   //deve ser ignorado
				.dobralance(ragnar)   //4
				.dobralance(ragnar)   //deve ser ignorado
				.dobralance(ragnar)   //deve ser ignorado
				.constroi();
		
		assertEquals(4, leilao.getLances().size());
		assertEquals(200.0, leilao.getLances().get(0).getValor(),0.00001);
		assertEquals(250.0, leilao.getLances().get(1).getValor(),0.00001);
		assertEquals(400.0, leilao.getLances().get(2).getValor(),0.00001);
		assertEquals(500.0, leilao.getLances().get(3).getValor(),0.00001);

	}
	
	@Test(expected=IllegalArgumentException.class)
    public void naoDeveAceitarLancesMenorQueZero() {
        
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
        		.lance(steveJobs, -100.0)
        		.lance(uhtred, -1)
        		.constroi();

        assertEquals(0, leilao.getLances().size());
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void naoDeveAceitarLancesIgualZero() {
        
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
        		.lance(ragnar, 0)
        		.lance(steveJobs, 0.0)
        		.lance(uhtred, 0.00)
        		.constroi();

        assertEquals(0, leilao.getLances().size());
    }
	
	// @Factory
		// public static Matcher<Leilao> temUmLance(Lance lance) {
		// 	return new LeilaoMatcher(lance);
		// }
		
		@Test
		public void existeLanceNoLeilao() {
			
			Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
					.lance(odda, 100.0)
					.lance(maria, 200.0)
					.lance(joao, 300.0)
					.lance(joao, 400.0)
					.lance(ragnar, 500.0)
					.constroi();

			leiloeiro.avalia(leilao);
			
			//assertThat(leilao, temUmLance(leiloeiro.getTodosLances().get(2)));
			assertThat(leilao, temUmLance(new Lance(joao, 300.0)));
		}
	
}
