package apoio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class testeFile {
	public static void escreverArquivo() throws Exception {
        String entrada;
        String saida = "";
        
        String id = "123";
       
        String nomePlanilha = JOptionPane.showInputDialog("Digite o nome da planilha para execução: ");
        
        try {
            entrada = new String(Files.readAllBytes(Paths.get("C:\\Users\\Inmetrics\\Desktop\\"+ nomePlanilha +".csv")));  
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "PLANILHA '"+ nomePlanilha.toUpperCase() +"' NAO EXISTE , PRESTA ATENÇÃO !!!! ");
			throw new Exception();
		}
        
        String[] quebraLinhas = entrada.split("\r\n");
        
        for (String linha : quebraLinhas) {
			if(linha.contains(id)){
				linha = linha.replace(linha, linha + "passed");
				saida += linha + "\r\n";
			}else
				saida += linha+ "\r\n";
		}

        FileWriter fileWriter = new FileWriter("C:\\Users\\Inmetrics\\Desktop\\testeplanilha2.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(saida);       
        printWriter.close();

	}
	
	public static void main(String[] args) throws Exception {
		escreverArquivo();
		System.out.println("FIM TESTE DO LUCAS LEPRI s2 QUE QUER ASSIM");
	}
	
}
