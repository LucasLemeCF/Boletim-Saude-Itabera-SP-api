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
    private int dia;
    @Column(name = "DES_ATENDIMENTOS")
    private int atendimentos;
    @ManyToOne
    @JoinColumn(name="ID_RESULTADO_MENSAL", nullable = true)
    private ResultadoMensalCirurgiaoEntity resultadoMensalCirurgiao;

    public ResultadoDiarioCirurgiaoEntity(int dia, int atendimentos) {
        this.dia = dia;
        this.atendimentos = atendimentos;
    }

    public ResultadoDiarioCirurgiaoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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
