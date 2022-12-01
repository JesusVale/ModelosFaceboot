/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Notificacion;
import entidades.Usuario;
import enumeradores.MotorEnvio;
import enumeradores.Sexo;
import interfaces.IConexionBD;
import interfaces.IModeloNotificacion;
import interfaces.INotificador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.NotificacionDominio;
import utils.NotificacionSMS;
import utils.SimpleJavaMail;

/**
 *
 * @author tonyd
 */
public class ModeloNotificacion implements IModeloNotificacion {

    private final IConexionBD conexionBD;
    private static Logger log = LogManager.getLogger(ModeloNotificacion.class);

    public ModeloNotificacion(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Notificacion registrar(Notificacion notificacion) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            INotificador notificador = new NotificacionDominio();
            if (notificacion.getMotorEnvio().equals(MotorEnvio.TwilioSMS)) {
                notificador = new NotificacionSMS(notificador);
                notificador.notificar(notificacion);
            }
            if (notificacion.getMotorEnvio().equals(MotorEnvio.simpleJavaMail)) {
                notificador = new SimpleJavaMail(notificador);
                notificador.notificar(notificacion);
            } else if(notificacion.getMotorEnvio().equals(MotorEnvio.ambos)) {
                notificador = new SimpleJavaMail(notificador);
                //notificador.notificar(notificacion);
                notificador = new NotificacionSMS(notificador);
                notificador.notificar(notificacion);
            }
            em.getTransaction().begin();
            em.persist(notificacion);
            em.getTransaction().commit();
            log.info("Registro Notificacion" + notificacion.getId());
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

    @Override
    public List<Notificacion> consultarNotificacionesPorRemitente(Usuario remitente) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
            Query query = em.createQuery("SELECT e FROM Notificacion e WHERE e.remitente= :remitente", Notificacion.class);
            query.setParameter("remitente", remitente);
            List<Notificacion> notificaciones = query.getResultList();
            return notificaciones;
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudieron consultar las notificaciones");
            e.printStackTrace();
            return null;
        }
    }
}
