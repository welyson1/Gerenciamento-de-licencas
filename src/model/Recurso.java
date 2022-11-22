package model;

import java.io.Serializable;

public class Recurso implements Serializable{
    String recursoNome = "";
    String recursoEmail = "";
    String recursoProjeto = "";

    public Recurso() {
        
    }
    public Recurso(String recursoNome, String recursoEmail, String recursoProjeto) {
        this.recursoNome = recursoNome;
        this.recursoEmail = recursoEmail;
        this.recursoProjeto = recursoProjeto;
    }
    public String getRecursoNome() {
        return recursoNome;
    }
    public void setRecursoNome(String recursoNome) {
        this.recursoNome = recursoNome;
    }
    public String getRecursoEmail() {
        return recursoEmail;
    }
    public void setRecursoEmail(String recursoEmail) {
        this.recursoEmail = recursoEmail;
    }
    public String getRecursoProjeto() {
        return recursoProjeto;
    }
    public void setRecursoProjeto(String recursoProjeto) {
        this.recursoProjeto = recursoProjeto;
    }
    public String readObject() {
        return null;
    }

    public void mostrarRecurso() {
        System.out.println("------------------------------------");
        System.out.println("Nome: "+ getRecursoNome());
        System.out.println("E-mail: "+ getRecursoEmail());
        System.out.println("Projeto: " + getRecursoProjeto());
        
    }
}
