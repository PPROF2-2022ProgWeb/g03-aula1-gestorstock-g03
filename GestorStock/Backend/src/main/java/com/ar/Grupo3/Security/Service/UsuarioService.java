package com.ar.Grupo3.Security.Service;

import com.ar.Grupo3.Security.Entity.Usuario;
import com.ar.Grupo3.Security.Repository.IUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    IUsuarioRepository iusuarioRepository;

    public Optional<Usuario> getByUsername(String nombreUsuario) {
        return Optional.ofNullable(iusuarioRepository.findByUsername(nombreUsuario));
    }

    public boolean existsByUsername(String nombreUsuario) {
        return iusuarioRepository.existsByUsername(nombreUsuario);
    }

    public void save(Usuario usuario) {
        iusuarioRepository.save(usuario);
    }

}
