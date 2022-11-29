/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Hashtag;
import java.util.List;

/**
 *
 * @author tonyd
 */
public interface IModeloHashtag {
    public Hashtag registrar(Hashtag hashtag);
    public List registrarHashtags(List<Hashtag> hashtags);
    public Hashtag consultar(Integer idHashtag);
    public Hashtag eliminar(Integer idHashtag);
}
