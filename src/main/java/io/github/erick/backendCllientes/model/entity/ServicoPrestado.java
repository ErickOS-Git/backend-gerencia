package io.github.erick.backendCllientes.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
public class ServicoPrestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String Descricao;

    @ManyToOne
    @JoinColumn ( name = "id_cliente")
    private Cliente cliente;

    @Column
    private BigDecimal valor;

    @Column(name = "data_servico")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    public ServicoPrestado() {
    }

    public ServicoPrestado(String descricao, Cliente cliente, BigDecimal valor, LocalDate data) {
        Descricao = descricao;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
