package boletimdasaude.infra.gateways.tabela.mappers;

import boletimdasaude.domain.tabela.ItemTabelaEspecialidade;
import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaEspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemTabelaEspecialidadeEntityMapper {

    public static ItemTabelaEspecialidadeEntity toEntity(ItemTabelaEspecialidade domain) {
        return new ItemTabelaEspecialidadeEntity(
                domain.id(),
                domain.posicao(),
                domain.tipo(),
                domain.especialidadeId()
        );
    }

    public static List<ItemTabelaEspecialidadeEntity> toEntityList(List<ItemTabelaEspecialidade> domainList) {
        return domainList.stream()
                .map(ItemTabelaEspecialidadeEntityMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ItemTabelaEspecialidade toDomain(ItemTabelaEspecialidadeEntity domain) {
        return new ItemTabelaEspecialidade(
                domain.getId(),
                domain.getPosicao(),
                domain.getTipo(),
                domain.getEspecialidadeId()
        );
    }

    public static List<ItemTabelaEspecialidade> toDomainList(List<ItemTabelaEspecialidadeEntity> domainList) {
        return domainList.stream()
                .map(ItemTabelaEspecialidadeEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
