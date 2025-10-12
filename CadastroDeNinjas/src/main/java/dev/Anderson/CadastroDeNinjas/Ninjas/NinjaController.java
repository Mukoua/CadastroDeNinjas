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
        public String criarNinja() {
            return "Ninja criado";
        }


        // Mostrar todos os Ninjas (read)
        @GetMapping("/listar")
        public List<NinjaModel> listarNinjas() {
            return ninjaService.listarNinjas();
        }


        //Procurar Ninja por ID (read)
        @GetMapping("/listarID")
        public String mostrarTodosOsNinjasPorID() {
            return "Mostrar Ninja por ID";
        }

        // Alterar dados do Ninja (Update)
        @PutMapping("/alterarID")
        public String alterarNinjasPorID() {
            return "Alterar Ninja por ID";
        }


        //Deletar Ninja (Delete)
        @DeleteMapping("/deletarID")
        public String deletarNinjasPorID() {
            return "Deletar Ninja";
        }



}