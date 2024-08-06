package boletimdasaude.infra.gateways.ordemtabela.mappers;

import boletimdasaude.domain.ordemtabela.DataOrdemTabela;
import boletimdasaude.infra.persitence.ordemtabela.entities.DataOrdemTabelaEntity;

import java.util.List;

public class DataOrdemTabelaEntityMapper {

    public static DataOrdemTabelaEntity toEntity(DataOrdemTabela domain) {
        return new DataOrdemTabelaEntity(
                domain.data()
        );
    }

    public static List<DataOrdemTabelaEntity> toEntityList(List<DataOrdemTabela> domainList) {
        return domainList.stream()
                .map(DataOrdemTabelaEntityMapper::toEntity)
                .toList();
    }

    public static DataOrdemTabela toDomain(DataOrdemTabelaEntity entity) {
        return new DataOrdemTabela(
                null,
                entity.getData()
        );
    }

    public static List<DataOrdemTabela> toDomainList(List<DataOrdemTabelaEntity> entityList) {
        return entityList.stream()
                .map(DataOrdemTabelaEntityMapper::toDomain)
                .toList();
    }

}
