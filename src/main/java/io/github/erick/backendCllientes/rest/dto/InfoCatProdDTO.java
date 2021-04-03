package io.github.erick.backendCllientes.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoCatProdDTO {
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    private String nomeCategoriaProduto;

    private List<InfoCatAdicionalDTO> adicionais;

}
