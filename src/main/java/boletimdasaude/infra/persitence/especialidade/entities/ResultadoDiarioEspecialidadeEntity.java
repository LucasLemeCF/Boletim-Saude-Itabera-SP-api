package boletimdasaude.infra.persitence.especialidade.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "resultadosDiariosEspecialidades")
public class ResultadoDiarioEspecialidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULTADO_DIARIO")
    private Long id;
    @Column(name = "DES_DATA")
    private Date data;
    @Column(name = "DES_ATENDIMENTOS")
    private int atendimentos;
    @Column(name = "DES_MEDICO")
    private String medico;
    @ManyToOne
    @JoinColumn(name="ID_RESULTADO_MENSAL", nullable = true)
    private ResultadoMensalEspecialidadeEntity resultadoMensalEspecialidade;

    public ResultadoDiarioEspecialidadeEntity(Date data, int atendimentos) {
        this.data = data;
        this.atendimentos = atendimentos;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public ResultadoMensalEspecialidadeEntity getResultadoMensal() { return resultadoMensalEspecialidade; }

    public void setResultadoMensal(ResultadoMensalEspecialidadeEntity resultadoMensal) { this.resultadoMensalEspecialidade = resultadoMensal; }

    public String getMedico() { return medico; }

    public void setMedico(String medico) { this.medico = medico; }
}
