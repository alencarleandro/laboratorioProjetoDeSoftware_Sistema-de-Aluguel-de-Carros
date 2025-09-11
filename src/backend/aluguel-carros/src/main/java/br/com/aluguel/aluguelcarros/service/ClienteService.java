package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario usuarioExistente = buscarPorId(id);
        usuarioExistente.setNome(dadosAtualizados.getNome());
        usuarioExistente.setCpf(dadosAtualizados.getCpf());
        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuarioExistente = buscarPorId(id);
        usuarioRepository.delete(usuarioExistente);
    }
}