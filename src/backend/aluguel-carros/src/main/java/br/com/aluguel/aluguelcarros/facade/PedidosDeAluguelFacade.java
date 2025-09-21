package br.com.aluguel.aluguelcarros.facade;

import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelRequestDTO;
import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelResponseDTO;
import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.model.Agente;
import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.service.PedidosDeAluguelService;
import br.com.aluguel.aluguelcarros.repository.UsuarioRepository;
import br.com.aluguel.aluguelcarros.repository.AgenteRepository;
import br.com.aluguel.aluguelcarros.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidosDeAluguelFacade {

    @Autowired
    private PedidosDeAluguelService pedidosDeAluguelService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AgenteRepository agenteRepository;
    @Autowired
    private AutomovelRepository automovelRepository;

    public PedidosDeAluguelResponseDTO criar(PedidosDeAluguelRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Agente agente = agenteRepository.findById(requestDTO.agenteId())
                .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
        Automovel automovel = automovelRepository.findById(requestDTO.automovelId())
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

        PedidosDeAluguel pedido = new PedidosDeAluguel();
        pedido.setPreco(requestDTO.preco());
        pedido.setDataEmissao(requestDTO.dataEmissao());
        pedido.setUsuario(usuario);
        pedido.setAgente(agente);
        pedido.setAutomovel(automovel);

        PedidosDeAluguel salvo = pedidosDeAluguelService.criar(pedido);
        return new PedidosDeAluguelResponseDTO(
            salvo.getId(),
            salvo.getPreco(),
            salvo.getDataEmissao(),
            salvo.getUsuario().getId(),
            salvo.getAgente().getId(),
            salvo.getAutomovel().getId()
        );
    }

    public PedidosDeAluguelResponseDTO buscarPorId(Long id) {
        PedidosDeAluguel pedido = pedidosDeAluguelService.buscarPorId(id);
        return new PedidosDeAluguelResponseDTO(
            pedido.getId(),
            pedido.getPreco(),
            pedido.getDataEmissao(),
            pedido.getUsuario().getId(),
            pedido.getAgente().getId(),
            pedido.getAutomovel().getId()
        );
    }

    public List<PedidosDeAluguelResponseDTO> listarTodos() {
        return pedidosDeAluguelService.listarTodos().stream()
            .map(pedido -> new PedidosDeAluguelResponseDTO(
                pedido.getId(),
                pedido.getPreco(),
                pedido.getDataEmissao(),
                pedido.getUsuario().getId(),
                pedido.getAgente().getId(),
                pedido.getAutomovel().getId()
            ))
            .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        pedidosDeAluguelService.deletar(id);
    }

    public PedidosDeAluguelResponseDTO atualizar(Long id, PedidosDeAluguelRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Agente agente = agenteRepository.findById(requestDTO.agenteId())
                .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
        Automovel automovel = automovelRepository.findById(requestDTO.automovelId())
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

        PedidosDeAluguel dadosAtualizados = new PedidosDeAluguel();
        dadosAtualizados.setPreco(requestDTO.preco());
        dadosAtualizados.setDataEmissao(requestDTO.dataEmissao());
        dadosAtualizados.setUsuario(usuario);
        dadosAtualizados.setAgente(agente);
        dadosAtualizados.setAutomovel(automovel);

        PedidosDeAluguel atualizado = pedidosDeAluguelService.atualizar(id, dadosAtualizados);
        return new PedidosDeAluguelResponseDTO(
            atualizado.getId(),
            atualizado.getPreco(),
            atualizado.getDataEmissao(),
            atualizado.getUsuario().getId(),
            atualizado.getAgente().getId(),
            atualizado.getAutomovel().getId()
        );
    }
}
