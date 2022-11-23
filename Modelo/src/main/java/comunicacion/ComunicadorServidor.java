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
import peticiones.PeticionPublicacion;
import principales.Server;
/**
 *
 * @author jegav
 */
public class ComunicadorServidor implements IComunicadorServidor{
    
    public IJsonToObject conversor;

    public ComunicadorServidor() {
        this.conversor = new JsonToObject();
    }
    

    @Override
    public void notificarNuevaPublicacion(Publicacion publicacion) {
        PeticionPublicacion respuesta = new PeticionPublicacion(Eventos.registrarPublicacion, 200, publicacion);
        Server.getInstance().notificarTodos(conversor.convertirObjetoString(respuesta));
    }
    
    
}
