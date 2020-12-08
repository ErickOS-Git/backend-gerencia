package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.UsuarioCadastradoException;
import io.github.erick.backendCllientes.model.entity.Usuario;
import io.github.erick.backendCllientes.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        return repository.save(usuario);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Login não encontrado"));
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public List<Usuario> listar(){
        return repository.findAll();
    }
}
