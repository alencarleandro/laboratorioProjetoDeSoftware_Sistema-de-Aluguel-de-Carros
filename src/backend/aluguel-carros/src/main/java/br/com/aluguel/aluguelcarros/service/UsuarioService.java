package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.repository.PedidosDeAluguelRepository;
import br.com.aluguel.aluguelcarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PedidosDeAluguelRepository pedidosDeAluguelRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          PedidosDeAluguelRepository pedidosDeAluguelRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.pedidosDeAluguelRepository = pedidosDeAluguelRepository;
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        usuario.setSenha(usuario.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o email: " + email));
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

        for(PedidosDeAluguel p : pedidosDeAluguelRepository.findByUsuarioEmail(usuarioExistente.getEmail()))
            pedidosDeAluguelRepository.delete(p);

        usuarioRepository.delete(usuarioExistente);
    }
}