package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.Agente;
import br.com.aluguel.aluguelcarros.repository.AgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgenteService {

    private final AgenteRepository agenteRepository;

    @Autowired
    public AgenteService(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    @Transactional
    public Agente criar(Agente agente) {
        if (agenteRepository.findByNome(agente.getNome()).isPresent()) {
            throw new RuntimeException("Agente com esse nome já existe.");
        }
        return agenteRepository.save(agente);
    }

    @Transactional(readOnly = true)
    public Agente buscarPorId(Long id) {
        return agenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Agente> listarTodos() {
        return agenteRepository.findAll();
    }

    @Transactional
    public Agente atualizar(Long id, Agente dadosAtualizados) {
        Agente agenteExistente = buscarPorId(id);
        agenteExistente.setNome(dadosAtualizados.getNome());
        return agenteRepository.save(agenteExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Agente agenteExistente = buscarPorId(id);
        agenteRepository.delete(agenteExistente);
    }
}
