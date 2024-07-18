package boletimdasaude.infra.gateways.procedimentocirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;

import java.util.List;
import java.util.Optional;

public class ProcedimentoCirurgiaoEntityMapper {

    public static ProcedimentoCirurgiaoEntity toEntity(ProcedimentoCirurgiao domain) {
        return new ProcedimentoCirurgiaoEntity(
                domain.nome(),
                ResultadoMensalCirurgiaoMapper.toEntityList(domain.resultadosMensais())
        );
    }

    public static List<ProcedimentoCirurgiaoEntity> toEntityList(List<ProcedimentoCirurgiao> domainList) {
        return domainList.stream()
                .map(ProcedimentoCirurgiaoEntityMapper::toEntity)
                .toList();
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
                .map(ProcedimentoCirurgiaoEntityMapper::toDomain)
                .toList();
    }

    public static Optional<ProcedimentoCirurgiao> toDomainOptional(ProcedimentoCirurgiaoEntity entityOptional) {
        return Optional.of(new ProcedimentoCirurgiao(
                entityOptional.getId(),
                entityOptional.getNome(),
                ResultadoMensalCirurgiaoMapper.toDomainList(entityOptional.getResultadosMensais())
        ));
    }

}