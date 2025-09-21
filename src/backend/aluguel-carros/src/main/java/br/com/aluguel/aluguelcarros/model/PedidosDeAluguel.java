package br.com.aluguel.aluguelcarros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "PedidosDeAluguel")
@Table(name = "pedidosDeAluguel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidosDeAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco")
    private float preco;
    @Column(name = "dataEmissao")
    private LocalDateTime dataEmissao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;


    public boolean valido(){
        return true;
    }


}
