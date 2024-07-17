package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoDiarioCirurgiaoMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoDiarioCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoMensalCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoDiarioCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;

import java.util.Date;
import java.util.Optional;

public class ResultadoMensalCirurgiaoRepository implements IResultadoMensalCirurgiaoRepository {

    private int dia;
    private int mes;
    private int ano;

    private final IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa;
    private final IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa;
    private final IResultadoDiarioCirurgiaoRepositoryJpa resultadoDiarioCirurgiaoRepositoryJpa;

    public ResultadoMensalCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                              IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa,
                                              IResultadoDiarioCirurgiaoRepositoryJpa resultadoDiarioCirurgiaoRepositoryJpa) {
        this.procedimentoCirurgiaoRepositoryJpa = procedimentoCirurgiaoRepositoryJpa;
        this.resultadoMensalCirurgiaoRepositoryJpa = resultadoMensalCirurgiaoRepositoryJpa;
        this.resultadoDiarioCirurgiaoRepositoryJpa = resultadoDiarioCirurgiaoRepositoryJpa;
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
        resultadoMensalCirurgiaoEntity.setAtendimentos(somarAtendimentos(resultadoMensalCirurgiaoEntity));
        ResultadoMensalCirurgiaoEntity resultado = resultadoMensalCirurgiaoRepositoryJpa.save(resultadoMensalCirurgiaoEntity);

        return ResultadoMensalCirurgiaoMapper.toDomain(resultado);
    }

    private int somarAtendimentos(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity) {
        int atendimentos = 0;

        for (ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity : resultadoMensalCirurgiaoEntity.getResultadosDiarios()) {
            atendimentos += resultadoDiarioCirurgiaoEntity.getAtendimentos();
        }

        return atendimentos;
    }

    private ResultadoDiarioCirurgiaoEntity buscarDiaCirurgiao(Date data, Long resultadoMensalId) {
        ResultadoDiarioCirurgiaoEntity resultado = null;

        instanciaVariaveis(data);

        Optional<ProcedimentoCirurgiaoEntity> procedimentoCirurgiaoEntity = procedimentoCirurgiaoRepositoryJpa.findById(resultadoMensalId);

        if (procedimentoCirurgiaoEntity.isPresent()) {
            resultado = percorrerResultadosMensal(procedimentoCirurgiaoEntity.get());
        }

        return resultado;
    }

    private void instanciaVariaveis(Date data) {
        this.dia = ConverterData.toDia(data);
        this.mes = ConverterData.toMes(data);
        this.ano = ConverterData.toAno(data);
    }

    private ResultadoDiarioCirurgiaoEntity percorrerResultadosMensal(ProcedimentoCirurgiaoEntity procedimentoCirurgiaoEntity) {
        ResultadoDiarioCirurgiaoEntity resultado = null;

        for(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity : procedimentoCirurgiaoEntity.getResultadosMensais()) {
            if (mesmoMesEAno(resultadoMensalCirurgiaoEntity)) {
                resultado = percorrerResultadoDiario(resultadoMensalCirurgiaoEntity);
            }
        }

        return resultado;
    }

    private boolean mesmoMesEAno(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity) {
        return resultadoMensalCirurgiaoEntity.getMes() == mes && resultadoMensalCirurgiaoEntity.getAno() == ano;
    }

    private ResultadoDiarioCirurgiaoEntity percorrerResultadoDiario(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity) {
        ResultadoDiarioCirurgiaoEntity resultado = null;

        for (ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity : resultadoMensalCirurgiaoEntity.getResultadosDiarios()) {
            if (mesmoDia(resultadoDiarioCirurgiaoEntity)) {
                resultado = resultadoDiarioCirurgiaoEntity;
            }
        }

        return resultado;
    }

    private boolean mesmoDia(ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity) {
        return ConverterData.toDia(resultadoDiarioCirurgiaoEntity.getData()) == dia;
    }

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

    @Override
    public boolean existeMesProcedimentoCirurgiao(Date data, Long resultadoMensalId) {
        return buscarMesAnoProcedimentoCirurgiao(data, resultadoMensalId) != null;
    }

    @Override
    public boolean existeDiaCirurgiao(Date data, Long resultadoMensalId) {
        return buscarDiaCirurgiao(data, resultadoMensalId) != null;
    }

    @Override
    public ResultadoDiarioCirurgiao atualizarDadosDoDia(Date data, LinhaTabelaRequest linhaTabelaRequest) {
        ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity = buscarDiaCirurgiao(data, linhaTabelaRequest.componenteId());
        resultadoDiarioCirurgiaoEntity.setAtendimentos(linhaTabelaRequest.pacientesAtendidos());

        resultadoDiarioCirurgiaoEntity = resultadoDiarioCirurgiaoRepositoryJpa.save(resultadoDiarioCirurgiaoEntity);

        ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity = resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao();
        resultadoMensalCirurgiaoEntity.setAtendimentos(somarAtendimentos(resultadoMensalCirurgiaoEntity));
        resultadoMensalCirurgiaoRepositoryJpa.save(resultadoMensalCirurgiaoEntity);

        return ResultadoDiarioCirurgiaoMapper.toDomain(resultadoDiarioCirurgiaoEntity);
    }

}
