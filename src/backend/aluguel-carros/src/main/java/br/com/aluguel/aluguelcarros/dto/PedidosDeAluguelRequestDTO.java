package br.com.aluguel.aluguelcarros.dto;

import java.time.LocalDateTime;

public record PedidosDeAluguelRequestDTO(
    float preco,
    LocalDateTime dataEmissao,
    Long usuarioId,
    Long agenteId,
    Long automovelId
) {}
