package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
    Optional<Agente> findByNome(String nome);
}
