package sdv.jpa.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "LIVRE")
public class Livre {

    @Id
    private int id;

    @Column(name = "TITRE")
    private String titre;

    @Column(name = "AUTEUR")
    private String auteur;

    @ManyToMany(mappedBy="livres", cascade = CascadeType.PERSIST)
    private Set<Emprunt> emprunts;

    public Livre(String titre, String auteur, Set<Emprunt> emprunts) {
        this.titre = titre;
        this.auteur = auteur;
        this.emprunts = emprunts;
    }

    public Livre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
