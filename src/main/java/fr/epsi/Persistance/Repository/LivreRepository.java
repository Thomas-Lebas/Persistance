package fr.epsi.Persistance.Repository;

import fr.epsi.Persistance.common.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long > {
    List<Livre> findAll();
}
