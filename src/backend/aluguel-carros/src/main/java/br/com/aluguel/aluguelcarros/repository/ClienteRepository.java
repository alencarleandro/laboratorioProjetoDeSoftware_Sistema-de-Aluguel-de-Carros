package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca um cliente pelo seu endereço de e-mail.
     * O Spring Data JPA cria automaticamente a implementação deste método
     * com base no nome "findByEmail".
     *
     * @param email O e-mail do cliente a ser buscado.
     * @return um Optional contendo o Cliente se encontrado, ou vazio caso contrário.
     */
    Optional<Cliente> findByEmail(String email);

}