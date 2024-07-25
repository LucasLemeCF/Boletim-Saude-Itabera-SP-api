package boletimdasaude.infra.gateways.ordemtabela;


import boletimdasaude.application.gateways.ordemtabela.ILinhaTabelaEspecialidadeRepository;
import boletimdasaude.application.gateways.ordemtabela.IOrdemTabelaRepository;
import boletimdasaude.domain.enums.TipoLinha;
import boletimdasaude.domain.ordemtabela.LinhaTabela;
import boletimdasaude.domain.ordemtabela.OrdemTabela;
import boletimdasaude.infra.gateways.ordemtabela.mappers.OrdemTabelaEntityMapper;
import boletimdasaude.infra.persitence.ordemtabela.ICabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ILinhaTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.IOrdemTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.ITextoCabecalhoTabelaRepositoryJpa;
import boletimdasaude.infra.persitence.ordemtabela.entities.CabecalhoTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.LinhaTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.OrdemTabelaEntity;
import boletimdasaude.infra.persitence.ordemtabela.entities.TextoCabecalhoTabelaEntity;

import java.util.List;

public class LinhaTabelaRepository implements ILinhaTabelaEspecialidadeRepository {

    private final ILinhaTabelaRepositoryJpa linhaTabelaRepository;

    public LinhaTabelaRepository(ILinhaTabelaRepositoryJpa itemEspecialidadeRepository) {
        this.linhaTabelaRepository = itemEspecialidadeRepository;
    }

    @Override
    public Long buscarPosicaoEspecialidade(Long especialidadeId) {
        List<LinhaTabelaEntity> linhasTabelaEntities = linhaTabelaRepository.findAll();

        for (LinhaTabelaEntity linhaTabelaEntity : linhasTabelaEntities) {
            if (linhaTabelaEntity.getComponenteId().equals(especialidadeId) && linhaTabelaEntity.getTipo().equals(TipoLinha.ESPECIALIDADE_LINHA)) {
                return linhaTabelaEntity.getPosicao();
            }
        }

        return null;
    }

    @Override
    public Long buscarPosicaoProcedimento(Long procedimentoId) {
        List<LinhaTabelaEntity> linhasTabelaEntities = linhaTabelaRepository.findAll();

        for (LinhaTabelaEntity linhaTabelaEntity : linhasTabelaEntities) {
            if (linhaTabelaEntity.getComponenteId().equals(procedimentoId) && linhaTabelaEntity.getTipo().equals(TipoLinha.CIRURGIAO_LINHA)) {
                return linhaTabelaEntity.getPosicao();
            }
        }

        return null;
    }
}
