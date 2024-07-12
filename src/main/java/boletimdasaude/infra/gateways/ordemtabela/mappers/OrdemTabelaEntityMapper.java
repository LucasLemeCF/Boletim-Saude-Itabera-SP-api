package boletimdasaude.infra.gateways.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;

import java.util.List;

public class OrdemTabelaEntityMapper {

    public OrdemTabelaEntity toEntity(OrdemTabela domain) {
        return new OrdemTabelaEntity(
                LinhaTabelaEntityMapper.toEntityList(domain.linhasTabela()),
                CabecalhoTabelaEntityMapper.toEntityList(domain.cabecalhosTabela())
        );
    }

    public static OrdemTabela toDomain(OrdemTabelaEntity entity) {
        return new OrdemTabela(
                entity.getId(),
                LinhaTabelaEntityMapper.toDomainList(entity.getLinhasTabelaEntity()),
                CabecalhoTabelaEntityMapper.toDomainList(entity.getCabecalhosTabelaEntity())
        );
    }

    public static List<OrdemTabela> toDomainList(List<OrdemTabelaEntity> domainList) {
        return domainList.stream()
                .map(OrdemTabelaEntityMapper::toDomain)
                .toList();
    }

}
