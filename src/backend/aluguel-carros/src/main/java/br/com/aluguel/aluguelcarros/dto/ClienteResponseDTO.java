package br.com.aluguel.aluguelcarros.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String senha,
        String cpf,
        String rg,
        String email
) {}