package br.com.aluguel.aluguelcarros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "Usuario") // Define que esta classe é uma entidade do banco
@Table(name = "usuarios") // Mapeia para a tabela "usuarios"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rg;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;
    
    @ElementCollection 
    private List<String> profissao;
    
    @ElementCollection
    private List<String> empresas;
    
    @ElementCollection
    private List<BigDecimal> rendimentos;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private int scoreCredito;

    @Embedded 
    private Endereco endereco;


}