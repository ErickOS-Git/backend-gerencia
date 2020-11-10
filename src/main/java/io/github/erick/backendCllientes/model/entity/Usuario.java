package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column(name = "senha")
    private String password;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column
    private String telefone;

    @Column
    private String celular;

    @Column
    private String email;

    @Column(nullable = false)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column
    private String sexo;

    @Column(name = "data_nascimento")
    @NotNull(message = "{campo.dataNascimento.obrigatorio}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(updatable = false, name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn ( name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column
    private String cep;

    @Column
    private String lagradouro;

    @Column
    private String bairro;

    @Column
    private String complemento;

    @Column
    private String numero;

    @Column
    private String banco;

    @Column
    private String agencia;

    @Column
    private String conta;


    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }




}