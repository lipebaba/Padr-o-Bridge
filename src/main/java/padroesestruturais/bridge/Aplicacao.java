package padroesestruturais.bridge;

public class Aplicacao {

    public static void main(String[] args) {
        CanalNotificacao email = new CanalEmail();
        CanalNotificacao sms = new CanalSms();

        Notificacao informativa = new NotificacaoInformativa("Pedido confirmado", email);
        Notificacao urgente = new NotificacaoUrgente("Estoque esgotado", email);
        System.out.println(informativa.enviar());
        System.out.println(urgente.enviar());

        informativa.setCanal(sms);
        urgente.setCanal(sms);
        System.out.println(informativa.enviar());
        System.out.println(urgente.enviar());
    }
}