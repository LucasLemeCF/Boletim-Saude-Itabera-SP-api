package boletimdasaude.application.mappers.tabela;

import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;

import java.util.Date;

public class TabelaMapper {

    private static ConverterData converterData;

//    public static ResultadoMensalEspecialidade toDomain(LinhaTabelaRequest request, Date data) {
//        return new ResultadoMensalEspecialidade(
//                null,
//                ConverterData.toMes(data),
//                ConverterData.toAno(data),
//                0,
//                0,
//                0,
//                LinhaTabelaMapper.toDomainList(request.linhas(), request.data())
//        );
//    }

}
