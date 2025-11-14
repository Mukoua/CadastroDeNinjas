package dev.Anderson.CadastroDeNinjas.Ninjas;

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
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!!";

    }
        //Adicionar Ninja (create)
        @PostMapping("/criar")
        public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
           NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " +  novoNinja.getId());
        }


        // Mostrar todos os Ninjas (read)
        @GetMapping("/listar")
        public ResponseEntity<List<NinjaDTO>> listarNinjas() {
            List<NinjaDTO> ninjas = ninjaService.listarNinjas();
            return ResponseEntity.ok(ninjas);
        }


        //Procurar Ninja por ID (read)
        @GetMapping("/listar/{id}")
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
        public ResponseEntity<?> alterarNinjasPorID(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
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