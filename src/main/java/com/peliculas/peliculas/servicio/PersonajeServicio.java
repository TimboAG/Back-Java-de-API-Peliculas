package com.peliculas.peliculas.servicio;

import com.peliculas.peliculas.entidad.Personaje;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.repositorio.PersonajeRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Transactional(readOnly = true)
    public List<Personaje> listar() {
        return personajeRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List<Personaje> findAllCustom() {
        return personajeRepositorio.findAllCustom();
    }

    @Transactional
    public Optional<Personaje> finById(Long id) {
        return personajeRepositorio.findById(id);
    }

    @Transactional
    public List<Personaje> findByName(String nombre) {
        return personajeRepositorio.findByName(nombre);
    }

    @Transactional
    public List<Personaje> findByAge(Double edad) {
        return personajeRepositorio.findByAge(edad);
    }

    @Transactional
    public List<Personaje> findByIdMoviesId(Long idPelicula) {
        return personajeRepositorio.findByMoviesId(idPelicula);
    }

    @Transactional
    public Personaje crear(Personaje miPersonaje) {
        return personajeRepositorio.save(miPersonaje);
    }

    @Transactional
    public Personaje buscarId(Long id) throws MiException {
        Optional<Personaje> miOptional = personajeRepositorio.unPersonajeId(id);
        try {
            if (miOptional.isPresent()) {
                Personaje miObjPers = miOptional.get();
                return miObjPers;
            }
        } catch (Exception e) {
            throw new MiException("Error verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Personaje actualizar(Personaje miPersonaje) {
        Personaje objPersonaje = personajeRepositorio.getById(miPersonaje.getId());
        BeanUtils.copyProperties(miPersonaje, objPersonaje);
        return personajeRepositorio.save(objPersonaje);
    }

    @Transactional
    public Personaje eliminar(Long id) throws MiException {
        Optional<Personaje> miOptional = personajeRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                personajeRepositorio.deleteById(id);
            }
        } catch (Exception e) {
            throw new MiException("Error al eliminar, verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Personaje alta(Long id) throws MiException {
        Optional<Personaje> miOptional = personajeRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                Personaje miObjPeli = miOptional.get();
                miObjPeli.setEliminado(false);
            }
        } catch (Exception e) {
            throw new MiException("Error al dar de alta, verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Personaje baja(Long id) throws MiException {
        Optional<Personaje> miOptional = personajeRepositorio.findById(id);
        try {
            if (miOptional.isPresent()) {
                Personaje miObjPeli = miOptional.get();
                miObjPeli.setEliminado(true);
            }
        } catch (Exception e) {
            throw new MiException("Error al dar de alta, verifique si el elemnto ");
        }
        return null;
    }
}
