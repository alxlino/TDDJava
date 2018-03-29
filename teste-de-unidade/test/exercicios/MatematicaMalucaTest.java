package exercicios;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
    public void validarMatematicaMaluca() {
		
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int maiorTrinta = 85;
		int maiorDezMenorTrinta = 29;
		int dez = 10;
		int trinta = 30;
		int menorDez = 2;
		
		int teste1 = matematica.contaMaluca(maiorTrinta);
		int teste2 = matematica.contaMaluca(maiorDezMenorTrinta);
		int teste3 = matematica.contaMaluca(dez);
		int teste4 = matematica.contaMaluca(trinta);
		int teste5 = matematica.contaMaluca(menorDez);
		
		assertEquals(maiorTrinta*4, teste1);
		assertEquals(maiorDezMenorTrinta*3, teste2);
		assertEquals(dez*2, teste3);
		assertEquals(trinta*3, teste4);
		assertEquals(menorDez*2, teste5);
	}
	
	@Test
	public void deveMultiplicarNumerosMaioresQue30() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(50 * 4, matematica.contaMaluca(50));
	}

	@Test
	public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(20 * 3, matematica.contaMaluca(20));
	}

	@Test
	public void deveMultiplicarNumerosMenoresQue10() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(5 * 2, matematica.contaMaluca(5));
	}
}
