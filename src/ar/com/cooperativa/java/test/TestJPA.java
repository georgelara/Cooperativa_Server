package ar.com.cooperativa.java.test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import ar.com.cooperativa.java.entities.*;
import ar.com.cooperativa.java.enumerados.Lugares;
public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("COOPU");
        EntityManager em=emf.createEntityManager();
        
        Cliente cliente=new Cliente(987272, "20-31991379/4", "9435", 
                "Lara", "Jorge", Lugares.Administracion, "", "","");
        
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println(cliente);
        
        System.out.println("*****************************************");
        em
                .createNamedQuery("Cliente.findAll")
                .getResultList()
                .forEach(System.out::println);

/*        Cuenta cuenta=new Cuenta(cliente, 0, true), 600, true);        
        em.getTransaction().begin();
        em.persist(cuenta);
        em.getTransaction().commit();
        System.out.println(cliente);
        
        System.out.println("*****************************************");
        em
                .createNamedQuery("Cliente.findAll")
                .getResultList()
                .forEach(System.out::println);
        /*
        
        System.out.println("*************************************************");
        Query query=em.createNamedQuery("Usuario.findByTitulo");
        query.setParameter("usuario", "MySQL");
        query.getResultList().forEach(System.out::println);
        
        System.out.println("*************************************************");
        query=em.createQuery("SELECT c FROM Curso c WHERE c.titulo like :titulo");
        query.setParameter("titulo", "%ja%");
        query.getResultList().forEach(System.out::println);
   */
 
        em.close();
        emf.close();
    }
}