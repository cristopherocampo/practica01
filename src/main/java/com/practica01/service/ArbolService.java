package com.practica01.service;
//define las operaciones que la aplicacion va a permitir hacer con los "arboles"
//(listar, guardar, eliminar)
import com.practica01.domain.Arbol;
import java.util.List;

public interface ArbolService {
    // Retorna la lista de árboles
    public List<Arbol> getArboles(boolean activos);
    
    // Obtiene un árbol por ID
    public Arbol getArbol(Arbol arbol);
    
    // Inserta o actualiza un árbol
    public void save(Arbol arbol);
    
    // Elimina un árbol
    public void delete(Arbol arbol);
}
