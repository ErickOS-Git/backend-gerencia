package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.entity.Empresa;
import io.github.erick.backendCllientes.model.repository.EmpresaRepository;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaService {

    private final EmpresaRepository repository;
    private ReplaceString replaceString;

    public Empresa salvar(Empresa empresa) {
        empresa.setCnpj(replaceString.repalceCnpj(empresa.getCnpj()));
        empresa.setTelefone(replaceString.replaceTelefone(empresa.getTelefone()));
        empresa.setCelular(replaceString.replaceCelular(empresa.getCelular()));

        try {
            return repository.save(empresa);
        } catch (Exception e) {
            throw new GenericException("Não foi possivel salvar", e.toString());
        }
    }

    public void  atualizar(Integer id, Empresa empresaAtualizada){
        repository.findById(id).map(empresa -> {
            if (empresa.getId().equals(id)){
                empresaAtualizada.setCnpj(replaceString.repalceCnpj(empresaAtualizada.getCnpj()));
                empresaAtualizada.setTelefone(replaceString.repalceCnpj(empresaAtualizada.getTelefone()));
                empresaAtualizada.setCelular(replaceString.repalceCnpj(empresaAtualizada.getCelular()));
                repository.save(empresaAtualizada);
                return Void.TYPE;
            }
            return Void.TYPE;
        }).orElseThrow(()-> new GenericException("Ocorreu um erro ao atualizar!", ""));
    }

    public byte[] logotipo(Integer id, Part foto){
        Optional<Empresa> empresa = repository.findById(id);
        return empresa.map(empresa1 -> {
            try {
                InputStream is = foto.getInputStream();
                byte[] bytes = new byte[(int) foto.getSize()];
                IOUtils.readFully(is, bytes);
                empresa1.setLogoTipo(bytes);
                repository.save(empresa1);
                is.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElseThrow(null);

    }

    public Optional<Empresa> carregar(){
        try {
          return repository.findById(1);
        } catch (Exception e) {
            throw new GenericException("Sem Empresa", e.toString());
        }
    }

    public void apagarLogo(Integer id, Empresa empresa){
        try {
           empresa.setLogoTipo(null);
           repository.save(empresa);
        } catch (Exception e){
            throw new GenericException("Não existe logo", e.toString());
        }
    }
}