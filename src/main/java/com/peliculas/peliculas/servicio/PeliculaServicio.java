package com.peliculas.peliculas.servicio;

import com.google.gson.Gson;
import com.peliculas.peliculas.entidad.Genero;
import com.peliculas.peliculas.entidad.Imagen;
import com.peliculas.peliculas.entidad.Pelicula;
import com.peliculas.peliculas.entidad.Personaje;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.repositorio.GeneroRepositorio;
import com.peliculas.peliculas.repositorio.PeliculaRepositorio;
import com.peliculas.peliculas.repositorio.PersonajeRepositorio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Autowired
    private AlmacenServicio almacen;
    @Autowired(required = true)
    private ImagenServicio miserv;

    @Transactional
    public List<Pelicula> findAllCustom() {
        return peliculaRepositorio.findAllCustom();
    }

    @Transactional
    public List<Pelicula> listar() {
        return peliculaRepositorio.findAll();
    }

    @Transactional
    public Optional<Pelicula> findById(Long id) {
        return peliculaRepositorio.findById(id);
    }

    @Transactional
    public Pelicula agregar(String miPelicula, MultipartFile foto) throws MiException, Exception {
        Imagen miImagen = miserv.guardar(foto);
        Pelicula data= new Pelicula();
        Gson gson = new Gson();
        System.out.println(miPelicula);
        data = gson.fromJson(miPelicula, Pelicula.class);
        data.setFoto(miImagen);
        return peliculaRepositorio.save(data);
    }

    @Transactional
    public Pelicula actualizar(Pelicula miPelicula) {
        Pelicula miObjPeli = peliculaRepositorio.getById(miPelicula.getId());
        BeanUtils.copyProperties(miPelicula, miObjPeli);
        return peliculaRepositorio.save(miObjPeli);
    }

    @Transactional
    public Pelicula eliminar(Long id) throws MiException {
        Optional<Pelicula> miOptional = peliculaRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                peliculaRepositorio.deleteById(id);
            }
        } catch (Exception e) {
            throw new MiException("Error al eliminar, verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Pelicula alta(Long id) throws MiException {
        Optional<Pelicula> miOptional = peliculaRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                Pelicula miObjPeli = miOptional.get();
                miObjPeli.setEliminado(false);
            }
        } catch (Exception e) {
            throw new MiException("Error al dar de alta, verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Pelicula baja(Long id) throws MiException {
        Optional<Pelicula> miOptional = peliculaRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                Pelicula miObjPeli = miOptional.get();
                miObjPeli.setEliminado(true);
            }
        } catch (Exception e) {
            throw new MiException("Error al dar de baja, verifique si el elemnto ");
        }
        return null;
    }

    public Pelicula getOne(Long id) {
        return peliculaRepositorio.getOne(id);
    }
}
