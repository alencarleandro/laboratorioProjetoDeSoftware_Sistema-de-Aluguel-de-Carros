package br.com.aluguel.aluguelcarros.facade;

import br.com.aluguel.aluguelcarros.dto.ClienteRequestDTO;
import br.com.aluguel.aluguelcarros.dto.ClienteResponseDTO;
import br.com.aluguel.aluguelcarros.model.Usuario; // Seu modelo se chama Usuario, o que está ótimo
import br.com.aluguel.aluguelcarros.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 1. Importe o PasswordEncoder
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteFacade {

    @Autowired
    private ClienteService clienteService;

    // 2. Injete o PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClienteResponseDTO criar(ClienteRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.nome());
        usuario.setEmail(requestDTO.email());
        usuario.setCpf(requestDTO.cpf());
        usuario.setRg(requestDTO.rg());

        // 3. AQUI ESTÁ A MUDANÇA CRÍTICA: Criptografe a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(requestDTO.senha());
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = clienteService.criar(usuario);

        return new ClienteResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                // Sugestão: Não retorne a senha, mesmo criptografada. Veja nota abaixo.
                null, // ou remova este campo do DTO de resposta
                usuarioSalvo.getCpf(),
                usuarioSalvo.getRg(),
                usuarioSalvo.getEmail()
        );
    }

    // ... o restante dos seus métodos está perfeito ...
    public ClienteResponseDTO buscarPorId(Long id) {
        Usuario usuario = clienteService.buscarPorId(id);
        return new ClienteResponseDTO(usuario.getId(), usuario.getNome(), null, usuario.getCpf(), usuario.getRg(), usuario.getEmail());
    }

    public ClienteResponseDTO buscarPorEmail(String email) {
        Usuario usuario = clienteService.buscarPorEmail(email);
        return new ClienteResponseDTO(usuario.getId(), usuario.getNome(), null, usuario.getCpf(), usuario.getRg(), usuario.getEmail());
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos().stream()
                .map(u -> new ClienteResponseDTO(u.getId(), u.getNome(), null, u.getCpf(), u.getRg(), u.getEmail()))
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        clienteService.deletar(id);
    }
}