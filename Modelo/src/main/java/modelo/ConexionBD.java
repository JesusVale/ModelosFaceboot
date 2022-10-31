/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import interfaces.IConexionBD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author tonyd
 */
public class ConexionBD implements IConexionBD{
    @Override
    public EntityManager crearConexion() throws IllegalStateException {
        //Obtiene acceso alemFactory a partir de la persistence unit utilizada
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("facebootPU"); 
        // Creamos una em(bd) para realizar operaciones con la bd
        EntityManager em = emFactory.createEntityManager();
        return em;
    }
}
