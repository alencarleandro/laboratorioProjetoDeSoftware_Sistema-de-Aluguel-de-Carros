package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.model.Usuario;
import br.com.aluguel.aluguelcarros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService; // Usando UsuarioService

    @GetMapping("/gestao-clientes")
    public String listarClientes(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "admin/gestao-clientes";
    }

    @GetMapping("/clientes/novo")
    public String novoCliente(Model model) {
        model.addAttribute("usuario", new Usuario()); // Usando Usuario
        return "admin/form-cliente";
    }

    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id)); // Usando Usuario
        return "admin/form-cliente";
    }

    @PostMapping("/clientes/salvar")
    public String salvarCliente(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) { // Usando Usuario
        try {
            if (usuario.getId() == null) {
                usuarioService.criar(usuario);
                redirectAttributes.addFlashAttribute("successMessage", "Cliente criado com sucesso!");
            } else {
                usuarioService.atualizar(usuario.getId(), usuario);
                redirectAttributes.addFlashAttribute("successMessage", "Cliente atualizado com sucesso!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar cliente: " + e.getMessage());
        }
        return "redirect:/admin/gestao-clientes";
    }

    @GetMapping("/clientes/excluir/{id}")
    public String excluirCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir cliente: " + e.getMessage());
        }
        return "redirect:/admin/gestao-clientes";
    }
}