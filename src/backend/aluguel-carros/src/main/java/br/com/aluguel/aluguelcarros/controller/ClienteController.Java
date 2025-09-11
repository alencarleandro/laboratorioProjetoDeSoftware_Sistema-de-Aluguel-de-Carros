package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.dto.ClienteRequestDTO;
import br.com.aluguel.aluguelcarros.dto.ClienteResponseDTO;
import br.com.aluguel.aluguelcarros.facade.ClienteFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteFacade clienteFacade;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO requestDTO) {
        ClienteResponseDTO responseDTO = clienteFacade.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteFacade.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodosClientes() {
        return ResponseEntity.ok(clienteFacade.listarTodos());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}