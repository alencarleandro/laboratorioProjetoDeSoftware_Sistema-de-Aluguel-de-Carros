package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelRequestDTO;
import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelResponseDTO;
import br.com.aluguel.aluguelcarros.facade.PedidosDeAluguelFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-aluguel")
public class PedidosDeAluguelController {

    @Autowired
    private PedidosDeAluguelFacade pedidosDeAluguelFacade;

    @PostMapping
    public ResponseEntity<PedidosDeAluguelResponseDTO> criarPedido(@RequestBody @Valid PedidosDeAluguelRequestDTO requestDTO) {
        PedidosDeAluguelResponseDTO responseDTO = pedidosDeAluguelFacade.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosDeAluguelResponseDTO> buscarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidosDeAluguelFacade.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidosDeAluguelResponseDTO>> listarTodosPedidos() {
        return ResponseEntity.ok(pedidosDeAluguelFacade.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosDeAluguelResponseDTO> atualizarPedido(@PathVariable Long id, @RequestBody @Valid PedidosDeAluguelRequestDTO requestDTO) {
        PedidosDeAluguelResponseDTO responseDTO = pedidosDeAluguelFacade.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidosDeAluguelFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
