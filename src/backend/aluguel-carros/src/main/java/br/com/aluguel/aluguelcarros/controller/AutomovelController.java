package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller para gestão da Frota de Veículos
 * Responsável por gerenciar os automóveis disponíveis para aluguel
 */
@Controller
@RequestMapping("/automoveis")
public class AutomovelController {

    private final AutomovelService automovelService;

    @Autowired
    public AutomovelController(AutomovelService automovelService) {
        this.automovelService = automovelService;
    }

    /**
     * Lista todos os veículos da frota
     * Acesso: Administradores e Clientes (para catálogo)
     */
    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("automoveis", automovelService.listarTodos());
        return "automoveis/lista";
    }

    /**
     * Exibe formulário para cadastro de novo veículo
     * Acesso: Administradores
     */
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("automovel", new Automovel());
        return "automoveis/form";
    }

    /**
     * Processa o cadastro de novo veículo
     * Acesso: Administradores
     */
    @PostMapping
    public String salvar(@ModelAttribute Automovel automovel, RedirectAttributes redirectAttributes) {
        try {
            automovelService.criar(automovel);
            redirectAttributes.addFlashAttribute("successMessage", "Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar veículo: " + e.getMessage());
        }
        return "redirect:/automoveis";
    }

    /**
     * Exibe formulário para edição de veículo existente
     * Acesso: Administradores
     */
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        try {
            Automovel automovel = automovelService.buscarPorId(id);
            model.addAttribute("automovel", automovel);
            return "automoveis/form";
        } catch (Exception e) {
            return "redirect:/automoveis?error=veiculo_nao_encontrado";
        }
    }

    /**
     * Processa a atualização de veículo existente
     * Acesso: Administradores
     */
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Automovel automovel, RedirectAttributes redirectAttributes) {
        try {
            automovelService.atualizar(id, automovel);
            redirectAttributes.addFlashAttribute("successMessage", "Veículo atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar veículo: " + e.getMessage());
        }
        return "redirect:/automoveis";
    }

    /**
     * Remove veículo da frota
     * Acesso: Administradores
     * Nota: Verificar se não há pedidos ativos antes de excluir
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            automovelService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Veículo removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao remover veículo: " + e.getMessage());
        }
        return "redirect:/automoveis";
    }
}
