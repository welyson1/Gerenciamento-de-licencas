package model;

import java.io.Serializable;

public class LicencasObtidas implements Serializable{
    String licencasObtidasRecursoEmail = "";
    String licencasObtidasTreinamentoNome = "";
    String dataConclusao = "";
    public LicencasObtidas() {
    }
    public LicencasObtidas(String licencasObtidasRecursoEmail, String licencasObtidasTreinamentoNome,
            String dataConclusao) {
        this.licencasObtidasRecursoEmail = licencasObtidasRecursoEmail;
        this.licencasObtidasTreinamentoNome = licencasObtidasTreinamentoNome;
        this.dataConclusao = dataConclusao;
    }
    public String getLicencasObtidasRecursoEmail() {
        return licencasObtidasRecursoEmail;
    }
    public void setLicencasObtidasRecursoEmail(String licencasObtidasRecursoEmail) {
        this.licencasObtidasRecursoEmail = licencasObtidasRecursoEmail;
    }
    public String getLicencasObtidasTreinamentoNome() {
        return licencasObtidasTreinamentoNome;
    }
    public void setLicencasObtidasTreinamentoNome(String licencasObtidasTreinamentoNome) {
        this.licencasObtidasTreinamentoNome = licencasObtidasTreinamentoNome;
    }
    public String getDataConclusao() {
        return dataConclusao;
    }
    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

}
