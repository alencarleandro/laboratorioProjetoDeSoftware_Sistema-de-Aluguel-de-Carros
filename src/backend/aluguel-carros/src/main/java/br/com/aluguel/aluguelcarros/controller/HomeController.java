package br.com.aluguel.aluguelcarros.controller;

import br.com.aluguel.aluguelcarros.service.AutomovelService; // 1. Importe o serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 2. Importe o Model
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller para páginas principais do sistema
 * Gerencia a navegação principal e páginas públicas
 */
@Controller
public class HomeController {

    // 3. Injete o AutomovelService para poder buscar os carros
    @Autowired
    private AutomovelService automovelService;

    /**
     * Página inicial do sistema
     * Acesso: Público
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Página de login
     * Acesso: Público (usuários não logados)
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Catálogo de carros para clientes
     * Acesso: Público e Clientes logados
     */
    @GetMapping("/catalogo-carros")
    public String catalogoCarros(Model model) { // 4. Adicione o Model como parâmetro
        // 5. Busque os automóveis do banco e adicione-os ao Model
        model.addAttribute("automoveis", automovelService.listarTodos());
        return "catalogo-carros";
    }

}