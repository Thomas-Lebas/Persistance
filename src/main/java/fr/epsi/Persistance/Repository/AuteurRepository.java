package fr.epsi.Persistance.Repository;

import fr.epsi.Persistance.common.Auteur;
import org.springframework.data.repository.CrudRepository;

public interface AuteurRepository extends CrudRepository<Auteur,Long > {
}
