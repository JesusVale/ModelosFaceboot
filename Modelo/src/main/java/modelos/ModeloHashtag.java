/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Hashtag;
import entidades.Hashtag;
import interfaces.IConexionBD;
import interfaces.IModeloHashtag;
import interfaces.INotificador;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author tonyd
 */
public class ModeloHashtag implements IModeloHashtag {

    private final IConexionBD conexionBD;
    private static Logger log = LogManager.getLogger(ModeloHashtag.class);

    public ModeloHashtag(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Hashtag registrar(Hashtag hashtag) {
        EntityManager em = this.conexionBD.crearConexion(); //Establece conexion con la BD
        try {
            em.getTransaction().begin();
            em.persist(hashtag);
            em.getTransaction().commit();
            return hashtag;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar el hashtag");
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List registrarHashtags(List<Hashtag> hashtags){
        for (Hashtag hashtag : hashtags) {
            this.registrar(hashtag);
        }
        return hashtags;
    }

    @Override
    public Hashtag consultar(Integer idHashtag) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            return em.find(Hashtag.class, idHashtag);
        } catch (IllegalStateException e) {
            System.err.println("No se pudo consultar el Hashtag" + idHashtag);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Hashtag eliminar(Integer idHashtag) {
        EntityManager em = this.conexionBD.crearConexion();
        Hashtag hashtag = this.consultar(idHashtag);
        if (hashtag != null) {
            try {
                em.getTransaction().begin();
                em.remove(hashtag);
                em.getTransaction().commit();
                return null;
            } catch (IllegalStateException e) {
                System.err.println("No se pudo eliminar el Hashtag");
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
