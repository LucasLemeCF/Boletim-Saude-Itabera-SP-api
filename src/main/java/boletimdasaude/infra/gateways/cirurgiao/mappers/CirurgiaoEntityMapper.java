package boletimdasaude.infra.gateways.cirurgiao.mappers;

import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.infra.gateways.procedimentocirurgiao.mappers.ProcedimentoCirurgiaoEntityMapper;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CirurgiaoEntityMapper {

    public CirurgiaoEntity toEntity(Cirurgiao domain) {
        return new CirurgiaoEntity(
                domain.nome(),
                ProcedimentoCirurgiaoEntityMapper.toEntityList(domain.procedimentos())
        );
    }

    public static Cirurgiao toDomain(CirurgiaoEntity entity) {
        return new Cirurgiao(
                entity.getId(),
                entity.getNome(),
                ProcedimentoCirurgiaoEntityMapper.toDomainList(entity.getProcedimentos())
        );
    }

    public List<Cirurgiao> toDomainList(List<CirurgiaoEntity> entityList) {
        return entityList.stream()
                .map(CirurgiaoEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
