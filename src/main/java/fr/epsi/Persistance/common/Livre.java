package fr.epsi.Persistance.common;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Temporal(TemporalType.DATE)
    private Date date_de_Parution;
    private int nombre_de_pages;

    @ManyToOne
    @JoinColumn(name = "auteur_id", referencedColumnName = "id")
    private Auteur auteur;

    @ManyToOne
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    private Categorie categorie;

    @OneToOne(mappedBy = "livre")
    private Emprunt emprunt;

}
