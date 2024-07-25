package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.gateways.tabela.ITabelaCirurgiaoRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaCirurgioesResponse;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.cirurgiao.ResultadoDiarioCirurgiao;
import boletimdasaude.domain.cirurgiao.ResultadoMensalCirurgiao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DadosCirurgiaoInteractor {

    private TabelaRequest tabelaRequest;

    private final ITabelaCirurgiaoRepository tabelaCirurgiaoRepository;
    private final IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository;
    private final IOrdemTabelaRepository ordemTabelaRepository;

    private List<TabelaCabecalhoCirurgioesResponse> cabecalhosCirurgioes = new ArrayList<>();
    private List<TabelaCirurgioesResponse> dadosCirurgioes;

    public DadosCirurgiaoInteractor(
            ITabelaCirurgiaoRepository tabelaCirurgiaoRepository,
            IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository,
            IOrdemTabelaRepository ordemTabelaRepository
    ) {
        this.tabelaCirurgiaoRepository = tabelaCirurgiaoRepository;
        this.resultadoMensalCirurgiaoRepository = resultadoMensalCirurgiaoRepository;
        this.ordemTabelaRepository = ordemTabelaRepository;
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
            } if (!existeDadosParaODia(dadosProcedimento.componenteId())) {
                adicionarDadosDoDia(dadosProcedimento);
            } else {
                atualizarDadosDoDia(dadosProcedimento);
            }
        }
    }

    private boolean existeDadosParaOMes(Long procedimentoId) {
        return resultadoMensalCirurgiaoRepository.existeMesProcedimentoCirurgiao(this.tabelaRequest.data(), procedimentoId);
    }

    private List<ResultadoDiarioCirurgiao> adicionaDadosPrimeiroDiaDoMes(LinhaTabelaRequest dadosCirurgiao) {
        List<ResultadoDiarioCirurgiao> resultadoDiarioCirurgiaos = new ArrayList<>();

        resultadoDiarioCirurgiaos.add(new ResultadoDiarioCirurgiao(
                null,
                ConverterData.toDia(this.tabelaRequest.data()),
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

    private boolean existeDadosParaODia(Long especialidadeId) {
        return resultadoMensalCirurgiaoRepository.existeDiaCirurgiao(this.tabelaRequest.data(), especialidadeId);
    }

    private void adicionarDadosDoDia(LinhaTabelaRequest dadosCirurgiao) {
        ResultadoDiarioCirurgiao  resultadoDiario = new ResultadoDiarioCirurgiao(
                null,
                ConverterData.toDia(tabelaRequest.data()),
                dadosCirurgiao.pacientesAtendidos()
        );

        resultadoMensalCirurgiaoRepository.salvarDadosDoDia(resultadoDiario, dadosCirurgiao.componenteId(), tabelaRequest.data());
    }

    private void atualizarDadosDoDia(LinhaTabelaRequest dadosCirurgiao) {
        resultadoMensalCirurgiaoRepository.atualizarDadosDoDia(
                this.tabelaRequest.data(),
                dadosCirurgiao
        );
    }

    public List<TabelaCirurgioesResponse> buscarDadosCirurgioes(String data) {
        return resultadoMensalCirurgiaoRepository.buscarDadosCirurgioes(data);
    }

    public List<TabelaCabecalhoCirurgioesResponse> buscarCabecalhosCirurgioes(String data) {
        return ordemTabelaRepository.buscarCabecalhosCirurgioes(data);
    }

    public List<TabelaCabecalhoCirurgioesResponse> organizarDadosCirurgioes(String data) {
        List<TabelaCabecalhoCirurgioesResponse> cabecalhosCirurgioes = buscarCabecalhosCirurgioes(data);
        List<TabelaCirurgioesResponse> dadosCirurgioes = buscarDadosCirurgioes(data);

        ordenarCabecalhosCirurgioes(cabecalhosCirurgioes);
        ordenarDadosCirurgioes(dadosCirurgioes);

        this.cabecalhosCirurgioes = cabecalhosCirurgioes;
        this.dadosCirurgioes = dadosCirurgioes;

        agregarDadosCirurgioes();

        return cabecalhosCirurgioes;
    }

    private void ordenarCabecalhosCirurgioes(List<TabelaCabecalhoCirurgioesResponse> cabecalhos) {
        cabecalhos.sort(Comparator.comparing(TabelaCabecalhoCirurgioesResponse::posicao));
    }

    private void ordenarDadosCirurgioes(List<TabelaCirurgioesResponse> dadosCirurgioes) {
        dadosCirurgioes.sort(Comparator.comparing(TabelaCirurgioesResponse::posicao));
    }

    private void agregarDadosCirurgioes() {
        for (int i = 0; i < cabecalhosCirurgioes.size(); i++) {
            percorrerCirurgiao(i);
        }
    }

    private void percorrerCirurgiao(int i) {
        for (TabelaCirurgioesResponse cirurgiao : dadosCirurgioes) {
            validarSeCirurgiaoPertenceAoCabecalho(i, cirurgiao);
        }
    }

    private void validarSeCirurgiaoPertenceAoCabecalho(int i, TabelaCirurgioesResponse cirurgiao) {
        if (cirurgiaoPertenceAoCabecalho(i, cirurgiao)){
            cabecalhosCirurgioes.get(i).cirurgioes().add(cirurgiao);
        }
    }

    private boolean cirurgiaoPertenceAoCabecalho(int i, TabelaCirurgioesResponse cirurgiao) {
        return ultimoLoopCirurgiao(i) && posicaoCirurgiaoMaiorQuePosicaoCabecalho(i, cirurgiao) ||
                !ultimoLoopCirurgiao(i) && posicaoCirurgiaoMaiorQuePosicaoCabecalho(i, cirurgiao) && posicaoCirurgiaoMenorQueProximaPosicaoCabecalho(i, cirurgiao);
    }

    private boolean ultimoLoopCirurgiao(int i) {
        return i == cabecalhosCirurgioes.size() - 1;
    }

    private boolean posicaoCirurgiaoMaiorQuePosicaoCabecalho(int i, TabelaCirurgioesResponse cirurgiao) {
        return cirurgiao.posicao() > cabecalhosCirurgioes.get(i).posicao();
    }

    private boolean posicaoCirurgiaoMenorQueProximaPosicaoCabecalho(int i, TabelaCirurgioesResponse cirurgiao) {
        return cirurgiao.posicao() < cabecalhosCirurgioes.get(i + 1).posicao();
    }

}