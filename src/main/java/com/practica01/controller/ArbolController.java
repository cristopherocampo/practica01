/*
 * recibe las peticiones del navegador y 
decide qué datos mostrarle al usuario usando el Service
 */
package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import com.practica01.service.FirebaseStorageService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arbol")
public class ArbolController {

    @Autowired
    private ArbolService arbolService;
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var arboles = arbolService.getArboles(false);
        model.addAttribute("arboles", arboles);
        model.addAttribute("totalArboles", arboles.size());

        model.addAttribute("arbol", new Arbol());
        return "general/listado";
    }

    @GetMapping("/nuevo")
    public String arbolNuevo(Arbol arbol) {
        return "general/modifica";
    }

    @PostMapping("/guardar")
    public String arbolGuardar(Arbol arbol, @RequestParam("imagenFile") MultipartFile imagenFile) {
        // primero se guarda el arbol para que la BD le de un id
        
        arbolService.save(arbol);

        if (!imagenFile.isEmpty()) {
            try {
                
                String ruta = firebaseStorageService.cargaImagen(
                        imagenFile,
                        "arbol",
                        arbol.getIdArbol()); 

                
                arbol.setRutaImagen(ruta);

                
                arbolService.save(arbol);

            } catch (IOException e) {
                System.out.println("Error al subir la imagen a Firebase: " + e.getMessage());
            }
        }
        return "redirect:/arbol/listado";
    }

    @GetMapping("/eliminar/{idArbol}")
    public String arbolEliminar(Arbol arbol) {
        arbolService.delete(arbol);

        return "redirect:/arbol/listado";
    }

    @GetMapping("/modificar/{idArbol}")
    public String arbolModificar(Arbol arbol, Model model) {
        arbol = arbolService.getArbol(arbol);
        model.addAttribute("arbol", arbol);
        return "general/modifica";
    }
}
