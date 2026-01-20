package br.com.banhoetosa.gihpet.pets.domain.entity;

import br.com.banhoetosa.gihpet.services.domain.entity.DataAgendamento;
import br.com.banhoetosa.gihpet.services.domain.entity.Servico;
import br.com.banhoetosa.gihpet.pets.domain.enums.pet.PorteAnimal;
import br.com.banhoetosa.gihpet.pets.domain.enums.pet.RacaDoAnimal;
import br.com.banhoetosa.gihpet.pets.domain.enums.pet.TipoAnimal;
import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pet")
@EntityListeners(AuditingEntityListener.class)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_pet", nullable = false, length = 100)
    private String nomePet;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal", nullable = false, length = 50)
    private TipoAnimal tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "raca_animal", nullable = false, length = 50)
    private RacaDoAnimal raca;

    @Column(name = "cor", length = 50)
    private String cor;

    @Enumerated(EnumType.STRING)
    @Column(name = "porte_animal", length = 30)
    private PorteAnimal porte;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "possui_alergia")
    private Boolean possuiAlergia;

    @Column(name = "observacoes", length = 255)
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<DataAgendamento> agendamentos;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<Servico> servicos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    public Pet() {}

    public Pet(String nomePet, TipoAnimal tipo, RacaDoAnimal raca, String cor,
               PorteAnimal porte, Double peso, Boolean possuiAlergia, String observacoes,
               Cliente cliente) {
        this.nomePet = nomePet;
        this.tipo = tipo;
        this.raca = raca;
        this.cor = cor;
        this.porte = porte;
        this.peso = peso;
        this.possuiAlergia = possuiAlergia;
        this.observacoes = observacoes;
        this.cliente = cliente;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public TipoAnimal getTipo() { return tipo; }
    public void setTipo(TipoAnimal tipo) { this.tipo = tipo; }
    public RacaDoAnimal getRaca() { return raca; }
    public void setRaca(RacaDoAnimal raca) { this.raca = raca; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public PorteAnimal getPorte() { return porte; }
    public void setPorte(PorteAnimal porte) { this.porte = porte; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Boolean getPossuiAlergia() { return possuiAlergia; }
    public void setPossuiAlergia(Boolean possuiAlergia) { this.possuiAlergia = possuiAlergia; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<DataAgendamento> getAgendamentos() { return agendamentos; }
    public void setAgendamentos(List<DataAgendamento> agendamentos) { this.agendamentos = agendamentos; }
    public List<Servico> getServicos() { return servicos; }
    public void setServicos(List<Servico> servicos) { this.servicos = servicos; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nomePet='" + nomePet + '\'' +
                ", tipo=" + tipo +
                ", raca=" + raca +
                ", cor='" + cor + '\'' +
                ", porte=" + porte +
                ", peso=" + peso +
                ", possuiAlergia=" + possuiAlergia +
                ", observacoes='" + observacoes + '\'' +
                ", cliente=" + cliente +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}