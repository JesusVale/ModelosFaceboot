/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import entidades.Publicacion;
/**
 *
 * @author tonyd
 */
public interface IModeloPublicacion {
    public Publicacion consultar(String idPublicacion);
    public Publicacion actualizar(String idPublicacion);
    public boolean eliminar(String idPublicacion);
    public boolean registrar(Publicacion publicacion);
}
