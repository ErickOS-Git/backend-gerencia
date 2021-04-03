package io.github.erick.backendCllientes.rest.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.erick.backendCllientes.model.entity.Lancamento;
import io.github.erick.backendCllientes.model.entity.Produto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoLanProdutoDTO {

    private Integer id;


    private Integer produto;

    private String nomeProduto;


    private BigDecimal valorVenda;


    private BigDecimal desconto;


    private Integer quantidade;


    private BigDecimal totalProduto;

    private List<InfoLancamentoAdicionalDTO> adicionais;
}
