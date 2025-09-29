private PedidosDeAluguelResponseDTO toResponseDTO(PedidosDeAluguel pedido) {
    return new PedidosDeAluguelResponseDTO(
            pedido.getId(),
            pedido.getPreco(),
            pedido.getDataEmissao(),
            pedido.getStatus(),
            pedido.getUsuario() != null ? pedido.getUsuario().getId() : null,
            pedido.getAgente() != null ? pedido.getAgente().getId() : null,
            pedido.getAutomovel() != null ? pedido.getAutomovel().getId() : null
    );
}
