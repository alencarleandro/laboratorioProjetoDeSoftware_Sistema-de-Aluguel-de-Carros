package br.com.aluguel.aluguelcarros.dto;

import br.com.aluguel.aluguelcarros.model.StatusPedido;
import java.time.LocalDate;

// DTOs são ideais como 'records' no Java moderno.
// Eles são imutáveis e já vêm com getters, construtor, equals e hashCode.
public record PedidosDeAluguelResponseDTO(
        Long id,
        String nomeCliente,
        String infoAutomovel,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusPedido status
) {
    // Um record não precisa de mais nada aqui dentro. O Java cria tudo para você.
}