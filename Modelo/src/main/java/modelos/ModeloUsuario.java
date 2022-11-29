/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import entidades.Usuario;
import interfaces.IConexionBD;
import interfaces.IModeloUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.*;
/**
 *
 * @author tonyd
 */
public class ModeloUsuario implements IModeloUsuario{
    private final IConexionBD conexionBD;
    private static Logger log = LogManager.getLogger(ModeloUsuario.class);

    public ModeloUsuario(IConexionBD conexionBD) 
    {
        this.conexionBD = conexionBD;
    }

    @Override
    public Usuario login(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            String jpqlQuery = "SELECT c FROM Usuario c";
            TypedQuery<Usuario> consulta = em.createQuery(jpqlQuery, Usuario.class);
            List<Usuario> usuarios = consulta.getResultList();
            for (Usuario u : usuarios) {
                if(u.getEmail().equals(usuario.getEmail()) && u.getPassword().equals(usuario.getPassword())){
                    log.info("Inicio sesion "+ u.getEmail());
                    return u;
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    @Override
    public Usuario loginFacebook(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            String jpqlQuery = "SELECT c FROM Usuario c";
            TypedQuery<Usuario> consulta = em.createQuery(jpqlQuery, Usuario.class);
            List<Usuario> usuarios = consulta.getResultList();
            for (Usuario u : usuarios) {
                if (u.getIdFb() == null) {
                    continue;
                }
                if (u.getIdFb().equals(usuario.getIdFb())) {
                    log.info("Inicio sesion " + u.getIdFb());
                    return u;
                }
            }
            registrar(usuario);
            return usuario;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Usuario consultar(Integer idUsuario) {
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
    public Usuario actualizar(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion();
        Usuario usuarioActualizar = this.consultar(usuario.getId());
        if (usuarioActualizar != null) {
            try {
                em.getTransaction().begin();
                em.merge(usuario);
                em.getTransaction().commit();
                return usuario;
            } catch (IllegalStateException e) {
                System.err.println("No se pudo actualizar el usuario");
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Usuario eliminar(Integer idUsuario) {
        EntityManager em = this.conexionBD.crearConexion();
        Usuario usuario = this.consultar(idUsuario);
        if (usuario != null) {
            try {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
                return null;
            } catch (IllegalStateException e) {
                System.err.println("No se pudo eliminar el usuario");
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
    
    @Override
    public Usuario registrar(Usuario usuario) {
        EntityManager em = this.conexionBD.crearConexion(); //Establece conexion con la BD
        try
        {
           em.getTransaction().begin(); //Comienza la Transacción
           em.persist(usuario); //Agrega el usuario
           em.getTransaction().commit(); //Termina Transacción
           return usuario;
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo agregar el usuario");
            e.printStackTrace();
            return null;
        }
    }
    
    public void existeUsuario(Usuario usuario){
        
    }

    @Override
    public Usuario consultarUsuarioPorNombre(String nombre) {
        EntityManager em = this.conexionBD.crearConexion();
        try
        {
            Query query = em.createQuery("SELECT e FROM Usuario e WHERE e.nombre= :nombreUsuario", Usuario.class);
            query.setParameter("nombreUsuario", nombre);
            List<Usuario> usuarios =query.getResultList();
            return usuarios.isEmpty()? null: usuarios.get(0);
        }
        catch(IllegalStateException e)
        {
            System.err.println("No se pudo consultar el usuario" + nombre);
            e.printStackTrace();
            return null;
        }
    }
    
}
