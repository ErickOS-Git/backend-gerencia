package io.github.erick.backendCllientes.model.entity;

import io.github.erick.backendCllientes.validationGroups.groups.PessoaFisica;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @CNPJ(message = "{campo.cnpj.invalido}")
    private String cnpj;

    @Column
    private String nomeFantasia;

    @Column
    private String razaoSocial;

    @Column
    @Lob
    private byte[] logoTipo;

    @Column(name = "area_atuacao")
    private String areaAtuacao;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal")
    private String inscricaoMunicipal;

    @Column
    private String telefone;

    @Column
    private String celular;

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


}
