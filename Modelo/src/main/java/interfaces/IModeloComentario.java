/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import entidades.Comentario;
import java.util.List;
/**
 *
 * @author tonyd
 */
public interface IModeloComentario {
    public Comentario consultar(String idComentario);
    public Comentario actualizar(String idComentario);
    public Comentario eliminar(String idComentario);
    public Comentario registrar(Comentario comentario);
    public List<Comentario> consultarComentarios(Integer idPublicacion);
}
