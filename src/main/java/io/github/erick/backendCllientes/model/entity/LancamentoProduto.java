package io.github.erick.backendCllientes.model.entity;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.repository.LancamentoAdicionalRepository;
import io.github.erick.backendCllientes.model.repository.LancamentoProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Lancamento lancamento;

    @ManyToOne
    @JoinColumn
    private Produto produto;

    @Column
    private BigDecimal valorVenda;

    @Column
    private BigDecimal desconto;

    @Column
    private Integer quantidade;

    @Column
    private BigDecimal totalProduto;

    @OneToMany(mappedBy = "lancamentoProduto")
    private List<LancamentoAdicional> adicionais;

}
