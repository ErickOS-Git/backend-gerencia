package io.github.erick.backendCllientes.model.entity;




import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column
    private String rg;

    @Column
    private String sexo;

    @Column(name = "data_nascimento")
    @NotNull(message = "{campo.dataNascimento.obrigatorio}")
    private LocalDate dataNascimento;

    @Column(updatable = false, name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column
    private String telefone;

    @Column
    private String celular;

    @Column
    private String email;

    @Column
    private String observacao;

    @Column
    private String cep1;

    @Column
    private String lagradouro1;

    @Column
    private String bairro1;

    @Column
    private String complemento1;

    @Column
    private String numero1;

    @Column
    private String cep2;

    @Column
    private String lagradouro2;

    @Column
    private String bairro2;

    @Column
    private String complemento2;

    @Column
    private String numero2;



    @PrePersist
    public void prePersist(){
    setDataCadastro(LocalDate.now());
    }
}