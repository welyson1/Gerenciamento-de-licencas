package model;

import java.io.Serializable;

public class Projeto implements Serializable{
    String projetoNome = null;
    String projetoTecnologia = null;
    float projetoValor = 0;

    public Projeto() {
        
    }

    public Projeto(String projetoNome, String projetoTecnologia, float projetoValor) {
        this.projetoNome = projetoNome;
        this.projetoTecnologia = projetoTecnologia;
        this.projetoValor = projetoValor;
    }
    
    public String getProjetoNome() {
        return projetoNome;
    }
    public void getProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }
    public String getProjetoTecnologia() {
        return projetoTecnologia;
    }
    public void setProjetoTecnologia(String projetoTecnologia) {
        this.projetoTecnologia = projetoTecnologia;
    }
    public float getProjetoValor() {
        return projetoValor;
    }
    public void setProjetoValor(float projetoValor) {
        this.projetoValor = projetoValor;
    }
}
