package model;

public class Recurso {
    String recursoNome = null;
    String recursoEmail = null;
    String recursoProjeto = null;

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
}
