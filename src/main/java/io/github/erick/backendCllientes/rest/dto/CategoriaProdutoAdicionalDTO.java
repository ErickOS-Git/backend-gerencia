package io.github.erick.backendCllientes.rest.dto;

import io.github.erick.backendCllientes.model.entity.Adicional;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaProdutoAdicionalDTO {

    private Integer id;
    private Integer idCatProAd;
    private String nome;

}
