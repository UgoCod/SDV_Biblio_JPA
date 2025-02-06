package sdv.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE_DEBUT")
    private LocalDateTime date_debut;

    @Column(name = "DATE_FIN")
    private LocalDateTime date_fin;

    @Column(name = "DELAI")
    private int delai;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "COMPO",
        joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "id")
    )
    private Set<Livre> livres;

    {
        livres = new HashSet<>();
    }

    public Emprunt() {
    }

    public Emprunt(LocalDateTime date_debut, LocalDateTime date_fin, int delai, Client client, Set<Livre> livres) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.delai = delai;
        this.client = client;
        this.livres = livres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client != null) {
            this.client.getEmprunts().remove(this);
        }
        this.client = client;
        if (this.client != null) {
            this.client.getEmprunts().add(this);
        }
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public void ajouterLivre(Livre livre) {
        if (livre != null) {
            this.livres.add(livre);
            livre.getEmprunts().add(this);
        }
    }

    public void supprimerLivre(Livre livre) {
        if (livre != null) {
            this.livres.remove(livre);
            livre.getEmprunts().remove(this);
        }
    }


}
