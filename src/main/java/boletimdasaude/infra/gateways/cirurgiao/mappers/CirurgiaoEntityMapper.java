package boletimdasaude.infra.gateways.cirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CirurgiaoEntityMapper {

    public CirurgiaoEntity toEntity(Cirurgiao domain) {
        return new CirurgiaoEntity(
                domain.nome(),
                ProcedimentoCirurgiaoMapper.toEntityList(domain.procedimentos())
        );
    }

    public static Cirurgiao toDomain(CirurgiaoEntity entity) {
        return new Cirurgiao(
                entity.getId(),
                entity.getNome(),
                ProcedimentoCirurgiaoMapper.toDomainList(entity.getProcedimentos())
        );
    }

    public List<Cirurgiao> toDomainList(List<CirurgiaoEntity> entityList) {
        return entityList.stream()
                .map(CirurgiaoEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
