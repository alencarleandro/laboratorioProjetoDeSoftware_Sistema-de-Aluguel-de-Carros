package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Cliente;
import br.com.aluguel.aluguelcarros.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClienteService clienteService;

    // 1️⃣ Lista todos os clientes
    @GetMapping("/gestao-clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "admin/gestao-clientes"; // Página HTML com a tabela
    }

    // 2️⃣ Página de adicionar cliente
    @GetMapping("/clientes/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/form-cliente"; // Página que você mostrou
    }

    // 3️⃣ Página de editar cliente
    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        return "admin/form-cliente"; // Mesma página que adicionar, com dados preenchidos
    }

    // 4️⃣ Salvar cliente (novo ou editar)
    @PostMapping("/clientes/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.salvar(cliente);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar cliente: " + e.getMessage());
        }
        return "redirect:/admin/gestao-clientes";
    }

    // 5️⃣ Excluir cliente
    @GetMapping("/clientes/excluir/{id}")
    public String excluirCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.excluir(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir cliente: " + e.getMessage());
        }
        return "redirect:/admin/gestao-clientes";
    }
}
