package boletimdasaude.infra.controller.tabela.mappers;

import boletimdasaude.domain.tabela.OrdemTabela;
import boletimdasaude.infra.controller.tabela.requests.EditarTabelaRequest;

public class EditarTabelaMapper {

    public static OrdemTabela toDomain(EditarTabelaRequest request) {
        return new OrdemTabela(
                EspecialidadeTabelaMapper.toDomainList(request.especialidades()),
                CirurgiaoTabelaMapper.toDomainList(request.cirurgioes())
        );
    }

}
