package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "valor_compra")
    private BigDecimal valorCompra;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    @ManyToOne
    @JoinColumn ( name = "id_categoria_produto")
    private CategoriaProduto categoriaProduto;

    @ManyToOne
    @JoinColumn ( name = "id_tipo_produto")
    private TipoProduto tipoProduto;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
