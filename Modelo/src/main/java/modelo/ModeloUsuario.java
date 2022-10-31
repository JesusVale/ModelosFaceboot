/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import entidades.Usuario;
import interfaces.IConexionBD;
import interfaces.IModeloUsuario;
import jakarta.persistence.EntityManager;
/**
 *
 * @author tonyd
 */
public class ModeloUsuario implements IModeloUsuario{
    private final IConexionBD conexionBD;
    
    public ModeloUsuario(IConexionBD conexionBD) 
    {
        this.conexionBD=conexionBD;
    }

    @Override
    public Usuario consultar(String idUsuario) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
           return em.find(Usuario.class, idUsuario);
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo consultar el usuario" + idUsuario);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean actualizar(String idUsuario) {
        EntityManager em = this.conexionBD.crearConexion();
        Usuario usuario = this.consultar(idUsuario);
        if (usuario != null) {
            try {
                em.getTransaction().begin();
                em.persist(usuario);
                em.getTransaction().commit();
                return true;
            } catch (IllegalStateException e) {
                System.err.println("No se pudo actualizar el usuario");
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminar(String idUsuario) {
        EntityManager em = this.conexionBD.crearConexion();
        Usuario usuario = this.consultar(idUsuario);
        if (usuario != null) {
            try {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
                return true;
            } catch (IllegalStateException e) {
                System.err.println("No se pudo eliminar el usuario");
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean registrar(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion();
        System.out.println("registrar entro");
        
        try
        {
           System.out.println("entro try registrar");
           em.getTransaction().begin();
           em.persist(usuario);
            System.out.println("agregado");
           em.getTransaction().commit();
           return true;
        }
        
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo agregar el usuario");
            e.printStackTrace();
            return false;
        }
    }
    
}
