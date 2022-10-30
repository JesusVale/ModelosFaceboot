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
    public void conexionBD();
    public Usuario consultar(String idUsuario);
    public void actualizar(String idUsuario);
    public void eliminar(String idUsuario);
    public Boolean registrar(Usuario usuario);
}
