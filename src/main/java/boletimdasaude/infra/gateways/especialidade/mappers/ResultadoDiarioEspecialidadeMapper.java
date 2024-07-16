package boletimdasaude.infra.gateways.especialidade.mappers;

import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ResultadoDiarioEspecialidadeMapper {

    public static ResultadoDiarioEspecialidadeEntity toEntity(ResultadoDiarioEspecialidade domain) {
        return new ResultadoDiarioEspecialidadeEntity(
                domain.data(),
                domain.atendimentos(),
                domain.medico()
        );
    }

    public static List<ResultadoDiarioEspecialidadeEntity> toEntityList(List<ResultadoDiarioEspecialidade> domainList) {
        return domainList.stream()
                .map(ResultadoDiarioEspecialidadeMapper::toEntity)
                .toList();
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
