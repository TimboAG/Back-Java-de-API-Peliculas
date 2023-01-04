package com.peliculas.peliculas.controlador;

import com.peliculas.peliculas.entidad.Pelicula;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.servicio.PeliculaServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pelicula")
@CrossOrigin("*")

public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping("/custom")
    public ResponseEntity<List<Pelicula>> findAllCustom() {
        return new ResponseEntity<>(peliculaServicio.findAllCustom(), OK);
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> findAll() {
        return new ResponseEntity<>(peliculaServicio.listar(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pelicula>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(peliculaServicio.findById(id), OK);
    }

 @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
        public ResponseEntity<Pelicula> crear(@RequestPart("form1") Pelicula miPelicula, @RequestPart("imagenPelicula") MultipartFile foto) throws MiException, Exception {
        return new ResponseEntity<>(peliculaServicio.agregar(miPelicula, foto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula miPelicula) {
        miPelicula.setId(id);
        return new ResponseEntity<>(peliculaServicio.actualizar(miPelicula), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pelicula> eliminar(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(peliculaServicio.eliminar(id), NO_CONTENT);
    }

    @PutMapping("/alta/{id}")
    public ResponseEntity<Pelicula> alta(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(peliculaServicio.alta(id), OK);
        } catch (Exception e) {
            System.err.println("error");
        }
        return null;
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<Pelicula> baja(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(peliculaServicio.baja(id), OK);
        } catch (Exception e) {
            System.err.println("error");
        }
        return null;
    }
}
