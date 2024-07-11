package boletimdasaude.infra.controller.tabela.mappers;

import boletimdasaude.domain.tabela.ItemTabelaEspecialidade;
import boletimdasaude.infra.controller.tabela.requests.EspecialidadeTabelaRequest;

import java.util.List;
import java.util.stream.Collectors;

public class EspecialidadeTabelaMapper {

    public static ItemTabelaEspecialidade toDomain(EspecialidadeTabelaRequest request) {
        return new ItemTabelaEspecialidade(
                null,
                request.posicao(),
                request.tipo(),
                request.especialidadeId()
        );
    }

    public static List<ItemTabelaEspecialidade> toDomainList(List<EspecialidadeTabelaRequest> domainList) {
        return domainList.stream()
                .map(EspecialidadeTabelaMapper::toDomain)
                .collect(Collectors.toList()
        );
    }

}
