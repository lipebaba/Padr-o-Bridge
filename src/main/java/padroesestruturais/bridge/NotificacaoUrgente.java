package padroesestruturais.bridge;

public class NotificacaoUrgente extends Notificacao {

    public NotificacaoUrgente(String mensagem, CanalNotificacao canal) {
        super(mensagem, canal);
    }

    @Override
    public String enviar() {
        return this.canal.enviar("[URGENTE] " + this.mensagem);
    }
}
