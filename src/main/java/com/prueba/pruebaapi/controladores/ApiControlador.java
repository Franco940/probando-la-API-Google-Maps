package com.prueba.pruebaapi.controladores;

import com.prueba.pruebaapi.entidades.Ubicacion;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Franco
 */

@RestController
public class ApiControlador {
    
    @RequestMapping(value = "api/lugar", method = RequestMethod.POST)
    public Ubicacion mostrarMapa(@RequestBody String lugar){
        
        Ubicacion ubicacion = new Ubicacion();
        String key = "AIzaSyDNpEo_XpZMVD6IXr3yKTg_QnMscCAyjTg";
        String lugarABuscar = lugar;
        
        final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + lugarABuscar + "&key=" + key;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        
        ubicacion.setLat(buscarCoordenadas(result, "lat"));
        ubicacion.setLng(buscarCoordenadas(result, "lng"));

        return ubicacion;
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
