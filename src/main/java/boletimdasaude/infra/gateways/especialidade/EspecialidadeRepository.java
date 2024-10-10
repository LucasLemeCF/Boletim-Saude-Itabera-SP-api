package boletimdasaude.infra.gateways.especialidade;

import boletimdasaude.application.gateways.especialidade.IEspecialidadeRepository;
import boletimdasaude.application.gateways.tabela.ITabelaEspecialidadeRepository;
import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.config.exceptions.NotFoundException;
import boletimdasaude.infra.gateways.especialidade.mappers.ResultadoMensalEspecialidadeMapper;
import boletimdasaude.infra.persitence.especialidade.IEspecialidadeRepositoryJpa;
import boletimdasaude.infra.gateways.especialidade.mappers.EspecialidadeEntityMapper;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;
import boletimdasaude.infra.persitence.especialidade.entities.ResultadoMensalEspecialidadeEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EspecialidadeRepository implements IEspecialidadeRepository, ITabelaEspecialidadeRepository {

    private final IEspecialidadeRepositoryJpa repository;
    private final EspecialidadeEntityMapper especialidadeEntityMapper;
    private final ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper;

    public EspecialidadeRepository(IEspecialidadeRepositoryJpa repository, EspecialidadeEntityMapper especialidadeEntityMapper, ResultadoMensalEspecialidadeMapper resultadoMensalEspecialidadeMapper) {
        this.repository = repository;
        this.especialidadeEntityMapper = especialidadeEntityMapper;
        this.resultadoMensalEspecialidadeMapper = resultadoMensalEspecialidadeMapper;
    }

    @Override
    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return EspecialidadeEntityMapper.toDomain(repository.save(especialidadeEntityMapper.toEntity(especialidade)));
    }

    @Override
    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeEntityMapper.toDomainList(repository.findAll());
    }

    @Override
    public List<Especialidade> buscarTodasEspecialidadesComDadosMes(String data) {
        List<Especialidade> resultado = new ArrayList<>();

        int mes = extrairtMes(data);
        int ano = extrairtAno(data);

        List<EspecialidadeEntity> listaEspecialidade = repository.findAll();

        for (EspecialidadeEntity especialidadeEntity : listaEspecialidade) {
            if (temresultadosMensais(especialidadeEntity.getResultadosMensais())) {
                for (ResultadoMensalEspecialidadeEntity resultadoMensal : especialidadeEntity.getResultadosMensais()) {
                    if (resultadoMensal.getMes() == mes && resultadoMensal.getAno() == ano) {
                        List<ResultadoMensalEspecialidadeEntity> listaResultadoMensal = new ArrayList<>();
                        listaResultadoMensal.add(resultadoMensal);
                        especialidadeEntity.setResultadosMensais(listaResultadoMensal);
                        resultado.add(EspecialidadeEntityMapper.toDomain(especialidadeEntity));
                    }
                }
            }
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

    private boolean temresultadosMensais(List<ResultadoMensalEspecialidadeEntity> resultadosMensais) {
        return resultadosMensais != null;
    }

    @Override
    public Optional<Especialidade> buscarEspecialidade(Long especialidadeId) {
        return EspecialidadeEntityMapper.toDomainOptional(repository.findById(especialidadeId).get());
    }

    @Override
    public Especialidade editarEspecialidade(Long id, Especialidade especialidade) {
        EspecialidadeEntity oldEntity = repository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não econtrado", id))
        );

        EspecialidadeEntity newEntity = new EspecialidadeEntity(
                id,
                especialidade.especialidade() != null ? especialidade.especialidade() : oldEntity.getEspecialidade(),
                especialidade.medicoAtual() != null ? especialidade.medicoAtual() : oldEntity.getMedicoAtual(),
                especialidade.metaDiariaAtual(),
                especialidade.metaMensalAtual(),
                oldEntity.getResultadosMensais()
        );

        return EspecialidadeEntityMapper.toDomain(repository.saveAndFlush(newEntity));
    }

    @Override
    public String excluirEspecialidade(Long id) {
        return repository.findById(id)
                .map(especialidade -> {
                    repository.delete(especialidade);
                    return "Especialidade excluida com sucesso";
                })
                .orElseThrow(() -> new NotFoundException(String.format("ID %s não encontrado", id)));
    }

}