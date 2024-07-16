package boletimdasaude.application.mappers.cirurgiao;

import boletimdasaude.application.requests.cirurgiao.CirurgiaoRequest;
import boletimdasaude.domain.cirurgiao.Cirurgiao;

import java.util.ArrayList;

public class CirurgiaoMapper {

    public static Cirurgiao toDomain(CirurgiaoRequest request) {
        return new Cirurgiao(
                null,
                request.nome(),
                new ArrayList<>()
        );
    }

}
