package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}
