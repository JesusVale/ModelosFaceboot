/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Comentario;
import interfaces.IConexionBD;
import interfaces.IModeloComentario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author tonyd
 */
public class ModeloComentario implements IModeloComentario {

    private final IConexionBD conexionBD;

    public ModeloComentario(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Comentario consultar(String idComentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            return em.find(Comentario.class, idComentario);
        } catch (IllegalStateException e) {
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
    public Comentario eliminar(String idComentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            Comentario comentario = this.consultar(idComentario);
            em.getTransaction().begin();
            em.remove(comentario);
            em.getTransaction().commit();
            return null;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo eliminar el comentario");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Comentario registrar(Comentario comentario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
            return comentario;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar el comentario");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comentario> consultarComentarios() {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            Query query = em.createQuery("SELECT e FROM Comentario e");
            return query.getResultList();
        } catch (IllegalStateException e) {
            System.err.println("No se puedieron consultar los comentarios");
            e.printStackTrace();
            return null;
        }
    }
}
