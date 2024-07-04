package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.CirurgiaoEntityMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ProcedimentoCirurgiaoMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.ICirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;

import java.util.List;

public class CirurgiaoRepository implements ICirurgiaoRepository {

    private final ICirurgiaoRepositoryJpa cirurgiaoRepository;
    private final CirurgiaoEntityMapper cirurgiaoEntityMapper;
    private final ResultadoMensalCirurgiaoMapper resultadoMensalCirurgiaoMapper;

    public CirurgiaoRepository(ICirurgiaoRepositoryJpa cirurgiaoRepository, CirurgiaoEntityMapper cirurgiaoEntityMapper, ResultadoMensalCirurgiaoMapper resultadoMensalCirurgiaoMapper) {
        this.cirurgiaoRepository = cirurgiaoRepository;
        this.cirurgiaoEntityMapper = cirurgiaoEntityMapper;
        this.resultadoMensalCirurgiaoMapper = resultadoMensalCirurgiaoMapper;
    }

    @Override
    public Cirurgiao criarCirurgiao(Cirurgiao cirurgiao) {
        return CirurgiaoEntityMapper.toDomain(cirurgiaoRepository.save(cirurgiaoEntityMapper.toEntity(cirurgiao)));
    }

    @Override
    public List<Cirurgiao> buscarTodosCirurgioes() {
        return cirurgiaoEntityMapper.toDomainList(cirurgiaoRepository.findAll());
    }

    @Override
    public Cirurgiao editarCirurgiao(Long id, Cirurgiao cirurgiao) {
        CirurgiaoEntity oldEntity = cirurgiaoRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não econtrado", id))
        );

        CirurgiaoEntity newEntity = new CirurgiaoEntity(
                id,
                cirurgiao.nome() != null ? cirurgiao.nome() : oldEntity.getNome(),
                ProcedimentoCirurgiaoMapper.toEntityList(cirurgiao.procedimentos())
        );

        return CirurgiaoEntityMapper.toDomain(cirurgiaoRepository.save(newEntity));
    }

    @Override
    public String excluirCirurgiao(Long id) {
        return cirurgiaoRepository.findById(id)
                .map(cirurgiao -> {
                    cirurgiaoRepository.delete(cirurgiao);
                    return "Cirurgiao excluido com sucesso";
                })
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", id))
        );
    }

}