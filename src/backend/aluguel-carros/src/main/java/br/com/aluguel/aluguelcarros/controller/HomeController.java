package br.com.aluguel.aluguelcarros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller para páginas principais do sistema
 * Gerencia a navegação principal e páginas públicas
 */
@Controller
public class HomeController {

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
    public String catalogoCarros() {
        return "catalogo-carros";
    }

    /**
     * Página dos pedidos do cliente logado
     * Acesso: Clientes logados
     */
    @GetMapping("/meus-pedidos")
    public String meusPedidos() {
        return "meus-pedidos";
    }
}