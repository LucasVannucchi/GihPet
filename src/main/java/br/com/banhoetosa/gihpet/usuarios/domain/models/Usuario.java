package br.com.banhoetosa.gihpet.usuarios.domain.models;

import br.com.banhoetosa.gihpet.usuarios.domain.enums.TipoDeAcesso;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    // ========== Identificação ==========
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ========== Credenciais ==========
    @Column(name = "login", nullable = false, unique = true, length = 100)
    private String login;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acesso", nullable = false, length = 50)
    private TipoDeAcesso roles;

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
    public Usuario() {
    }

    public Usuario(UUID id, String login, String senha, TipoDeAcesso roles,
                   LocalDateTime dataCadastro, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.roles = roles;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }

    // ========== Getters e Setters ==========
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public TipoDeAcesso getRoles() { return roles; }
    public void setRoles(TipoDeAcesso roles) { this.roles = roles; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    // ========== Utilitários ==========
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoAcesso=" + roles +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}