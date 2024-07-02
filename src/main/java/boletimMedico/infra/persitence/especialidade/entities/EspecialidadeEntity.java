package boletimMedico.infra.persitence.especialidade.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ESPECIALIDADES")
public class EspecialidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESPECIALIDADE")
    private Long id;

    @Column(name = "DES_ESPECIALIDADE", unique = true)
    private String especialidade;
    @Column(name = "DES_MEDICO_ATUAL")
    private String medicoAtual;
    @Column(name = "DES_META_DIARIA_ATUAL")
    private int metaDiariaAtual;
    @Column(name = "DES_META_MENSAL_ATUAL")
    private int metaMensalAtual;

    @OneToMany(mappedBy = "especialidade")
    private List<ResultadoMensalEspecialidadeEntity> resultadosMensais;

    public EspecialidadeEntity(String especialidade, String medicoAtual, int metaDiariaAtual, int metaMensalAtual, List<ResultadoMensalEspecialidadeEntity> resultadosMensais) {
        this.especialidade = especialidade;
        this.medicoAtual = medicoAtual;
        this.metaDiariaAtual = metaDiariaAtual;
        this.metaMensalAtual = metaMensalAtual;
        this.resultadosMensais = resultadosMensais;
    }

    public EspecialidadeEntity(Long id, String especialidade, String medicoAtual, int metaDiariaAtual, int metaMensalAtual, List<ResultadoMensalEspecialidadeEntity> resultadosMensais) {
        this.id = id;
        this.especialidade = especialidade;
        this.medicoAtual = medicoAtual;
        this.metaDiariaAtual = metaDiariaAtual;
        this.metaMensalAtual = metaMensalAtual;
        this.resultadosMensais = resultadosMensais;
    }

    public EspecialidadeEntity() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getMedicoAtual() { return medicoAtual; }

    public void setMedicoAtual(String medicoAtual) { this.medicoAtual = medicoAtual; }

    public int getMetaDiariaAtual() { return metaDiariaAtual;}

    public void setMetaDiariaAtual(int metaDiariaAtual) { this.metaDiariaAtual = metaDiariaAtual; }

    public int getMetaMensalAtual() { return metaMensalAtual; }

    public void setMetaMensalAtual(int metaMensalAtual) { this.metaMensalAtual = metaMensalAtual; }

    public List<ResultadoMensalEspecialidadeEntity> getResultadosMensais() {
        return resultadosMensais;
    }

    public void setResultadosMensais(List<ResultadoMensalEspecialidadeEntity> resultadosMensais) { this.resultadosMensais = resultadosMensais; }

}
