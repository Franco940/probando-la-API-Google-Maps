package com.prueba.pruebaapi.entidades;

/**
 *
 * @author Franco
 */

public class Lugar {
    private String nombre;

    public Lugar(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
