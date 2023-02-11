
package com.peliculas.peliculas.repositorio;

import com.peliculas.peliculas.entidad.RoleUser;
import com.peliculas.peliculas.enumeracion.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleUserRepositorio extends JpaRepository<RoleUser, Long> {
    Optional<RoleUser> findByName(Rol name);
}
