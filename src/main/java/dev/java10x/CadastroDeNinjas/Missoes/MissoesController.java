package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //GET - Envia requisição para  MOSTRAR as missoes
    @GetMapping()
    public ResponseEntity<List<MissoesDTO>> listar(){
        List<MissoesDTO> missoes = missoesService.getAllMissoes();
        return ResponseEntity.status(HttpStatus.OK).body(missoes);
    }

    //Post - Envia uma requisição para CRIAR missoes
    @PostMapping()
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesModel){
        MissoesDTO missoesDTO = missoesService.createMissoes(missoesModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(missoesDTO.toString());
    }

    //Put - Envia uma requisição para ALTERAR missoes
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesModel){
        MissoesDTO missao = missoesService.updateMissoes(id, missoesModel);
        if(missao != null){
            return ResponseEntity.status(HttpStatus.OK).body(missao.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Delete - Envia uma requisição para DELETAR missoes
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if (missoesService.getMissoes(id) != null){
            missoesService.deleteMissoes(id);
            return ResponseEntity.ok("Deletado com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
