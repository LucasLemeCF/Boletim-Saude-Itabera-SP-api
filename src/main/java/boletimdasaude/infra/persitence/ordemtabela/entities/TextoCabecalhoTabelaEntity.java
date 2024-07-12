package boletimdasaude.infra.persitence.ordemtabela.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TEXTO_CABECALHO_TABELA")
public class TextoCabecalhoTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TEXTO_CABECALHO_TABELA")
    private Long id;

    @Column(name = "TEXTO")
    private String texto;

    @ManyToOne
    @JoinColumn(name="ID_CABECALHO_ORDEM_TABELA", nullable = true)
    private CabecalhoTabelaEntity cabecalhoOrdemTabela;

    public TextoCabecalhoTabelaEntity(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public TextoCabecalhoTabelaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public CabecalhoTabelaEntity getCabecalhoOrdemTabela() {
        return cabecalhoOrdemTabela;
    }

    public void setCabecalhoOrdemTabela(CabecalhoTabelaEntity cabecalhoOrdemTabela) {
        this.cabecalhoOrdemTabela = cabecalhoOrdemTabela;
    }
}
