package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> getAllMissoes() {
        List<MissoesModel> missoesModels = missoesRepository.findAll();
        return missoesModels.stream().map(missoesMapper::toDto).collect(Collectors.toList());
    }

    public MissoesDTO getMissoes(Long id) {
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.map(missoesMapper::toDto).orElse(null);
    }

    public MissoesDTO createMissoes(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = new MissoesMapper().toModel(missoesDTO);
        missoesRepository.save(missoesModel);
        return missoesMapper.toDto(missoesModel);
    }

    public void deleteMissoes(Long id) {
        missoesRepository.deleteById(id);
    }

    public MissoesDTO updateMissoes(Long id, MissoesDTO missoesDTO) {
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        if (missoesModel.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.toModel(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.toDto(missaoSalva);
        } else {
            return null;
        }
    }
}
