package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.application.responses.tabela.TabelaEspecialidadesResponse;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;

import java.util.*;

public class DadosEspecialidadeInteractor {

    private TabelaRequest tabelaRequest;

    private final ITabelaEspecialidadeRepository tabelaEspecialidadeRepository;
    private final IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository;
    private final IOrdemTabelaRepository ordemTabelaRepository;

    private List<TabelaCabecalhoEspecialidadesResponse> cabecalhosEspecialidades = new ArrayList<>();
    private List<TabelaEspecialidadesResponse> dadosEspecialidades;

    public DadosEspecialidadeInteractor(
            ITabelaEspecialidadeRepository tabelaEspecialidadeRepository,
            IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository,
            IOrdemTabelaRepository ordemTabelaRepository
    ) {
        this.tabelaEspecialidadeRepository = tabelaEspecialidadeRepository;
        this.resultadoMensalEspecialidadeRepository = resultadoMensalEspecialidadeRepository;
        this.ordemTabelaRepository = ordemTabelaRepository;
    }

    public void salvarDadosEspecialidade(TabelaRequest tabelaRequest) {
        instanciarVariaveis(tabelaRequest);
        removerDadosNaoEspecialidade();
        salvarDadosDasLinhas();
    }

    private void instanciarVariaveis(TabelaRequest tabelaRequest) {
        this.tabelaRequest = tabelaRequest;
    }

    private void removerDadosNaoEspecialidade() {
        this.tabelaRequest = montarTabelaRequestEspecialidade();
    }

    private TabelaRequest montarTabelaRequestEspecialidade() {
        return new TabelaRequest(
                this.tabelaRequest.data(),
                separarDadosEspecialidade(),
                this.tabelaRequest.cabecalhos()
        );
    }

    private List<LinhaTabelaRequest> separarDadosEspecialidade(){
        List<LinhaTabelaRequest> listaLinhaTabelaRequest = new ArrayList<>();

        this.tabelaRequest.linhas().forEach(linhaTabela -> {
            if(linhaTabela.tipo() == TipoLinha.ESPECIALIDADE_LINHA)
                listaLinhaTabelaRequest.add(linhaTabela);
        });

        return listaLinhaTabelaRequest;
    }

    private void salvarDadosDasLinhas() {
        this.tabelaRequest.linhas().forEach(this::salvarDadoDaLinha);
    }

    private void salvarDadoDaLinha(LinhaTabelaRequest dadosEspecialidade) {
        Optional<Especialidade> especialidadeOptional =
                tabelaEspecialidadeRepository.buscarEspecialidade(dadosEspecialidade.componenteId());

        if (especialidadeOptional.isPresent()) {
            if (!existeDadosParaOMes(dadosEspecialidade.componenteId(), this.tabelaRequest.data())) {
                criarDadosIniciaisDoMes(dadosEspecialidade, especialidadeOptional.get());
            } if (!existeDadosParaODia(dadosEspecialidade.componenteId())) {
                adicionarDadosDoDia(dadosEspecialidade, especialidadeOptional.get().medicoAtual());
            } else {
                atualizarDadosDoDia(dadosEspecialidade);
            }
        }
    }

    private boolean existeDadosParaOMes(Long especialidadeId, String data) {
       return resultadoMensalEspecialidadeRepository.existeMesAnoEspecialidade(data, especialidadeId);
    }

    private void criarDadosIniciaisDoMes(LinhaTabelaRequest dadosEspecialidade, Especialidade especialidade) {
        ResultadoMensalEspecialidade resultadoMensal = new ResultadoMensalEspecialidade(
                null,
                ConverterData.toMes(this.tabelaRequest.data()),
                ConverterData.toAno(this.tabelaRequest.data()),
                dadosEspecialidade.pacientesAtendidos(),
                especialidade.metaDiariaAtual(),
                especialidade.metaMensalAtual(),
                adicionaDadosPrimeiroDiaDoMes(dadosEspecialidade, especialidade.medicoAtual())
        );

        resultadoMensalEspecialidadeRepository.salvarDadosIniciaisDoMes(resultadoMensal, especialidade.id());
    }

    private List<ResultadoDiarioEspecialidade> adicionaDadosPrimeiroDiaDoMes(LinhaTabelaRequest dadosEspecialidade, String nomeMedicoAtual) {
        List<ResultadoDiarioEspecialidade> resultadoDiarioEspecialidades = new ArrayList<>();

        resultadoDiarioEspecialidades.add(new ResultadoDiarioEspecialidade(
                null,
                ConverterData.toDia(this.tabelaRequest.data()),
                dadosEspecialidade.pacientesAtendidos(),
                nomeMedicoAtual
        ));

        return resultadoDiarioEspecialidades;
    }

    private boolean existeDadosParaODia(Long especialidadeId) {
        return resultadoMensalEspecialidadeRepository.existeDiaEspecialidade(this.tabelaRequest.data(), especialidadeId);
    }

    private void adicionarDadosDoDia(LinhaTabelaRequest dadosEspecialidade, String medicoAtual) {
        ResultadoDiarioEspecialidade  resultadoDiario = new ResultadoDiarioEspecialidade(
                null,
                ConverterData.toDia(tabelaRequest.data()),
                dadosEspecialidade.pacientesAtendidos(),
                medicoAtual
        );

        resultadoMensalEspecialidadeRepository.salvarDadosDoDia(resultadoDiario, dadosEspecialidade.componenteId(), tabelaRequest.data());
    }

    private void atualizarDadosDoDia(LinhaTabelaRequest dadosEspecialidade) {
        resultadoMensalEspecialidadeRepository.atualizarDadosDoDia(
                this.tabelaRequest.data(),
                dadosEspecialidade
        );
    }

    public List<TabelaCabecalhoEspecialidadesResponse> organizarDadosEspecialidades(String data) {
        List<TabelaCabecalhoEspecialidadesResponse> cabecalhosEspecialidades = buscarCabecalhosEspecialidades(data);
        List<TabelaEspecialidadesResponse> dadosEspecialidades = buscarDadosEspecialidade(data);

        ordenarCabecalhosEspecialidades(cabecalhosEspecialidades);
        ordenarDadosEspecialidades(dadosEspecialidades);

        this.cabecalhosEspecialidades = cabecalhosEspecialidades;
        this.dadosEspecialidades = dadosEspecialidades;

        agregarDadosEspecialidades();

        return cabecalhosEspecialidades;
    }

    public List<TabelaCabecalhoEspecialidadesResponse> buscarCabecalhosEspecialidades(String data) {
        return ordemTabelaRepository.buscarCabecalhosEspecialidades(data);
    }

    public List<TabelaEspecialidadesResponse> buscarDadosEspecialidade(String data) {
        return resultadoMensalEspecialidadeRepository.buscarDadosEspecialidades(data);
    }

    private void ordenarCabecalhosEspecialidades(List<TabelaCabecalhoEspecialidadesResponse> cabecalhos) {
        cabecalhos.sort(Comparator.comparing(TabelaCabecalhoEspecialidadesResponse::posicao));
    }

    private void ordenarDadosEspecialidades(List<TabelaEspecialidadesResponse> dadosEspecialidades) {
        dadosEspecialidades.sort(Comparator.comparing(TabelaEspecialidadesResponse::posicao));
    }

    private void agregarDadosEspecialidades() {
        for (int i = 0; i < cabecalhosEspecialidades.size(); i++) {
            percorrerEspecialidade(i);
        }
    }

    private void percorrerEspecialidade(int i) {
        for (TabelaEspecialidadesResponse especialidade : dadosEspecialidades) {
            validarSeEspecialidadePertenceAoCabecalho(i, especialidade);
        }
    }

    private void validarSeEspecialidadePertenceAoCabecalho(int i, TabelaEspecialidadesResponse especialidade) {
        if (especialidadePertenceAoCabecalho(i, especialidade)){
            cabecalhosEspecialidades.get(i).especialidades().add(especialidade);
        }
    }

    private boolean especialidadePertenceAoCabecalho(int i, TabelaEspecialidadesResponse especialidade) {
        return ultimoLoopEspecialidade(i) && posicaoEspecialidadeMaiorQuePosicaoCabecalho(i, especialidade) ||
                !ultimoLoopEspecialidade(i) && posicaoEspecialidadeMaiorQuePosicaoCabecalho(i, especialidade) && posicaoEspecialidadeMenorQueProximaPosicaoCabecalho(i, especialidade);
    }

    private boolean ultimoLoopEspecialidade(int i) {
        return i == cabecalhosEspecialidades.size() - 1;
    }

    private boolean posicaoEspecialidadeMaiorQuePosicaoCabecalho(int i, TabelaEspecialidadesResponse especialidade) {
        return especialidade.posicao() > cabecalhosEspecialidades.get(i).posicao();
    }

    private boolean posicaoEspecialidadeMenorQueProximaPosicaoCabecalho(int i, TabelaEspecialidadesResponse especialidade) {
        return especialidade.posicao() < cabecalhosEspecialidades.get(i + 1).posicao();
    }

}