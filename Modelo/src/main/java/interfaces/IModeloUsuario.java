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
    public Usuario consultar(Long idUsuario);
    public Usuario login(Usuario usuario);
    public Usuario actualizar(Usuario usuario);
    public boolean eliminar(Long idUsuario);
    public Usuario registrar(Usuario usuario);
}
