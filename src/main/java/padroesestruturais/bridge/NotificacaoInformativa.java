package padroesestruturais.bridge;

public class NotificacaoInformativa extends Notificacao {

    public NotificacaoInformativa(String mensagem, CanalNotificacao canal) {
        super(mensagem, canal);
    }

    @Override
    public String enviar() {
        return this.canal.enviar("[INFORMAÇÃO] " + this.mensagem);
    }
}
