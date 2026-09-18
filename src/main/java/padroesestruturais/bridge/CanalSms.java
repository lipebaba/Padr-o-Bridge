package padroesestruturais.bridge;

public class CanalSms implements CanalNotificacao {

    @Override
    public String enviar(String mensagem) {
        return "SMS: " + mensagem;
    }
}
