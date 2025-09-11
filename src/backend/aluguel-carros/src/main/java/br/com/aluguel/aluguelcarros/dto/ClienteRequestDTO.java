package br.com.aluguel.aluguelcarros.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,

        @NotBlank
        String rg,
        
        @NotBlank
        String cpf,

        @NotBlank @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha
) {}