package com.peliculas.peliculas.servicio;

import com.peliculas.peliculas.excepciones.MiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlmacenServicio {

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    public void iniciarAlmacenDeArchivo() throws MiException {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new MiException("Error al iniciar ubicacion en el almacen de archivos");
        }
    }

    public String almacenarArchivo(MultipartFile archivo) throws MiException {
        String nombreArchivo = archivo.getOriginalFilename();
        if (archivo.isEmpty()) {
            throw new MiException("No se puede almacenar un archivo vacio");
        }
        try {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new MiException("Error al almacenar el archivo" + nombreArchivo);
        }
        return nombreArchivo;
    }

    public Path cargarArchivo(String nombreArchivo) {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    public Resource cargarComoRecurso(String nombreArchivo) throws MiException {
        try {
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new MiException("No se pudo encontrar el archivo" + nombreArchivo);
            }
        } catch (MalformedURLException e) {
            throw new MiException("No se pudo encontrar el archivo" + nombreArchivo);

        }
    }

    public void eliminarArchivo(String nombreArchivo) {
        Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
