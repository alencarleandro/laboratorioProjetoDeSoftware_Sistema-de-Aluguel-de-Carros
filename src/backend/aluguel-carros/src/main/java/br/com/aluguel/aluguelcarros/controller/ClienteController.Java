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

/**
 * Controller REST para gestão de clientes via API
 * Usado principalmente para integrações e operações administrativas
 * Nota: O cadastro público é feito via CadastroWebController
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteFacade clienteFacade;

    /**
     * Cria novo cliente via API (uso administrativo)
     * Acesso: Administradores
     */
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO requestDTO) {
        try {
            ClienteResponseDTO responseDTO = clienteFacade.criar(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Busca cliente por ID
     * Acesso: Administradores e o próprio cliente
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(@PathVariable Long id) {
        try {
            ClienteResponseDTO cliente = clienteFacade.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lista todos os clientes (uso administrativo)
     * Acesso: Administradores
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodosClientes() {
        List<ClienteResponseDTO> clientes = clienteFacade.listarTodos();
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * Remove cliente do sistema
     * Acesso: Administradores
     * Nota: Verificar se não há pedidos ativos antes de excluir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            clienteFacade.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}