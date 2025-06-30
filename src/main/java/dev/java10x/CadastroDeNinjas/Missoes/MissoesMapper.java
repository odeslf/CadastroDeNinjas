package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel toModel(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setNome(missoesDTO.nome());
        missoesModel.setDificuldade(missoesDTO.dificuldade());
        return missoesModel;
    }

    public MissoesDTO toDto(MissoesModel missoesModel) {
        return new MissoesDTO(missoesModel.getNome(), missoesModel.getDificuldade());
    }
}
