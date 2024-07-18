package boletimdasaude.infra.gateways.cirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoDiarioCirurgiaoEntity;

import java.util.List;

public class ResultadoDiarioCirurgiaoMapper {

    public static ResultadoDiarioCirurgiaoEntity toEntity(ResultadoDiarioCirurgiao domain) {
        return new ResultadoDiarioCirurgiaoEntity(
                domain.data(),
                domain.atendimentos()
        );
    }

    public static List<ResultadoDiarioCirurgiaoEntity> toEntityList(List<ResultadoDiarioCirurgiao> domainList) {
        return domainList.stream()
                .map(ResultadoDiarioCirurgiaoMapper::toEntity)
                .toList();
    }

    public static ResultadoDiarioCirurgiao toDomain(ResultadoDiarioCirurgiaoEntity entity) {
        return new ResultadoDiarioCirurgiao(
                entity.getId(),
                entity.getDia(),
                entity.getAtendimentos()
        );
    }

    public static List<ResultadoDiarioCirurgiao> toDomainList(List<ResultadoDiarioCirurgiaoEntity> entityList) {
        return entityList.stream()
                .map(ResultadoDiarioCirurgiaoMapper::toDomain)
                .toList();
    }

}
