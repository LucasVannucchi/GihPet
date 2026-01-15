package br.com.banhoetosa.gihpet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    // ========== Identificação ==========
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // ========== Dados pessoais ==========
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(name = "rg", length = 11, unique = true, nullable = false)
    private String rg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    // ========== Contato ==========
    @Column(name = "telefone", length = 25, unique = true)
    private String telefone;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    // ========== Relacionamentos ==========
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pet> pets;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // ========== Auditoria ==========
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // ========== Construtores ==========
    public Cliente() {}

    public Cliente(String nome, String cpf, String rg, LocalDate dataNascimento,
                   String telefone, String email, List<Endereco> enderecos,
                   String nacionalidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.enderecos = enderecos;
        this.nacionalidade = nacionalidade;
    }

    // ========== Getters e Setters ==========
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Pet> getPets() { return pets; }
    public void setPets(List<Pet> pets) { this.pets = pets; }

    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    // ========== Utilitários ==========
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}