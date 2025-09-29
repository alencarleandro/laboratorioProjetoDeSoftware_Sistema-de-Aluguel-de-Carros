package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Automovel;
import br.com.aluguel.aluguelcarros.service.AutomovelService;
import br.com.aluguel.aluguelcarros.service.PedidoDeAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos") // Todas as rotas de pedido começarão com /pedidos
public class PedidoController {

    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private PedidoDeAluguelService pedidoService;

    // =======================================================
    // MÉTODOS PARA O CLIENTE
    // =======================================================

    @GetMapping("/novo/{automovelId}")
    public String novoPedidoForm(@PathVariable Long automovelId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Automovel automovel = automovelService.buscarPorId(automovelId);
            model.addAttribute("automovel", automovel);
            return "pedidos/form-aluguel";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Veículo não encontrado.");
            return "redirect:/catalogo-carros";
        }
    }

    @PostMapping("/salvar")
    public String salvarPedido(@RequestParam Long automovelId,
                               @RequestParam String dataInicio,
                               @RequestParam String dataFim,
                               @AuthenticationPrincipal UserDetails userDetails,
                               RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        try {
            pedidoService.criarPedido(userDetails.getUsername(), automovelId, dataInicio, dataFim);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido de aluguel realizado com sucesso! Acompanhe o status em 'Meus Pedidos'.");
            return "redirect:/pedidos/meus-pedidos"; // Redireciona para a página de pedidos do cliente
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao realizar o pedido: " + e.getMessage());
            return "redirect:/pedidos/novo/" + automovelId;
        }
    }

    @GetMapping("/meus-pedidos")
    public String meusPedidos(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String email = userDetails.getUsername();
        model.addAttribute("pedidos", pedidoService.buscarPorEmailCliente(email));
        // AQUI ESTÁ A ÚNICA ALTERAÇÃO
        return "pedidos/meus-pedidos";
    }

    // =======================================================
    // MÉTODOS PARA O ADMINISTRADOR / AGENTE
    // =======================================================

    @GetMapping("/todos") // Nova rota para a lista de admin: /pedidos/todos
    public String listarTodosPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "pedidos-aluguel/lista"; // A view que já criamos
    }

    @GetMapping("/aprovar/{id}")
    public String aprovarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.aprovar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido aprovado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao aprovar pedido: " + e.getMessage());
        }
        return "redirect:/pedidos/todos"; // Redireciona para a nova lista de admin
    }

    @GetMapping("/rejeitar/{id}")
    public String rejeitarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.rejeitar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido rejeitado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao rejeitar pedido: " + e.getMessage());
        }
        return "redirect:/pedidos/todos"; // Redireciona para a nova lista de admin
    }

    @GetMapping("/cancelar-admin/{id}") // Rota diferente para o admin cancelar
    public String cancelarPedidoAdmin(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.cancelar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pedido cancelado pelo administrador.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cancelar pedido: " + e.getMessage());
        }
        return "redirect:/pedidos/todos"; // Redireciona para a nova lista de admin
    }
}