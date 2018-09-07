package br.ortodontech.notification;

public class NotificationVO {

    private String titulo;
    private String mensagem;
    private String iconeUrl;
    private String acao;
    private String acaoDestino;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getIconeUrl() {
        return iconeUrl;
    }

    public void setIconeUrl(String iconeUrl) {
        this.iconeUrl = iconeUrl;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getAcaoDestino() {
        return acaoDestino;
    }

    public void setAcaoDestino(String acaoDestino) {
        this.acaoDestino = acaoDestino;
    }
}
