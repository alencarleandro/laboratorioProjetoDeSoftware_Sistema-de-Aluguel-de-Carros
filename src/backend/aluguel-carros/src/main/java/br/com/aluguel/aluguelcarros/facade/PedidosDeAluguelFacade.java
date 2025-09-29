package br.com.aluguel.aluguelcarros.facade;

import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelRequestDTO;
import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.repository.AutomovelRepository;
import br.com.aluguel.aluguelcarros.repository.UsuarioRepository;
import br.com.aluguel.aluguelcarros.service.PedidosDeAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PedidosDeAluguelFacade {

    private final PedidosDeAluguelService pedidosDeAluguelService;
    private final UsuarioRepository usuarioRepository;
    private final AutomovelRepository automovelRepository;

    @Autowired
    public PedidosDeAluguelFacade(PedidosDeAluguelService pedidosDeAluguelService, UsuarioRepository usuarioRepository, AutomovelRepository automovelRepository) {
        this.pedidosDeAluguelService = pedidosDeAluguelService;
        this.usuarioRepository = usuarioRepository;
        this.automovelRepository = automovelRepository;
    }

    @Transactional
    public PedidosDeAluguel criar(PedidosDeAluguelRequestDTO dto) {
        // 1. Busca as entidades completas usando os IDs do DTO
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Automovel automovel = automovelRepository.findById(dto.getAutomovelId())
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

        // 2. Monta a entidade PedidosDeAluguel
        PedidosDeAluguel pedido = new PedidosDeAluguel();
        pedido.setPreco(dto.getPreco());
        pedido.setDataInicio(dto.getDataInicio());
        pedido.setDataFim(dto.getDataFim());
        pedido.setUsuario(usuario); // Associa a entidade completa
        pedido.setAutomovel(automovel); // Associa a entidade completa

        // 3. Passa a entidade completa para o serviço salvar
        return pedidosDeAluguelService.criar(pedido);
    }

    public PedidosDeAluguel buscarPorId(Long id) {
        return pedidosDeAluguelService.buscarPorId(id);
    }

    public List<PedidosDeAluguel> listarTodos() {
        return pedidosDeAluguelService.listarTodos();
    }

    @Transactional
    public PedidosDeAluguel atualizar(Long id, PedidosDeAluguelRequestDTO dto) {
        // No método de atualizar do serviço, já temos a lógica que usa o DTO
        return pedidosDeAluguelService.atualizar(id, dto);
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