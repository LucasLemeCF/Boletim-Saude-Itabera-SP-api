package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoDiarioCirurgiaoMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoMensalCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoDiarioCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;

import java.util.Date;
import java.util.Optional;

public class ResultadoMensalCirurgiaoRepository implements IResultadoMensalCirurgiaoRepository {

    private final IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa;
    private final IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa;

    public ResultadoMensalCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                              IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa) {
        this.procedimentoCirurgiaoRepositoryJpa = procedimentoCirurgiaoRepositoryJpa;
        this.resultadoMensalCirurgiaoRepositoryJpa = resultadoMensalCirurgiaoRepositoryJpa;
    }

    @Override
    public ResultadoMensalCirurgiao salvarDadosIniciaisDoMes(ResultadoMensalCirurgiao resultadoMensalCirurgiao, Long procedimentoId) {
        ProcedimentoCirurgiaoEntity oldProcedimentoCirurgiaoEntity = procedimentoCirurgiaoRepositoryJpa.findById(procedimentoId)
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", procedimentoId)));

        ResultadoMensalCirurgiaoEntity novoResultadoMensalEntity = ResultadoMensalCirurgiaoMapper.toEntity(resultadoMensalCirurgiao);
        novoResultadoMensalEntity.getResultadosDiarios().forEach(resultadoDiario -> resultadoDiario.setResultadoMensalCirurgiao(novoResultadoMensalEntity));

        oldProcedimentoCirurgiaoEntity.getResultadosMensais().add(novoResultadoMensalEntity);
        novoResultadoMensalEntity.setProcedimento(oldProcedimentoCirurgiaoEntity);

        procedimentoCirurgiaoRepositoryJpa.save(oldProcedimentoCirurgiaoEntity);

        return ResultadoMensalCirurgiaoMapper.toDomain(novoResultadoMensalEntity);
    }

    @Override
    public ResultadoMensalCirurgiao salvarDadosDoDia(ResultadoDiarioCirurgiao resultadoDiarioCirurgiao, Long procedimentoId) {
        ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity = buscarMesAnoProcedimentoCirurgiao(resultadoDiarioCirurgiao.data(), procedimentoId);
        ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity = ResultadoDiarioCirurgiaoMapper.toEntity(resultadoDiarioCirurgiao);
        resultadoDiarioCirurgiaoEntity.setResultadoMensalCirurgiao(resultadoMensalCirurgiaoEntity);

        resultadoMensalCirurgiaoEntity.getResultadosDiarios().add(resultadoDiarioCirurgiaoEntity);
        ResultadoMensalCirurgiaoEntity resultado = resultadoMensalCirurgiaoRepositoryJpa.save(resultadoMensalCirurgiaoEntity);

        return ResultadoMensalCirurgiaoMapper.toDomain(resultado);
    }

    @Override
    public ResultadoMensalCirurgiaoEntity buscarMesAnoProcedimentoCirurgiao(Date data, Long procedimentoId) {
        ResultadoMensalCirurgiaoEntity resultado = null;

        Optional<ProcedimentoCirurgiaoEntity> procedimentoCirurgiaoEntity = procedimentoCirurgiaoRepositoryJpa.findById(procedimentoId);

        int mes = ConverterData.toMes(data);
        int ano = ConverterData.toAno(data);

        if (procedimentoCirurgiaoEntity.isPresent()) {
            for(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity : procedimentoCirurgiaoEntity.get().getResultadosMensais()) {
                if (resultadoMensalCirurgiaoEntity.getMes() == mes && resultadoMensalCirurgiaoEntity.getAno() == ano) {
                    resultado = resultadoMensalCirurgiaoEntity;
                }
            }
        }

        return resultado;
    }

}
