package boletimdasaude.infra.gateways.cirurgiao;

import boletimdasaude.application.gateways.cirurgiao.ICirurgiaoRepository;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.domain.cirurgiao.Cirurgiao;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.infra.gateways.cirurgiao.mappers.CirurgiaoEntityMapper;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.gateways.procedimentocirurgiao.mappers.ProcedimentoCirurgiaoEntityMapper;
import boletimdasaude.infra.persitence.cirurgiao.ICirurgiaoRepositoryJpa;
import boletimdasaude.infra.persitence.cirurgiao.entities.CirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ProcedimentoCirurgiaoEntity;
import boletimdasaude.infra.persitence.cirurgiao.entities.ResultadoMensalCirurgiaoEntity;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.ArrayList;
import java.util.List;

public class CirurgiaoRepository implements ICirurgiaoRepository {

    private final ICirurgiaoRepositoryJpa cirurgiaoRepository;
    private final CirurgiaoEntityMapper cirurgiaoEntityMapper;

    public CirurgiaoRepository(ICirurgiaoRepositoryJpa cirurgiaoRepository, CirurgiaoEntityMapper cirurgiaoEntityMapper) {
        this.cirurgiaoRepository = cirurgiaoRepository;
        this.cirurgiaoEntityMapper = cirurgiaoEntityMapper;
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
    public List<Cirurgiao> buscarTodosCirurgioesComDadosMes(String data) {
        List<Cirurgiao> resultado = new ArrayList<>();

        int mes = extrairtMes(data);
        int ano = extrairtAno(data);

        List<CirurgiaoEntity> listaCirurgioes = cirurgiaoRepository.findAll();

        for (CirurgiaoEntity cirurgiaoEntity : listaCirurgioes) {
            List<ProcedimentoCirurgiaoEntity> procedimentos = new ArrayList<>();

            for(ProcedimentoCirurgiaoEntity procedimento : cirurgiaoEntity.getProcedimentos()) {
                if (procedimento.getResultadosMensais() != null) {
                    separarResultadosMensais(mes, ano, procedimentos, procedimento);
                }
            }

            cirurgiaoEntity.setProcedimentos(procedimentos);
            resultado.add(CirurgiaoEntityMapper.toDomain(cirurgiaoEntity));
        }

        return resultado;
    }

    private int extrairtMes(String date) {
        String[] partes = date.split("-");
        int mes = Integer.parseInt(partes[0]);
        return mes;
    }

    private int extrairtAno(String date) {
        String[] partes = date.split("-");
        int ano = Integer.parseInt(partes[1]);
        return ano;
    }

    private void separarResultadosMensais(int mes, int ano, List<ProcedimentoCirurgiaoEntity> procedimentos, ProcedimentoCirurgiaoEntity procedimento) {
        for (ResultadoMensalCirurgiaoEntity resultadoMensal : procedimento.getResultadosMensais()) {
            if (resultadoMensal.getMes() == mes && resultadoMensal.getAno() == ano) {
                List<ResultadoMensalCirurgiaoEntity> listaResultadoMensal = new ArrayList<>();
                listaResultadoMensal.add(resultadoMensal);
                procedimento.setResultadosMensais(listaResultadoMensal);
                procedimentos.add(procedimento);
            }
        }
    }

    public CirurgiaoEntity buscarCirurgiaoPorId(Long id) {
        return cirurgiaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", id))
        );
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
                ProcedimentoCirurgiaoEntityMapper.toEntityList(cirurgiao.procedimentos())
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