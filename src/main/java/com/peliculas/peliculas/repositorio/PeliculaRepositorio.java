package com.peliculas.peliculas.repositorio;

import com.peliculas.peliculas.entidad.Pelicula;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long> {

    @Query("SELECT p FROM Pelicula p WHERE p.eliminado = 'FALSE' ")
    public List<Pelicula> findAllCustom();

    public Optional<Pelicula> findById(Long id);


}
