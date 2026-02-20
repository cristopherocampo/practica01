package com.practica01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

@Data // Genera automáticamente getters, setters, toString, etc.
@Entity // le dice a java que esta clase es una tabla de la BD
@Table(name="arbol")
public class Arbol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_arbol")
    private Long idArbol;
    
    private String nombre;
    private String tipoFlor;
    private String durezaMadera;
    private int edadArbol;
    private String rutaImagen;
    private boolean activo;

    public Arbol() {
    }

    
    public Arbol(String nombre, String tipoFlor, String durezaMadera, int edadArbol, String rutaImagen, boolean activo) {
        this.nombre = nombre;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.edadArbol = edadArbol;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}