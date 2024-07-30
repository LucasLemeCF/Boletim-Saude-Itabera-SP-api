package boletimdasaude.infra.gateways.especialidade;

import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.application.gateways.especialidade.IResultadoMensalEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.application.requests.tabela.LinhaTabelaRequest;
import boletimdasaude.application.responses.tabela.TabelaEspecialidadesResponse;
import boletimdasaude.application.util.ConverterData;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.especialidade.ResultadoDiarioEspecialidade;
import boletimdasaude.domain.especialidade.ResultadoMensalEspecialidade;
import boletimdasaude.domain.ordemtabela.LinhaTabela;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoDiarioEspecialidadeMapper;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.gateways.ordemtabela.LinhaTabelaRepository;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.IResultadoMensalEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.IResultadoDiarioEspecialidadeRepositoryJpa;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoDiarioEspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ResultadoMensalEspecialidadeRepository implements IResultadoMensalEspecialidadeRepository {

    private int dia;
    private int mes;
    private int ano;
    private String data;

    private final IEspecialidadeRepositoryJpa especialidadeRepositoryJpa;
    private final IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa;
    private final IResultadoDiarioEspecialidadeRepositoryJpa resultadoDiarioEspecialidadeRepositoryJpa;
    private final LinhaTabelaRepository linhaTabelaRepository;
    private final IOrdemTabelaRepository tabelaRepository;
    private final IEspecialidadeRepository especialidadeRepository;

    public ResultadoMensalEspecialidadeRepository(IEspecialidadeRepositoryJpa especialidadeRepositoryJpa,
                                                  IResultadoMensalEspecialidadeRepositoryJpa resultadoMensalEspecialidadeRepositoryJpa,
                                                  IResultadoDiarioEspecialidadeRepositoryJpa resultadoDiarioEspecialidadeRepositoryJpa,
                                                  LinhaTabelaRepository linhaTabelaRepository,
                                                  IOrdemTabelaRepository tabelaRepository,
                                                  IEspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepositoryJpa = especialidadeRepositoryJpa;
        this.resultadoMensalEspecialidadeRepositoryJpa = resultadoMensalEspecialidadeRepositoryJpa;
        this.resultadoDiarioEspecialidadeRepositoryJpa = resultadoDiarioEspecialidadeRepositoryJpa;
        this.linhaTabelaRepository = linhaTabelaRepository;
        this.tabelaRepository = tabelaRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public ResultadoMensalEspecialidade salvarDadosIniciaisDoMes(ResultadoMensalEspecialidade resultadoMensalEspecialidade, Long especialidadeId) {
        EspecialidadeEntity oldEspecialidadeEntity = especialidadeRepositoryJpa.findById(especialidadeId)
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", especialidadeId)));

        ResultadoMensalEspecialidadeEntity novoResultadoMensalEntity = ResultadoMensalEspecialidadeMapper.toEntity(resultadoMensalEspecialidade);
        novoResultadoMensalEntity.getResultadosDiarios().forEach(resultadoDiario -> resultadoDiario.setResultadoMensal(novoResultadoMensalEntity));

        oldEspecialidadeEntity.getResultadosMensais().add(novoResultadoMensalEntity);
        novoResultadoMensalEntity.setEspecialidade(oldEspecialidadeEntity);

        especialidadeRepositoryJpa.save(oldEspecialidadeEntity);

        return ResultadoMensalEspecialidadeMapper.toDomain(novoResultadoMensalEntity);
    }

    @Override
    public ResultadoMensalEspecialidade salvarDadosDoDia(ResultadoDiarioEspecialidade resultadoDiarioEspecialidade, Long especialidadeId, String data) {
        ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity = ResultadoDiarioEspecialidadeMapper.toEntity(resultadoDiarioEspecialidade);

        this.dia = resultadoDiarioEspecialidade.dia();
        this.mes = ConverterData.toMes(data);
        this.ano = ConverterData.toAno(data);

        ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity = buscarMesAnoEspecialidade(data, especialidadeId);
        resultadoDiarioEspecialidadeEntity.setResultadoMensal(resultadoMensalEspecialidadeEntity);

        resultadoMensalEspecialidadeEntity.getResultadosDiarios().add(resultadoDiarioEspecialidadeEntity);
        resultadoMensalEspecialidadeEntity.setAtendimentos(somarAtendimentos(resultadoMensalEspecialidadeEntity));
        ResultadoMensalEspecialidadeEntity resultado = resultadoMensalEspecialidadeRepositoryJpa.save(resultadoMensalEspecialidadeEntity);

        return ResultadoMensalEspecialidadeMapper.toDomain(resultado);
    }

    private int somarAtendimentos(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity) {
        int atendimentos = 0;

        for (ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity : resultadoMensalEspecialidadeEntity.getResultadosDiarios()) {
            atendimentos += resultadoDiarioEspecialidadeEntity.getAtendimentos();
        }

        return atendimentos;
    }

    private ResultadoDiarioEspecialidadeEntity buscarDiaEspecialidade(String data, Long resultadoMensalId) {
        ResultadoDiarioEspecialidadeEntity resultado = null;

        instanciaVariaveis(data);

        Optional<EspecialidadeEntity> especialidadeEntity = especialidadeRepositoryJpa.findById(resultadoMensalId);

        if (especialidadeEntity.isPresent()) {
            resultado = percorrerResultadosMensal(especialidadeEntity.get());
        }

        return resultado;
    }

    private void instanciaVariaveis(String data) {
        this.dia = ConverterData.toDia(data);
        this.mes = ConverterData.toMes(data);
        this.ano = ConverterData.toAno(data);
        this.data = data;
    }

    private ResultadoDiarioEspecialidadeEntity percorrerResultadosMensal(EspecialidadeEntity especialidadeEntity) {
        ResultadoDiarioEspecialidadeEntity resultado = null;

        for(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity : especialidadeEntity.getResultadosMensais()) {
            if (mesmoMesEAno(resultadoMensalEspecialidadeEntity)) {
                resultado = percorrerResultadoDiario(resultadoMensalEspecialidadeEntity);
            }
        }

        return resultado;
    }

    private boolean mesmoMesEAno(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity) {
        return resultadoMensalEspecialidadeEntity.getMes() == mes && resultadoMensalEspecialidadeEntity.getAno() == ano;
    }

    private ResultadoDiarioEspecialidadeEntity percorrerResultadoDiario(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity) {
        ResultadoDiarioEspecialidadeEntity resultado = null;

        for (ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity : resultadoMensalEspecialidadeEntity.getResultadosDiarios()) {
            if (mesmoDia(resultadoDiarioEspecialidadeEntity)) {
                resultado = resultadoDiarioEspecialidadeEntity;
            }
        }

        return resultado;
    }

    private boolean mesmoDia(ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity) {
        return resultadoDiarioEspecialidadeEntity.getDia() == dia;
    }

    private ResultadoMensalEspecialidadeEntity buscarMesAnoEspecialidade(String data, Long especialidadeId) {
        ResultadoMensalEspecialidadeEntity resultado = null;

        instanciaVariaveis(data);

        Optional<EspecialidadeEntity> especialidadeEntity = especialidadeRepositoryJpa.findById(especialidadeId);

        if (especialidadeEntity.isPresent()) {
            for(ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity : especialidadeEntity.get().getResultadosMensais()) {
                if (resultadoMensalEspecialidadeEntity.getMes() == mes && resultadoMensalEspecialidadeEntity.getAno() == ano) {
                    resultado = resultadoMensalEspecialidadeEntity;
                }
            }
        }

        return resultado;
    }

    @Override
    public boolean existeMesAnoEspecialidade(String data, Long especialidadeId) {
        return buscarMesAnoEspecialidade(data, especialidadeId) != null;
    }

    @Override
    public boolean existeDiaEspecialidade(String data, Long resultadoMensalId) {
        return buscarDiaEspecialidade(data, resultadoMensalId) != null;
    }

    @Override
    public ResultadoDiarioEspecialidade atualizarDadosDoDia(String data, LinhaTabelaRequest linhaTabelaRequest) {
        ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity = buscarDiaEspecialidade(data, linhaTabelaRequest.componenteId());
        resultadoDiarioEspecialidadeEntity.setAtendimentos(linhaTabelaRequest.pacientesAtendidos());

        resultadoDiarioEspecialidadeEntity = resultadoDiarioEspecialidadeRepositoryJpa.save(resultadoDiarioEspecialidadeEntity);

        ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity = resultadoDiarioEspecialidadeEntity.getResultadoMensal();
        resultadoMensalEspecialidadeEntity.setAtendimentos(somarAtendimentos(resultadoMensalEspecialidadeEntity));
        resultadoMensalEspecialidadeRepositoryJpa.save(resultadoMensalEspecialidadeEntity);

        return ResultadoDiarioEspecialidadeMapper.toDomain(resultadoDiarioEspecialidadeEntity);
    }

    @Override
    public List<TabelaEspecialidadesResponse> buscarDadosEspecialidades(String data) {
        List<TabelaEspecialidadesResponse> tabelaEspecialidadesResponses = new ArrayList<>();

        instanciaVariaveis(data);

        List<ResultadoMensalEspecialidadeEntity> listaResultadoMensal = resultadoMensalEspecialidadeRepositoryJpa.findByMesAndAno(mes, ano);
        List<ResultadoDiarioEspecialidadeEntity> listaResultadoDiario = buscarResultadoDiarioEspecialidade(listaResultadoMensal);

        montarTabelaEspecialidades(tabelaEspecialidadesResponses, listaResultadoDiario, listaResultadoMensal);

        return tabelaEspecialidadesResponses;
    }

    private List<ResultadoDiarioEspecialidadeEntity> buscarResultadoDiarioEspecialidade( List<ResultadoMensalEspecialidadeEntity> listaResultadoMensal) {
        List<ResultadoDiarioEspecialidadeEntity> listaResultadoDiario = new ArrayList<>();

        for (ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity : listaResultadoMensal) {
            for (ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity : resultadoMensalEspecialidadeEntity.getResultadosDiarios()) {
                if (resultadoDiarioEspecialidadeEntity.getDia() == dia) {
                    listaResultadoDiario.add(resultadoDiarioEspecialidadeEntity);
                }
            }
        }

        return listaResultadoDiario;
    }

    private void montarTabelaEspecialidades(List<TabelaEspecialidadesResponse> tabelaEspecialidadesResponses, List<ResultadoDiarioEspecialidadeEntity> listaResultadoDiario, List<ResultadoMensalEspecialidadeEntity> listaResultadoMensal) {
        OrdemTabela ordemTabela = tabelaRepository.buscarOrdemTabela(data);
        List<LinhaTabela> ordemTabelaEspecialidade = separarOrdemTabelaEspecialidade(ordemTabela);

        for (LinhaTabela linha : ordemTabelaEspecialidade) {
            TabelaEspecialidadesResponse tabelaEspecialidadesResponse = null;

            ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity = buscarResultadoDiarioEspecialidade(listaResultadoDiario, linha);
            ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity = buscarResultadoMensalEspecialidade(listaResultadoMensal, linha);

            if (resultadoDiarioEspecialidadeEntity != null){
                tabelaEspecialidadesResponse = new TabelaEspecialidadesResponse(
                        linha.posicao(),
                        resultadoDiarioEspecialidadeEntity.getResultadoMensal().getEspecialidade().getId(),
                        resultadoDiarioEspecialidadeEntity.getResultadoMensal().getEspecialidade().getEspecialidade(),
                        resultadoDiarioEspecialidadeEntity.getAtendimentos(),
                        resultadoDiarioEspecialidadeEntity.getResultadoMensal().getMetaDiaria(),
                        resultadoDiarioEspecialidadeEntity.getResultadoMensal().getAtendimentos(),
                        resultadoDiarioEspecialidadeEntity.getResultadoMensal().getMetaMensal()
                );
            } else if (resultadoMensalEspecialidadeEntity == null) {
                tabelaEspecialidadesResponse = new TabelaEspecialidadesResponse(
                        linha.posicao(),
                        linha.componenteId(),
                        especialidadeRepository.buscarEspecialidade(linha.componenteId()).get().especialidade(),
                        0,
                        0,
                        0,
                        0
                );
            } else {
                tabelaEspecialidadesResponse = new TabelaEspecialidadesResponse(
                        linha.posicao(),
                        linha.componenteId(),
                        resultadoMensalEspecialidadeEntity.getEspecialidade().getEspecialidade(),
                        0,
                        resultadoMensalEspecialidadeEntity.getMetaDiaria(),
                        resultadoMensalEspecialidadeEntity.getAtendimentos(),
                        resultadoMensalEspecialidadeEntity.getMetaMensal()
                );
            }

            tabelaEspecialidadesResponses.add(tabelaEspecialidadesResponse);
        }

    }

    private List<LinhaTabela> separarOrdemTabelaEspecialidade(OrdemTabela request) {
        List<LinhaTabela> response = new ArrayList<>();

        for (LinhaTabela linha : request.linhasTabela()) {
            if (linha.tipo().equals(TipoLinha.ESPECIALIDADE_LINHA)) {
                response.add(linha);
            }
        }

        return response;
    }

    private ResultadoDiarioEspecialidadeEntity buscarResultadoDiarioEspecialidade(List<ResultadoDiarioEspecialidadeEntity> listaResultadoDiario, LinhaTabela linha) {
        ResultadoDiarioEspecialidadeEntity resultado = null;

        for (ResultadoDiarioEspecialidadeEntity resultadoDiarioEspecialidadeEntity : listaResultadoDiario) {
           if (Objects.equals(linha.componenteId(), resultadoDiarioEspecialidadeEntity.getResultadoMensal().getEspecialidade().getId())) {
               resultado = resultadoDiarioEspecialidadeEntity;
           }
        }

        return resultado;
    }

    private ResultadoMensalEspecialidadeEntity buscarResultadoMensalEspecialidade(List<ResultadoMensalEspecialidadeEntity> listaResultadoMensal, LinhaTabela linha) {
        ResultadoMensalEspecialidadeEntity resultado = null;

        for (ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidadeEntity : listaResultadoMensal) {
            if (Objects.equals(linha.componenteId(), resultadoMensalEspecialidadeEntity.getEspecialidade().getId())) {
                resultado = resultadoMensalEspecialidadeEntity;
            }
        }

        return resultado;
    }

}
