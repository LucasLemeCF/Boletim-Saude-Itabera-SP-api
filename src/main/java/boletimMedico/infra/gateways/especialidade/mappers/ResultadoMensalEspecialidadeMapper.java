package boletimMedico.infra.gateways.especialidade.mappers;

import boletimMedico.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimMedico.domain.especialidade.ResultadoMensalEspecialidade;
import boletimMedico.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import boletimMedico.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ResultadoMensalEspecialidadeMapper {

    public static ResultadoMensalEspecialidadeEntity toEntity(ResultadoMensalEspecialidade domain) {
        return new ResultadoMensalEspecialidadeEntity(
                domain.ano(),
                domain.atendimentos(),
                domain.metaDiaria(),
                domain.metaMensal(),
                ResultadoDiarioEspecialidadeMapper.toEntityList(domain.resultadosDiarios())
        );
    }

    public static List<ResultadoMensalEspecialidadeEntity> toEntityList(List<ResultadoMensalEspecialidade> domainList) {
        return domainList.stream()
                .map(ResultadoMensalEspecialidadeMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ResultadoMensalEspecialidade toDomain(ResultadoMensalEspecialidadeEntity entity) {
        return new ResultadoMensalEspecialidade(
                entity.getId(),
                entity.getAno(),
                entity.getAtendimentos(),
                entity.getMetaDiaria(),
                entity.getMetaMensal(),
                ResultadoDiarioEspecialidadeMapper.toDomainList(entity.getResultadosDiarios())
        );
    }

    public static List<ResultadoMensalEspecialidade> toDomainList(List<ResultadoMensalEspecialidadeEntity> entityList) {
        return entityList.stream()
                .map(ResultadoMensalEspecialidadeMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
