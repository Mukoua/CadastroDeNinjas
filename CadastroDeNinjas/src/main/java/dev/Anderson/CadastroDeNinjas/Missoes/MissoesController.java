package dev.Anderson.CadastroDeNinjas.Missoes;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria uma nova missao", description = "Rota cria uma nova missao e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missao")
    })
    public MissoesDTO criarMissoes(@RequestBody MissoesDTO missoes) {
        return missoesService.criarMissoes(missoes);
    }

    //mostrar todas as missoes (read)

    @GetMapping("/listar")
    @Operation(summary = "Lista todos as missoes", description = "Essa rota cria uma lista com todas as missoes")
    public List<MissoesDTO> listarMissoes() {
        return this.missoesService.listarTodas();
    }

    //Procurar Ninja por Id (read)
    @GetMapping("listar/{id}")
    @Operation(summary = "Lista as missoes por Id", description = "Essa rota lista uma missao por seu Id")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Missao encontrada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Erro na localização da missao")
    })
    public MissoesDTO listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Lista deleta as missoes por Id", description = "Essa rota deleta uma missao por seu Id")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Missao deletada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Não foi possível deletar esta missao")
    })
    public void deletarMissoesPorID(@PathVariable Long id) {

        missoesService.deletarMissoesPorId(id);
    }


}

