package model;

public class LicencasNecessarias {
    String treinamentoNome = null;
    String treinamentoLink = null;
    String treinamentoCategoria = null;
    String treinamentoLevel = null;

    public String getTreinamentoNome() {
        return treinamentoNome;
    }

    public void setTreinamentoNome(String treinamentoNome) {
        this.treinamentoNome = treinamentoNome;
    }

    public String getTreinamentoLink() {
        return treinamentoLink;
    }

    public void setTreinamentoLink(String treinamentoLink) {
        this.treinamentoLink = treinamentoLink;
    }

    public String getTreinamentoCategoria() {
        return treinamentoCategoria;
    }

    public void setTreinamentoCategoria(String treinamentoCategoria) {
        this.treinamentoCategoria = treinamentoCategoria;
    }

    public String getTreinamentoLevel() {
        return treinamentoLevel;
    }

    public void setTreinamentoLevel(String treinamentoLevel) {
        this.treinamentoLevel = treinamentoLevel;
    }

    public LicencasNecessarias() {
    }
    
    public LicencasNecessarias(String treinamentoNome, String treinamentoLink, String treinamentoCategoria,
            String treinamentoLevel) {
        this.treinamentoNome = treinamentoNome;
        this.treinamentoLink = treinamentoLink;
        this.treinamentoCategoria = treinamentoCategoria;
        this.treinamentoLevel = treinamentoLevel;
    }

    
}
