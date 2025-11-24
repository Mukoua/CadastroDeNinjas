package dev.Anderson.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas-vindas", description = "Essa rota dá uma mensagem de boas vindas para quem acessa ela")

    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!!";

    }
        //Adicionar Ninja (create)
        @PostMapping("/criar")
        @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
        @ApiResponses(value = {
                @ApiResponse( responseCode = "201", description = "Ninja criado com sucesso"),
                @ApiResponse( responseCode = "400", description = "Erro na criação do ninja")
        })
        public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
           NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " +  novoNinja.getId());
        }


        // Mostrar todos os Ninjas (read)
        @GetMapping("/listar")
        @Operation(summary = "Lista todos os ninjas", description = "Essa rota cria uma lista com todos os ninjas")
        public ResponseEntity<List<NinjaDTO>> listarNinjas() {
            List<NinjaDTO> ninjas = ninjaService.listarNinjas();
            return ResponseEntity.ok(ninjas);
        }


        //Procurar Ninja por ID (read)
        @GetMapping("/listar/{id}")
        @Operation(summary = "Lista os ninjas por Id", description = "Essa rota lista um ninja por seu Id")
        @ApiResponses(value = {
                @ApiResponse( responseCode = "200", description = "Ninja encontrado com sucesso"),
                @ApiResponse( responseCode = "404", description = "Erro na localização do ninja")
        })
        public ResponseEntity<?> listarNinjasPorID(@PathVariable Long id) {
            NinjaDTO ninja = ninjaService.listarNinjasporId(id);
            if (ninja != null) {
                return ResponseEntity.ok(ninja);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ninja com o ID: " + id + " não existe nos nossos registros");
            }
        }


        // Alterar dados do Ninja (Update)
        @PutMapping("/alterar/{id}")
        @Operation(summary = "Altera o ninja pelo Id", description = "Essa rota altera o ninja pelo seu Id")
        @ApiResponses(value = {
                @ApiResponse( responseCode = "201", description = "Ninja alterado com sucesso"),
                @ApiResponse( responseCode = "400", description = "Erro na alteração do ninja")
        })
        public ResponseEntity<?> alterarNinjasPorID(
                @Parameter(description = "Usuario manda o id no caminho da requisicao")
                @PathVariable Long id,
                @Parameter( description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisicao" )
                @RequestBody NinjaDTO ninjaAtualizado) {
           NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
           if (ninja != null ){
               return ResponseEntity.ok(ninja);
           } else {
               return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body("O ninja com o ID " + id + " não foi encontrado");
           }
        }

        //Deletar Ninja (Delete)
        @DeleteMapping("/deletar/{id}")
        @Operation(summary = "Deleta o ninja pelo Id", description = "Essa rota deleta o ninja pelo seu Id")
        @ApiResponses(value = {
                @ApiResponse( responseCode = "201", description = "Ninja deletado com sucesso"),
                @ApiResponse( responseCode = "400", description = "Não foi possivel deletar o ninja")
        })
        public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id) {
            if (ninjaService.listarNinjasporId(id) != null) {
                ninjaService.deletarNinjaPorId(id);
                return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o ID " + id + " não encontrado!");
            }

    }






        //continua na aula 34

}