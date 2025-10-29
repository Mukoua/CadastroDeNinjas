package dev.Anderson.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // Listar todos os meus ninjas

    public List<NinjaModel> listarNinjas () {
        return ninjaRepository.findAll();
    }

    //Listar os ninjas por ID

    public NinjaModel listarNinjasporId(long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    // Criar um novo ninja

    public NinjaModel criarNinja (NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

    //Deletar um ninja -  tem que ser um metodo VOID

    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }

    // Atualizar ninja

    public NinjaModel atualizarNinja (Long id, NinjaModel ninjaAtualizado){
        if(ninjaRepository.existsById(id)) {
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }
}
// continua na aula 32