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
public class InfoLanProdutosDTO {

    private String nomeProduto;
    private BigDecimal valorVenda;
    private BigDecimal desconto;
    private Integer quantidade;

 }
