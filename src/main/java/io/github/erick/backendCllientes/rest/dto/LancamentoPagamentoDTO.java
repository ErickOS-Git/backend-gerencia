package io.github.erick.backendCllientes.rest.dto;

import io.github.erick.backendCllientes.model.entity.Lancamento;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoPagamentoDTO {

    private Integer formaPagamento;
    private BigDecimal valorPago;
    private Integer taxa;

}
