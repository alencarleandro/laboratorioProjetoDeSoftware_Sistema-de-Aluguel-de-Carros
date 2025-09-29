package br.com.aluguel.aluguelcarros.dto;

import java.time.LocalDate;

public class PedidosDeAluguelRequestDTO {

    private Long automovelId;
    private Long usuarioId;
    private Float preco;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor vazio (necessário para o framework)
    public PedidosDeAluguelRequestDTO() {
    }

    // --- Getters e Setters ---
    // A existência deste método resolve o seu erro
    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Long getAutomovelId() {
        return automovelId;
    }

    public void setAutomovelId(Long automovelId) {
        this.automovelId = automovelId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}