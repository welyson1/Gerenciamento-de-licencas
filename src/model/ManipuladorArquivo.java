package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorArquivo {
    
    /**
     * 
     * @param caminho
     * @return vetor com a informações concatenadas
     */
    public List<String> manipuladorLeitura(String caminho) {  
        
        List<String> listArquivo = new ArrayList<String>();     
        try {  

            BufferedReader leitor = 
                new BufferedReader(new FileReader(new File(caminho)));
            
            while (leitor.ready()) {
                listArquivo.add(leitor.readLine());
            } 

            leitor.close();  

        } catch (IOException erro){
            System.out.println("Você precisa fazer alguma entrada para consultar");
        }

        //Retorna um array com as linhas de informação
        return listArquivo;   
    }

    /**
     * Metodo salva as informações do formulario em um arquivo txt
     * 
     * @param informacoes campos do cadastro concatenados por ;
     * @param caminho local do arquivo com extensão .txt
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void manipuladorEscrita(List<Recurso> recurso, String caminho) throws FileNotFoundException, IOException {
        
        FileWriter writer = new FileWriter(caminho); 
        for(Recurso str: recurso) {
            writer.write(
                str.getRecursoNome() + ";" + 
                str.getRecursoEmail() + ";" +  
                str.getRecursoProjeto() + 
                System.lineSeparator()
            );
        }
        writer.close();

        /*/List<Recurso> listRecursosArquivo = new ArrayList<Recurso>();
        StringBuffer sb = new StringBuffer();
        Iterator<Recurso> it = recurso.iterator();
        while(it.hasNext()){
            sb.append(it.next());
            sb.append(";");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        bw.write(sb.toString());
        bw.flush();
        bw.close();*/
        

        //Metodo 2 para armazenar dados
        /*BufferedWriter fileWriter = null;
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
        }*/

        /* //Maneira 1 de fazer a criação do arquivo caso não exista, colocar texto em cada linha
        File entrada = new File(caminho);
        FileWriter escritor = new FileWriter(entrada, true);
        escritor.write(informacoes);
        escritor.write(System.getProperty( "line.separator" ));
        escritor.flush();
        escritor.close();*/
    }
}
