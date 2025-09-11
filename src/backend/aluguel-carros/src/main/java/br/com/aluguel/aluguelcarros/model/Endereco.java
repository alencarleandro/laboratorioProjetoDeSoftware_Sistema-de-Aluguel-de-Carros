package br.com.aluguel.aluguelcarros.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String cep;
    private String numero;
    private String complemento;
}