package br.com.aluguel.aluguelcarros.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Automovel")
@Table(name = "automovel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricula;
    @Column(unique = true, nullable = false)
    private int ano;
    @Column(unique = true, nullable = false)
    private String marca;
    @Column(unique = true, nullable = false)
    private String modelo;
    @Column(unique = true, nullable = false)
    private String placa;

    public boolean valido(){
        return true;
    }

}
