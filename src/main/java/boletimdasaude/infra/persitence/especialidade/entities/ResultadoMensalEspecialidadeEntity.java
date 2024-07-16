package boletimdasaude.infra.persitence.especialidade.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RESULTADOS_MENSAIS_ESPECIALIDADES")
public class ResultadoMensalEspecialidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULTADO_MENSAL_ESPECIALIDADE")
    private Long id;
    @Column(name = "DES_MES")
    private int mes;
    @Column(name = "DES_ANO")
    private int ano;
    @Column(name = "DES_ATENDIMENTOS")
    private int atendimentos;
    @Column(name = "DES_META_DIARIA")
    private int metaDiaria;
    @Column(name = "DES_META_MENSAL")
    private int metaMensal;
    @OneToMany(mappedBy = "resultadoMensalEspecialidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultadoDiarioEspecialidadeEntity> resultadosDiarios;

    @ManyToOne
    @JoinColumn(name="ID_ESPECIALIDADE", nullable = true)
    private EspecialidadeEntity especialidade;

    public ResultadoMensalEspecialidadeEntity(int mes, int ano, int atendimentos, int metaDiaria, int metaMensal, List<ResultadoDiarioEspecialidadeEntity> resultadosDiarios) {
        this.mes = mes;
        this.ano = ano;
        this.atendimentos = atendimentos;
        this.metaDiaria = metaDiaria;
        this.metaMensal = metaMensal;
        this.resultadosDiarios = resultadosDiarios;
    }

    public ResultadoMensalEspecialidadeEntity() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(int atendimentos) {
        this.atendimentos = atendimentos;
    }

    public int getMetaDiaria() {return metaDiaria;}

    public void setMetaDiaria(int metaDiaria) {this.metaDiaria = metaDiaria;}

    public int getMetaMensal() {return metaMensal; }

    public void setMetaMensal(int metaMensal) { this.metaMensal = metaMensal; }

    public List<ResultadoDiarioEspecialidadeEntity> getResultadosDiarios() {
        return resultadosDiarios;
    }

    public void setResultadosDiarios(List<ResultadoDiarioEspecialidadeEntity> resultadosDiarios) { this.resultadosDiarios = resultadosDiarios; }

    public EspecialidadeEntity getEspecialidade() { return especialidade; }

    public void setEspecialidade(EspecialidadeEntity especialidade) { this.especialidade = especialidade; }
}
