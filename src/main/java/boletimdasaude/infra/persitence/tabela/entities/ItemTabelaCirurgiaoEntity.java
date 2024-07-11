package boletimdasaude.infra.persitence.tabela.entities;

import boletimdasaude.domain.enums.TipoLinha;
import jakarta.persistence.*;

@Entity
@Table(name = "ITENS_TABELA_CIRURGIAO")
public class ItemTabelaCirurgiaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TABELA_ESPECIALIDADE")
    private Long id;

    @Column(name = "POSICAO")
    private Long posicao;

    @Column(name = "TIPO_LINHA")
    private TipoLinha tipo;

    @Column(name = "PROCEDIMENTO_ID")
    private Long procedimentoId;

    @ManyToOne
    @JoinColumn(name="ID_ORDEM_TABELA", nullable = true)
    private OrdemTabelaEntity ordemTabela;

    public ItemTabelaCirurgiaoEntity(Long id, Long posicao, TipoLinha tipo, Long procedimentoId) {
        this.id = id;
        this.posicao = posicao;
        this.tipo = tipo;
        this.procedimentoId = procedimentoId;
    }

    public ItemTabelaCirurgiaoEntity() {}

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

    public Long getProcedimentoId() {
        return procedimentoId;
    }

    public void setProcedimentoId(Long cirurgiaoId) {
        this.procedimentoId = cirurgiaoId;
    }

    public OrdemTabelaEntity getOrdemTabela() {
        return ordemTabela;
    }

    public void setOrdemTabela(OrdemTabelaEntity ordemTabela) {
        this.ordemTabela = ordemTabela;
    }

}
