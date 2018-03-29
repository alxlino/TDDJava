package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	public int total = 0;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		qntdeDeLancesDo(lance.getUsuario());
		
		if (lances.isEmpty() || podeDarLance(lance.getUsuario()) ){
			lances.add(lance);
		} 	
	}
	
	public void dobraLance(Usuario usuario) {
		
		Double valor = valorUltimoLanceDoUsuario(usuario);
		
		qntdeDeLancesDo(usuario);
		
		if (valor!=null && podeDarLance(usuario)) {
			propoe(new Lance(usuario, valor * 2));
		}
				
	}
	
	private Double valorUltimoLanceDoUsuario(Usuario usuario) {
		
		Double valor = null;
		
		Lance lance = null;
		
		lance = ultimoLanceDo(usuario);
		
		if (lance!=null) {
			
			valor = lance.getValor();
		}
		
		return valor;
		
	}
	
	private Lance ultimoLanceDo(Usuario usuario) {
		
		Lance lance = null;
		
		int qtdLancesUsr = 1;
		
		for (Lance l : lances) {
			
			if (l.getUsuario().equals(usuario) && qtdLancesUsr < 5) {
				lance = l;
				qtdLancesUsr++;
			}
			
		}
		
		return lance;
	}
	
	public void dobraLanceAlura(Usuario usuario) {
        Lance ultimoLance = ultimoLanceDo(usuario);
        if(ultimoLance!=null) {
            propoe(new Lance(usuario, ultimoLance.getValor()*2));
        }
    }

    private Lance ultimoLanceDoAlura(Usuario usuario) {
        Lance ultimo = null;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) ultimo = lance;
        }

        return ultimo;
    }

	private boolean podeDarLance(Usuario usuario) {
		return ultimoLanceDado(usuario) && total < 5;
	}

	private void qntdeDeLancesDo(Usuario usuario) {
		total = 0;
		for (Lance l : lances) {
			if (l.getUsuario().equals(usuario)) total++;
		}
	}

	private boolean ultimoLanceDado(Usuario usuario) {
		return !lances.get(lances.size()-1).getUsuario().equals(usuario);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

}
