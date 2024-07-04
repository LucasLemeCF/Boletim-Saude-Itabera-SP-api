package boletimdasaude.infra.gateways.cirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProcedimentoCirurgiaoMapper {

    public static ProcedimentoCirurgiaoEntity toEntity(ProcedimentoCirurgiao domain) {
        return new ProcedimentoCirurgiaoEntity(
                domain.nome(),
                ResultadoMensalCirurgiaoMapper.toEntityList(domain.resultadosMensais())
        );
    }

    public static List<ProcedimentoCirurgiaoEntity> toEntityList(List<ProcedimentoCirurgiao> domainList) {
        return domainList.stream()
                .map(ProcedimentoCirurgiaoMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ProcedimentoCirurgiao toDomain(ProcedimentoCirurgiaoEntity entity) {
        return new ProcedimentoCirurgiao(
                entity.getId(),
                entity.getNome(),
                ResultadoMensalCirurgiaoMapper.toDomainList(entity.getResultadosMensais())
        );
    }

    public static List<ProcedimentoCirurgiao> toDomainList(List<ProcedimentoCirurgiaoEntity> entityList) {
        return entityList.stream()
                .map(ProcedimentoCirurgiaoMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}