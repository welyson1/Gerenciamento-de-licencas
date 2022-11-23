package model;

import java.io.Serializable;

public class Projeto implements Serializable{
    String projetoNome = null;
    String projetoTecnologia = null;
    String projetoValor = null;

    public Projeto() {
        
    }

    public Projeto(String projetoNome, String projetoTecnologia, String projetoValor) {
        this.projetoNome = projetoNome;
        this.projetoTecnologia = projetoTecnologia;
        this.projetoValor = projetoValor;
    }
    
    public String setProjetoNome() {
        return projetoNome;
    }
    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }
    public String getProjetoTecnologia() {
        return projetoTecnologia;
    }
    public void setProjetoTecnologia(String projetoTecnologia) {
        this.projetoTecnologia = projetoTecnologia;
    }
    public String getProjetoValor() {
        return projetoValor;
    }
    public void setProjetoValor(String projetoValor) {
        this.projetoValor = projetoValor;
    }
}
