package br.com.aluguel.aluguelcarros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Agente")
@Table(name = "agente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    public boolean valido(){
        return true;
    }
}
