package com.practica01.service;
//se ejecutan las ordenes del usuario
//va a la clase "ArbolRepository" y le pide los datos

import com.practica01.repository.ArbolRepository;
import com.practica01.domain.Arbol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArbolServiceImpl implements ArbolService {

    @Autowired
    private ArbolRepository arbolR;

    @Override
    @Transactional(readOnly = true)
    public List<Arbol> getArboles(boolean activos) {
        var lista = arbolR.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) {
        return arbolR.findById(arbol.getIdArbol()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Arbol arbol) {
        arbolR.save(arbol);
    }

    @Override
    @Transactional
    public void delete(Arbol arbol) {
        arbolR.delete(arbol);
    }
}
