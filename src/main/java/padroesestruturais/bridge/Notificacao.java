package padroesestruturais.bridge;

public abstract class Notificacao {

    protected CanalNotificacao canal;
    protected final String mensagem;

    public Notificacao(String mensagem, CanalNotificacao canal) {
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Mensagem obrigatória");
        }
        this.mensagem = mensagem;
        this.setCanal(canal);
    }

    public void setCanal(CanalNotificacao canal) {
        if (canal == null) {
            throw new IllegalArgumentException("Canal obrigatório");
        }
        this.canal = canal;
    }

    public abstract String enviar();
}