package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }


    public List<NinjaDTO> listarNinjas(){
       List<NinjaModel> ninjas = ninjaRepository.findAll();
       return ninjas.stream().map(ninjaMapper::map).collect(Collectors.toList());
    }


    public NinjaDTO listarId(Long id){
        Optional<NinjaModel> ninja = ninjaRepository.findById(id);
        return ninja.map(ninjaMapper::map).orElse(null);
    }


    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = new NinjaMapper().map(ninjaDTO);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);

    }

    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }


    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if(ninjaExistente.isPresent()){
            NinjaModel ninjaAtualizada = ninjaMapper.map(ninjaDTO);
            ninjaAtualizada.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizada);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }

}
