package dev.Anderson.CadastroDeNinjas.Ninjas;


import dev.Anderson.CadastroDeNinjas.Missoes.MissoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;
    private final MissoesService missoesService;


    public NinjaControllerUi(NinjaService ninjaService, MissoesService missoesService) {
        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";  //tem que retornar o nome da pagina que renderiza
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorID(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorID(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasporId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesninja";

        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasporId(id);
        if (ninja == null) {
            return "redirect:/ninjas/ui/listar";
        }

        model.addAttribute("ninja", ninja);
       /* model.addAttribute("todasMissoes", missoesService.listarMissoes());*/ // ← deve retornar List<MissoesModel>// ← MissoesModel
        model.addAttribute("todasMissoes", missoesService.listarTodas());

        // Seleciona automaticamente a missão atual do ninja
        model.addAttribute("missaoSelecionada",
                ninja.getMissoesId() != null ? missoesService.buscarPorId(ninja.getMissoesId()) : null);

        return "editarNinja"; // seu template
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute @Valid NinjaDTO ninjaDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        System.out.println("=== SALVANDO NINJA ===");
        System.out.println("ID recebido: " + ninjaDTO.getId());

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Preencha todos os campos corretamente.");
            if (ninjaDTO.getId() != null) {
                return "redirect:/ninjas/ui/editar/" + ninjaDTO.getId();
            } else {
                return "redirect:/ninjas/ui/adicionar";
            }
        }

        try {
            NinjaDTO salvo;

            if (ninjaDTO.getId() == null) {
                // É CADASTRO NOVO
                salvo = ninjaService.criarNinja(ninjaDTO);
                redirectAttributes.addFlashAttribute("mensagem", "Ninja criado com sucesso!");
            } else {
                // É EDIÇÃO
                salvo = ninjaService.atualizarNinja(ninjaDTO.getId(), ninjaDTO);
                redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
            }

            if (salvo == null) {
                redirectAttributes.addFlashAttribute("erro", "Erro ao salvar ninja.");
                return "redirect:/ninjas/ui/listar";
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("erro", "Erro: " + e.getMessage());
            return "redirect:/ninjas/ui/listar";
        }

        return "redirect:/ninjas/ui/listar";
    }

}

