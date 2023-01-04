package com.peliculas.peliculas.repositorio;

import com.peliculas.peliculas.entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Long> {

    @Override
    public Optional<Imagen> findById(Long id);

}