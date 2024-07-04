package boletimdasaude.infra.controller.cirurgiao;

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
