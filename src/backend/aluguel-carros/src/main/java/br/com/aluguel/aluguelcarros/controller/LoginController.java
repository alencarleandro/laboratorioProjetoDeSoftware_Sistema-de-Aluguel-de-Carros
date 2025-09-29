package br.com.aluguel.aluguelcarros.controller.web;

import br.com.aluguel.aluguelcarros.dto.ClienteResponseDTO;
import br.com.aluguel.aluguelcarros.facade.ClienteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ClienteFacade clienteFacade;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login"; // thymeleaf template: templates/login.html
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginRequest loginRequest, Model model) {
        try {
            // Exemplo simplificado: login por email
            ClienteResponseDTO cliente = clienteFacade.buscarPorEmail(loginRequest.getEmail());

            if (cliente != null && cliente.senha().equals(loginRequest.getSenha())) {
                model.addAttribute("cliente", cliente);
                return "redirect:/pedidos"; // após login, vai para pedidos
            } else {
                model.addAttribute("erro", "Email ou senha inválidos");
                return "login";
            }

        } catch (Exception e) {
            model.addAttribute("erro", "Erro no login");
            return "login";
        }
    }

    // DTO interno para capturar email/senha do form
    public static class LoginRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }
}
