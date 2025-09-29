package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    Optional<Automovel> findByPlaca(String placa);
    Optional<Automovel> findByMatricula(String matricula);
}
