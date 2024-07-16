package boletimdasaude.infra.gateways.especialidade.mappers;

import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.List;

public class ResultadoMensalEspecialidadeMapper {

    public static ResultadoMensalEspecialidadeEntity toEntity(ResultadoMensalEspecialidade domain) {
        return new ResultadoMensalEspecialidadeEntity(
                domain.mes(),
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
                .toList();
    }

    public static ResultadoMensalEspecialidade toDomain(ResultadoMensalEspecialidadeEntity entity) {
        return new ResultadoMensalEspecialidade(
                entity.getId(),
                entity.getMes(),
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
                .toList();
    }

}
