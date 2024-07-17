package boletimdasaude.infra.persitence.cirurgiao.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RESULTADOS_DIARIOS_CIRURGIOES")
public class ResultadoDiarioCirurgiaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULTADO_DIARIO_CIRURGIAO")
    private Long id;
    @Column(name = "DES_DATA")
    private Date data;
    @Column(name = "DES_ATENDIMENTOS")
    private int atendimentos;
    @ManyToOne
    @JoinColumn(name="ID_RESULTADO_MENSAL", nullable = true)
    private ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiao;

    public ResultadoDiarioCirurgiaoEntity(Date data, int atendimentos) {
        this.data = data;
        this.atendimentos = atendimentos;
    }

    public ResultadoDiarioCirurgiaoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(int atendimentos) {
        this.atendimentos = atendimentos;
    }

    public ResultadoMensalCirurgiaoEntity getResultadoMensalCirurgiao() {
        return resultadoMensalCirurgiao;
    }

    public void setResultadoMensalCirurgiao(ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiao) {
        this.resultadoMensalCirurgiao = resultadoMensalCirurgiao;
    }
}
