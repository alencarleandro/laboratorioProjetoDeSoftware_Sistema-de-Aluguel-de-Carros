package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.model.StatusPedido;
import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.repository.AutomovelRepository;
import br.com.aluguel.aluguelcarros.repository.PedidosDeAluguelRepository;
import br.com.aluguel.aluguelcarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidosDeAluguelService {

    // DEPENDÊNCIAS DO SERVIÇO
    private final PedidosDeAluguelRepository pedidosDeAluguelRepository;
    private final UsuarioRepository usuarioRepository;
    private final AutomovelRepository automovelRepository;

    // CONSTRUTOR ATUALIZADO PARA INJETAR TODAS AS DEPENDÊNCIAS
    @Autowired
    public PedidosDeAluguelService(PedidosDeAluguelRepository pedidosDeAluguelRepository,
                                   UsuarioRepository usuarioRepository,
                                   AutomovelRepository automovelRepository) {
        this.pedidosDeAluguelRepository = pedidosDeAluguelRepository;
        this.usuarioRepository = usuarioRepository;
        this.automovelRepository = automovelRepository;
    }

    // =======================================================
    // NOVO MÉTODO PARA CRIAR O PEDIDO (orquestração)
    // =======================================================
    @Transactional
    public PedidosDeAluguel criarPedido(String emailCliente, Long automovelId, String dataInicio, String dataFim) {
        Usuario cliente = usuarioRepository.findByEmail(emailCliente)
                .orElseThrow(() -> new RuntimeException("Cliente com e-mail " + emailCliente + " não encontrado"));
        Automovel automovel = automovelRepository.findById(automovelId)
                .orElseThrow(() -> new RuntimeException("Automóvel com ID " + automovelId + " não encontrado"));

        PedidosDeAluguel novoPedido = new PedidosDeAluguel();
        novoPedido.setUsuario(cliente);
        novoPedido.setAutomovel(automovel);
        novoPedido.setDataInicio(LocalDate.parse(dataInicio));
        novoPedido.setDataFim(LocalDate.parse(dataFim));
        novoPedido.setDataEmissao(LocalDate.now()); // Adiciona a data de hoje como data de emissão
        novoPedido.setStatus(StatusPedido.PENDENTE); // Ou o status inicial que você preferir

        return pedidosDeAluguelRepository.save(novoPedido);
    }

    // =======================================================
    // NOVO MÉTODO PARA BUSCAR PEDIDOS DE UM CLIENTE ESPECÍFICO
    // =======================================================
    @Transactional(readOnly = true)
    public List<PedidosDeAluguel> buscarPorEmailCliente(String email) {
        return pedidosDeAluguelRepository.findByUsuarioEmail(email);
    }


    @Transactional
    public PedidosDeAluguel criar(PedidosDeAluguel pedido) {
        pedido.setStatus(StatusPedido.PENDENTE);
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

    @Transactional
    public PedidosDeAluguel aprovar(Long id) {
        PedidosDeAluguel pedido = buscarPorId(id);
        pedido.setStatus(StatusPedido.APROVADO);
        return pedidosDeAluguelRepository.save(pedido);
    }

    @Transactional
    public PedidosDeAluguel rejeitar(Long id) {
        PedidosDeAluguel pedido = buscarPorId(id);
        pedido.setStatus(StatusPedido.REJEITADO);
        return pedidosDeAluguelRepository.save(pedido);
    }

    @Transactional
    public PedidosDeAluguel cancelar(Long id) {
        PedidosDeAluguel pedido = buscarPorId(id);
        pedido.setStatus(StatusPedido.CANCELADO);
        return pedidosDeAluguelRepository.save(pedido);
    }
}