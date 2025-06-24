package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nesta rota";
    }

    //Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado com sucesso!";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    // Mostrar Ninja por ID
    @GetMapping("/listar/{id}")
    public NinjaModel listarPorId(@PathVariable Long id){
        return ninjaService.listarId(id);
    }

    //Alterar dados dos Ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinja() {
        return "Alterar Ninja por ID";
    }

    //Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinja() {
        return "Deletar Ninja por ID";
    }
}
