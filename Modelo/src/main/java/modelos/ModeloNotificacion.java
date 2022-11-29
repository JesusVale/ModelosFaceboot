/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Notificacion;
import interfaces.IConexionBD;
import interfaces.IModeloNotificacion;
import interfaces.INotificador;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.NotificacionDominio;
import utils.NotificacionSMS;
import utils.SimpleJavaMail;

/**
 *
 * @author tonyd
 */
public class ModeloNotificacion implements IModeloNotificacion{

    private final IConexionBD conexionBD;
    private static Logger log = LogManager.getLogger(ModeloHashtag.class);

    public ModeloNotificacion(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Notificacion registrar(Notificacion notificacion) {
        EntityManager em = this.conexionBD.crearConexion(); //Establece conexion con la BD
        try {
            INotificador notificador = new NotificacionDominio();
            notificador = new SimpleJavaMail(notificador);
            notificador.notificar(notificacion);
//            notificador = new NotificacionSMS(notificador);
//            notificador.notificar(notificacion);

            em.getTransaction().begin();
            em.persist(notificacion);
            em.getTransaction().commit();
            return notificacion;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar la notificacion");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notificacion consultar(Integer idNotificacion) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            return em.find(Notificacion.class, idNotificacion);
        } catch (IllegalStateException e) {
            System.err.println("No se pudo consultar la notificacion" + idNotificacion);
            e.printStackTrace();
            return null;
        }
    }
}
