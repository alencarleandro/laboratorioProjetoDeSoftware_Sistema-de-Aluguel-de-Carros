package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.facade.ClienteFacade;
import br.com.aluguel.aluguelcarros.dto.ClienteRequestDTO; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller para cadastro público de clientes
 * Permite que novos usuários se cadastrem no sistema
 */
@Controller 
public class CadastroWebController {

    @Autowired
    private ClienteFacade clienteFacade; 

    /**
     * Exibe formulário de cadastro para novos clientes
     * Acesso: Público (usuários não logados)
     */
    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("clienteDTO", new ClienteRequestDTO("", "", "", "", ""));
        return "cadastro"; 
    }

    /**
     * Processa o cadastro de novo cliente
     * Acesso: Público (usuários não logados)
     * Validação: CPF único, e-mail único
     */
    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute("clienteDTO") ClienteRequestDTO dto, RedirectAttributes redirectAttributes) {
        try {
            clienteFacade.criar(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Cadastro realizado com sucesso! Faça login para acessar o sistema.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro no cadastro: " + e.getMessage());
            return "redirect:/cadastro";
        }
    }
}