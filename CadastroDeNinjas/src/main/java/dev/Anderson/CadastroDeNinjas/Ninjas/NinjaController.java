package dev.Anderson.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!!";

    }
        //Adicionar Ninja (create)
        @PostMapping("/criar")
        public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
            return ninjaService.criarNinja(ninja);
        }


        // Mostrar todos os Ninjas (read)
        @GetMapping("/listar")
        public List<NinjaModel> listarNinjas() {
            return ninjaService.listarNinjas();
        }


        //Procurar Ninja por ID (read)
        @GetMapping("/listar/{id}")
        public NinjaModel listarNinjasPorID(@PathVariable Long id) {
            return ninjaService.listarNinjasporId(id);
        }

        // Alterar dados do Ninja (Update)
        @PutMapping("/alterar/{id}")
        public NinjaModel alterarNinjasPorID(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado) {
            return ninjaService.atualizarNinja(id, ninjaAtualizado);
        }


        //Deletar Ninja (Delete)
        @DeleteMapping("/deletar/{id}")
        public void deletarNinjaPorID(@PathVariable Long id) {
            ninjaService.deletarNinjaPorId(id);
        }



}           //continua na aula