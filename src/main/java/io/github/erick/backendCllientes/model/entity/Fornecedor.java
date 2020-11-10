package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.erick.backendCllientes.validationGroups.groups.PessoaFisica;
import io.github.erick.backendCllientes.validationGroups.groups.PessoaJuridica;
import io.github.erick.backendCllientes.validationGroups.groups.sequence.FornecedorGroupSequenceProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.*;
import java.time.LocalDate;

@GroupSequenceProvider(value = FornecedorGroupSequenceProvider.class)
@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "razao_social", length = 150)
    private String razaoSocial;

    @Column
    @CNPJ(message = "{campo.cnpj.invalido}", groups = PessoaJuridica.class)
    private String cnpj;

    @Column
    @CPF(message = "{campo.cpf.invalido}", groups = PessoaFisica.class)
    private String cpf;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal")
    private String inscricaoMunicipal;

    @Column
    private String telefone;

    @Column
    private String celular;

    @Column(updatable = false, name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @Column
    private String endereco;

    @Column
    private String numero;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private String cep;

    @Column
    private String email;

    @Column
    private String site;

    @Column
    private Boolean checked;

    @Column
    private String observacao;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
