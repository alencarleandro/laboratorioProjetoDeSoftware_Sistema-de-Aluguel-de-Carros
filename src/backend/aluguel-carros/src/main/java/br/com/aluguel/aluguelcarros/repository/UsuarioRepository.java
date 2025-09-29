package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Usuario; // Corrigido para Usuario
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Corrigido para UsuarioRepository e JpaRepository<Usuario, Long>
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     * O Spring Data JPA cria automaticamente a implementação deste método
     * com base no nome "findByEmail".
     *
     * @param email O e-mail do usuário a ser buscado.
     * @return um Optional contendo o Usuario se encontrado, ou vazio caso contrário.
     */
    // Corrigido para retornar Optional<Usuario>
    Optional<Usuario> findByEmail(String email);

}