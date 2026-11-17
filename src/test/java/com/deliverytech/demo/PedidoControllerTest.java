package com.deliverytech.demo;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoControllerTest {
    private final PedidoController controller = new PedidoController();

    @Test
    void calculaQuatroKits() {
        ResponseEntity<?> resposta = controller.calcular(new PedidoController.Pedido(4));
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isInstanceOf(PedidoController.Resultado.class);
        var resultado = (PedidoController.Resultado) resposta.getBody();
        assertThat(resultado.quantidade()).isEqualTo(4);
        assertThat(resultado.valorUnitario()).isEqualByComparingTo(new BigDecimal("25.00"));
        assertThat(resultado.valorTotal()).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void rejeitaQuantidadeZero() {
        ResponseEntity<?> resposta = controller.calcular(new PedidoController.Pedido(0));
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void rejeitaQuantidadeAcimaDoLimite() {
        ResponseEntity<?> resposta = controller.calcular(new PedidoController.Pedido(21));
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
