package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCirurgioesResponse;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoDiarioCirurgiaoMapper;
import boletimdasaude.infra.gateways.cirurgiao.mappers.ResultadoMensalCirurgiaoMapper;
import boletimdasaude.infra.gateways.ordemtabela.LinhaTabelaRepository;
import boletimdasaude.infra.persitence.cirurgiao.IProcedimentoCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoDiarioCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.IResultadoMensalCirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoDiarioCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ResultadoMensalCirurgiaoRepository implements IResultadoMensalCirurgiaoRepository {

    private int dia;
    private int mes;
    private int ano;

    private final IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa;
    private final IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa;
    private final IResultadoDiarioCirurgiaoRepositoryJpa resultadoDiarioCirurgiaoRepositoryJpa;
    private final LinhaTabelaRepository linhaTabelaRepository;

    public ResultadoMensalCirurgiaoRepository(IProcedimentoCirurgiaoRepositoryJpa procedimentoCirurgiaoRepositoryJpa,
                                              IResultadoMensalCirurgiaoRepositoryJpa resultadoMensalCirurgiaoRepositoryJpa,
                                              IResultadoDiarioCirurgiaoRepositoryJpa resultadoDiarioCirurgiaoRepositoryJpa,
                                              LinhaTabelaRepository linhaTabelaRepository) {
        this.procedimentoCirurgiaoRepositoryJpa = procedimentoCirurgiaoRepositoryJpa;
        this.resultadoMensalCirurgiaoRepositoryJpa = resultadoMensalCirurgiaoRepositoryJpa;
        this.resultadoDiarioCirurgiaoRepositoryJpa = resultadoDiarioCirurgiaoRepositoryJpa;
        this.linhaTabelaRepository = linhaTabelaRepository;
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
    public ResultadoMensalCirurgiao salvarDadosDoDia(ResultadoDiarioCirurgiao resultadoDiarioCirurgiao, Long procedimentoId, String data) {
        ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity = buscarMesAnoProcedimentoCirurgiao(data, procedimentoId);
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

    private ResultadoDiarioCirurgiaoEntity buscarDiaCirurgiao(String data, Long resultadoMensalId) {
        ResultadoDiarioCirurgiaoEntity resultado = null;

        instanciaVariaveis(data);

        Optional<ProcedimentoCirurgiaoEntity> procedimentoCirurgiaoEntity = procedimentoCirurgiaoRepositoryJpa.findById(resultadoMensalId);

        if (procedimentoCirurgiaoEntity.isPresent()) {
            resultado = percorrerResultadosMensal(procedimentoCirurgiaoEntity.get());
        }

        return resultado;
    }

    private void instanciaVariaveis(String data) {
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
        return resultadoDiarioCirurgiaoEntity.getDia() == dia;
    }

    public ResultadoMensalCirurgiaoEntity buscarMesAnoProcedimentoCirurgiao(String data, Long procedimentoId) {
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
    public boolean existeMesProcedimentoCirurgiao(String data, Long resultadoMensalId) {
        return buscarMesAnoProcedimentoCirurgiao(data, resultadoMensalId) != null;
    }

    @Override
    public boolean existeDiaCirurgiao(String data, Long resultadoMensalId) {
        return buscarDiaCirurgiao(data, resultadoMensalId) != null;
    }

    @Override
    public ResultadoDiarioCirurgiao atualizarDadosDoDia(String data, LinhaTabelaRequest linhaTabelaRequest) {
        ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity = buscarDiaCirurgiao(data, linhaTabelaRequest.componenteId());
        resultadoDiarioCirurgiaoEntity.setAtendimentos(linhaTabelaRequest.pacientesAtendidos());

        resultadoDiarioCirurgiaoEntity = resultadoDiarioCirurgiaoRepositoryJpa.save(resultadoDiarioCirurgiaoEntity);

        ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity = resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao();
        resultadoMensalCirurgiaoEntity.setAtendimentos(somarAtendimentos(resultadoMensalCirurgiaoEntity));
        resultadoMensalCirurgiaoRepositoryJpa.save(resultadoMensalCirurgiaoEntity);

        return ResultadoDiarioCirurgiaoMapper.toDomain(resultadoDiarioCirurgiaoEntity);
    }

    @Override
    public List<TabelaCirurgioesResponse> buscarDadosCirurgioes(String data) {
        List<TabelaCirurgioesResponse> tabelaCirurgioesResponses = new ArrayList<>();

        instanciaVariaveis(data);

        List<ResultadoMensalCirurgiaoEntity> listaResultadoMensal = resultadoMensalCirurgiaoRepositoryJpa.findByMesAndAno(mes, ano);
        List<ResultadoDiarioCirurgiaoEntity> listaResultadoDiario = buscarResultadoDiarioCirurgiao(listaResultadoMensal);

        montarTabelaCirurgioes(tabelaCirurgioesResponses, listaResultadoDiario);

        return tabelaCirurgioesResponses;
    }

    private List<ResultadoDiarioCirurgiaoEntity> buscarResultadoDiarioCirurgiao(List<ResultadoMensalCirurgiaoEntity> listaResultadoMensal) {
        List<ResultadoDiarioCirurgiaoEntity> listaResultadoDiario = new ArrayList<>();

        for (ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity : listaResultadoMensal) {
            for (ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity : resultadoMensalCirurgiaoEntity.getResultadosDiarios()) {
                if (resultadoDiarioCirurgiaoEntity.getDia() == dia) {
                    listaResultadoDiario.add(resultadoDiarioCirurgiaoEntity);
                }
            }
        }

        return listaResultadoDiario;
    }

    private void montarTabelaCirurgioes(List<TabelaCirurgioesResponse> tabelaCirurgioesResponses, List<ResultadoDiarioCirurgiaoEntity> listaResultadoDiario) {
        for (ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity: listaResultadoDiario) {
            Long posicao = linhaTabelaRepository.buscarPosicaoProcedimento(resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getProcedimento().getId());

            TabelaCirurgioesResponse tabelaCirurgioesResponse = new TabelaCirurgioesResponse(
                    posicao,
                    resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getProcedimento().getId(),
                    resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getProcedimento().getCirurgiao().getNome(),
                    resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getProcedimento().getNome(),
                    resultadoDiarioCirurgiaoEntity.getAtendimentos(),
                    resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getAtendimentos(),
                    calculaAtendimentosNoAno(resultadoDiarioCirurgiaoEntity)

            );

            tabelaCirurgioesResponses.add(tabelaCirurgioesResponse);
        }
    }

    private int calculaAtendimentosNoAno(ResultadoDiarioCirurgiaoEntity resultadoDiarioCirurgiaoEntity) {
        int atendimentosAno = 0;

        for (ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiaoEntity : resultadoDiarioCirurgiaoEntity.getResultadoMensalCirurgiao().getProcedimento().getResultadosMensais()) {
            atendimentosAno += resultadoMensalCirurgiaoEntity.getAtendimentos();
        }

        return atendimentosAno;
    }

}
