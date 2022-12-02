/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EtiquetaUsuario;
import excepciones.PersistException;
import java.util.List;

/**
 *
 * @author tonyd
 */
public interface IModeloEtiqueta {
    public EtiquetaUsuario registrar(EtiquetaUsuario etiqueta) throws PersistException;
    public List<EtiquetaUsuario> registrarEtiquetas(List<EtiquetaUsuario> hashtags) throws PersistException;
}
