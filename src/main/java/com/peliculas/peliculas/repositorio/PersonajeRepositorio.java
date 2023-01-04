package com.peliculas.peliculas.repositorio;

import com.peliculas.peliculas.entidad.Personaje;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Long> {

    @Query("SELECT p FROM Personaje p WHERE eliminado = 'FALSE'")
    public List<Personaje> findAllCustom();

    public Optional<Personaje> findById(Long id);

    @Query("SELECT p FROM Personaje p WHERE nombre = :nombre ")
    List<Personaje> findByName(@Param("nombre") String nombre);

    @Query("SELECT p FROM Personaje p WHERE edad = :edad ")
    List<Personaje> findByAge(@Param("edad") Double edad);

    @Query("SELECT p FROM Personaje p WHERE pelicula_id = :id ")
    List<Personaje> findByMoviesId(@Param("id") Long id);
    
      @Query("SELECT p FROM Personaje p WHERE p.id = :id ")
    List<Personaje> findByUnPersonaje(@Param("id") Long id);
    
    
    @Query("SELECT g FROM Genero g WHERE g.id =:id ")
    public  Optional<Personaje> unPersonajeId(@Param("id") Long id);

}
