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

            Livre livre4 = new Livre();
            livre4.setTitre("Livre 1");
            livre4.setAuteur("Charles Pépine");
            em.persist(livre4);


            Client clientAPersister = new Client("Jonh", "Doe");
            em.persist(clientAPersister);

            Emprunt emprunt1 = new Emprunt();
            emprunt1.setDate_debut(LocalDateTime.of(2020, 1, 1, 1, 1));
            emprunt1.setDate_fin(LocalDateTime.of(2020, 1, 1, 1, 1));
            emprunt1.setDelai(23);
            emprunt1.setClient(clientAPersister);

            clientAPersister.getEmprunts().add(emprunt1);

            emprunt1.ajouterLivre(livre4);


            em.persist(emprunt1);

            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}