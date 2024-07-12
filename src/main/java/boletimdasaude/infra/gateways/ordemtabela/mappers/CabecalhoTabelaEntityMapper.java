package boletimdasaude.infra.gateways.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.CabecalhoTabela;
import boletimdasaude.infra.persitence.ordemtabela.entities.CabecalhoTabelaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CabecalhoTabelaEntityMapper {

    public static CabecalhoTabelaEntity toEntity(CabecalhoTabela domain) {
        return new CabecalhoTabelaEntity(
                domain.id(),
                domain.posicao(),
                domain.tipo(),
                TextoCabecalhoTabelaEntityMapper.toEntityList(domain.textos())
        );
    }

    public static List<CabecalhoTabelaEntity> toEntityList(List<CabecalhoTabela> domainList) {
        return domainList.stream()
                .map(CabecalhoTabelaEntityMapper::toEntity)
                .toList();
    }

    public static CabecalhoTabela toDomain(CabecalhoTabelaEntity entity) {
        return new CabecalhoTabela(
                entity.getId(),
                entity.getPosicao(),
                entity.getTipo(),
                TextoCabecalhoTabelaEntityMapper.toDomainList(entity.getTextos())
        );
    }

    public static List<CabecalhoTabela> toDomainList(List<CabecalhoTabelaEntity> domainList) {
        return domainList.stream()
                .map(CabecalhoTabelaEntityMapper::toDomain)
                .toList();
    }

}
