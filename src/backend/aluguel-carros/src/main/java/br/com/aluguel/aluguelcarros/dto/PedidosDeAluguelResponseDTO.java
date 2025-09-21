package br.com.aluguel.aluguelcarros.dto;

import java.time.LocalDateTime;

public record PedidosDeAluguelResponseDTO(
    Long id,
    float preco,
    LocalDateTime dataEmissao,
    Long usuarioId,
    Long agenteId,
    Long automovelId
) {}
