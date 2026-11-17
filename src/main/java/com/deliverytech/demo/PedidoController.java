package com.deliverytech.demo;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final BigDecimal PRECO = new BigDecimal("25.00");

    public record Pedido(int quantidade) {}

    public record Resultado(
            int quantidade,
            BigDecimal valorUnitario,
            BigDecimal valorTotal) {}

    public record Erro(String mensagem) {}

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(@RequestBody Pedido pedido) {
        // TODO (Atividade 2): recuperar a quantidade enviada.
        // TODO: devolver HTTP 400 para quantidade menor que 1 ou maior que 20.
        // TODO: multiplicar a quantidade pelo PRECO usando BigDecimal.
        // TODO: devolver HTTP 200 com Resultado(quantidade, PRECO, total).

        return ResponseEntity.status(501)
                .body(new Erro("Funcionalidade ainda não implementada."));
    }
}
