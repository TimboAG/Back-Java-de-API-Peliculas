package com.peliculas.peliculas.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "imagen_ruta_pelicula")
    private String imagen;
    @Column(name = "youtube_trailer")
    private String youtubeTrailerId;
    private String titulo;
    private boolean eliminado = Boolean.FALSE;
    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date fechaCreacion;
    private Integer calificacion;
    @ManyToMany(mappedBy = "peliPersonaje")
    private Set<Personaje> personajePelicula;
    @JoinTable(name = "pelicula_genero",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Genero> peliGenero = new HashSet();
    @OneToOne
    private Imagen foto;

    public Pelicula() {
    }

    public Pelicula(Long id, String imagen, String youtubeTrailerId, String titulo, Date fechaCreacion, Integer calificacion, Set<Personaje> personajePelicula, Imagen foto) {
        this.id = id;
        this.imagen = imagen;
        this.youtubeTrailerId = youtubeTrailerId;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajePelicula = personajePelicula;
        this.foto = foto;
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

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Personaje> getPersonajePelicula() {
        return personajePelicula;
    }

    public void setPersonajePelicula(Set<Personaje> personajePelicula) {
        this.personajePelicula = personajePelicula;
    }

    public Set<Genero> getPeliGenero() {
        return peliGenero;
    }

    public void setPeliGenero(Set<Genero> peliGenero) {
        this.peliGenero = peliGenero;
    }

    public Imagen getFoto() {
        return foto;
    }

    public void setFoto(Imagen foto) {
        this.foto = foto;
    }

}
