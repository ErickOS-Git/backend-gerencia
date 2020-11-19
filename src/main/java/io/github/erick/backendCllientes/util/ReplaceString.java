package io.github.erick.backendCllientes.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReplaceString {
    public BigDecimal converter( String value){
        if (value == null){
            return null;
        }
        value = value.replace(".","").replace(",",".");
        return new BigDecimal(value);
    }

    public String replaceRg(String rg){
        if (rg == null) {

            return null;
        }
        rg =  rg.replace(".","").replace("-","");
        return rg;
    }

    public String repalceCPF(String cpf){
        if(cpf == null){
            return null;
        }

        cpf = cpf.replace(".","").replace("-","");
        return cpf;
    }

    public String repalceCnpj(String cnpj){
        if(cnpj == null){
            return null;
        }

        cnpj = cnpj.replace(".","").replace("-","").replace("/","");
        return cnpj;
    }

    public String replaceCelular(String celular){
        if(celular == null){
           return null;
        }
        celular = celular.replace("(","")
                .replace(")","")
                  .replace(" ","")
                    .replace("-","");
        return celular;
    }
    public String replaceTelefone( String telefone){
        if(telefone == null){
            return null;
        }
        telefone = telefone.replace("(","")
                .replace(")","")
                .replace(" ","")
                .replace("-","");
        return telefone;
    }
}
