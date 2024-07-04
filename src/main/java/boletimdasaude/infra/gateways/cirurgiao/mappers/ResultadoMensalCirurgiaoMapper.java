package boletimdasaude.infra.gateways.cirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ResultadoMensalCirurgiaoMapper {

    public static ResultadoMensalCirurgiaoEntity toEntity(ResultadoMensalCirurgiao domain) {
        return new ResultadoMensalCirurgiaoEntity(
                domain.mes(),
                domain.ano(),
                domain.atendimentos(),
                ResultadoDiarioCirurgiaoMapper.toEntityList(domain.resultadosDiarios())
        );
    }

    public static List<ResultadoMensalCirurgiaoEntity> toEntityList(List<ResultadoMensalCirurgiao> domainList) {
        return domainList.stream()
                .map(ResultadoMensalCirurgiaoMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ResultadoMensalCirurgiao toDomain(ResultadoMensalCirurgiaoEntity entity) {
        return new ResultadoMensalCirurgiao(
                entity.getId(),
                entity.getMes(),
                entity.getAno(),
                entity.getAtendimentos(),
                ResultadoDiarioCirurgiaoMapper.toDomainList(entity.getResultadosDiarios())
        );
    }

    public static List<ResultadoMensalCirurgiao> toDomainList(List<ResultadoMensalCirurgiaoEntity> entityList) {
        return entityList.stream()
                .map(ResultadoMensalCirurgiaoMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
