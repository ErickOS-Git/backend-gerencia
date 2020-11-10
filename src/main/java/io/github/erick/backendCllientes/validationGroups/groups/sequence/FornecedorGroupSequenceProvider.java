package io.github.erick.backendCllientes.validationGroups.groups.sequence;

import io.github.erick.backendCllientes.model.entity.Fornecedor;
import io.github.erick.backendCllientes.validationGroups.groups.PessoaFisica;
import io.github.erick.backendCllientes.validationGroups.groups.PessoaJuridica;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class FornecedorGroupSequenceProvider implements DefaultGroupSequenceProvider<Fornecedor> {



    public List<Class<?>> getValidationGroups(Fornecedor fornecedor) {
        List<Class<?>> groups = new ArrayList<>();

        groups.add(Fornecedor.class);
        if (fornecedor != null){
            if ("PF".equalsIgnoreCase(fornecedor.getTipo())){
                groups.add(PessoaFisica.class);
            }else if ("PJ".equalsIgnoreCase(fornecedor.getTipo())){
                groups.add(PessoaJuridica.class);
            }
        }
        return  groups;
    }
}
