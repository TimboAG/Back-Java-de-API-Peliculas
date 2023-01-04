package com.peliculas.peliculas.controlador;


import com.peliculas.peliculas.entidad.Pelicula;
import com.peliculas.peliculas.servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> imagen(@PathVariable Long id) {
        Pelicula miPeli = peliculaServicio.getOne(id);
        byte[] imagen = miPeli.getFoto().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

   
}