package io.github.erick.backendCllientes.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesLancamentoDTO {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;

    private String nomeCliente;

    private String cpf;

    private String cep;

    private String email;

    private String bairro;

    private String lagradouro;

    private String complemento;

    private String numero;

    private String telefone;

    private String celular;

    private BigDecimal total;

    private List<InfoLanProdutosDTO> produtos;

}