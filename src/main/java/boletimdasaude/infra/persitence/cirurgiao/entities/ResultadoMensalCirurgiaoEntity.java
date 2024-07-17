package boletimdasaude.infra.persitence.cirurgiao.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RESULTADOS_MENSAIS_CIRURGIOES")
public class ResultadoMensalCirurgiaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULTADO_MENSAL_CIRURGIAO")
    private Long id;
    @Column(name = "DES_MES")
    private int mes;
    @Column(name = "DES_ANO")
    private int ano;
    @Column(name = "DES_ATENDIMENTOS")
    private int atendimentos;
    @OneToMany(mappedBy = "resultadoMensalCirurgiao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultadoDiarioCirurgiaoEntity> resultadosDiarios;
    @ManyToOne
    @JoinColumn(name="ID_PROCEDIMENTO", nullable = true)
    private ProcedimentoCirurgiaoEntity procedimento;

    public ResultadoMensalCirurgiaoEntity(int mes, int ano, int atendimentos, List<ResultadoDiarioCirurgiaoEntity> resultadosDiarios) {
        this.mes = mes;
        this.ano = ano;
        this.atendimentos = atendimentos;
        this.resultadosDiarios = resultadosDiarios;
    }

    public ResultadoMensalCirurgiaoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ResultadoDiarioCirurgiaoEntity> getResultadosDiarios() {
        return resultadosDiarios;
    }

    public void setResultadosDiarios(List<ResultadoDiarioCirurgiaoEntity> resultadosDiarios) {
        this.resultadosDiarios = resultadosDiarios;
    }

    public ProcedimentoCirurgiaoEntity getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(ProcedimentoCirurgiaoEntity procedimento) {
        this.procedimento = procedimento;
    }
}
