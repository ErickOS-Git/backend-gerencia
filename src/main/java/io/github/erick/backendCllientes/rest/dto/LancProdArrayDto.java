package io.github.erick.backendCllientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancProdArrayDto {

    private Integer lancamento;
    private List<LancamentoProdutosDTO> produtos;
}
