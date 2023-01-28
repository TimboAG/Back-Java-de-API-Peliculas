package com.peliculas.peliculas.controlador;

import com.peliculas.peliculas.repositorio.RoleUserRepositorio;
import com.peliculas.peliculas.repositorio.UserRepository;
import com.peliculas.peliculas.seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.peliculas.peliculas.cargasUtilesSolicitud.ApiResponse;
import com.peliculas.peliculas.cargasUtilesSolicitud.LoginRequest;
import com.peliculas.peliculas.cargasUtilesSolicitud.SignUpRequest;
import com.peliculas.peliculas.cargasUtilesSolicitud.JwtAuthenticationResponse;
import com.peliculas.peliculas.entidad.RoleUser;
import com.peliculas.peliculas.entidad.User;
import com.peliculas.peliculas.enumeracion.Rol;
import com.peliculas.peliculas.excepciones.AppException;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleUserRepositorio roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> iniciarSession(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

@PostMapping(path = "/signup", consumes = "application/json")
public ResponseEntity<?> registrarUsuario(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "El nombre de usuario ya existe!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "El email ingresado ya esta en uso!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleUser userRole = roleRepository.findByName(Rol.USER)
                .orElseThrow(() -> new AppException("El rol no se encuentra"));
        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "El usuario ya se encuentra registrado"));
    }
}
