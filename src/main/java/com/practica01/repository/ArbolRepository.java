package com.practica01.repository;
//Es un clase "bodega", sabe donde estan los datos y como sacarlos de la BD
//clase que trae los metodos para guardar, buscar por id, borrar y listar todo
import com.practica01.domain.Arbol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArbolRepository extends JpaRepository<Arbol, Long> {
}
