package com.peliculas.peliculas.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
@Entity
@Table(name = "genero")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long id;
    @JoinColumn(name = "imagen_ruta_genero")
    private String rutaImagen;
    @Transient
    private MultipartFile imagen;
    private String genero;
    private boolean eliminado = Boolean.FALSE;
    @ManyToMany(mappedBy = "peliGenero")
    @Column(name = "pelicula_genero")
    private Set<Pelicula> peliculaGenero = new HashSet();

    public Genero() {
    }

    public Genero(Long id, String rutaImagen, MultipartFile imagen, String genero) {
        this.id = id;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Set<Pelicula> getPeliculaGenero() {
        return peliculaGenero;
    }

    public void setPeliculaGenero(Set<Pelicula> peliculaGenero) {
        this.peliculaGenero = peliculaGenero;
    }

}
