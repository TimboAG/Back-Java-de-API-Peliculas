package com.peliculas.peliculas.controlador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.peliculas.peliculas.entidad.Pelicula;
import com.peliculas.peliculas.entidad.Usuario;
import com.peliculas.peliculas.excepciones.MiException;
import com.peliculas.peliculas.excepciones.RegistroResponse;
import com.peliculas.peliculas.servicio.UsuarioServicio;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;



//    
//    @RequestMapping(value = "/registro", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> registro(@RequestParam("form1") String usuario) throws Exception {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            Gson gson = new Gson();
//            Usuario miUsuario = gson.fromJson(usuario, Usuario.class);
//            usuarioServicio.buscar(miUsuario);
//            response.put("Mensaje", "Se guardo con exito");
//            response.put("status", 1);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (JsonSyntaxException | MiException e) {
//            response.put("Mensaje", "Ocurrio un error");
//            response.put("Error", e.getMessage());
//            response.put("status", 2);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @RequestMapping(value = "/registro", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> registro(@RequestParam("form1") String usuario) throws Exception {
        try {
            Gson gson = new Gson();
            Usuario miUsuario = gson.fromJson(usuario, Usuario.class);
            return new ResponseEntity<>(usuarioServicio.buscar(miUsuario), OK);
        } catch (JsonSyntaxException jsonSyntaxException) {
            throw new MiException(jsonSyntaxException.getMessage());
        }
    }

//
//@GetMapping("/login")
//        public String login(@RequestParam(required = false) String error, ModelMap modelo) throws MiException {
//        if (error != null) {
//            modelo.put("error", "usuario o contraseña invalido");
//        }
//        return "login";
//    }
}
