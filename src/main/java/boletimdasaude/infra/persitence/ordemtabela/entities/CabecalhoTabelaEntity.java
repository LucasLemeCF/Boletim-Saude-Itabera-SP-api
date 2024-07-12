package boletimdasaude.infra.persitence.ordemtabela.entities;

import boletimdasaude.domain.enums.TipoLinha;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CABECALHO_TABELA")
public class CabecalhoTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CABECALHO_TABELA")
    private Long id;

    @Column(name = "POSICAO")
    private Long posicao;

    @Column(name = "TIPO_LINHA")
    private TipoLinha tipo;

    @OneToMany(mappedBy = "cabecalhoOrdemTabela", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TextoCabecalhoTabelaEntity> textos;

    @ManyToOne
    @JoinColumn(name="ID_ORDEM_TABELA", nullable = true)
    private OrdemTabelaEntity ordemTabela;

    public CabecalhoTabelaEntity(Long id, Long posicao, TipoLinha tipo, List<TextoCabecalhoTabelaEntity> textos) {
        this.id = id;
        this.posicao = posicao;
        this.tipo = tipo;
        this.textos = textos;
    }

    public CabecalhoTabelaEntity() {}

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

    public List<TextoCabecalhoTabelaEntity> getTextos() { return textos; }

    public void setTextos(List<TextoCabecalhoTabelaEntity> textos) { this.textos = textos; }

    public OrdemTabelaEntity getOrdemTabela() {
        return ordemTabela;
    }

    public void setOrdemTabela(OrdemTabelaEntity ordemTabela) {
        this.ordemTabela = ordemTabela;
    }
}
