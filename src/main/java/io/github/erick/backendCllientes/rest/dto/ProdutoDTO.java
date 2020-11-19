package io.github.erick.backendCllientes.rest.dto;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.model.entity.TipoProduto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String nomeProduto;

    @NotNull(message = "{campo.categoria.obrigatorio}")
    private CategoriaProduto categoriaProduto;

    @NotNull(message = "{campo.tipo.obrigatorio}")
    private TipoProduto tipoProduto;

    @NotNull(message = "{campo.valor.obrigatorio}")
    private BigDecimal valorVenda;

    private BigDecimal valorCompra;

}
