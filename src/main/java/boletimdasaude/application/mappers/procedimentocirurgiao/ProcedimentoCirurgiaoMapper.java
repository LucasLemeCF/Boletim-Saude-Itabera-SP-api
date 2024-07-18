package boletimdasaude.application.mappers.procedimentocirurgiao;

import boletimdasaude.application.requests.procedimentocirurgiao.ProcedimentoCirurgiaoRequest;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;

import java.util.ArrayList;

public class ProcedimentoCirurgiaoMapper {

    public static ProcedimentoCirurgiao toDomain(ProcedimentoCirurgiaoRequest request) {
        return new ProcedimentoCirurgiao(
                null,
                request.nome(),
                new ArrayList<>()
        );
    }

}
