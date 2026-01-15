package br.com.banhoetosa.gihpet.entity;

import br.com.banhoetosa.gihpet.enums.PorteAnimal;
import br.com.banhoetosa.gihpet.enums.RacaDoAnimal;
import br.com.banhoetosa.gihpet.enums.TipoAnimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_pet")
@EntityListeners(AuditingEntityListener.class)
public class Pet {

    // ========== Identificação ==========
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ========== Dados do animal ==========
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

    // ========== Relacionamentos ==========
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<DataAgendamento> agendamentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // ========== Auditoria ==========
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // ========== Construtores ==========
    public Pet() {}

    public Pet(Long id, String nomePet, TipoAnimal tipo, RacaDoAnimal raca, String cor,
               PorteAnimal porte, Double peso, Boolean possuiAlergia, String observacoes,
               LocalDateTime dataCadastro, LocalDateTime dataAtualizacao,
               Cliente cliente, List<DataAgendamento> agendamentos) {
        this.id = id;
        this.nomePet = nomePet;
        this.tipo = tipo;
        this.raca = raca;
        this.cor = cor;
        this.porte = porte;
        this.peso = peso;
        this.possuiAlergia = possuiAlergia;
        this.observacoes = observacoes;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.cliente = cliente;
        this.agendamentos = agendamentos;
    }

    // ========== Getters e Setters ==========
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    // ========== Utilitários ==========
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
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}