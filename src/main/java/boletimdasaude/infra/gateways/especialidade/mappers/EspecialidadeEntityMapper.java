package boletimdasaude.infra.gateways.especialidade.mappers;

import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;

import java.util.List;
import java.util.Optional;
public class EspecialidadeEntityMapper {

    public static EspecialidadeEntity toEntity(Especialidade domain) {
        return new EspecialidadeEntity(
                domain.especialidade(),
                domain.medicoAtual(),
                domain.metaDiariaAtual(),
                domain.metaMensalAtual(),
                ResultadoMensalEspecialidadeMapper.toEntityList(domain.resultadosMensais())
        );
    }

    public static Especialidade toDomain(EspecialidadeEntity entity) {
        return new Especialidade(
                entity.getId(),
                entity.getEspecialidade(),
                entity.getMedicoAtual(),
                entity.getMetaDiariaAtual(),
                entity.getMetaMensalAtual(),
                ResultadoMensalEspecialidadeMapper.toDomainList(entity.getResultadosMensais())
        );
    }

    public List<Especialidade> toDomainList(List<EspecialidadeEntity> entityList) {
        return entityList.stream()
                .map(EspecialidadeEntityMapper::toDomain)
                .toList();
    }

    public static Optional<Especialidade> toDomainOptional(EspecialidadeEntity entityOptional) {
        return Optional.of(new Especialidade(
                entityOptional.getId(),
                entityOptional.getEspecialidade(),
                entityOptional.getMedicoAtual(),
                entityOptional.getMetaDiariaAtual(),
                entityOptional.getMetaMensalAtual(),
                ResultadoMensalEspecialidadeMapper.toDomainList(entityOptional.getResultadosMensais())
        ));
    }

}
