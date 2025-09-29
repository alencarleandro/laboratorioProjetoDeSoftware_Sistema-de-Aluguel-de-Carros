package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Agente;
import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.service.AgenteService;
import br.com.aluguel.aluguelcarros.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller para operações administrativas
 * Centraliza funcionalidades de administração do sistema
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AgenteService agenteService;

    @Autowired
    private AutomovelService automovelService;

    /**
     * Dashboard administrativo principal
     * Acesso: Administradores
     */
    @GetMapping
    public String dashboard(Model model) {
        // TODO: Implementar estatísticas do sistema
        model.addAttribute("totalAgentes", agenteService.listarTodos().size());
        model.addAttribute("totalAutomoveis", automovelService.listarTodos().size());
        return "admin/dashboard";
    }

    /**
     * Lista todos os agentes para seleção em formulários
     * Usado principalmente para popular dropdowns
     */
    @GetMapping("/agentes")
    @ResponseBody
    public Object listarAgentesParaSelect() {
        return agenteService.listarTodos();
    }

    /**
     * Lista todos os automóveis para seleção em formulários
     * Usado principalmente para popular dropdowns
     */
    @GetMapping("/automoveis")
    @ResponseBody
    public Object listarAutomoveisParaSelect() {
        return automovelService.listarTodos();
    }

    /**
     * Atualiza status de disponibilidade de um automóvel
     * Acesso: Administradores
     */
    @PostMapping("/automoveis/{id}/status")
    public String atualizarStatusAutomovel(@PathVariable Long id, 
                                         @RequestParam String status,
                                         RedirectAttributes redirectAttributes) {
        try {
            // TODO: Implementar lógica de atualização de status
            redirectAttributes.addFlashAttribute("successMessage", "Status do veículo atualizado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar status: " + e.getMessage());
        }
        return "redirect:/automoveis";
    }

    /**
     * Relatórios administrativos
     * Acesso: Administradores
     */
    @GetMapping("/relatorios")
    public String relatorios(Model model) {
        // TODO: Implementar relatórios
        return "admin/relatorios";
    }

    /**
     * Configurações do sistema
     * Acesso: Administradores
     */
    @GetMapping("/configuracoes")
    public String configuracoes() {
        return "admin/configuracoes";
    }
}
