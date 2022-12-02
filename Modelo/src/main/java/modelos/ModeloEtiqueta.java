/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.EtiquetaUsuario;
import excepciones.PersistException;
import interfaces.IConexionBD;
import interfaces.IModeloEtiqueta;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author tonyd
 */
public class ModeloEtiqueta implements IModeloEtiqueta {

    private final IConexionBD conexionBD;
    private static Logger log = LogManager.getLogger(ModeloEtiqueta.class);

    public ModeloEtiqueta(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public EtiquetaUsuario registrar(EtiquetaUsuario etiqueta) throws PersistException {
        EntityManager em = this.conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(etiqueta);
            em.getTransaction().commit();
            log.info("Registro etiqueta" + etiqueta.getId());
            return etiqueta;
        } catch (IllegalStateException e) {
            throw new PersistException("No se pudo Registrar en la BD");
        }
    }

    @Override
    public List<EtiquetaUsuario> registrarEtiquetas(List<EtiquetaUsuario> etiquetas) throws PersistException {
        ModeloUsuario mu = new ModeloUsuario(conexionBD);
        List<EtiquetaUsuario> etiquetasRegistradas = new ArrayList();
        for (EtiquetaUsuario etiqueta : etiquetas) {
            try {
                EtiquetaUsuario eu = new EtiquetaUsuario(mu.consultarUsuarioPorNombre(etiqueta.getNombre()));
                this.registrar(eu);
                etiquetasRegistradas.add(eu);
            } catch (Exception ex) {
                throw new PersistException("No se pudo registrar en la BD");
            }
        }
        return etiquetasRegistradas;
    }



}
