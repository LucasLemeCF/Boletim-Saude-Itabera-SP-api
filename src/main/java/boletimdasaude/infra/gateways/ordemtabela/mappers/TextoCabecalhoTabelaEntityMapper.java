package boletimdasaude.infra.gateways.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;
import boletimdasaude.infra.persitence.ordemtabela.entities.TextoCabecalhoTabelaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TextoCabecalhoTabelaEntityMapper {

    public static TextoCabecalhoTabelaEntity toEntity(TextoCabecalhoTabela domain) {
        return new TextoCabecalhoTabelaEntity(
                domain.id(),
                domain.texto()
        );
    }

    public static List<TextoCabecalhoTabelaEntity> toEntityList(List<TextoCabecalhoTabela> domainList) {
        return domainList.stream()
                .map(TextoCabecalhoTabelaEntityMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static TextoCabecalhoTabela toDomain(TextoCabecalhoTabelaEntity entity) {
        return new TextoCabecalhoTabela(
                entity.getId(),
                entity.getTexto()
        );
    }

    public static List<TextoCabecalhoTabela> toDomainList(List<TextoCabecalhoTabelaEntity> domainList) {
        return domainList.stream()
                .map(TextoCabecalhoTabelaEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
