package boletimdasaude.infra.persitence.ordemtabela.entities;

import boletimdasaude.domain.enums.TipoLinha;
import jakarta.persistence.*;

@Entity
@Table(name = "LINHA_TABELA")
public class LinhaTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LINHA_TABELA")
    private Long id;

    @Column(name = "POSICAO")
    private Long posicao;

    @Column(name = "TIPO_LINHA")
    @Enumerated(EnumType.STRING)
    private TipoLinha tipo;

    @Column(name = "COMPONENTE_ID")
    private Long componenteId ;

    @ManyToOne
    @JoinColumn(name="ID_ORDEM_TABELA", nullable = true)
    private OrdemTabelaEntity ordemTabela;

    public LinhaTabelaEntity(Long id, Long posicao, TipoLinha tipo, Long componenteId) {
        this.id = id;
        this.posicao = posicao;
        this.tipo = tipo;
        this.componenteId = componenteId;
    }

    public LinhaTabelaEntity() {}

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

    public Long getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(Long ComponenteId) {
        this.componenteId = ComponenteId;
    }

    public OrdemTabelaEntity getOrdemTabela() {
        return ordemTabela;
    }

    public void setOrdemTabela(OrdemTabelaEntity ordemTabela) {
        this.ordemTabela = ordemTabela;
    }
}
