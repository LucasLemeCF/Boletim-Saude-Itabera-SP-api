package boletimMedico.infra.gateways.especialidade.mappers;

import boletimMedico.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimMedico.domain.especialidade.ResultadoMensalEspecialidade;
import boletimMedico.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import boletimMedico.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ResultadoDiarioEspecialidadeMapper {

    public static ResultadoDiarioEspecialidadeEntity toEntity(ResultadoDiarioEspecialidade domain) {
        return new ResultadoDiarioEspecialidadeEntity(
                domain.data(),
                domain.atendimentos()
        );
    }

    public static List<ResultadoDiarioEspecialidadeEntity> toEntityList(List<ResultadoDiarioEspecialidade> domainList) {
        return domainList.stream()
                .map(ResultadoDiarioEspecialidadeMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ResultadoDiarioEspecialidade toDomain(ResultadoDiarioEspecialidadeEntity entity) {
        return new ResultadoDiarioEspecialidade(
                entity.getId(),
                entity.getData(),
                entity.getAtendimentos(),
                entity.getMedico()
        );
    }

    public static List<ResultadoDiarioEspecialidade> toDomainList(List<ResultadoDiarioEspecialidadeEntity> entityList) {
        return entityList.stream()
                .map(ResultadoDiarioEspecialidadeMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
