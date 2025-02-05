package sdv.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import sdv.jpa.entities.Client;
import sdv.jpa.entities.Emprunt;
import sdv.jpa.entities.Livre;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblio-jpa"); EntityManager em = emf.createEntityManager();) {

            em.getTransaction().begin();

            Client client1 = em.find(Client.class, 1);
            System.out.print(client1.getPrenom() + " " + client1.getNom() + " a emprunté " );
            Set<Emprunt> emprunts = client1.getEmprunts();
            Set<Livre> livres = new HashSet<>();
            for (Emprunt emprunt : emprunts) {
                for (Livre livre : emprunt.getLivres()) {
                    livres.add(livre);
                }
            }

            System.out.println(livres.size() + " livres:");

            for (Livre livre: livres) {
                System.out.println("    - " + livre.getTitre());
            }

            System.out.println("----------------------------------");

            Client client2 = em.find(Client.class, 2);
            Set<Emprunt> emprunts2 = client2.getEmprunts();

            System.out.println("Client: " + client2.getNom() + " " + client2.getPrenom());
            for (Emprunt emprunt : emprunts2) {
                for (Livre livre : emprunt.getLivres()) {
                    System.out.println("    - " + livre.getTitre());
                }
            }

            Livre livre4 = new Livre("Les Vertus de l'échec", "Charles Pépin", emprunts2);
            em.persist(livre4);


            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}