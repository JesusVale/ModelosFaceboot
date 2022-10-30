/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;

/**
 *
 * @author tonyd
 */
public interface IModeloUsuario {
    public Usuario consultar(String idUsuario);
    public boolean actualizar(String idUsuario);
    public boolean eliminar(String idUsuario);
    public boolean registrar(Usuario usuario);
}
