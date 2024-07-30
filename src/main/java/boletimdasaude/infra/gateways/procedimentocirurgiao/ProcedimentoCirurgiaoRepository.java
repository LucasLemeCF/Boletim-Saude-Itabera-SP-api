package boletimdasaude.infra.gateways.procedimentocirurgiao;

import boletimdasaude.application.gateways.procedimentocirurgiao.IProcedimentoCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.CirurgiaoRepository;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.gateways.procedimentocirurgiao.mappers.ProcedimentoCirurgiaoEntityMapper;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;

import java.util.List;
import java.util.Optional;

public class ProcedimentoCirurgiaoRepository implements IProcedimentoCirurgiaoRepository, ITabelaCirurgiaoRepository {

    private final IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa;
    private final CirurgiaoRepository cirurgiaoRepository;

    public ProcedimentoCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                           CirurgiaoRepository cirurgiaoRepository) {
        this.procedimentoCirurgiaoRepositoryJpa = procedimentoCirurgiaoRepositoryJpa;
        this.cirurgiaoRepository = cirurgiaoRepository;
    }

    @Override
    public ProcedimentoCirurgiao criarProcedimentoCirurgiao(ProcedimentoCirurgiao procedmientoCirurgiao, Long cirurgiaoId) {
        CirurgiaoEntity cirurgiaoEntity = cirurgiaoRepository.buscarCirurgiaoPorId(cirurgiaoId);

        ProcedimentoCirurgiaoEntity procedimentoCirurgiaoEntity = ProcedimentoCirurgiaoEntityMapper.toEntity(procedmientoCirurgiao);
        procedimentoCirurgiaoEntity.setCirurgiao(cirurgiaoEntity);

        return ProcedimentoCirurgiaoEntityMapper.toDomain(procedimentoCirurgiaoRepositoryJpa.save(procedimentoCirurgiaoEntity));
    }

    @Override
    public List<ProcedimentoCirurgiao> buscarTodosProcedimentosCirurgioes() {
        return ProcedimentoCirurgiaoEntityMapper.toDomainList(procedimentoCirurgiaoRepositoryJpa.findAll());
    }

    @Override
    public Optional<ProcedimentoCirurgiao> buscarProcedimentoCirurgiao(Long procedimentoId) {
        return ProcedimentoCirurgiaoEntityMapper.toDomainOptional(procedimentoCirurgiaoRepositoryJpa.findById(procedimentoId).get());
    }

    @Override
    public Optional<ProcedimentoCirurgiaoEntity> buscarProcedimentoCirurgiaoEntity(Long procedimentoId) {
        return procedimentoCirurgiaoRepositoryJpa.findById(procedimentoId);
    }

    @Override
    public ProcedimentoCirurgiao editarProcedimentoCirurgiao(Long id, ProcedimentoCirurgiao procedimentoCirurgiao) {
        ProcedimentoCirurgiaoEntity oldEntity = procedimentoCirurgiaoRepositoryJpa.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não econtrado", id))
                );

        ProcedimentoCirurgiaoEntity newEntity = new ProcedimentoCirurgiaoEntity(
                id,
                procedimentoCirurgiao.nome() != null ? procedimentoCirurgiao.nome() : oldEntity.getNome(),
                ResultadoMensalCirurgiaoMapper.toEntityList(procedimentoCirurgiao.resultadosMensais())
        );

        return ProcedimentoCirurgiaoEntityMapper.toDomain(procedimentoCirurgiaoRepositoryJpa.save(newEntity));
    }

    @Override
    public String excluirProcedimentoCirurgiao(Long id) {
        return procedimentoCirurgiaoRepositoryJpa.findById(id)
                .map(procedimentoCirurgiao -> {
                    procedimentoCirurgiaoRepositoryJpa.delete(procedimentoCirurgiao);
                    return "Procedimento de cirurgiao excluido com sucesso";
                })
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", id))
                );
    }

}