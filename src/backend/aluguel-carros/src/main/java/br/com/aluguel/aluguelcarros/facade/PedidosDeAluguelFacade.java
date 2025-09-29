package br.com.aluguel.aluguelcarros.facade;

import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelRequestDTO;
import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.service.PedidosDeAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidosDeAluguelFacade {

    private final PedidosDeAluguelService pedidosDeAluguelService;

    @Autowired
    public PedidosDeAluguelFacade(PedidosDeAluguelService pedidosDeAluguelService) {
        this.pedidosDeAluguelService = pedidosDeAluguelService;
    }

    public PedidosDeAluguel criar(PedidosDeAluguelRequestDTO dto) {
        PedidosDeAluguel pedido = new PedidosDeAluguel();
        pedido.setPreco(dto.getPreco());
        pedido.setDataEmissao(dto.getDataEmissao());
        pedido.setUsuario(dto.getUsuario());
        pedido.setAgente(dto.getAgente());
        pedido.setAutomovel(dto.getAutomovel());
        return pedidosDeAluguelService.criar(pedido);
    }

    public PedidosDeAluguel buscarPorId(Long id) {
        return pedidosDeAluguelService.buscarPorId(id);
    }

    public List<PedidosDeAluguel> listarTodos() {
        return pedidosDeAluguelService.listarTodos();
    }

    public PedidosDeAluguel atualizar(Long id, PedidosDeAluguelRequestDTO dto) {
        PedidosDeAluguel pedido = new PedidosDeAluguel();
        pedido.setPreco(dto.getPreco());
        pedido.setDataEmissao(dto.getDataEmissao());
        pedido.setUsuario(dto.getUsuario());
        pedido.setAgente(dto.getAgente());
        pedido.setAutomovel(dto.getAutomovel());
        return pedidosDeAluguelService.atualizar(id, pedido);
    }

    public void deletar(Long id) {
        pedidosDeAluguelService.deletar(id);
    }

    public PedidosDeAluguel aprovar(Long id) {
        return pedidosDeAluguelService.aprovar(id);
    }

    public PedidosDeAluguel rejeitar(Long id) {
        return pedidosDeAluguelService.rejeitar(id);
    }

    public PedidosDeAluguel cancelar(Long id) {
        return pedidosDeAluguelService.cancelar(id);
    }
}
