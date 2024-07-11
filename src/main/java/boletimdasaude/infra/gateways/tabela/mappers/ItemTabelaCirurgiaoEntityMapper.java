package boletimdasaude.infra.gateways.tabela.mappers;

import boletimdasaude.domain.tabela.ItemTabelaCirurgiao;
import boletimdasaude.domain.tabela.ItemTabelaEspecialidade;
import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaCirurgiaoEntity;
import boletimdasaude.infra.persitence.tabela.entities.ItemTabelaEspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemTabelaCirurgiaoEntityMapper {

    public static ItemTabelaCirurgiaoEntity toEntity(ItemTabelaCirurgiao domain) {
        return new ItemTabelaCirurgiaoEntity(
                domain.id(),
                domain.posicao(),
                domain.tipo(),
                domain.procedimentoId()
        );
    }

    public static List<ItemTabelaCirurgiaoEntity> toEntityList(List<ItemTabelaCirurgiao> domainList) {
        return domainList.stream()
                .map(ItemTabelaCirurgiaoEntityMapper::toEntity)
                .collect(Collectors.toList()
        );
    }

    public static ItemTabelaCirurgiao toDomain(ItemTabelaCirurgiaoEntity entity) {
        return new ItemTabelaCirurgiao(
                entity.getId(),
                entity.getPosicao(),
                entity.getTipo(),
                entity.getProcedimentoId()
        );
    }

    public static List<ItemTabelaCirurgiao> toDomainList(List<ItemTabelaCirurgiaoEntity> domainList) {
        return domainList.stream()
                .map(ItemTabelaCirurgiaoEntityMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
