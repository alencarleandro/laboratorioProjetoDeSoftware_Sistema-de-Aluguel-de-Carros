package br.com.aluguel.aluguelcarros.facade;

import br.com.aluguel.aluguelcarros.dto.ClienteRequestDTO;
import br.com.aluguel.aluguelcarros.dto.ClienteResponseDTO;
import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteFacade {

    @Autowired
    private ClienteService clienteService;

    public ClienteResponseDTO criar(ClienteRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.nome());
        usuario.setEmail(requestDTO.email());
        usuario.setSenha(requestDTO.senha());
        usuario.setCpf(requestDTO.cpf());
        usuario.setRg(requestDTO.rg());

        Usuario usuarioSalvo = clienteService.criar(usuario);

        return new ClienteResponseDTO(
            usuarioSalvo.getId(),
            usuarioSalvo.getNome(), 
            usuarioSalvo.getSenha(),
            usuarioSalvo.getCpf(),
            usuarioSalvo.getRg(), 
            usuarioSalvo.getEmail()
        );
    }
    
    public ClienteResponseDTO buscarPorId(Long id) {
        Usuario usuario = clienteService.buscarPorId(id);
        return new ClienteResponseDTO(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getCpf(), usuario.getRg(), usuario.getEmail());
    }


    public ClienteResponseDTO buscarPorEmail(String email) {
        Usuario usuario = clienteService.buscarPorEmail(email);
        return new ClienteResponseDTO(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getCpf(), usuario.getRg(), usuario.getEmail());
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos().stream()
                .map(u -> new ClienteResponseDTO(u.getId(), u.getNome(), u.getSenha(), u.getCpf(), u.getRg(), u.getEmail()))
                .collect(Collectors.toList());
    }
    
    public void deletar(Long id) {
        clienteService.deletar(id);
    }
}