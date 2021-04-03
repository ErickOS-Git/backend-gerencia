package io.github.erick.backendCllientes.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "data_lancamento")
    @NotNull(message = "{campo.dataLancamento.obrigatorio}")
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column
    private String cep;

    @Column
    private String bairro;

    @Column
    private String lagradouro;

    @Column
    private String complemento;

    @Column
    private String numero;

    @Column
    private String telefone;

    @Column
    private String celular;

    @Column
    private BigDecimal total;

    @Column
    private String status;

  // @OneToMany(mappedBy = "lancamento")
  // private List<LancamentoPagamento> lancamentoPagamentos;
  //
  // @OneToMany(mappedBy = "lancamento")
  // private List<LancamentoProduto> lancamentoProdutos;


    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
        setStatus("Pedido em aberto");
    }




}
