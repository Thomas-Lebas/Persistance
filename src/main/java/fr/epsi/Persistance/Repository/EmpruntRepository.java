package fr.epsi.Persistance.Repository;

import fr.epsi.Persistance.common.Emprunt;
import org.springframework.data.repository.CrudRepository;

public interface EmpruntRepository extends CrudRepository<Emprunt,Long > {
}
