
package com.ar.Grupo3.Security.Repository;

import com.ar.Grupo3.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByUsername(String nombreUsuario);
    
    boolean existsByUsername(String nombreUsuario);

	Optional<Usuario> findByIdUsuario(Long idUsuario);

    
}
