package boletimdasaude.infra.gateways.especialidade.mappers;

import boletimdasaude.domain.especialidade.Especialidade;
import boletimdasaude.infra.persitence.especialidade.entities.EspecialidadeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EspecialidadeEntityMapper {

    public EspecialidadeEntity toEntity(Especialidade domain) {
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
                .collect(Collectors.toList()
        );
    }

}