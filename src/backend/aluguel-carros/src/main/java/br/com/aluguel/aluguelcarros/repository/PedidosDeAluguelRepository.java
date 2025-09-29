package br.com.aluguel.aluguelcarros.repository;

import br.com.aluguel.aluguelcarros.model.PedidosDeAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // <-- ADICIONE ESTA LINHA

@Repository
public interface PedidosDeAluguelRepository extends JpaRepository<PedidosDeAluguel, Long> {

    List<PedidosDeAluguel> findByUsuarioEmail(String email);

}