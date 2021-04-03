package io.github.erick.backendCllientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InforProduto {

    private Integer id;
    private String nomeProduto;
    private BigDecimal valorCompra;
    private BigDecimal valorVenda;
    private InfoCatProdDTO categoriaProduto;
}