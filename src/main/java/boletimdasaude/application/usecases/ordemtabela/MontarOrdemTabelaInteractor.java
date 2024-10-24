package boletimdasaude.application.usecases.ordemtabela;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.application.gateways.cirurgiao.IResultadoMensalCirurgiaoRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.responses.ordemTabela.*;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaCabecalhoEspecialidadesResponse;
import boletimdasaude.application.responses.tabela.TabelaCirurgioesResponse;
import boletimdasaude.application.responses.tabela.TabelaEspecialidadesResponse;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.domain.cirurgiao.ProcedimentoCirurgiao;
import boletimdasaude.domain.ordemtabela.TextoCabecalhoTabela;

import java.util.ArrayList;
import java.util.List;

public class MontarOrdemTabelaInteractor {

    private final IOrdemTabelaRepository tabelaRepository;
    private final IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository;
    private final IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository;
    private final ICirurgiaoRepository cirurgiaoRepository;

    private String data;

    public MontarOrdemTabelaInteractor(
            IOrdemTabelaRepository tabelaRepository,
            IResultadoMensalEspecialidadeRepository resultadoMensalEspecialidadeRepository, IResultadoMensalCirurgiaoRepository resultadoMensalCirurgiaoRepository,
            ICirurgiaoRepository cirurgiaoRepository
    ) {
        this.tabelaRepository = tabelaRepository;
        this.resultadoMensalEspecialidadeRepository = resultadoMensalEspecialidadeRepository;
        this.resultadoMensalCirurgiaoRepository = resultadoMensalCirurgiaoRepository;
        this.cirurgiaoRepository = cirurgiaoRepository;
    }

    public OrdemTabelaResponse criarOrdemTabelaResponse(String data) {
        this.data = data;

        List<CabecalhoResponse> cabecalhosEspecialidades = criarCabecalhosEspecialidades();
        List<CabecalhoResponse> cabecalhosCirurgioes = criarCabecalhosCirurgioes();
        List<LinhaEspecialidadeResponse> linhasEspecialidades = criarLinhasEspecialidades();
        List<CirurgiaoResponse> listaCirurgioes = criarCirurgioes();

        return new OrdemTabelaResponse(
                data,
                cabecalhosEspecialidades,
                cabecalhosCirurgioes,
                linhasEspecialidades,
                listaCirurgioes
        );
    }

    private List<CabecalhoResponse> criarCabecalhosEspecialidades() {
        List<CabecalhoResponse> resultado = new ArrayList<>();

        List<TabelaCabecalhoEspecialidadesResponse> listaCabecalhosEspecialidades = tabelaRepository.buscarCabecalhosEspecialidades(data);

        for (TabelaCabecalhoEspecialidadesResponse cabecalho : listaCabecalhosEspecialidades) {
            List<String> textos = new ArrayList<>();

            for (TextoCabecalhoTabela texto : cabecalho.textos()) {
                textos.add(texto.texto());
            }

            resultado.add(new CabecalhoResponse(cabecalho.posicao(), textos));
        }

        return resultado;
    }

    private List<CabecalhoResponse> criarCabecalhosCirurgioes() {
        List<CabecalhoResponse> resultado = new ArrayList<>();

        List<TabelaCabecalhoCirurgioesResponse> listaCabecalhosCirurgioes = tabelaRepository.buscarCabecalhosCirurgioes(data);

        for (TabelaCabecalhoCirurgioesResponse cabecalho : listaCabecalhosCirurgioes) {
            List<String> textos = new ArrayList<>();

            for (TextoCabecalhoTabela texto : cabecalho.textos()) {
                textos.add(texto.texto());
            }

            resultado.add(new CabecalhoResponse(cabecalho.posicao(), textos));
        }

        return resultado;
    }

    private List<LinhaEspecialidadeResponse> criarLinhasEspecialidades() {
        List<LinhaEspecialidadeResponse> resultado = new ArrayList<>();

        List<TabelaEspecialidadesResponse> listaEspecialidades = resultadoMensalEspecialidadeRepository.buscarDadosEspecialidades(data);

        for (TabelaEspecialidadesResponse especialidade : listaEspecialidades) {
            resultado.add(new LinhaEspecialidadeResponse(
                    especialidade.posicao(),
                    especialidade.especialidadeId(),
                    especialidade.especialidade()
            ));
        }

        return resultado;
    }

    private List<CirurgiaoResponse> criarCirurgioes() {
        List<CirurgiaoResponse> resultado = new ArrayList<>();

        List<Cirurgiao> listaCirurgioes = cirurgiaoRepository.buscarTodosCirurgioesComDadosMes(data);
        List<TabelaCirurgioesResponse> listaProcedimentos = resultadoMensalCirurgiaoRepository.buscarDadosCirurgioes(data);

        for (Cirurgiao cirurgiao : listaCirurgioes) {
            resultado.add(new CirurgiaoResponse(
                    cirurgiao.nome(),
                    montarLinhasProcedimentos(cirurgiao.nome(), listaProcedimentos)
            ));
        }

        return resultado;
    }

    private List<LinhaProcedimentoResponse> montarLinhasProcedimentos(String nomeCirurgiao, List<TabelaCirurgioesResponse> listaProcedimentos) {
        List<LinhaProcedimentoResponse> resultado = new ArrayList<>();

        for (TabelaCirurgioesResponse procedimento : listaProcedimentos) {
            if (procedimento.cirurgiao().equals(nomeCirurgiao)) {
                resultado.add(new LinhaProcedimentoResponse(
                        procedimento.posicao(),
                        procedimento.procedimentoId(),
                        procedimento.cirurgiao(),
                        procedimento.procedimento()
                ));
            }
        }

        return resultado;
    }

}
