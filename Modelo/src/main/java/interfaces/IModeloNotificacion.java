/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Notificacion;

/**
 *
 * @author tonyd
 */
public interface IModeloNotificacion {
    public Notificacion registrar(Notificacion notificacion);
    public Notificacion consultar(Integer idNotificacion);
}
