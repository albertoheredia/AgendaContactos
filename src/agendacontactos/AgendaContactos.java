/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

/**
 *
 * @author Alberto
 */
import Entity.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AgendaContactos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaContactosPU");
        EntityManager em = emf.createEntityManager();

        //AQUI VA LAS OPERACIONES
        Provincia provinciaCadiz = new Provincia(0, "Cádiz");

        Provincia provinciaSevilla = new Provincia();
        provinciaSevilla.setNombre("Sevilla");
        //INICIAR UNA TRANSACCION
        em.getTransaction().begin();
        em.persist(provinciaCadiz);
        em.persist(provinciaSevilla);
        // Añadir aquí las operaciones de modificación de la base de datos
        em.getTransaction().commit();
        em.close();
        emf.close();

        try {
            DriverManager.getConnection("jdbc:derby:BDAgendaContactos;shutdown=true");
        } catch (SQLException ex) {
        }

    }

}
