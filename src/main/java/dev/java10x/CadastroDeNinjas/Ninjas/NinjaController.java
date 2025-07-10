package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota tem uma mensagem")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nesta rota";
    }


    @PostMapping("/criar")
    @Operation(summary = "Cria um novo Ninja", description = "Cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar novo ninja")
    })
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
    @Operation(summary = "Listar Ninja por Id", description = "Lista ninja de acordo com o Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null){
            return ResponseEntity.status(HttpStatus.OK).body(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();        }

    }


    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera um ninja por id", description = "Altera um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    })
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