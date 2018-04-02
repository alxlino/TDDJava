package br.com.caelum.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

	private final Lance lance;
	private Leilao leilao;

	public LeilaoMatcher(Lance lance) {
		this.lance = lance;
	}

	@Override
	protected boolean matchesSafely(Leilao leilao) {
		this.leilao = leilao;
		
		Boolean temLance = false;
		
		//SOLUÇÃO ALURA
		//return leilao.getLances().contains(this.lance);
		
		for (int i = 0; i < leilao.getLances().size(); i++) {
			
			if (this.lance.equals(leilao.getLances().get(i))) { 
				temLance = true;
			}		
		} 
		
		return temLance;
	}
	
	public void describeTo(Description description) {
		description.appendText("valor do lance ").appendValue(this.lance.getValor());
		description.appendText(" usuário do lance ").appendValue(this.lance.getUsuario().toString());
		description.appendText(" leilão do lance ").appendValue(this.leilao);
		
		//SOLUÇÃO ALURA
		//description.appendText("leilao com lance " + lance.getValor());
	}
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }
}
