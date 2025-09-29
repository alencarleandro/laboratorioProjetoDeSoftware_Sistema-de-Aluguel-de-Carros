package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.Cliente;
import br.com.aluguel.aluguelcarros.repository.ClienteRepository; // Importe seu repositório de Cliente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository; // Injete o repositório que acessa seus clientes

    /**
     * Este método é chamado pelo Spring Security quando um usuário tenta fazer login.
     * Ele busca o cliente pelo e-mail (que o Spring trata como 'username').
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o cliente no banco de dados pelo e-mail fornecido no formulário de login
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // Cria e retorna um objeto UserDetails que o Spring Security entende,
        // contendo o email, a senha (já criptografada do banco) e uma lista de permissões (vazia por enquanto).
        return new User(cliente.getEmail(), cliente.getSenha(), new ArrayList<>());
    }
}