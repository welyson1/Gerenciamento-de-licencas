package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipuladorArquivo {
    
    /**
     * 
     * @param caminho
     * @return vetor com a informações concatenadas
     */
    public String[] manipuladorLeitura(String caminho) {    
        String dados = ""; 
        String infoArrayExterno[] = null;      
        try {            
            BufferedReader leitor = 
                new BufferedReader(new FileReader(new File(caminho)));
            
            //Gambiarra para contar as linhas com dados para então colocar no vetor
            BufferedReader leitor2 = 
                new BufferedReader(new FileReader(new File(caminho)));
            
            int contador = 0;
            while (leitor2.readLine() != null) contador++;
            leitor2.close();
            //Fim da gambiarra

            String infoArray[] = new String[contador];
            int i = 0;
            while (leitor.ready()) {
                dados = leitor.readLine();
                infoArray[i] = dados;
                i++;
            } 

            //Passa para o array de fora do try para poder retornar
            //Não sei o que eu to fazendo, perguntar para o professor......           
            infoArrayExterno = infoArray;

            leitor.close();            
        } catch (IOException erro){
            System.out.println("Você precisa fazer alguma entrada para consultar");
        }

        //Retorna um array com as linhas de informação
        return infoArrayExterno;      
    }

    /**
     * Metodo salva as informações do formulario em um arquivo txt
     * 
     * @param informacoes campos do cadastro concatenados por ;
     * @param caminho local do arquivo com extensão .txt
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void manipuladorEscrita(String informacoes, String caminho) throws FileNotFoundException, IOException {
        //Metodo 2 para armazenar dados
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(new File(caminho), true));
            fileWriter.write(informacoes);
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado.");
        } catch (IOException erro) {
            erro.printStackTrace();
        }

        /* //Maneira 1 de fazer a criação do arquivo caso não exista, colocar texto em cada linha
        File entrada = new File(caminho);
        FileWriter escritor = new FileWriter(entrada, true);
        escritor.write(informacoes);
        escritor.write(System.getProperty( "line.separator" ));
        escritor.flush();
        escritor.close();*/
    }
}
