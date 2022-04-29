package com.prueba.pruebaapi.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Franco
 */

@RequestMapping("/")
public class HomeControlador {
    
    @GetMapping("")
    public String index(){
        return "index.html";
    }
    
}
