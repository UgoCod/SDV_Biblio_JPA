package sdv.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import sdv.jpa.entities.Livre;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblio-jpa")) {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            Livre livre1 = em.find(Livre.class, 1);
            System.out.println(livre1.getTitre());

            em.getTransaction().commit();
            em.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}