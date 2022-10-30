/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import entidades.Usuario;
import interfaces.IConexionBD;
import interfaces.IModeloUsuario;
import javax.persistence.EntityManager;
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
    public void conexionBD() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            System.err.println("No se pudo consultar el cliente" + idUsuario);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actualizar(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean registrar(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion();
        
        try
        {
           em.getTransaction().begin();
           em.persist(usuario);
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
