package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nesta rota";
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaModel){
       NinjaDTO ninjaDTO = ninjaService.criarNinja(ninjaModel);
       return ResponseEntity.status(HttpStatus.CREATED).body("Ninja Criado com Sucesso! "
       + ninjaDTO.getNome() + " (ID: " + ninjaDTO.getId() + ")");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.status(HttpStatus.OK).body(ninjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null){
            return ResponseEntity.status(HttpStatus.OK).body(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();        }

    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja Deletado com Sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}