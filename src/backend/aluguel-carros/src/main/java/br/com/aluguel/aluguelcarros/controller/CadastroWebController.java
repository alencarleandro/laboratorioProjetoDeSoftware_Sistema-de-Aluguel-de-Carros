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

@Controller 
public class CadastroWebController {

    @Autowired
    private ClienteFacade clienteFacade; 

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("clienteDTO", new ClienteRequestDTO("", "", "", "", ""));
        return "cadastro"; 
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute("clienteDTO") ClienteRequestDTO dto, RedirectAttributes redirectAttributes) {
        try {
            clienteFacade.criar(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Cadastro realizado com sucesso!");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cadastro";
        }
    }
}