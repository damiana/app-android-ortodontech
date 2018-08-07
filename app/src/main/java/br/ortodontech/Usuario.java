package br.ortodontech;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {

    private int idPaciente;

    private String dataNascimento;
    private String nome;
    private String cidade;
    private String email;
    private String nickname;
    private String senha;

    private Boolean isVaiAoDentista;
    private Boolean isUsaAparelho;
    private Boolean isTemSangramento;
    private Boolean isQtdVezesEscova;
    private Boolean isQtdVezesUsaFio;
    private Boolean isUsaEnxagueBucal;
    private Boolean isFuma;
    private Boolean isTemDiabetes;

    private int pontuacao;


    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isVaiAoDentista() {
        return isVaiAoDentista;
    }

    public void setVaiAoDentista(Boolean vaiAoDentista) {
        isVaiAoDentista = vaiAoDentista;
    }

    public Boolean isUsaAparelho() {
        return isUsaAparelho;
    }

    public void setUsaAparelho(Boolean usaAparelho) {
        isUsaAparelho = usaAparelho;
    }

    public Boolean isTemSangramento() {
        return isTemSangramento;
    }

    public void setTemSangramento(Boolean temSangramento) {
        isTemSangramento = temSangramento;
    }

    public Boolean isQtdVezesEscova() {
        return isQtdVezesEscova;
    }

    public void setQtdVezesEscova(Boolean qtdVezesEscova) {
        isQtdVezesEscova = qtdVezesEscova;
    }

    public Boolean isQtdVezesUsaFio() {
        return isQtdVezesUsaFio;
    }

    public void setQtdVezesUsaFio(Boolean qtdVezesUsaFio) {
        isQtdVezesUsaFio = qtdVezesUsaFio;
    }

    public Boolean isUsaEnxagueBucal() {
        return isUsaEnxagueBucal;
    }

    public void setUsaEnxagueBucal(Boolean usaEnxagueBucal) {
        isUsaEnxagueBucal = usaEnxagueBucal;
    }

    public Boolean isFuma() {
        return isFuma;
    }

    public void setFuma(Boolean fuma) {
        isFuma = fuma;
    }

    public Boolean isTemDiabetes() {
        return isTemDiabetes;
    }

    public void setTemDiabetes(Boolean temDiabetes) {
        isTemDiabetes = temDiabetes;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
