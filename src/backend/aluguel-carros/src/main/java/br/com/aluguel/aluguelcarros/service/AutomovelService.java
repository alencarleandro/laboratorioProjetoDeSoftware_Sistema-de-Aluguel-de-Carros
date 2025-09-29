package br.com.aluguel.aluguelcarros.service;

import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutomovelService {

    private final AutomovelRepository automovelRepository;

    @Autowired
    public AutomovelService(AutomovelRepository automovelRepository) {
        this.automovelRepository = automovelRepository;
    }

    @Transactional
    public Automovel criar(Automovel automovel) {
        if (automovelRepository.findByPlaca(automovel.getPlaca()).isPresent()) {
            throw new RuntimeException("Automóvel com esta placa já existe.");
        }
        if (automovelRepository.findByMatricula(automovel.getMatricula()).isPresent()) {
            throw new RuntimeException("Automóvel com esta matrícula já existe.");
        }
        return automovelRepository.save(automovel);
    }

    @Transactional(readOnly = true)
    public Automovel buscarPorId(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    @Transactional
    public Automovel atualizar(Long id, Automovel dadosAtualizados) {
        Automovel automovelExistente = buscarPorId(id);
        automovelExistente.setAno(dadosAtualizados.getAno());
        automovelExistente.setMarca(dadosAtualizados.getMarca());
        automovelExistente.setModelo(dadosAtualizados.getModelo());
        automovelExistente.setPlaca(dadosAtualizados.getPlaca());
        automovelExistente.setMatricula(dadosAtualizados.getMatricula());
        return automovelRepository.save(automovelExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Automovel automovelExistente = buscarPorId(id);
        automovelRepository.delete(automovelExistente);
    }
}
