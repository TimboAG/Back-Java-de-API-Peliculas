package com.peliculas.peliculas.controlador;

import com.peliculas.peliculas.entidad.Genero;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.servicio.GeneroServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genero")
@CrossOrigin("*")
public class GeneroControlador {

    @Autowired
    private GeneroServicio servicioGenero;

    @GetMapping("/custom")
    public ResponseEntity<List<Genero>> findAllCustom() {
        return new ResponseEntity<>(servicioGenero.findAllCustom(), OK);
    }
    
    
    @GetMapping
    public ResponseEntity<List<Genero>> findAll() {
        return new ResponseEntity<>(servicioGenero.listar(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Genero>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(servicioGenero.findById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Genero> crear(@RequestBody Genero miGenero) {
        return new ResponseEntity<>(servicioGenero.agregar(miGenero), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizar(@PathVariable Long id, @RequestBody Genero miGenero) {
        miGenero.setId(id);
        return new ResponseEntity<>(servicioGenero.actualizar(miGenero), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genero> eliminar(@PathVariable Long id) throws MiException {
        return new ResponseEntity<>(servicioGenero.eliminar(id), NO_CONTENT);
    }

    @PutMapping("/alta/{id}")
    public ResponseEntity<Genero> alta(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(servicioGenero.alta(id), OK);
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<Genero> baja(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(servicioGenero.baja(id), OK);
    }

}
