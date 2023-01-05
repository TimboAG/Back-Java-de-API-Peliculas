package com.peliculas.peliculas.servicio;

import com.peliculas.peliculas.entidad.Genero;
import com.peliculas.peliculas.entidad.Imagen;
import com.peliculas.peliculas.entidad.Pelicula;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.repositorio.PeliculaRepositorio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    private GeneroServicio generoServicio;
    
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
    
    public Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }
    
    public Date ConvertDate(Pelicula miPelicula) {
        Date miDate = miPelicula.getFechaCreacion();
        Date dateActualizado = new Date();
        dateActualizado.setDate(miDate.getDate() + 1);
        dateActualizado.setMonth(miDate.getMonth());
        dateActualizado.setYear(miDate.getYear());
        return dateActualizado;
    }
    
    @Transactional
    public Set<Genero> agregarGenero(Long miGenero) throws MiException {
        Genero miGeneroCompleto = generoServicio.buscarId(miGenero);
        Set<Genero> miSetGenero = new HashSet();
        miSetGenero.add(miGeneroCompleto);
        return miSetGenero;
    }
    
    @Transactional
    public Pelicula agregar(Pelicula miPelicula, MultipartFile foto, Integer miGenero) throws MiException, Exception {
        Imagen miImagen = miserv.guardar(foto);
        Date nuevo = ConvertDate(miPelicula);
        miPelicula.setFechaCreacion(nuevo);
        miPelicula.setFoto(miImagen);
        Long miGLong = new Long(miGenero);
        miPelicula.setPeliGenero(agregarGenero(miGLong));
        String rutaImagen = almacen.almacenarArchivo(foto);
        miPelicula.setImagen(rutaImagen);
        return peliculaRepositorio.save(miPelicula);
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
