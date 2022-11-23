/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import entidades.Publicacion;
import java.util.List;
/**
 *
 * @author tonyd
 */
public interface IModeloPublicacion {
    public Publicacion consultar(String idPublicacion);
    public Publicacion actualizar(String idPublicacion);
    public Publicacion eliminar(String idPublicacion);
    public Publicacion registrar(Publicacion publicacion);
    public List<Publicacion> consultarPublicaciones();
}
