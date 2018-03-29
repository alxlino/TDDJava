package exercicios;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class FiltroDeLancesTest {

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000), 
                new Lance(joao,1000),
                new Lance(joao,1001),
                new Lance(joao,2999),
                new Lance(joao,3000), 
                new Lance(joao,3001), 
                new Lance(joao, 800)));

        assertEquals(3, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
        assertEquals(1001, resultado.get(1).getValor(), 0.00001);
        assertEquals(2999, resultado.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600), 
                new Lance(joao,500),
                new Lance(joao,501),
                new Lance(joao,699),
                new Lance(joao,700),
                new Lance(joao,701),
                new Lance(joao, 800)));

        assertEquals(3, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
        assertEquals(501, resultado.get(1).getValor(), 0.00001);
        assertEquals(699, resultado.get(2).getValor(), 0.00001);
    }
    
    @Test
    public void deveSelecionarLancesMaior5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,6000), 
                new Lance(joao,5001), 
                new Lance(joao,45000), 
                new Lance(joao,5000), 
                new Lance(joao,4999),
                new Lance(joao,3001)));

        assertEquals(3, resultado.size());
        assertEquals(6000, resultado.get(0).getValor(), 0.00001);
        assertEquals(5001, resultado.get(1).getValor(), 0.00001);
        assertEquals(45000, resultado.get(2).getValor(), 0.00001);
    }
    
    @Test
    public void naoDeveSelecionarLancesIguaisAosLimitesRanges() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,1000), 
                new Lance(joao,3000), 
                new Lance(joao,500), 
                new Lance(joao,700),
                new Lance(joao,5000)));

        assertEquals(0, resultado.size());
    }
    
    
    //------------------RESPOSTA
    
    @Test
    public void deveSelecionarLancesMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,10000), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(10000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveEliminarMenoresQue500() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,400), 
                new Lance(joao, 300)));

        assertEquals(0, resultado.size());
    }

    @Test
    public void deveEliminarEntre700E1000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 1000),
                new Lance(joao, 700),
                new Lance(joao, 900)));

        assertEquals(0, resultado.size());
    }

    @Test
    public void deveEliminarEntre3000E5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,4000), 
                new Lance(joao, 3500)));

        assertEquals(0, resultado.size());
    }
}