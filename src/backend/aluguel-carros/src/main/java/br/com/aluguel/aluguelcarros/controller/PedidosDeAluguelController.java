@GetMapping("/aprovar/{id}")
public String aprovarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        pedidosDeAluguelFacade.aprovar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pedido aprovado com sucesso!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Erro ao aprovar pedido: " + e.getMessage());
    }
    return "redirect:/pedidos-aluguel";
}

@GetMapping("/rejeitar/{id}")
public String rejeitarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        pedidosDeAluguelFacade.rejeitar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pedido rejeitado.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Erro ao rejeitar pedido: " + e.getMessage());
    }
    return "redirect:/pedidos-aluguel";
}

@GetMapping("/cancelar/{id}")
public String cancelarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        pedidosDeAluguelFacade.cancelar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pedido cancelado.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cancelar pedido: " + e.getMessage());
    }
    return "redirect:/pedidos-aluguel";
}
