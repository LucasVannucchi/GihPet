package br.com.banhoetosa.gihpet.servicos.domain.entity;

import br.com.banhoetosa.gihpet.servicos.domain.enums.pagamento.StatusPagamento;
import br.com.banhoetosa.gihpet.servicos.domain.enums.pagamento.TipoPagamento;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.HorarioTaxiDog;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.TipoServico;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import br.com.banhoetosa.gihpet.usuarios.domain.models.Cliente;
import br.com.banhoetosa.gihpet.usuarios.domain.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_servico")
@EntityListeners(AuditingEntityListener.class)
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false, length = 30)
    private TipoServico tipoServico;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false, length = 30)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false, length = 30)
    private StatusPagamento statusPagamento;

    @Column(name = "taxi_dog", nullable = false)
    private Boolean taxiDog;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario_taxi_dog", length = 30)
    private HorarioTaxiDog horarioTaxiDog;

    @Column(name = "pacote", nullable = false)
    private Boolean pacote;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_servico", nullable = false, length = 30)
    private StatusServico statusServico;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_data_agendamento", nullable = false)
    private DataAgendamento dataAgendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    public Servico() {}

    public Servico(TipoServico tipoServico, BigDecimal preco, TipoPagamento tipoPagamento,
                   StatusPagamento statusPagamento, Boolean taxiDog, HorarioTaxiDog horarioTaxiDog,
                   Boolean pacote, StatusServico statusServico, String observacoes,
                   DataAgendamento dataAgendamento, Cliente cliente, Pet pet, Usuario usuario) {
        this.tipoServico = tipoServico;
        this.preco = preco;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
        this.taxiDog = taxiDog;
        this.horarioTaxiDog = horarioTaxiDog;
        this.pacote = pacote;
        this.statusServico = statusServico;
        this.observacoes = observacoes;
        this.dataAgendamento = dataAgendamento;
        this.cliente = cliente;
        this.pet = pet;
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Boolean getTaxiDog() {
        return taxiDog;
    }

    public void setTaxiDog(Boolean taxiDog) {
        this.taxiDog = taxiDog;
    }

    public HorarioTaxiDog getHorarioTaxiDog() {
        return horarioTaxiDog;
    }

    public void setHorarioTaxiDog(HorarioTaxiDog horarioTaxiDog) {
        this.horarioTaxiDog = horarioTaxiDog;
    }

    public Boolean getPacote() {
        return pacote;
    }

    public void setPacote(Boolean pacote) {
        this.pacote = pacote;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public DataAgendamento getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(DataAgendamento dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", tipoServico=" + tipoServico +
                ", preco=" + preco +
                ", tipoPagamento=" + tipoPagamento +
                ", statusPagamento=" + statusPagamento +
                ", taxiDog=" + taxiDog +
                ", horarioTaxiDog=" + horarioTaxiDog +
                ", pacote=" + pacote +
                ", statusServico=" + statusServico +
                ", observacoes='" + observacoes + '\'' +
                ", dataAgendamento=" + dataAgendamento +
                ", cliente=" + cliente +
                ", pet=" + pet +
                ", usuario=" + usuario +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}