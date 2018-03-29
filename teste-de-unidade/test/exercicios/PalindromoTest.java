package exercicios;

import org.junit.Assert;
import org.junit.Test;

public class PalindromoTest {
	
	@Test
    public void validarPalindromo() {
		
		String frase1 = "Socorram-me subi no onibus em Marrocos";
		
		String frase2 = "Anotaram a data da maratona";
		
		String frase3 = "Frase nada a ver que não é palíndromo";
		
		String frase4 = "A mala nada na lama";
		
		Palindromo palindromo = new Palindromo();
		
		Boolean resultado1 = palindromo.ehPalindromo(frase1);
		Boolean resultado2 = palindromo.ehPalindromo(frase2);
		Boolean resultado3 = palindromo.ehPalindromo(frase3);
		Boolean resultado4 = palindromo.ehPalindromo(frase4);
		
		Assert.assertTrue(resultado1);
		Assert.assertTrue(resultado2);
		Assert.assertFalse(resultado3);
		Assert.assertTrue(resultado4);
		
	}

}
