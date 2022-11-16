/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Publicacion;
import eventos.Eventos;
import java.util.List;
import peticiones.Peticion;
import peticiones.PeticionPublicaciones;
import principales.Server;
/**
 *
 * @author jegav
 */
public class ComunicadorServidor {
    
    public IJsonToObject conversor;

    public ComunicadorServidor() {
        this.conversor = new JsonToObject();
    }
    
    
    public void notificarCambioNuevaPublicacion(List<Publicacion> publicaciones){
        System.out.println("Estoy en notificar cambio de nueva publicacion");
        PeticionPublicaciones respuesta = new PeticionPublicaciones(Eventos.registrarPublicacion, 200, publicaciones);
        Server.getInstance().notificarTodos(conversor.convertirObjetoString(respuesta));
    }
    
    
}
