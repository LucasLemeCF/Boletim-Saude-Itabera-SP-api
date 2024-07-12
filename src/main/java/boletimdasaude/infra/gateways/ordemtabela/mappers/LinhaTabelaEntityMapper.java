package boletimdasaude.infra.gateways.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.LinhaTabela;
import boletimdasaude.infra.persitence.ordemtabela.entities.LinhaTabelaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LinhaTabelaEntityMapper {

    public static LinhaTabelaEntity toEntity(LinhaTabela domain) {
        return new LinhaTabelaEntity(
                domain.id(),
                domain.posicao(),
                domain.tipo(),
                domain.componenteId()
        );
    }

    public static List<LinhaTabelaEntity> toEntityList(List<LinhaTabela> domainList) {
        return domainList.stream()
                .map(LinhaTabelaEntityMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static LinhaTabela toDomain(LinhaTabelaEntity domain) {
        return new LinhaTabela(
                domain.getId(),
                domain.getPosicao(),
                domain.getTipo(),
                domain.getComponenteId()
        );
    }

    public static List<LinhaTabela> toDomainList(List<LinhaTabelaEntity> domainList) {
        return domainList.stream()
                .map(LinhaTabelaEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
