package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalvarDadosCirurgiaoInteractor {

    private TabelaRequest tabelaRequest;

    private final ITabelaCirurgiaoRepository tabelaCirurgiaoRepository;
    private final IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository;

    public SalvarDadosCirurgiaoInteractor(
            ITabelaCirurgiaoRepository tabelaCirurgiaoRepository,
            IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository
    ) {
        this.tabelaCirurgiaoRepository = tabelaCirurgiaoRepository;
        this.resultadoMensalCirurgiaoRepository = resultadoMensalCirurgiaoRepository;
    }

    public void salvarDadosCirurgiao(TabelaRequest tabelaRequest) {
        instanciarVariaveis(tabelaRequest);
        removerDadosNaoCirurgiao();
        salvarDadosDasLinhas();
    }

    private void instanciarVariaveis(TabelaRequest tabelaRequest) {
        this.tabelaRequest = tabelaRequest;
    }

    private void removerDadosNaoCirurgiao() {
        this.tabelaRequest = montarTabelaRequestCirurgiao();
    }

    private TabelaRequest montarTabelaRequestCirurgiao() {
        return new TabelaRequest(
                this.tabelaRequest.data(),
                separarDadosCirurgiao()
        );
    }

    private List<LinhaTabelaRequest> separarDadosCirurgiao(){
        List<LinhaTabelaRequest> listaLinhaTabelaRequest = new ArrayList<>();

        this.tabelaRequest.linhas().forEach(linhaTabela -> {
            if(linhaTabela.tipo() == TipoLinha.CIRURGIAO_LINHA)
                listaLinhaTabelaRequest.add(linhaTabela);
        });

        return listaLinhaTabelaRequest;
    }

    private void salvarDadosDasLinhas() {
        this.tabelaRequest.linhas().forEach(this::salvarDadoDaLinha);
    }

    private void salvarDadoDaLinha(LinhaTabelaRequest dadosProcedimento) {
        Optional<ProcedimentoCirurgiao> procedimentoCirurgiaoOptional =
                tabelaCirurgiaoRepository.buscarProcedimentoCirurgiao(dadosProcedimento.componenteId());

        if (procedimentoCirurgiaoOptional.isPresent()) {
            if (!existeDadosParaOMes(dadosProcedimento.componenteId())) {
                criarDadosIniciaisDoMes(dadosProcedimento, procedimentoCirurgiaoOptional.get().id());
            } else {
                adicionarDadosDoDia(dadosProcedimento);
            }
        }
    }

    private boolean existeDadosParaOMes(Long procedimentoId) {
        return  resultadoMensalCirurgiaoRepository.buscarMesAnoProcedimentoCirurgiao(this.tabelaRequest.data(), procedimentoId) != null;
    }

    private List<ResultadoDiarioCirurgiao> adicionaDadosPrimeiroDiaDoMes(LinhaTabelaRequest dadosCirurgiao) {
        List<ResultadoDiarioCirurgiao> resultadoDiarioCirurgiaos = new ArrayList<>();

        resultadoDiarioCirurgiaos.add(new ResultadoDiarioCirurgiao(
                null,
                this.tabelaRequest.data(),
                dadosCirurgiao.pacientesAtendidos()
        ));

        return resultadoDiarioCirurgiaos;
    }

    private void criarDadosIniciaisDoMes(LinhaTabelaRequest dadosCirurgiao, Long procedimentoId) {
        ResultadoMensalCirurgiao resultadoMensal = new ResultadoMensalCirurgiao(
                null,
                ConverterData.toMes(this.tabelaRequest.data()),
                ConverterData.toAno(this.tabelaRequest.data()),
                dadosCirurgiao.pacientesAtendidos(),
                adicionaDadosPrimeiroDiaDoMes(dadosCirurgiao)
        );

        resultadoMensalCirurgiaoRepository.salvarDadosIniciaisDoMes(resultadoMensal, procedimentoId);
    }

    private void adicionarDadosDoDia(LinhaTabelaRequest dadosCirurgiao) {
        ResultadoDiarioCirurgiao  resultadoDiario = new ResultadoDiarioCirurgiao(
                null,
                this.tabelaRequest.data(),
                dadosCirurgiao.pacientesAtendidos()
        );

        resultadoMensalCirurgiaoRepository.salvarDadosDoDia(resultadoDiario, dadosCirurgiao.componenteId());
    }

}
