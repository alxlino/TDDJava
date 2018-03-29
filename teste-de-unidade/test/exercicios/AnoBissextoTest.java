package exercicios;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveSerBissextoQndoMultiploDe4() {

		Random randomGenerator = new Random();
		int randomInt;

		Boolean resultado1 = false;
		Boolean resultado2 = false;
		Boolean resultado3 = false;
		Boolean resultado4 = false;
		Boolean resultado5 = false;

		AnoBissexto anoBissexto = new AnoBissexto();

		randomInt = randomGenerator.nextInt(3000);
		resultado1 = anoBissexto.ehBissexto(randomInt * 4);

		randomInt = randomGenerator.nextInt(1500);
		resultado2 = anoBissexto.ehBissexto(randomInt * 4);

		randomInt = randomGenerator.nextInt(800);
		resultado3 = anoBissexto.ehBissexto(randomInt * 4);

		randomInt = randomGenerator.nextInt(1310);
		resultado4 = anoBissexto.ehBissexto(randomInt * 4);

		randomInt = randomGenerator.nextInt(1000);
		resultado5 = anoBissexto.ehBissexto(randomInt * 4);

		assertEquals(true, resultado1);
		assertEquals(true, resultado2);
		assertEquals(true, resultado3);
		assertEquals(true, resultado4);
		assertEquals(true, resultado5);

	}
	
	@Test
	public void naoDeveSerBissextoQndoMultiploDe100() {

		Random randomGenerator = new Random();
		int randomInt;

		Boolean resultado1 = false;
		Boolean resultado2 = false;
		Boolean resultado3 = false;

		AnoBissexto anoBissexto = new AnoBissexto();

		randomInt = randomGenerator.nextInt(3000);
		resultado1 = anoBissexto.ehBissexto(29 * 100);

		randomInt = randomGenerator.nextInt(1500);
		resultado2 = anoBissexto.ehBissexto(15 * 100);

		randomInt = randomGenerator.nextInt(800);
		resultado3 = anoBissexto.ehBissexto(77 * 100);
		
		assertEquals(false, resultado1);
		assertEquals(false, resultado2);
		assertEquals(false, resultado3);

	}	
	
	@Test
	public void deveSerBissextoQndoMultiploDe400() {

		Random randomGenerator = new Random();
		int randomInt;

		Boolean resultado1 = false;
		Boolean resultado2 = false;
		Boolean resultado3 = false;

		AnoBissexto anoBissexto = new AnoBissexto();

		randomInt = randomGenerator.nextInt(300);
		resultado1 = anoBissexto.ehBissexto(randomInt * 400);

		randomInt = randomGenerator.nextInt(150);
		resultado2 = anoBissexto.ehBissexto(randomInt * 400);

		randomInt = randomGenerator.nextInt(800);
		resultado3 = anoBissexto.ehBissexto(randomInt * 400);

		assertEquals(true, resultado1);
		assertEquals(true, resultado2);
		assertEquals(true, resultado3);

	}

	
	@Test
	public void deveRetornarAnoBissextoAlura() {        
	    AnoBissexto anoBissexto = new AnoBissexto();

	    assertEquals(true, anoBissexto.ehBissexto(2016));
	    assertEquals(true, anoBissexto.ehBissexto(2012));
	}

	
	@Test
	public void naoDeveRetornarAnoBissextoAlura() {        
	    AnoBissexto anoBissexto = new AnoBissexto();        

	    assertEquals(false, anoBissexto.ehBissexto(2015));
	    assertEquals(false, anoBissexto.ehBissexto(2011));
	}
	
	
}
