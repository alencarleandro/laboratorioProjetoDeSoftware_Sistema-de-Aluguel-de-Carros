package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
}
