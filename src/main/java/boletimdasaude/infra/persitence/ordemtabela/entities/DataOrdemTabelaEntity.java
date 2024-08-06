package boletimdasaude.infra.persitence.ordemtabela.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DATA_TABELA")
public class DataOrdemTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LINHA_TABELA")
    private Long id;

    @Column(name = "DATA")
    private String data;

    @ManyToOne
    @JoinColumn(name="ID_ORDEM_TABELA", nullable = true)
    private OrdemTabelaEntity ordemTabela;

    public DataOrdemTabelaEntity(String data) {
        this.data = data;
    }

    public DataOrdemTabelaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public OrdemTabelaEntity getOrdemTabela() {
        return ordemTabela;
    }

    public void setOrdemTabela(OrdemTabelaEntity ordemTabela) {
        this.ordemTabela = ordemTabela;
    }
}
