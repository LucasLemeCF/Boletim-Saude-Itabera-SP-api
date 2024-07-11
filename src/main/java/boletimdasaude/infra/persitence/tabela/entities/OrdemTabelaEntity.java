package boletimdasaude.infra.persitence.tabela.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ORDEM_TABELA")
public class OrdemTabelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEM_TABELA")
    private Long id;

    @OneToMany(mappedBy = "especialidadeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTabelaEspecialidadeEntity> itemTabelaEspecialidade;

    @OneToMany(mappedBy = "procedimentoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTabelaCirurgiaoEntity> itemTabelaCirurgiao;

    public OrdemTabelaEntity(Long id, List<ItemTabelaEspecialidadeEntity> itemTabelaEspecialidade, List<ItemTabelaCirurgiaoEntity> itemTabelaCirurgiao) {
        this.id = id;
        this.itemTabelaEspecialidade = itemTabelaEspecialidade;
        this.itemTabelaCirurgiao = itemTabelaCirurgiao;
    }

    public OrdemTabelaEntity(List<ItemTabelaEspecialidadeEntity> itemTabelaEspecialidade, List<ItemTabelaCirurgiaoEntity> itemTabelaCirurgiao) {
        this.itemTabelaEspecialidade = itemTabelaEspecialidade;
        this.itemTabelaCirurgiao = itemTabelaCirurgiao;
    }

    public OrdemTabelaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemTabelaEspecialidadeEntity> getItemTabelaEspecialidade() {
        return itemTabelaEspecialidade;
    }

    public void setItemTabelaEspecialidade(List<ItemTabelaEspecialidadeEntity> itemTabelaEspecialidade) {
        this.itemTabelaEspecialidade = itemTabelaEspecialidade;
    }

    public List<ItemTabelaCirurgiaoEntity> getItemTabelaCirurgiao() {
        return itemTabelaCirurgiao;
    }

    public void setItemTabelaCirurgiao(List<ItemTabelaCirurgiaoEntity> itemTabelaCirurgiao) {
        this.itemTabelaCirurgiao = itemTabelaCirurgiao;
    }

}
