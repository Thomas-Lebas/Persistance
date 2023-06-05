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
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date_emprunt;

    @Temporal(TemporalType.DATE)
    private Date date_fin_prevue;

    @Temporal(TemporalType.DATE)
    private Date date_retour;

    @OneToOne
    @JoinColumn(name = "livre_id", referencedColumnName = "id")
    private Livre livre;
}
