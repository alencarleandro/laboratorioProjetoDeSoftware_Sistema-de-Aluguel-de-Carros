package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Agente;
import br.com.aluguel.aluguelcarros.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller para gestão de Agentes Financeiros (Bancos e Empresas)
 * Responsável por gerenciar os agentes que financiam os aluguéis de veículos
 */
@Controller
@RequestMapping("/agentes")
public class AgenteController {

    private final AgenteService agenteService;

    @Autowired
    public AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    /**
     * Lista todos os agentes financeiros cadastrados
     * Acesso: Administradores
     */
    @GetMapping
    public String listarAgentes(Model model) {
        model.addAttribute("agentes", agenteService.listarTodos());
        return "agentes/lista";
    }

    /**
     * Exibe formulário para cadastro de novo agente
     * Acesso: Administradores
     */
    @GetMapping("/novo")
    public String novoAgenteForm(Model model) {
        model.addAttribute("agente", new Agente());
        return "agentes/form";
    }

    /**
     * Processa o cadastro de novo agente
     * Acesso: Administradores
     */
    @PostMapping
    public String salvarAgente(@ModelAttribute Agente agente, RedirectAttributes redirectAttributes) {
        try {
            agenteService.criar(agente);
            redirectAttributes.addFlashAttribute("successMessage", "Agente cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar agente: " + e.getMessage());
        }
        return "redirect:/agentes";
    }

    /**
     * Exibe formulário para edição de agente existente
     * Acesso: Administradores
     */
    @GetMapping("/editar/{id}")
    public String editarAgenteForm(@PathVariable Long id, Model model) {
        try {
            Agente agente = agenteService.buscarPorId(id);
            model.addAttribute("agente", agente);
            return "agentes/form";
        } catch (Exception e) {
            return "redirect:/agentes?error=agente_nao_encontrado";
        }
    }

    /**
     * Processa a atualização de agente existente
     * Acesso: Administradores
     */
    @PostMapping("/atualizar/{id}")
    public String atualizarAgente(@PathVariable Long id, @ModelAttribute Agente agente, RedirectAttributes redirectAttributes) {
        try {
            agenteService.atualizar(id, agente);
            redirectAttributes.addFlashAttribute("successMessage", "Agente atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar agente: " + e.getMessage());
        }
        return "redirect:/agentes";
    }

    /**
     * Remove agente do sistema
     * Acesso: Administradores
     * Nota: Verificar se não há pedidos vinculados antes de excluir
     */
    @GetMapping("/deletar/{id}")
    public String deletarAgente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            agenteService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Agente removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao remover agente: " + e.getMessage());
        }
        return "redirect:/agentes";
    }
}
