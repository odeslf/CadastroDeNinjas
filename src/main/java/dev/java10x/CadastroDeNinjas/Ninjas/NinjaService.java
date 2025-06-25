package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //Listar todos os ninjas
    public List<NinjaModel> listarNinjas(){
       return ninjaRepository.findAll();
    }

    //Listar ninjas por ID
    public NinjaModel listarId(Long id){
        Optional<NinjaModel> ninja = ninjaRepository.findById(id);
        return ninja.orElse(null);
    }

    //Criar ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = new NinjaMapper().map(ninjaDTO);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);

    }

    //Deletar Ninja -  VOID, não retorna nada, apenas deleta
    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }

    //Update Ninja - fazer alteração de dados já existentes
    public NinjaModel atualizarNinja(Long id, NinjaModel ninja){
        if(ninjaRepository.existsById(id)){
            ninja.setId(id);
            return ninjaRepository.save(ninja);
        }
        return null;
    }

}
