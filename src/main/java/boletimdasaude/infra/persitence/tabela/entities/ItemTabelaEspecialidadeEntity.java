package boletimdasaude.infra.persitence.tabela.entities;

import boletimdasaude.domain.enums.TipoLinha;
import jakarta.persistence.*;

@Entity
@Table(name = "ITENS_TABELA_ESPECIALIDADE")
public class ItemTabelaEspecialidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TABELA_ESPECIALIDADE")
    private Long id;

    @Column(name = "POSICAO")
    private Long posicao;

    @Column(name = "TIPO_LINHA")
    private TipoLinha tipo;

    @Column(name = "ESPECIALIDADE_ID")
    private Long especialidadeId;

    @ManyToOne
    @JoinColumn(name="ID_ORDEM_TABELA", nullable = true)
    private OrdemTabelaEntity ordemTabela;

    public ItemTabelaEspecialidadeEntity(Long id, Long posicao, TipoLinha tipo, Long especialidadeId) {
        this.id = id;
        this.posicao = posicao;
        this.tipo = tipo;
        this.especialidadeId = especialidadeId;
    }

    public ItemTabelaEspecialidadeEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
    }

    public TipoLinha getTipo() {
        return tipo;
    }

    public void setTipo(TipoLinha tipoLinha) {
        this.tipo = tipoLinha;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public OrdemTabelaEntity getOrdemTabela() {
        return ordemTabela;
    }

    public void setOrdemTabela(OrdemTabelaEntity ordemTabela) {
        this.ordemTabela = ordemTabela;
    }

}
