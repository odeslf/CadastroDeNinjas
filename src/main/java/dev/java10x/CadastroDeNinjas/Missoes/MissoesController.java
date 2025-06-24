package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    //GET - Envia requisição para  MOSTRAR as missoes
    @GetMapping("/listar")
    public String listar(){
        return "Lista de missoes";
    }

    //Post - Envia uma requisição para CRIAR missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão criado com sucesso!";
    }

    //Put - Envia uma requisição para ALTERAR missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso!";
    }

    //Delete - Envia uma requisição para DELETAR missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso!";
    }
}
