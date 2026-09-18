package padroesestruturais.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificacaoTest {

    @Test
    void deveEnviarInformativaPorEmail() {
        Notificacao notificacao = new NotificacaoInformativa("Pedido confirmado", new CanalEmail());
        assertEquals("E-mail: [INFORMAÇÃO] Pedido confirmado", notificacao.enviar());
    }

    @Test
    void deveEnviarInformativaPorSms() {
        Notificacao notificacao = new NotificacaoInformativa("Pedido confirmado", new CanalSms());
        assertEquals("SMS: [INFORMAÇÃO] Pedido confirmado", notificacao.enviar());
    }

    @Test
    void deveEnviarUrgentePorEmail() {
        Notificacao notificacao = new NotificacaoUrgente("Estoque esgotado", new CanalEmail());
        assertEquals("E-mail: [URGENTE] Estoque esgotado", notificacao.enviar());
    }

    @Test
    void deveEnviarUrgentePorSms() {
        Notificacao notificacao = new NotificacaoUrgente("Estoque esgotado", new CanalSms());
        assertEquals("SMS: [URGENTE] Estoque esgotado", notificacao.enviar());
    }

    @Test
    void deveTrocarCanalDaMesmaNotificacao() {
        Notificacao notificacao = new NotificacaoUrgente("Alerta", new CanalEmail());
        assertEquals("E-mail: [URGENTE] Alerta", notificacao.enviar());
        notificacao.setCanal(new CanalSms());
        assertEquals("SMS: [URGENTE] Alerta", notificacao.enviar());
    }

    @Test
    void deveDelegarMensagemFormatadaParaNovoCanal() {
        StringBuilder recebida = new StringBuilder();
        CanalNotificacao canal = mensagem -> {
            recebida.append(mensagem);
            return "Recebido pelo novo canal";
        };
        Notificacao notificacao = new NotificacaoInformativa("Novidade", canal);
        assertEquals("Recebido pelo novo canal", notificacao.enviar());
        assertEquals("[INFORMAÇÃO] Novidade", recebida.toString());
    }

    @Test
    void deveRejeitarCanalNuloNoConstrutor() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> new NotificacaoInformativa("Aviso", null));
        assertEquals("Canal obrigatório", erro.getMessage());
    }

    @Test
    void devePreservarCanalAposTrocaInvalida() {
        Notificacao notificacao = new NotificacaoUrgente("Alerta", new CanalSms());
        assertThrows(IllegalArgumentException.class, () -> notificacao.setCanal(null));
        assertEquals("SMS: [URGENTE] Alerta", notificacao.enviar());
    }

    @Test
    void deveRejeitarMensagemNula() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> new NotificacaoInformativa(null, new CanalEmail()));
        assertEquals("Mensagem obrigatória", erro.getMessage());
    }

    @Test
    void deveRejeitarMensagemVazia() {
        assertThrows(IllegalArgumentException.class,
                () -> new NotificacaoUrgente("", new CanalSms()));
    }

    @Test
    void deveRejeitarMensagemComEspacos() {
        assertThrows(IllegalArgumentException.class,
                () -> new NotificacaoUrgente(" \t\n", new CanalSms()));
    }
}