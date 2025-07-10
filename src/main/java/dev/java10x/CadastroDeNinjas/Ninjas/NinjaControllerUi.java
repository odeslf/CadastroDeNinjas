package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninja/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas.html";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninja/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null){
            model.addAttribute("ninja", ninja);
            return "detalhesNinja.html";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrada");
            return "listarNinjas.html";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja.html";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninja/ui/listar";
    }
}
