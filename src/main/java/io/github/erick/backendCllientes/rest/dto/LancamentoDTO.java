package io.github.erick.backendCllientes.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.erick.backendCllientes.model.entity.LancamentoPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;

    private Integer cliente;

    private List<LancamentoProdutosDTO> produtos;

    private List<LancamentoPagamentoDTO> tipoPagamentos;

    private String cep;

    private String bairro;

    private String lagradouro;

    private String complemento;

    private String numero;

    private String telefone;

    private String celular;

    private BigDecimal total;
}
