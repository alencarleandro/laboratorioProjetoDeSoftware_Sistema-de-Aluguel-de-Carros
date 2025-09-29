package br.com.aluguel.aluguelcarros.model;
import br.com.aluguel.aluguelcarros.model.StatusPedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data; // Anotação poderosa que inclui @Getter, @Setter, @ToString, etc.
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // Gera Getters, Setters, toString, equals e hashCode para todos os campos
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Entity
@Table(name = "pedidos_de_aluguel") // Sugestão: usar snake_case para nomes de tabela
public class PedidosDeAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CAMPOS QUE ESTAVAM FALTANDO
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    // SEUS CAMPOS EXISTENTES
    @Column(name = "preco")
    private float preco;

    @Column(name = "data_emissao")
    private LocalDateTime dataEmissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status = StatusPedido.PENDENTE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;

    // NÃO PRECISA MAIS DE GETTERS E SETTERS MANUAIS. O @Data CUIDA DISSO.

    public boolean valido() {
        // Lógica de validação futura aqui
        return true;
    }
}