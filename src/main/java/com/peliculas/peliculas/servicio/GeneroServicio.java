package com.peliculas.peliculas.servicio;

import com.peliculas.peliculas.entidad.Genero;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.repositorio.GeneroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Transactional(readOnly = true)
    public List<Genero> listar() {
        return generoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List<Genero> findAllCustom() {
        return generoRepositorio.findAllCustom();
    }

    @Transactional
    public Optional<Genero> findById(Long id) {
        return generoRepositorio.findById(id);
    }

    

    @Transactional
    public Genero buscarId(Long id) throws MiException {
        System.out.println("Esto es id genero" + id);
        Optional<Genero> miOptional = generoRepositorio.findById(id);
        try {
            Genero miObj = new Genero();
            if (miOptional.isPresent()) {
                miObj = miOptional.get();
                return miObj;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new MiException("Error verifique si el elemnto ");
        }
    }

    @Transactional
    public Genero agregar(Genero miGenero) {
        return generoRepositorio.save(miGenero);
    }

    @Transactional
    public Genero actualizar(Genero miGenero) {
        Genero objGenero = generoRepositorio.getById(miGenero.getId());
        BeanUtils.copyProperties(miGenero, objGenero);
        return generoRepositorio.save(objGenero);
    }

    @Transactional
    public Genero eliminar(Long id) throws MiException {
        Optional<Genero> respuesta = generoRepositorio.findById(id);
        try {
            if (respuesta.isPresent()) {
                generoRepositorio.deleteById(id);
            }
        } catch (Exception e) {
            throw new MiException("Error al eliminar, verifique si el elemnto ");
        }
        return null;
    }

    @Transactional
    public Genero alta(Long id) throws Exception {
        Optional<Genero> respuesta = generoRepositorio.findById(id);
        try {
            Genero miGenero = new Genero();
            if (respuesta.isPresent()) {
                miGenero = respuesta.get();
            }
            miGenero.setEliminado(false);
            return generoRepositorio.save(miGenero);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public Genero baja(Long id) throws Exception {
        Optional<Genero> respuesta = generoRepositorio.findById(id);
        try {
            Genero miGenero = new Genero();
            if (respuesta.isPresent()) {
                miGenero = respuesta.get();
            }
            miGenero.setEliminado(true);
            return generoRepositorio.save(miGenero);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
