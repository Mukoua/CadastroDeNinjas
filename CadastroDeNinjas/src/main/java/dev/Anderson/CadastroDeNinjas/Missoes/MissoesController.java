package dev.Anderson.CadastroDeNinjas.Missoes;


import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/missoes")
public class MissoesController {


    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //Adicionar missoes (create)
    @PostMapping("/criar")
    public MissoesDTO criarMissoes(@RequestBody MissoesDTO missoes) {
        return missoesService.criarMissoes(missoes);
    }

    //mostrar todas as missoes (read)

    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return this.missoesService.listarTodas();
    }

    //Procurar Ninja por Id (read)
    @GetMapping("listar/{id}")
    public MissoesDTO listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMissoesPorID(@PathVariable Long id) {

        missoesService.deletarMissoesPorId(id);
    }


}

