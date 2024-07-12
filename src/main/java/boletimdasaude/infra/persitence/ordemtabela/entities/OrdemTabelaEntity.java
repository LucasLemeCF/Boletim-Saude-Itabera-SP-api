package boletimdasaude.infra.persitence.ordemtabela.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ORDEM_TABELA")
public class OrdemTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEM_TABELA")
    private Long id;

    @OneToMany(mappedBy = "ordemTabela", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinhaTabelaEntity> linhasTabelaEntity;

    @OneToMany(mappedBy = "ordemTabela", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CabecalhoTabelaEntity> cabecalhosTabelaEntity;

    public OrdemTabelaEntity(Long id, List<LinhaTabelaEntity> linhasTabelaEntity, List<CabecalhoTabelaEntity> cabecalhosTabelaEntity) {
        this.id = id;
        this.linhasTabelaEntity = linhasTabelaEntity;
        this.cabecalhosTabelaEntity = cabecalhosTabelaEntity;
    }

    public OrdemTabelaEntity(List<LinhaTabelaEntity> linhasTabelaEntity, List<CabecalhoTabelaEntity> cabecalhosTabelaEntity) {
        this.linhasTabelaEntity = linhasTabelaEntity;
        this.cabecalhosTabelaEntity = cabecalhosTabelaEntity;
    }

    public OrdemTabelaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LinhaTabelaEntity> getLinhasTabelaEntity() {
        return linhasTabelaEntity;
    }

    public void setLinhasTabelaEntity(List<LinhaTabelaEntity> linhasTabelaEntity) {
        this.linhasTabelaEntity = linhasTabelaEntity;
    }

    public List<CabecalhoTabelaEntity> getCabecalhosTabelaEntity() {
        return cabecalhosTabelaEntity;
    }

    public void setCabecalhosTabelaEntity(List<CabecalhoTabelaEntity> cabecalhosTabelaEntity) {
        this.cabecalhosTabelaEntity = cabecalhosTabelaEntity;
    }
}
