package com.peliculas.peliculas.servicio;

import com.peliculas.peliculas.entidad.Usuario;
import com.peliculas.peliculas.enumeracion.Rol;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario registrar(Usuario miUsuario) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setApellido(miUsuario.getApellido());
        usuario.setEmail(miUsuario.getEmail());
        usuario.setNombre(miUsuario.getNombre());
        usuario.setUsername(miUsuario.getUsername());
        usuario.setPassword(new BCryptPasswordEncoder().encode(miUsuario.getPassword()));
        usuario.setRol(Rol.USER);
        return usuarioRepositorio.save(miUsuario);
    }

 
    
    @Transactional
    public Usuario buscarPorEmail(String email) throws Exception {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            throw new Exception("El email ya se encuentra registrado");
        }
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }
    
     @Transactional
    public Usuario buscar(Usuario  miUsuaurio) throws MiException {
       Usuario usuario = usuarioRepositorio.buscarPorEmail(miUsuaurio.getEmail());
       
        try {
            if (usuario == null) {
                registrar( miUsuaurio);
            }else{
                 throw new MiException("Error el email ya existe. pruebe con otro ");
            }
        } catch (Exception e) {
            throw new MiException("Error el email ya existe. pruebe con otro ");
        }
        return null;
    }
    
}
