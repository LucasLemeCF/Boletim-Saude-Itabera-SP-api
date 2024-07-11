package boletimdasaude.infra.gateways.tabela.mappers;

import boletimdasaude.domain.tabela.OrdemTabela;
import boletimdasaude.infra.persitence.tabela.entities.OrdemTabelaEntity;

public class OrdemTabelaEntityMapper {

    public OrdemTabelaEntity toEntity(OrdemTabela domain) {
        return new OrdemTabelaEntity(
                ItemTabelaEspecialidadeEntityMapper.toEntityList(domain.itensTabelaEspecialidade()),
                ItemTabelaCirurgiaoEntityMapper.toEntityList(domain.itensTabelaCirurgiao())
        );
    }

    public static OrdemTabela toDomain(OrdemTabelaEntity entity) {
        return new OrdemTabela(
                ItemTabelaEspecialidadeEntityMapper.toDomainList(entity.getItemTabelaEspecialidade()),
                ItemTabelaCirurgiaoEntityMapper.toDomainList(entity.getItemTabelaCirurgiao())
        );
    }

}
