package padroesestruturais.bridge;

public class CanalEmail implements CanalNotificacao {

    @Override
    public String enviar(String mensagem) {
        return "E-mail: " + mensagem;
    }
}
