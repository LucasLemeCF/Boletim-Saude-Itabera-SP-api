package boletimdasaude.application.usecases.tabela;

import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.requests.tabela.TabelaRequest;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalvarDadosEspecialidadeInteractor {

    private TabelaRequest tabelaRequest;

    private final ITabelaEspecialidadeRepository tabelaEspecialidadeRepository;
    private final IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository;

    public SalvarDadosEspecialidadeInteractor(
            ITabelaEspecialidadeRepository tabelaEspecialidadeRepository,
            IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository
    ) {
        this.tabelaEspecialidadeRepository = tabelaEspecialidadeRepository;
        this.resultadoMensalEspecialidadeRepository = resultadoMensalEspecialidadeRepository;
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
                separarDadosEspecialidade()
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
            if (!existeDadosParaOMes(dadosEspecialidade.componenteId())) {
                criarDadosIniciaisDoMes(dadosEspecialidade, especialidadeOptional.get());
            } else {
                adicionarDadosDoDia(dadosEspecialidade, especialidadeOptional.get().medicoAtual());
            }
        }
    }

    private boolean existeDadosParaOMes(Long especialidadeId) {
       return resultadoMensalEspecialidadeRepository.buscarMesAnoEspecialidade(this.tabelaRequest.data(), especialidadeId) != null;
    }

    private List<ResultadoDiarioEspecialidade> adicionaDadosPrimeiroDiaDoMes(LinhaTabelaRequest dadosEspecialidade, String nomeMedicoAtual) {
        List<ResultadoDiarioEspecialidade> resultadoDiarioEspecialidades = new ArrayList<>();

        resultadoDiarioEspecialidades.add(new ResultadoDiarioEspecialidade(
                null,
                this.tabelaRequest.data(),
                dadosEspecialidade.pacientesAtendidos(),
                nomeMedicoAtual
        ));

        return resultadoDiarioEspecialidades;
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

    private void adicionarDadosDoDia(LinhaTabelaRequest dadosEspecialidade, String medicoAtual) {
        ResultadoDiarioEspecialidade  resultadoDiario = new ResultadoDiarioEspecialidade(
                null,
                this.tabelaRequest.data(),
                dadosEspecialidade.pacientesAtendidos(),
                medicoAtual
        );

        resultadoMensalEspecialidadeRepository.salvarDadosDoDia(resultadoDiario, dadosEspecialidade.componenteId());
    }

}
