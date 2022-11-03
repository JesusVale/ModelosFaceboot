/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Comentario;
import interfaces.IConexionBD;
import interfaces.IModeloComentario;
import jakarta.persistence.EntityManager;

/**
 *
 * @author tonyd
 */
public class ModeloComentario implements IModeloComentario{
    private final IConexionBD conexionBD;
    
    public ModeloComentario(IConexionBD conexionBD) 
    {
        this.conexionBD=conexionBD;
    }

    @Override
    public Comentario consultar(String idComentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
           return em.find(Comentario.class, idComentario);
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo consultar el comentario" + idComentario);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Comentario actualizar(String idComentario) {
        EntityManager em = this.conexionBD.crearConexion(); 
        try {
            Comentario comentario = this.consultar(idComentario);
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
            return comentario;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo actualizar el comentario");
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public boolean eliminar(String idComentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            Comentario comentario = this.consultar(idComentario);
            em.getTransaction().begin();
            em.remove(comentario);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo eliminar el comentario");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registrar(Comentario comentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
           em.getTransaction().begin();
           em.persist(comentario);
           em.getTransaction().commit();
           return true;
        }
        
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo agregar el comentario");
            e.printStackTrace();
            return false;
        }
    }
}
