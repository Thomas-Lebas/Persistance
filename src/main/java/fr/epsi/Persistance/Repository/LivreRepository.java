package fr.epsi.Persistance.Repository;

import fr.epsi.Persistance.common.Livre;
import org.springframework.data.repository.CrudRepository;

public interface LivreRepository extends CrudRepository<Livre,Long >{
}
