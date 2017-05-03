/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import Entity.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Alberto
 */
public class ConsultaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaContactosPU");
        EntityManager em = emf.createEntityManager();
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        List<Provincia> listProvincias = queryProvincias.getResultList();
        for (Provincia provincia : listProvincias) {
            System.out.println(provincia.getNombre());
        }

        Provincia provinciaId2 = em.find(Provincia.class, 2);
        if (provinciaId2 != null) {
            em.remove(provinciaId2);
        } else {
            System.out.println("No hay ninguna provincia con ID=2");
        }

        Query queryProvinciaCadiz = em.createNamedQuery("Provincia.findByNombre");
        queryProvinciaCadiz.setParameter("nombre", "Cádiz");
        List<Provincia> listProvinciasCadiz = queryProvinciaCadiz.getResultList();
        em.getTransaction().begin();
        for (Provincia provinciaCadiz : listProvinciasCadiz) {
            provinciaCadiz.setCodigo("11");
            em.merge(provinciaCadiz);
        }
        em.getTransaction().commit();

        em.close();
        emf.close();

        try {
            DriverManager.getConnection("jdbc:derby:BDAgendaContactos;shutdown=true");
        } catch (SQLException ex) {
        }
        // TODO code application logic here
    }

}
