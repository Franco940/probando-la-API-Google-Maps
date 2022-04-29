package com.prueba.pruebaapi.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Franco
 */

@Controller
@RequestMapping("/api")
public class ApiControlador {
    
    @GetMapping("/mapa")
    public String index(ModelMap modelo){
        
        return "mostrar.html";
    }
    
    
    @PostMapping("/mapa")
    public String mostrarMapa(ModelMap modelo, @RequestParam String lugar){
        String direccion = lugar;
        final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + direccion + "&key=AIzaSyDNpEo_XpZMVD6IXr3yKTg_QnMscCAyjTg";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
  
        String mapa = "https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d15619.864533967735!2d" + buscarCoordenadas(result, "lng") + "!3d" + buscarCoordenadas(result, "lat") + "!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses-419!2sar!4v1651207120537!5m2!1ses-419!2sar";
        
        System.out.println(result);
        
        modelo.put("url", mapa);
        
        return "mostrar.html";
    }
    
    
    private String buscarCoordenadas(String informacion, String tipoCoordenada){
        String coordenada = "";
        
        // Le sumio 7 para pararme en el primer valor de la coordenada
        // que está en el string informacion
        int inicioCoordendada = informacion.indexOf(tipoCoordenada) + 7;
        
        // Agarro un solo caracter
        String aux = informacion.substring(inicioCoordendada, inicioCoordendada + 1);
        
        while(verificarNumero(aux)){
            coordenada = coordenada + aux;
            inicioCoordendada++;
            aux = informacion.substring(inicioCoordendada, inicioCoordendada + 1);
        }
        
        coordenada.trim();
        return coordenada;
    }
    
    
    // Esta función verifica que lo que se agarre del string información sea un numero,
    // un punto o un signo de menos
    private boolean verificarNumero(String num){
        if((num.charAt(0) >= 48 && num.charAt(0) <= 57) || num.equals("-") || num.equals(".")){
            return true;
        }else{
            return false;
        }
    }
}
