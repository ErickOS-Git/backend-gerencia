package io.github.erick.backendCllientes.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaProdutoDTO{

    private String nomeCategoriaProduto;
    private List<CategoriaProdutoAdicionalDTO> adicionais;
}
