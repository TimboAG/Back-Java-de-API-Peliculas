package com.peliculas.peliculas.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "personaje")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "imagen_ruta_personaje")
    private String imagen;
    @Transient
    private MultipartFile imagenPersonaje;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private boolean eliminado = Boolean.FALSE;
    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "personaje_pelicula_join",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    private Set<Pelicula> peliPersonaje = new HashSet();

    public Personaje() {
    }

    public Personaje(Long id, String imagen, MultipartFile imagenPersonaje, String nombre, Integer edad, Double peso, String historia) {
        this.id = id;
        this.imagen = imagen;
        this.imagenPersonaje = imagenPersonaje;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

    public Personaje(String imagen, MultipartFile imagenPersonaje, String nombre, Integer edad, Double peso, String historia) {
        this.imagen = imagen;
        this.imagenPersonaje = imagenPersonaje;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public MultipartFile getImagenPersonaje() {
        return imagenPersonaje;
    }

    public void setImagenPersonaje(MultipartFile imagenPersonaje) {
        this.imagenPersonaje = imagenPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Set<Pelicula> getPeliPersonaje() {
        return peliPersonaje;
    }

    public void setPeliPersonaje(Set<Pelicula> peliPersonaje) {
        this.peliPersonaje = peliPersonaje;
    }

}
