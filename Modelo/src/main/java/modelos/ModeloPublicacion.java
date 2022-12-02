/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import comunicacion.ComunicadorServidor;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
import entidades.Comentario;
import entidades.EtiquetaUsuario;
>>>>>>> Stashed changes
import interfaces.IComunicadorServidor;
=======
import comunicacion.IComunicadorServidor;
import entidades.Comentario;
>>>>>>> Stashed changes
import entidades.Hashtag;
import entidades.Publicacion;
import excepciones.NotFoundException;
import excepciones.PersistException;
import interfaces.IConexionBD;
import interfaces.IModeloHashtag;
import interfaces.IModeloPublicacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author tonyd
 */
public class ModeloPublicacion implements IModeloPublicacion {

    private final IConexionBD conexionBD;
    private IComunicadorServidor comunicadorServidor;
    private static Logger log = LogManager.getLogger(ModeloPublicacion.class);

    public ModeloPublicacion(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
        comunicadorServidor = new ComunicadorServidor();
    }

    @Override
    public Publicacion consultar(Integer idPublicacion) throws NotFoundException {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            return em.find(Publicacion.class, idPublicacion);
        } catch (IllegalStateException e) {
            throw new NotFoundException("No se pudo consultar la publicacion en la BD");
        }
    }

    @Override
    public List<Publicacion> consultarPublicaciones() throws NotFoundException {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            Query query = em.createQuery("SELECT e FROM Publicacion e");
            return query.getResultList();
        } catch (IllegalStateException e) {
            throw new NotFoundException("No se pudo consultar las publicaciones de la BD");
        }
    }

    @Override
    public Publicacion eliminar(Integer idPublicacion) throws PersistException {
        EntityManager em = this.conexionBD.crearConexion();
        try {
<<<<<<< Updated upstream
=======
            Publicacion publicacion = this.consultar(idPublicacion);
>>>>>>> Stashed changes
            ModeloComentario mc = new ModeloComentario(this.conexionBD);
            for (Comentario consultarComentario : mc.consultarComentarios(idPublicacion)) {
                mc.eliminar(consultarComentario);
            }
<<<<<<< Updated upstream
            em.getTransaction().begin();
            Publicacion publicacion = this.consultar(idPublicacion);
            Query query = em.createQuery("DELETE FROM Publicacion e WHERE e.id = :idPublicacion");
            query.setParameter("idPublicacion", idPublicacion).executeUpdate();
            em.getTransaction().commit();
=======
            Query query = em.createQuery("DELETE FROM Publicacion e WHERE e.id = :idPublicacion");
            query.setParameter("idPublicacion", publicacion.getId()).executeUpdate();
            log.info("Eliminacion Publicacion " + publicacion.getId());
>>>>>>> Stashed changes
            return publicacion;
        } catch (Exception e) {
            throw new PersistException("No se pudo registrar la publicacion en la BD");
        }
    }

    @Override
    public Publicacion registrar(Publicacion publicacion) throws PersistException {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            ModeloHashtag modelo = new ModeloHashtag(conexionBD);
            ModeloEtiqueta me = new ModeloEtiqueta(conexionBD);
            if (publicacion.getHashtagPublicacion() != null) {
                List<Hashtag> hashtagsRegistrados = modelo.registrarHashtags(publicacion.getHashtagPublicacion());
                publicacion.setHashtagPublicacion(hashtagsRegistrados);
            }
            if(publicacion.getEtiquetasPublicacion() != null){
                List<EtiquetaUsuario> etiquetasRegistradas = me.registrarEtiquetas(publicacion.getEtiquetasPublicacion());
                publicacion.setEtiquetasPublicacion(etiquetasRegistradas);
            }
            em.getTransaction().begin();
            em.persist(publicacion);
            em.getTransaction().commit();
            log.info("Registro Publicacion " + publicacion.getId());
            comunicadorServidor.notificarNuevaPublicacion(publicacion);
            return publicacion;
        } catch (IllegalStateException e) {
            throw new PersistException("No se pudo registrar la publicacion en la BD");
        }
    }

    @Override
    public List<Publicacion> consultarPublicacionesPorEtiqueta(String hashtag) throws Exception {
        ModeloHashtag modeloHashtag = new ModeloHashtag(conexionBD);
        List<Publicacion> publicacionesRespuesta = new ArrayList();
        Hashtag hashtagRegistrado = modeloHashtag.consultarPorTema(hashtag);

        for (Publicacion publicacion : this.consultarPublicaciones()) {
            if (publicacion.getHashtagPublicacion().contains(hashtagRegistrado)) {
                publicacionesRespuesta.add(publicacion);
            }
        }

        return publicacionesRespuesta;
    }

}
