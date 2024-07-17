package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ProcedimentoCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;

import java.util.Optional;

public class ProcedimentoCirurgiaoRepository implements ITabelaCirurgiaoRepository {

    private final IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa;

    public ProcedimentoCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa) {
        this.procedimentoCirurgiaoRepositoryJpa = procedimentoCirurgiaoRepositoryJpa;
    }

    @Override
    public Optional<ProcedimentoCirurgiao> buscarProcedimentoCirurgiao(Long procedimentoId) {
        return ProcedimentoCirurgiaoMapper.toDomainOptional(procedimentoCirurgiaoRepositoryJpa.findById(procedimentoId).get());
    }

}