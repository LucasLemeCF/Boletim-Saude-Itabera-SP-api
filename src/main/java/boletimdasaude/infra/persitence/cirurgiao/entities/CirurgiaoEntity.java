package boletimdasaude.infra.persitence.cirurgiao.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CIRURGIOES")
public class CirurgiaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIRURGIAO")
    private Long id;
    @Column(name = "DES_NOME")
    private String nome;
    @OneToMany(mappedBy = "cirurgiao")
    private List<ProcedimentoCirurgiaoEntity> procedimentos;

    public CirurgiaoEntity(String nome, List<ProcedimentoCirurgiaoEntity> procedimentos) {
        this.nome = nome;
        this.procedimentos = procedimentos;
    }

    public CirurgiaoEntity(Long id, String nome, List<ProcedimentoCirurgiaoEntity> procedimentos) {
        this.id = id;
        this.nome = nome;
        this.procedimentos = procedimentos;
    }

    public CirurgiaoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProcedimentoCirurgiaoEntity> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<ProcedimentoCirurgiaoEntity> procedimentos) {
        this.procedimentos = procedimentos;
    }

}
