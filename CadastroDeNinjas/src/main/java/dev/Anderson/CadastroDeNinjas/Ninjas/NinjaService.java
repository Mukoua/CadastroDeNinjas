package dev.Anderson.CadastroDeNinjas.Ninjas;

import dev.Anderson.CadastroDeNinjas.Missoes.MissoesModel;
import dev.Anderson.CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NinjaService {


    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;
    private final MissoesService missoesService;



    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper, MissoesService missoesService) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
        this.missoesService = missoesService;
    }
// Listar todos os meus ninjas

     public List<NinjaDTO> listarNinjas () {
          List<NinjaModel> ninjas = ninjaRepository.findAll();
          return ninjas.stream()
            .map(ninjaMapper::map)
            .collect(Collectors.toList());
     }


     //Listar os ninjas por ID

         public NinjaDTO listarNinjasporId(long id){
              Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
               return ninjaPorId.map(ninjaMapper::map).orElse(null);
     }

     // Criar um novo ninja

        public NinjaDTO criarNinja (NinjaDTO ninjaDTO) {
           NinjaModel ninja = ninjaMapper.map(ninjaDTO);
           ninja = ninjaRepository.save(ninja);
           return ninjaMapper.map(ninja);
        }

     //Deletar um ninja -  tem que ser um metodo VOID
     public void deletarNinjaPorId(Long id){
             ninjaRepository.deleteById(id);
         }

     // Atualizar ninja

    @Transactional
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> optional = ninjaRepository.findById(id);
        if (optional.isEmpty()) return null;

        NinjaModel ninja = optional.get();

        ninja.setNome(ninjaDTO.getNome());
        ninja.setIdade(ninjaDTO.getIdade());
        ninja.setEmail(ninjaDTO.getEmail());
        ninja.setRank(ninjaDTO.getRank());
        ninja.setImgUrl(ninjaDTO.getImgUrl());

        // TRATAMENTO PERFEITO DA MISSÃO
        if (ninjaDTO.getMissoesId() != null) {
            MissoesModel missao = missoesService.buscarPorId(ninjaDTO.getMissoesId());
            ninja.setMissoes(missao);
        } else {
            ninja.setMissoes(null); // remove missão
        }

        NinjaModel salvo = ninjaRepository.save(ninja);
        return ninjaMapper.map(salvo);
    }
     }