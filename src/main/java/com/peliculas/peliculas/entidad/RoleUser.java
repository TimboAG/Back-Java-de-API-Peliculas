
package com.peliculas.peliculas.entidad;

import com.peliculas.peliculas.enumeracion.Rol;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private Rol name;

    public RoleUser() {

    }

    public RoleUser(Rol name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rol getName() {
        return name;
    }

    public void setName(Rol name) {
        this.name = name;
    }

}
