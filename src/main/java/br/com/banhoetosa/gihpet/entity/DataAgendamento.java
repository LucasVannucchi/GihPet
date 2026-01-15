package br.com.banhoetosa.gihpet.entity;

import br.com.banhoetosa.gihpet.enums.StatusAgendamento;
import br.com.banhoetosa.gihpet.enums.TipoServico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamento")
@EntityListeners(AuditingEntityListener.class)
public class DataAgendamento {

    // ========== Identificação ==========
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ========== Dados do agendamento ==========
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false, length = 50)
    private TipoServico tipoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_agendamento", nullable = false, length = 50)
    private StatusAgendamento status;

    @Column(name = "observacoes", length = 255)
    private String observacoes;

    // ========== Relacionamentos ==========
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // ========== Auditoria ==========
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // ========== Construtores ==========
    public DataAgendamento() {}

    public DataAgendamento(Long id, LocalDateTime dataHora, TipoServico tipoServico,
                           StatusAgendamento status, String observacoes,
                           LocalDateTime dataCriacao, LocalDateTime dataAtualizacao,
                           Pet pet, Cliente cliente) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoServico = tipoServico;
        this.status = status;
        this.observacoes = observacoes;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.pet = pet;
        this.cliente = cliente;
    }

    // ========== Getters e Setters ==========
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public TipoServico getTipoServico() { return tipoServico; }
    public void setTipoServico(TipoServico tipoServico) { this.tipoServico = tipoServico; }

    public StatusAgendamento getStatus() { return status; }
    public void setStatus(StatusAgendamento status) { this.status = status; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    // ========== Utilitários ==========
    @Override
    public String toString() {
        return "DataAgendamento{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", tipoServico=" + tipoServico +
                ", status=" + status +
                ", observacoes='" + observacoes + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}