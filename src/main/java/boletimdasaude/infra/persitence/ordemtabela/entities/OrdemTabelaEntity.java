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

    @Column(name = "DATA")
    private String data;

    @Column(name = "ATIVO")
    private boolean ativo;

    @OneToMany(mappedBy = "ordemTabela", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinhaTabelaEntity> linhasTabelaEntity;

    @OneToMany(mappedBy = "ordemTabela", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CabecalhoTabelaEntity> cabecalhosTabelaEntity;

    public OrdemTabelaEntity(Long id, String data, boolean ativo, List<LinhaTabelaEntity> linhasTabelaEntity, List<CabecalhoTabelaEntity> cabecalhosTabelaEntity) {
        this.id = id;
        this.data = data;
        this.ativo = ativo;
        this.linhasTabelaEntity = linhasTabelaEntity;
        this.cabecalhosTabelaEntity = cabecalhosTabelaEntity;
    }

    public OrdemTabelaEntity(String data, boolean ativo, List<LinhaTabelaEntity> linhasTabelaEntity, List<CabecalhoTabelaEntity> cabecalhosTabelaEntity) {
        this.data = data;
        this.ativo = ativo;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
