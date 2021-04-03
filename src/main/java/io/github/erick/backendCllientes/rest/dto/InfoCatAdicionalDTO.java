package io.github.erick.backendCllientes.rest.dto;

import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoCatAdicionalDTO{

    private Integer idCatProAd;
    private Integer id;

    private String nome;
}
