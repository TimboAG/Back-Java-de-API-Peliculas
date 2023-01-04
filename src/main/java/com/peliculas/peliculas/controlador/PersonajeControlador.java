package com.peliculas.peliculas.controlador;

import com.peliculas.peliculas.entidad.Personaje;
import com.peliculas.peliculas.servicio.PersonajeServicio;
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
@RequestMapping("/personaje")
@CrossOrigin("*")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/custom")
    public ResponseEntity<List<Personaje>> findAllCustom() {
        return new ResponseEntity<>(personajeServicio.findAllCustom(), OK);
    }

    @GetMapping
    public ResponseEntity<List<Personaje>> findAll() {
        return new ResponseEntity<>(personajeServicio.listar(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Personaje>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(personajeServicio.finById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Personaje> crear(@RequestBody Personaje miPersonaje) {
        return new ResponseEntity <>(personajeServicio.crear(miPersonaje), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizar(@PathVariable Long id, @RequestBody Personaje miPersonaje) {
        miPersonaje.setId(id);
        return new ResponseEntity<> (personajeServicio.actualizar(miPersonaje), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Personaje> eliminar(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(personajeServicio.eliminar(id), NO_CONTENT);
    }

    @PutMapping("/alta/{id}")
    public ResponseEntity<Personaje> alta(@PathVariable Long id) {
        try {
            return new ResponseEntity <> (personajeServicio.alta(id), OK);
        } catch (Exception e) {
            System.err.println("error");
        }
        return null;
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<Personaje> baja(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<> (personajeServicio.baja(id), OK);
        } catch (Exception e) {
            System.err.println("error");
        }
        return null;
    }
}
