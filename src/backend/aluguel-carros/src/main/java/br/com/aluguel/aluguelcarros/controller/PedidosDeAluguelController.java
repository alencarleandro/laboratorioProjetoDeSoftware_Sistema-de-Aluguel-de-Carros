package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.dto.PedidosDeAluguelRequestDTO;
import br.com.aluguel.aluguelcarros.facade.PedidosDeAluguelFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

/**
 * Controller para gestão de Pedidos de Aluguel
 * Coração do sistema - gerencia todo o fluxo de pedidos de aluguel
 */
@Controller
@RequestMapping("/pedidos-aluguel")
public class PedidosDeAluguelController {

    private final PedidosDeAluguelFacade pedidosDeAluguelFacade;

    @Autowired
    public PedidosDeAluguelController(PedidosDeAluguelFacade pedidosDeAluguelFacade) {
        this.pedidosDeAluguelFacade = pedidosDeAluguelFacade;
    }

    /**
     * Lista todos os pedidos (visão administrativa)
     * Acesso: Administradores e Agentes
     */
    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("pedidos", pedidosDeAluguelFacade.listarTodos());
        return "pedidos/lista";
    }

    /**
     * Exibe formulário para novo pedido de aluguel
     * Acesso: Clientes logados
     */
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("pedido", new PedidosDeAluguelRequestDTO(0f, LocalDateTime.now(), null, null, null));
        model.addAttribute("pedidoId", null);
        return "pedidos/form";
    }

    /**
     * Processa criação de novo pedido
     * Acesso: Clientes logados
     * Status inicial: PENDENTE
     */
    @PostMapping
    public String salvar(@ModelAttribute("pedido") PedidosDeAluguelRequestDTO dto, RedirectAttributes redirectAttributes) {
        try {
            pedidosDeAluguelFacade.criar(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido criado com sucesso! Aguarde a análise do agente financeiro.");
            return "redirect:/meus-pedidos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao criar pedido: " + e.getMessage());
            return "redirect:/pedidos-aluguel/novo";
        }
    }

    /**
     * Exibe formulário para edição de pedido
     * Acesso: Cliente proprietário ou Administradores
     */
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        try {
            var pedido = pedidosDeAluguelFacade.buscarPorId(id);
            model.addAttribute("pedidoId", id);
            model.addAttribute("pedido", pedido);
            return "pedidos/form";
        } catch (Exception e) {
            return "redirect:/pedidos-aluguel?error=pedido_nao_encontrado";
        }
    }

    /**
     * Processa atualização de pedido existente
     * Acesso: Cliente proprietário ou Administradores
     * Nota: Pedidos aprovados podem ter restrições de edição
     */
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute("pedido") PedidosDeAluguelRequestDTO dto, RedirectAttributes redirectAttributes) {
        try {
            pedidosDeAluguelFacade.atualizar(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido atualizado com sucesso!");
            return "redirect:/pedidos-aluguel";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar pedido: " + e.getMessage());
            return "redirect:/pedidos-aluguel/editar/" + id;
        }
    }

    /**
     * Remove pedido do sistema
     * Acesso: Cliente proprietário (apenas pendentes) ou Administradores
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pedidosDeAluguelFacade.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao remover pedido: " + e.getMessage());
        }
        return "redirect:/pedidos-aluguel";
    }

    /**
     * Aprova pedido de aluguel
     * Acesso: Agentes Financeiros e Administradores
     */
    @GetMapping("/aprovar/{id}")
    public String aprovarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // TODO: Implementar lógica de aprovação no service
            redirectAttributes.addFlashAttribute("successMessage", "Pedido aprovado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao aprovar pedido: " + e.getMessage());
        }
        return "redirect:/pedidos-aluguel";
    }

    /**
     * Rejeita pedido de aluguel
     * Acesso: Agentes Financeiros e Administradores
     */
    @GetMapping("/rejeitar/{id}")
    public String rejeitarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // TODO: Implementar lógica de rejeição no service
            redirectAttributes.addFlashAttribute("successMessage", "Pedido rejeitado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao rejeitar pedido: " + e.getMessage());
        }
        return "redirect:/pedidos-aluguel";
    }
}
