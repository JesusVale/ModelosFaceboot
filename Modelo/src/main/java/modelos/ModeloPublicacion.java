/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import comunicacion.ComunicadorServidor;
import comunicacion.IComunicadorServidor;
import entidades.Publicacion;
import interfaces.IConexionBD;
import interfaces.IModeloPublicacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author tonyd
 */
public class ModeloPublicacion implements IModeloPublicacion{
    private final IConexionBD conexionBD;
    private IComunicadorServidor comunicadorServidor;
    
    public ModeloPublicacion(IConexionBD conexionBD) 
    {
        this.conexionBD=conexionBD;
        comunicadorServidor = new ComunicadorServidor();
    }

    @Override
    public Publicacion consultar(Integer idPublicacion) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
           return em.find(Publicacion.class, idPublicacion);
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo consultar la publicacion" + idPublicacion);
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Publicacion> consultarPublicaciones() {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
            Query query = em.createQuery("SELECT e FROM Publicacion e");
           return query.getResultList();
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se puedieron consultar las publicaciones");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Publicacion actualizar(Integer idPublicacion) {
        EntityManager em = this.conexionBD.crearConexion(); 
        try {
            Publicacion publicacion = this.consultar(idPublicacion);
            em.getTransaction().begin();
            em.persist(publicacion);
            em.getTransaction().commit();
            return publicacion;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo actualizar la publicacion");
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public Publicacion eliminar(Integer idPublicacion) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            Publicacion publicacion = this.consultar(idPublicacion);
            em.getTransaction().begin();
            em.remove(publicacion);
            em.getTransaction().commit();
            return null;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo eliminar la publicacion");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Publicacion registrar(Publicacion publicacion) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
           em.getTransaction().begin();
           em.persist(publicacion);
           em.getTransaction().commit();
           comunicadorServidor.notificarNuevaPublicacion(publicacion);
           return publicacion;
        }
        
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo agregar la publicacion");
            e.printStackTrace();
            return null;
        }
    }

    
    
}
