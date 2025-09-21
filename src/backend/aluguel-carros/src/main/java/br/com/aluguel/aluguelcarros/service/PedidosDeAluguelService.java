package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.repository.PedidosDeAluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidosDeAluguelService {

    private final PedidosDeAluguelRepository pedidosDeAluguelRepository;

    @Autowired
    public PedidosDeAluguelService(PedidosDeAluguelRepository pedidosDeAluguelRepository) {
        this.pedidosDeAluguelRepository = pedidosDeAluguelRepository;
    }

    @Transactional
    public PedidosDeAluguel criar(PedidosDeAluguel pedido) {
        return pedidosDeAluguelRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public PedidosDeAluguel buscarPorId(Long id) {
        return pedidosDeAluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido de aluguel não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<PedidosDeAluguel> listarTodos() {
        return pedidosDeAluguelRepository.findAll();
    }

    @Transactional
    public PedidosDeAluguel atualizar(Long id, PedidosDeAluguel dadosAtualizados) {
        PedidosDeAluguel pedidoExistente = buscarPorId(id);
        pedidoExistente.setPreco(dadosAtualizados.getPreco());
        pedidoExistente.setDataEmissao(dadosAtualizados.getDataEmissao());
        pedidoExistente.setUsuario(dadosAtualizados.getUsuario());
        pedidoExistente.setAgente(dadosAtualizados.getAgente());
        pedidoExistente.setAutomovel(dadosAtualizados.getAutomovel());
        return pedidosDeAluguelRepository.save(pedidoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        PedidosDeAluguel pedidoExistente = buscarPorId(id);
        pedidosDeAluguelRepository.delete(pedidoExistente);
    }
}
