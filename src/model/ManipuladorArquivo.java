package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorArquivo{
//Recurso
    /**
     * Metodo que salva os objetos em arquivo binario
     * @param recurso lista de objetos de do tipo recurso
     * @param caminho caminho do arquivo binario
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void escritaRecursos(List<Recurso> recurso, String caminho) throws FileNotFoundException, IOException {
        ObjectOutputStream outputStream;

        outputStream = new ObjectOutputStream(new FileOutputStream(caminho, false));

        outputStream.writeObject(recurso);

        outputStream.close();           
    }
    
    /**
     * @param caminho
     * @return a lista de objetos armazenada no arquivo binario
     */
    public ArrayList<Recurso> leituraRecurso(String caminho) {  

        ArrayList<Recurso> listArquivo = new ArrayList<Recurso>(); 
        ObjectInputStream oInputStream = null;

        try { 

            oInputStream = new ObjectInputStream(new FileInputStream(caminho));

            listArquivo = (ArrayList) oInputStream.readObject();

            oInputStream.close();

        } catch ( ClassNotFoundException erro){
            erro.printStackTrace();
        } catch (EOFException eofException){ 
            eofException.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        //Retorna um arrayList com as linhas de informação
        return listArquivo;     
    }
//Recurso

//Licenças Obtidas
    /**
     * Metodo que salva os objetos em arquivo binario
     * @param licencasObtidas lista de objetos de do tipo licencasObtidas
     * @param caminho caminho do arquivo binario
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void escritaLicencaObtida(List<LicencasObtidas> licencasObtidas, String caminho) throws FileNotFoundException, IOException {
        ObjectOutputStream outputStream;

        outputStream = new ObjectOutputStream(new FileOutputStream(caminho, false));

        outputStream.writeObject(licencasObtidas);

        outputStream.close();       
    }
   
    /**
     * @param caminho
     * @return a lista de objetos armazenada no arquivo binario
     */
    public ArrayList<LicencasObtidas> leituraLicencaObtida(String caminho) {  

        ArrayList<LicencasObtidas> listArquivo = new ArrayList<LicencasObtidas>(); 
        ObjectInputStream oInputStream = null;

        try { 

            oInputStream = new ObjectInputStream(new FileInputStream(caminho));

            listArquivo = (ArrayList) oInputStream.readObject();

            oInputStream.close();

        } catch ( ClassNotFoundException erro){
            erro.printStackTrace();
        } catch (EOFException eofException){ 
            eofException.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        //Retorna um arrayList com as linhas de informação
        return listArquivo;   
    }
//Licenças Obtidas

//Licenças Necessarias
    /**
     * Metodo que salva os objetos em arquivo binario
     * @param licencasNecessarias lista de objetos de do tipo licencasNecessarias
     * @param caminho caminho do arquivo binario
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void escritaLicencaNecessarias(List<LicencasNecessarias> licencasNecessarias, String caminho) throws FileNotFoundException, IOException {
        ObjectOutputStream outputStream;

        outputStream = new ObjectOutputStream(new FileOutputStream(caminho, false));

        outputStream.writeObject(licencasNecessarias);

        outputStream.close();       
    }
    
    /**
     * @param caminho
     * @return a lista de objetos armazenada no arquivo binario
     */
    public List<LicencasNecessarias> leituraLicencaNecessarias(String caminho) {  

        ArrayList<LicencasNecessarias> listArquivo = new ArrayList<LicencasNecessarias>(); 
        ObjectInputStream oInputStream = null;

        try { 

            oInputStream = new ObjectInputStream(new FileInputStream(caminho));

            listArquivo = (ArrayList) oInputStream.readObject();

            oInputStream.close();

        } catch ( ClassNotFoundException erro){
            erro.printStackTrace();
        } catch (EOFException eofException){ 
            eofException.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        //Retorna um arrayList com as linhas de informação
        return listArquivo;   
    }
//Licenças Necessarias

//Projeto
    /**
     * Metodo que salva os objetos em arquivo binario
     * @param projetos lista de objetos de do tipo projeto
     * @param caminho caminho do arquivo binario
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void escritaProjetos(List<Projeto> projetos, String caminho) throws FileNotFoundException, IOException {
        ObjectOutputStream outputStream;

        outputStream = new ObjectOutputStream(new FileOutputStream(caminho, false));

        outputStream.writeObject(projetos);

        outputStream.close();       
    }

    /**
     * @param caminho
     * @return a lista de objetos armazenada no arquivo binario
     */
    public ArrayList<Projeto> leituraProjetos(String caminho) {  

        ArrayList<Projeto> listArquivo = new ArrayList<Projeto>(); 
        ObjectInputStream oInputStream = null;

        try { 

            oInputStream = new ObjectInputStream(new FileInputStream(caminho));

            listArquivo = (ArrayList) oInputStream.readObject();

            oInputStream.close();

        } catch ( ClassNotFoundException erro){
            erro.printStackTrace();
        } catch (EOFException eofException){ 
            eofException.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        //Retorna um arrayList com as linhas de informação
        return listArquivo;   
    }
//Projeto
}
